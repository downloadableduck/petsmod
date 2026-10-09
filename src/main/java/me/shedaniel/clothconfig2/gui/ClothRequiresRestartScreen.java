package me.shedaniel.clothconfig2.gui;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;


public class ClothRequiresRestartScreen extends ConfirmScreen {

    public ClothRequiresRestartScreen(Screen parent) {
        super(createScreen(parent), I18n.translate("text.cloth-config.restart_required"), I18n.translate("text.cloth-config.restart_required_sub"), 0);
    }

    private static Screen createScreen(Screen parent) {
        return new Screen() {
            @Override
            public void confirmResult(boolean bl, int i) {
                if (bl)
                    Minecraft.getInstance().stop();
                else
                    Minecraft.getInstance().openScreen(parent);

            }
        };
    }
    
}
