# yes this is ai slop

# package_mod.ps1
# Builds a mod-only jar from RetroMCP's reobfuscated output (minecraft/jars/reobfuscated.zip or .jar).
# Keeps: your mod source, added libraries, and ONLY the vanilla base classes you edited.
# Run after "reobfuscate" in RetroMCP.

# powershell -ExecutionPolicy Bypass -File .\package_mod.ps1
[CmdletBinding()]
param(
    [string]$ProjectDir = '',
    [string]$InJar      = '',
    [string]$OutJar     = 'pets-mod-0.7.9-1.5.2-ritsugamis.jar'
)

$ErrorActionPreference = 'Stop'

if ([string]::IsNullOrEmpty($ProjectDir)) {
    $ProjectDir = if ($PSScriptRoot) { $PSScriptRoot } else { Split-Path -Parent $MyInvocation.MyCommand.Path }
}

if ([string]::IsNullOrEmpty($InJar)) {
    $cand = @('minecraft\jars\reobfuscated.jar', 'minecraft\jars\reobfuscated.jar', 'minecraft\jars\minecraft_reobf.jar')
    $InJar = ($cand | Where-Object { Test-Path (Join-Path $ProjectDir $_) } | Select-Object -First 1)
    if (-not $InJar) { throw "No reobfuscated jar/zip found under minecraft\jars" }
}

# Jar entry prefixes to ship verbatim (your code + libraries you added)
$keepPrefixes = @(
    'me/shedaniel/',   # Cloth Config port
    'com/jeff/',
    'mob/'# Pets mod
)

# Exact class names (without .class) to always ship (e.g. added helper classes)
$keepExact = @(
    'mod_PetsMod'
)

# Library jars to shade verbatim into the output (relative to project root).
# These are bundled so the mod jar is self-contained at runtime.
$shadeJars = @(
    'libraries\org\json\json\20230311\json-20230311.jar'   # org.json (JsonCodec / Cloth Config port)
)

# Resource folders to bundle into the output (relative to project root).
# The CONTENTS of each Path are copied into the jar under its Prefix.
$resourceRoots = @(
    @{ Path = 'minecraft\bin\mob'; Prefix = 'mob' }   # custom pet textures -> /mob/...
)

$src      = Join-Path $ProjectDir 'minecraft\src'
$orig     = Join-Path $ProjectDir 'minecraft\src_original'
$inPath   = Join-Path $ProjectDir $InJar
$outPath  = Join-Path $ProjectDir $OutJar
$mappings = Join-Path $ProjectDir 'conf\mappings.tiny'

if (-not (Test-Path $inPath))   { throw "Input jar not found: $inPath" }
if (-not (Test-Path $mappings)) { throw "Mappings not found: $mappings" }

# 1) named -> official class map from conf/mappings.tiny (tiny v2: "c <official> <named>")
$map = @{}
foreach ($line in [System.IO.File]::ReadAllLines($mappings)) {
    if ($line.Length -eq 0 -or $line[0] -ne 'c') { continue }
    $p = $line -split "`t"
    if ($p.Count -ge 3) { $map[$p[2]] = $p[1] }
}

# 2) vanilla classes you edited or added (current src differs from pristine src_original)
$editTops = New-Object System.Collections.Generic.HashSet[string]

# 3) obfuscated entry names for those edited/added classes (+ their inner classes)
$keepEntries = New-Object System.Collections.Generic.HashSet[string]
foreach ($ex in $keepExact) { [void]$keepEntries.Add("$ex.class") }
foreach ($k in $map.Keys) {
    $top = $k.Split('$')[0]
    if ($editTops.Contains($top)) { [void]$keepEntries.Add($map[$k] + '.class') }
    if ($editTops.Contains($top)) { [void]$keepEntries.Add($map[$k] + '.png')}
}
$editTopsArr = @($editTops)

function Test-Keep([string]$entry) {
    if ($keepEntries.Contains($entry)) { return $true }
    foreach ($p in $keepPrefixes) { if ($entry.StartsWith($p)) { return $true } }
    foreach ($t in $editTopsArr) {
        if ($entry -eq "$t.class" -or $entry.StartsWith("$t" + '$')) { return $true }
    }
    return $false
}

# 4) rewrite the jar keeping only the selected classes
Add-Type -AssemblyName System.IO.Compression
Add-Type -AssemblyName System.IO.Compression.FileSystem
Remove-Item $outPath -ErrorAction SilentlyContinue

$zin = [System.IO.Compression.ZipFile]::OpenRead($inPath)
$inCount = $zin.Entries.Count
$kept = 0
$shaded = 0
$resCount = 0
$added = New-Object System.Collections.Generic.HashSet[string]
try {
    $zout = [System.IO.Compression.ZipFile]::Open($outPath, [System.IO.Compression.ZipArchiveMode]::Create)
    try {
        foreach ($e in $zin.Entries) {
            if ($e.FullName.EndsWith('/')) { continue }
            if (-not $e.FullName.EndsWith('.class')) { continue }
            if (-not (Test-Keep $e.FullName)) { continue }
            $ne = $zout.CreateEntry($e.FullName, [System.IO.Compression.CompressionLevel]::Optimal)
            $is = $e.Open(); $os = $ne.Open()
            try { $is.CopyTo($os) } finally { $os.Dispose(); $is.Dispose() }
            [void]$added.Add($e.FullName)
            $kept++
        }

        # 4b) shade bundled libraries verbatim (e.g. org.json) into the output
        foreach ($lib in $shadeJars) {
            $libPath = Join-Path $ProjectDir $lib
            if (-not (Test-Path $libPath)) { throw "Shade jar not found: $libPath" }
            $lz = [System.IO.Compression.ZipFile]::OpenRead($libPath)
            try {
                foreach ($le in $lz.Entries) {
                    if ($le.FullName.EndsWith('/')) { continue }
                    if (-not $le.FullName.EndsWith('.class')) { continue }
                    if ($added.Contains($le.FullName)) { continue }   # skip duplicates
                    $ne = $zout.CreateEntry($le.FullName, [System.IO.Compression.CompressionLevel]::Optimal)
                    $is = $le.Open(); $os = $ne.Open()
                    try { $is.CopyTo($os) } finally { $os.Dispose(); $is.Dispose() }
                    [void]$added.Add($le.FullName)
                    $shaded++
                }
            } finally { $lz.Dispose() }
        }

        # 4c) bundle mod resource files (textures, etc.) from disk
        foreach ($res in $resourceRoots) {
            $resDir = Join-Path $ProjectDir $res.Path
            if (-not (Test-Path -LiteralPath $resDir)) { continue }
            $prefix = ($res.Prefix).TrimEnd('/')
            foreach ($f in (Get-ChildItem -Recurse -File -LiteralPath $resDir)) {
                $rel = $f.FullName.Substring($resDir.Length).TrimStart('\','/').Replace('\','/')
                $entry = if ($prefix) { "$prefix/$rel" } else { $rel }
                if ($added.Contains($entry)) { continue }
                $ne = $zout.CreateEntry($entry, [System.IO.Compression.CompressionLevel]::Optimal)
                $os = $ne.Open()
                $fs = [System.IO.File]::OpenRead($f.FullName)
                try { $fs.CopyTo($os) } finally { $os.Dispose(); $fs.Dispose() }
                [void]$added.Add($entry)
                $resCount++
            }
        }
    } finally { $zout.Dispose() }
} finally { $zin.Dispose() }

"Input : $inPath"
"        $inCount classes"
"Output: $outPath"
"         $kept classes kept"
"         $shaded classes shaded from $($shadeJars.Count) library jar(s)"
"         $resCount resource files bundled from $($resourceRoots.Count) folder(s)"
"Base classes kept (edited/added): $($editTopsArr.Count)"
$editTopsArr | Sort-Object | ForEach-Object { "   $_" }
