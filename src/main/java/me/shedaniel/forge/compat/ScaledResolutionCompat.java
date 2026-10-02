package me.shedaniel.forge.compat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public final class ScaledResolutionCompat {
    private ScaledResolutionCompat() {
    }

    public static ScaledResolution get(Minecraft client) {
        return new ScaledResolution(client, client.field_71443_c, client.field_71440_d);
    }
}
