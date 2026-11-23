//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;

public class ClothRequiresRestartScreen extends ConfirmScreen {
    public ClothRequiresRestartScreen(Screen parent) {
        super(createScreen(parent), I18n.getTranslation("text.cloth-config.restart_required"), I18n.getTranslation("text.cloth-config.restart_required_sub"), "", "", 0);
    }

    private static Screen createScreen(final Screen parent) {
        return new Screen() {
            public void confirmed(boolean bl, int i) {
                if (bl) {
                    Minecraft.getMinecraft().shutdown();
                } else {
                    Minecraft.getMinecraft().displayScreen(parent);
                }

            }
        };
    }
}
