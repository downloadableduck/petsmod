package me.shedaniel.clothconfig2;

import java.util.function.Consumer;

public class ButtonWidget extends net.minecraft.client.gui.widget.ButtonWidget {
    private Consumer<ButtonWidget> pressAction;

    public ButtonWidget(int i, int j, int k, int l, String string, Consumer<ButtonWidget> pressAction) {
        super(0, i, j, k, l, string);
        this.pressAction = pressAction;
    }

    @Override
    public void click(double mouseX, double mouseY) {
        if (pressAction != null) pressAction.accept(this);
    }

    public boolean wrapDegrees(double mouseX, double mouseY) {
        return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
    }
}
