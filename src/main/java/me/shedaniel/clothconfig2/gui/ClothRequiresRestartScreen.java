package me.shedaniel.clothconfig2.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TranslatableText;

import java.util.Random;

public class ClothRequiresRestartScreen extends ConfirmScreen {

    public ClothRequiresRestartScreen(Screen parent) {
        super((t, u) -> {
            if (t)
                MinecraftClient.getInstance().scheduleStop();
            else
                MinecraftClient.getInstance().setScreen(parent);
        }, new TranslatableText("text.cloth-config.restart_required").toString(), new TranslatableText("text.cloth-config.restart_required_sub").toString(), I18n.translate("text.cloth-config.exit_minecraft"), I18n.translate("text.cloth-config.ignore_restart"), new Random().nextInt());
    }

}
