package me.shedaniel.clothconfig2;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;

@Environment(EnvType.CLIENT)
public abstract class AbstractPressableButtonWidget extends ButtonWidget {
    protected float alpha = 1.0F;

    public AbstractPressableButtonWidget(int i, int j, int k, int l, String string) {
        super(0, i, j, k, l, string);
    }

    public abstract void onPress();

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.active && this.visible && this.method_18377(mouseX, mouseY)) {
            this.playDownSound(MinecraftClient.getInstance().getSoundManager());
            this.onPress();
            return true;
        }
        return false;
    }
}