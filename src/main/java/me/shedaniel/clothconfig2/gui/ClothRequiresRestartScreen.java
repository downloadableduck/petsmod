package me.shedaniel.clothconfig2.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.util.Random;

public class ClothRequiresRestartScreen extends GuiConfirmOpenLink {

    public ClothRequiresRestartScreen(GuiScreen parent) {
        super((t, u) -> {
            if (t)
                Minecraft.getMinecraft().shutdown();
            else
                Minecraft.getMinecraft().displayGuiScreen(parent);
        }, ("text.cloth-config.restart_required").toString(), new Random().nextInt(), false);
    }

}
