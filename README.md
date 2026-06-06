<p align="center" style="text-align: center;">
<a href = "https://cdn.modrinth.com/data/cached_images/974ac9621141c80a67cbc3e1e7df49fa28c28647.png"><img src="https://cdn.modrinth.com/data/cached_images/974ac9621141c80a67cbc3e1e7df49fa28c28647.png" alt="Hi there"></a>

<p align="center" style="text-align: center;">
  <a href="https://cdn.modrinth.com/data/WwqTyEtB/versions/9VB6Osx9/pets-mod-0.7.7-26.1.x.jar?mr_download_reason=standalone&mr_game_version=26.1.2&mr_loader=fabric"><img src="https://cdn.modrinth.com/data/cached_images/513035d1231a86dff137c74e04a5e64633593f10.png" alt="Fabric" style="margin: 5px 10px;"></a>
  <a href="https://cdn.modrinth.com/data/WwqTyEtB/versions/m1sIEARZ/pets-mod-0.7.7-26.1-neo.jar?mr_download_reason=standalone&mr_game_version=26.1.2&mr_loader=neoforge"><img src="https://cdn.modrinth.com/data/cached_images/b9ae07fbc26c08220b4856cb244f86904d0c172c.png" alt="Neoforge" style="margin: 5px 10px;"></a>
  <a href="https://cdn.modrinth.com/data/WwqTyEtB/versions/9VB6Osx9/pets-mod-0.7.7-26.1.x.jar?mr_download_reason=standalone&mr_game_version=26.1.2&mr_loader=fabric"><img src="https://cdn.modrinth.com/data/cached_images/a9ee492fd1a5fcbcc14d4105aa27be63f2518529.png" alt="Quilt" style="margin: 5px 10px;"></a>
  </p>

<p align="center" style="text-align: center;">
  <a href="https://github.com/downloadableduck/petsmod"><img src="https://tr7zw.github.io/uikit/social_buttons_icon/Github-Button-64.png" alt="GitHub" style="margin: 5px 10px;"></a>
  <a href="https://www.curseforge.com/minecraft/mc-mods/pets-mod/preview"><img src="https://tr7zw.github.io/uikit/social_buttons_icon/Curseforge-Button-64.png" alt="GitHub" style="margin: 5px 10px;"></a>
  <a href="https://modrinth.com/mod/pets-mod"><img src="https://tr7zw.github.io/uikit/social_buttons_icon/Modrinth-Button-64.png" alt="GitHub" style="margin: 5px 10px;"></a>
  
# The Pets Project
The pets project is bringing you your own client-sided pets into your game! From squids to iron golems to even penguins, PetsMod offers nearly every mob from vanilla Minecraft, and more! And it will work no matter whether you are on a multiplayer server or your own singleplayer world. Simply use /pet on to spawn the pet, and use /petspecies <species> to change what mob your pet is!


<details>
<summary>Commands</summary>

- /pethelp - displays a list of commands.
- /pet <on/off> - toggles whether your pet will appear. off by default.
- /petspecies - switches between your pet's species. (e.g. /petspecies villager.)
- /petskin - switches between the available skins of your pet, if applicable. (e.g. /petskin armorer.)
- /teleportpet - teleports your pet to you.

</details>

PetsMod comes with its own configuration screen **and** custom commands to easily toggle between pets, change whether the mod is enabled at all, and switch up the appearance of your pets with **skins** and **names**. 

**Showcase video:**
<iframe width="560" height="315" src="https://www.youtube-nocookie.com/embed/mlud8R7Cq7M" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

## Features
- Supports almost EVERY vanilla Minecraft mob
- Three CUSTOM mobs tailored specifically for this mod, complete with custom ambient sounds
- Custom interactions
- Completely client-sided and will work on ANY server, such as Hypixel, Mineplex, or MCC Island
- Custom names
- Skins for mobs that have multiple variants such as frogs, villagers, or cows

## Customization
Make your pets your own with **skins** and **names**! You can access these in two ways: by using our configuration menu or using `/petskin` and `/petname`. Both of these will switch up your pet's appearance in real time and make it yours!

There is no need to do this multiple times - all of this information is stored in a config file locatated in `.minecraft/config` and will be read every time you enter a new world.

To switch between pets, simply run `/petspecies <pet>`.

![Our easily configurable configuration menu.](https://cdn.modrinth.com/data/cached_images/73aeb6d8ba89fe52d1ac49eec1dbc7c3dd3d579e_0.webp)

> **TIP:** Pet names are stored for each individual pet - if you have both a blaze and a duck, you can name them each individually!

## Our Roots
This is an updated and upgraded version of [DuckMod](https://modrinth.com/mod/duck--mod). Following this release, DuckMod will recieve a bump to the latest version and thus be archived, continued by this project. If you are migrating from DuckMod to PetsMod, simply use 
`/pet on` and then `/petspecies duck`, or use our easily configurable configuration menu powered by Mod Menu and YetAnotherConfigLib to switch to a duck.

## Updates and Improvements
This section is dedicated to the latest updates and improvements of this mod. Check back here every once in a while, you might see something new!


<details>
<summary>0.5.0 (Initial Release)</summary>

- Added support for every mob from vanilla Minecraft
- Added our first mob pack, containing a new skin for the duck, the penguin, and the racoon
- Fixed an issue with the duck's wings going much farther than they should have
- Fixed the duck making an annoying rattling sound when it walks
- Added config library and dependencies on Mod Menu and YetAnotherConfigLib

</details>

<details>
  <summary>0.5.1</summary>
  
-   Fixed the link in the fabric.mod.json redirecting to the legacy (DuckMod) project and source code.
</details>

<details>
  <summary>0.5.3</summary>
  
- Fixed the ghasts going back down to the ground when stopped rather than staying in the air
- Fixed the bat doing the same thing
- Fixed the bat's animation not playing
</details>


<details>
<summary>0.6.0-0.6.7</summary>

- Bump version to 0.6.6
- When first installing the mod, pets are now on instead of off
- Fixed using the happy ghast not allowing users to use the configuration screen
- Fixed the magma cube casting a 500 block wide shadow
  - Dont ask.
- Fixed the breeze idle animation not playing
- Pets are now much faster and can keep up with players much more easily.
- Pets now will flip upside down if they are named “Grumm” or “Dinnerbone”
- Added an English translation key
  - If you want support in your native language, please open a discussion or issue on my github.
- Cleaned up code
- Added creeper skins (normal/charged)
- **Added support for baby mobs**
  - The toggle for baby mobs is universal, meaning that every pet will become a baby until toggled off.
  - Toggleable through /petskin baby or /petskin adult, or through the config screen.
We may or may not switch to the newer, baby mob models in the future but may not. It hasn't been decided yet.
- Added the head pet. You can switch its skin to that of any existing Minecraft player.
  - This pet requires a custom resource pack to work. You do not need to do anything on your end; it is automatically installed by the mod. However, the resources will reload when you change skins or equip the pet.
- **Added support for every past april fools mob** that does not resemble another. (Does not include mobs like the ‘pet fox’ or ‘boss warden’ since those resemble existing mobs.)
  - A patch will be coming soon to add the mobs included in this april fools update.
- Pets will now actually sit on your head when riding you, not stand up. This includes custom poses for the custom mobs.

</details>

<details>
  <summary>0.7.1</summary>
  
- Added the Aquatic Mob Pack 
  - Features the Dumbo Octopus, Koi Fish, and Stingray
- Added our own custom title and splash text 
  - This is toggleable through the config screen
- Flying pets no longer drop to the ground when they get close to you, instead staying in the air
- The code is now **much more compact** and easier for me to update
- Added a keybind to the config screen (by default, this is P)
- Made the config screen use spaces instead of underscores when selecting a mob
- Fixed a mismatch between the wolf and fox skins
</details>

<details>
  <summary>0.7.2</summary>
  
- Make the moon cows helmet render correctly
- Skin suggestions now work without having to switch your pet
  </details>

<details> <summary>0.7.4 (I accidentally skipped ahead a version cuz im smart</summary>
  
  - Added code documentation and massively cleaned up the code.
  - Added the Utils class, which contains a lot of helper methods that keep the jar much smaller.
- Added official addon support, including a guide which can be found [here](https://github.com/downloadableduck/petsmod/blob/26.1.x/CREATING%20A%20CUSTOM%20PET.md).
  - These addons add more pets into PetsMod. This was made out of:
    - A. Feeling that the mod would get too bloated if we kept adding a large amount of mods into the main mod.
    - B. Enabling users to create their own pets.
</details>

<details><summary>0.7.5</summary>
  - fix addon counter add some modmenu stuff
</details>

<details><summary>0.7.6</summary>

  - fix namespace so that developers can easily access the javadoc and sources
 </details>

Current release: **0.7.7**

- Added Neoforge support
- Fixed missing wandering trader & turtle textures
- Update metadata
- Update icon
  - Sorry it took so long
- Update addons guide
- Added an option to the config screen for viewing installed addons & browsing them
  - Currently, this redirects you to petsmod.com. This website is not up yet; please do not report this on my GitHub until at least a week has passed since 0.7.7 and it is still broken.
- Fixed version.png (the version subtitle text) showing the incorrect version
  - This was caused because 0.7.4 was initially planned to be 0.8.0 but ultimately it was decided it was not big enough of an update.
- Added the Bronze duck skin in celebration of 1,000 downloads!
  - Silver will be unlocked at 10,000, and gold at 100,000.
- Added some new splash text
- Fixed a bug where, when using ViaFabricPlus and connecting to a server on 1.8.9, the pets would sit inside your head instead of on.
  - This does **not** mean the mod requires ViaFabricPlus, it just executes a check.
- Pets will now wander when the owner is not moving, and come back when the owner starts moving again.

## Mob Packs
Mob Packs are our way of introducing custom mobs into the game. 

<details>
  <summary>The First Mob Pack</summary>The standard for custom mob packs is three mobs, but the full release came out so quickly we were only able to fit two in! Introducing the penguin and the racoon.

![The penguin](https://cdn.modrinth.com/data/cached_images/33507668f5fa065ea93ef106da065f38bff019f3_0.webp)

![The racoon](https://cdn.modrinth.com/data/cached_images/31a95afa9a7db102aecff927fe2267e8fe8eb56a_0.webp)

In addition, the racoon has an extra skin - albino!
![The racoon (albino)](https://cdn.modrinth.com/data/cached_images/29535c39b4ed7b980f7bd2fa15dda1a6a11c48ab_0.webp)

The racoon and duck models are available and free to use on my [GitHub](https://github.com/downloadableduck/petsmod). 

</details>


<details>
<summary>April Fools Mob Pack</summary>

![April Fools Mob Pack Banner](https://cdn.modrinth.com/data/cached_images/319cfebcb4f8b05bfb8a42e712006234dabbdb64_0.webp)
**The April Fools Mob Pack**

This mob pack introduces every April Fools mob into Minecraft, plus our own custom one. All of these mobs are **not** temporary, and are here to stay!

Full list of mobs included, with images:

<details><summary>The Head (our own custom mob!) </summary>
  
  ![The Head](https://cdn.modrinth.com/data/cached_images/68e83a35beee8d8bf633ba7c7bac44aca0b567a8_0.webp)
  - You can switch the head's skin to that of any player using /petskin. However, the resources will reload every time to process the change. To combat this, we recommend [Remove Resource Loading Screen](https://modrinth.com/mod/rrls)
</details>

<details><summary>Angry Ghast</summary>
  
  ![Angry Ghast](https://cdn.modrinth.com/data/cached_images/4e63e2cf857a1c68e4935325b5043724be7b3b36_0.webp)
</details>

<details><summary>Batato</summary>

  ![Batato](https://cdn.modrinth.com/data/cached_images/474b36e45202c7f16e959b15557446ce70fd686e_0.webp)
</details>

<details><summary>Diamond Chicken</summary>

  ![Diamond Chicken](https://cdn.modrinth.com/data/cached_images/c766407c4bff09d604f27cf407c0481fdb7badc4_0.webp)
</details>

<details><summary>Love Golem</summary>

  ![Love Golem](https://cdn.modrinth.com/data/cached_images/cb1903f5f4aeb36dc66e72b4780c42da4c2e0ee8_0.webp)</details>

<details>
  <summary>Mega Spud</summary>
  
  ![Mega Spud](https://cdn.modrinth.com/data/cached_images/903afad2213bff4a1bae9b4c704f8838f279db80_0.webp)
</details>

<details>
  <summary>Moon Cow</summary>
  
  ![Moon Cow](https://cdn.modrinth.com/data/cached_images/6314031e099415b4f98a69f82786d66e382f04cc_0.webp)

  - Yes, I know it's missing its helmet. I hope to fix this soon, but Mojang broke the system of using blocks on heads in 26.1.
</details>

<details>
  <summary>Nerd Creeper</summary>

  ![Nerd Creeper](https://cdn.modrinth.com/data/cached_images/6ff2213e009e2158b198c689dbdac5cdc79e8424_0.webp)
</details>

<details>
  <summary>Pink Wither/Friendly Wither</summary>

  ![Pink Wither](https://cdn.modrinth.com/data/cached_images/d005c3813f8b7b49f8a55dbc0ebdf17c4e26a52a_0.webp)
</details>

<details>
  <summary>Plaguewhale Slab</summary>

  ![Plaguewhale Slab](https://cdn.modrinth.com/data/cached_images/1d169f460acefce5a680b3389ba5ff9a0dbd1f43_0.webp)
</details>

<details>
  <summary>Poisonous Potato Zombie</summary>

  ![Poisonous Potato Zombie](https://cdn.modrinth.com/data/cached_images/54e698a997683ce62baaed9897b2a8bc8e2865ad_0.webp)
</details>

<details>
  <summary>Potato Husk</summary>

  ![Potato Husk](https://cdn.modrinth.com/data/cached_images/bd0085380b1f33d8213cc711d1f702913f5e71e2_0.webp)
</details>

<details>
  <summary>Ray Tracing</summary>

  ![Ray Tracing](https://cdn.modrinth.com/data/cached_images/23ac67d0e9b580792f3ad919e60a4d1aff18c542_0.webp)
</details>

<details>
  <summary>Redstone Bug</summary>

  ![Redstone Bug](https://cdn.modrinth.com/data/cached_images/49a5f13a408e53ee33e7ad1e93b7b6468b456291_0.webp)
</details>

<details>
  <summary>Smiling Creeper</summary>

  ![Smiling Creeper](https://cdn.modrinth.com/data/cached_images/ac2b32c4fc986564657fcf9ff7ab1fe5976bcedc_0.webp)
</details>

<details>
  <summary>Toxifin Slab</summary>

  ![Toxifin Slab](https://cdn.modrinth.com/data/cached_images/1cddcb2636313b87964f35e5a36f642e51e4a337_0.webp)
</details>

<details>
  <summary>Traitor</summary>

  ![Traitor](https://cdn.modrinth.com/data/cached_images/a9b5136673d40c49426e91b1c2625804edd2ad69_0.webp)
</details>

</details>

## The Aquatic Mob Pack
This mob pack introduces three new mobs: the Dumbo Octopus, Stingray, and Koi.

![Dumbo Octopus](https://cdn.modrinth.com/data/cached_images/885d35ec46a014e18bac6382335707b766404d76_0.webp)
- The dumbo octopus comes in six different skins: yellow, red, blue, orange, pink, and green.

![Stingray](https://cdn.modrinth.com/data/cached_images/27aa134577e503dd8534c83521b7dd5adee7bb94_0.webp)

![Koi](https://cdn.modrinth.com/data/cached_images/4b1757c1551b2cdbd77525f6bf28ec18efb9c595_0.webp)

As always, these models are free and available on our [GitHub](https://github.com/downloadableduck/petsmod/issues).

## Interactions
Simply shift and right click on a mob with an **empty hand** to pick it up, and shift and jump to drop it again!
![The penguin being picked up.](https://cdn.modrinth.com/data/cached_images/b9a065361a75ea117c311c05e0d3a563bb2fb4a8_0.webp)

## Requirements
On Fabric, this mod requires [Fabric API](https://modrinth.com/mod/fabric-api), [Cloth Config API](https://modrinth.com/mod/cloth-config), [YACL](https://modrinth.com/mod/yacl) (YetAnotherConfigLib) and [Mod Menu](https://modrinth.com/mod/modmenu). 

On Neoforge, this mod requires [Cloth Config API](https://modrinth.com/mod/cloth-config) and [YACL](https://modrinth.com/mod/yacl). 

It's a lot, I know, but hopefully you already have most of them installed!

**Why do you need two configuration libraries?**
Cloth Config is useful for saving and reading data across instances, so your pet retains its name even after you come back the next day. YACL is useful for the actual configuration screen.

## FAQ
**Is this mod paid/are some features paid?**
No, and they never will be! This mod will forever remain free for everyone to use.

**Why is this mod in BETA?**
Currently, we are missing a lot of features. Even with supporting nearly every vanilla Minecraft mob, we are still missing the tropical fish, the ability to change villager's biomes, the ability to add carpets to llamas, etc. We hope to improve on these features as we drift towards a full release.

**Will this mod adapt its textures with a texturepack?**
This mod uses the **vanilla texture system**, so yes, it will use custom textures if a texturepack is present.

**How can I contribute to the Pets Project?**
Simply play the mod! If you wish to join our team, please create a new discussion thread on our GitHub. If you simply have an issue, please report it there - we look forwards to making this mod the best - and clearest of bugs - that it can be!

**Is a Forge/Neoforge port ever coming?**
~~This project depends heavily on the Fabric API. If a working Fabric API ever releases for Neoforge, then yes, I will try my best to make this available on Neoforge. For Forge, a port will never come due to the amount of issues and slow updates it has had in the past.~~

Neoforge has been ported!

**May I use this in my modpack?**
Yep, go ahead! Just give credit where it's due :)
