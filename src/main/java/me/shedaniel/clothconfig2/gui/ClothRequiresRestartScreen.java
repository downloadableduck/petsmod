package me.shedaniel.clothconfig2.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class ClothRequiresRestartScreen extends ConfirmScreen {
    
    public ClothRequiresRestartScreen(Screen parent) {
        super(t -> {
            if (t)
                Minecraft.getInstance().stop();
            else
                Minecraft.getInstance().openScreen(parent);
        }, new TranslatableText("text.cloth-config.restart_required"), new TranslatableText("text.cloth-config.restart_required_sub"), I18n.translate("text.cloth-config.exit_minecraft"), I18n.translate("text.cloth-config.ignore_restart"));
    }
    
}
