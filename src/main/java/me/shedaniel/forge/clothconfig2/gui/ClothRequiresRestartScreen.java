package me.shedaniel.forge.clothconfig2.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClothRequiresRestartScreen extends ConfirmScreen {
    
    public ClothRequiresRestartScreen(Screen parent) {
        super(t -> {
            if (t)
                Minecraft.getInstance().stop();
            else
                Minecraft.getInstance().setScreen(parent);
        }, new TranslationTextComponent("text.cloth-config.restart_required"), new TranslationTextComponent("text.cloth-config.restart_required_sub"), I18n.get("text.cloth-config.exit_minecraft"), I18n.get("text.cloth-config.ignore_restart"));
    }
    
}
