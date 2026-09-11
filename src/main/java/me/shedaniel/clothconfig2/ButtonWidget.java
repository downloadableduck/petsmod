package me.shedaniel.clothconfig2;

import java.util.function.Consumer;

import net.minecraft.client.Minecraft;

public class ButtonWidget extends net.minecraft.client.gui.widget.ButtonWidget implements me.shedaniel.clothconfig2.compat.GuiEventListener {
    private Consumer<ButtonWidget> pressAction;

    public ButtonWidget(int i, int j, int k, int l, String string, Consumer<ButtonWidget> pressAction) {
        super(0, i, j, k, l, string);
        this.pressAction = pressAction;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.pressAction != null && mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height) {
            this.pressAction.accept(this);
            return true;
        }
        return false;
    }

    public void render(int mouseX, int mouseY, float delta) {
        super.render(Minecraft.getInstance(), mouseX, mouseY);
    }

    public boolean wrapDegrees(double mouseX, double mouseY) {
        return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
    }
}
