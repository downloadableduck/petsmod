package me.shedaniel.clothconfig2;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class ButtonWidget extends net.minecraft.client.gui.widget.ButtonWidget implements me.shedaniel.clothconfig2.mixin.ButtonWidgetHooks {
    public interface PressAction {
        void onPress(ButtonWidget button);
    }

    protected PressAction onPressAction;

    public ButtonWidget(int i, int j, int k, int l, String string, PressAction pressAction) {
        super(0, i, j, k, l, string);
        this.onPressAction = pressAction;
    }

    public void onPress() {
        if (onPressAction != null)
            onPressAction.onPress(this);
    }

    @Override
    public void setOnPress(PressAction action) {
        this.onPressAction = action;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

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