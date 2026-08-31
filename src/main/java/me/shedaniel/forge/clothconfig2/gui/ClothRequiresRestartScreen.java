package me.shedaniel.forge.clothconfig2.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentTranslation;


import java.util.Random;


public class ClothRequiresRestartScreen extends GuiYesNo {
    
    public ClothRequiresRestartScreen(GuiScreen parent) {
        super((t, u) -> {
            if (t)
                Minecraft.getMinecraft().shutdown();
            else
                Minecraft.getMinecraft().displayGuiScreen(parent);
        }, new TextComponentTranslation("text.cloth-config.restart_required").toString(), new TextComponentTranslation("text.cloth-config.restart_required_sub").toString(), I18n.format("text.cloth-config.exit_minecraft"), I18n.format("text.cloth-config.ignore_restart"), new Random().nextInt());
    }
    
}
