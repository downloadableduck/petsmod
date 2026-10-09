package me.shedaniel.clothconfig2.gui;

import java.util.Random;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.Screen;

public class ClothRequiresRestartScreen extends ConfirmChatLinkScreen {

    public ClothRequiresRestartScreen(Screen parent) {
        super(new Screen() {
            @Override
            public void method_22355(boolean t, int i) {
                if (t)
                    MinecraftClient.getInstance().scheduleStop();
                else
                    MinecraftClient.getInstance().setScreen(parent);
            }
        }, ("text.cloth-config.restart_required").toString(), new Random().nextInt(), false);
    }

}
