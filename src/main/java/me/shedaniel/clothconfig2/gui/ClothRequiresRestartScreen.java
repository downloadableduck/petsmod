package me.shedaniel.clothconfig2.gui;


import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.locale.Language;
import net.ornithemc.osl.lifecycle.api.client.MinecraftInstance;


public class ClothRequiresRestartScreen extends ConfirmScreen {

    public ClothRequiresRestartScreen(Screen parent) {
        super(createScreen(parent), Language.getInstance().translate("text.cloth-config.restart_required"), Language.getInstance().translate("text.cloth-config.restart_required_sub"), "", "",  0);
    }

    private static Screen createScreen(Screen parent) {
        return new Screen() {
            @Override
            public void confirmResult(boolean bl, int i) {
                if (bl)
                    MinecraftInstance.get().stop();
                else
                    MinecraftInstance.get().openScreen(parent);

            }
        };
    }
    
}
