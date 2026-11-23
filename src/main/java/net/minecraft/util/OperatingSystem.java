package net.minecraft.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.enums.EnumOS;

/**
 * Modern OS enum shape backed by BTA's {@link EnumOS}.
 */
public enum OperatingSystem {
    LINUX,
    SOLARIS,
    WINDOWS,
    MACOS,
    UNKNOWN;

    public static OperatingSystem current() {
        return fromBta(Minecraft.getOs());
    }

    public static boolean isMac() {
        return current() == MACOS;
    }

    public static OperatingSystem fromBta(EnumOS os) {
        if (os == null) {
            return UNKNOWN;
        }
        switch (os) {
            case linux:
                return LINUX;
            case solaris:
                return SOLARIS;
            case windows:
                return WINDOWS;
            case macos:
                return MACOS;
            default:
                return UNKNOWN;
        }
    }
}
