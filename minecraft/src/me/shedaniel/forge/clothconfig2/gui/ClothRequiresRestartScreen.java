package me.shedaniel.forge.clothconfig2.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiYesNo;
import net.minecraft.src.I18n;

import java.util.Random;


public class ClothRequiresRestartScreen extends GuiYesNo {

    public ClothRequiresRestartScreen(final GuiScreen parent) {
        super(new GuiScreen() {
            @Override
            public void confirmClicked(boolean t, int u) {
                if (t)
                    Minecraft.getMinecraft().shutdown();
                else
                    Minecraft.getMinecraft().displayGuiScreen(parent);
            }
        }, I18n.format("text.cloth-config.restart_required"), I18n.format("text.cloth-config.restart_required_sub"), I18n.format("text.cloth-config.exit_minecraft"), I18n.format("text.cloth-config.ignore_restart"), new Random().nextInt());
    }

}
