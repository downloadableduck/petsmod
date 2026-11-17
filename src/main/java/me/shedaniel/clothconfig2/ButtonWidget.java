//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2;

import me.shedaniel.clothconfig2.compat.GuiEventListener;
import net.minecraft.client.Minecraft;

import java.util.function.Consumer;

public class ButtonWidget extends net.minecraft.client.gui.widget.ButtonWidget implements GuiEventListener {
    private Consumer<ButtonWidget> pressAction;

    public ButtonWidget(int i, int j, int k, int l, String string, Consumer<ButtonWidget> pressAction) {
        super(0, i, j, k, l, string);
        this.pressAction = pressAction;
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.pressAction != null && mouseX >= (double)this.x && mouseX <= (double)(this.x + this.width) && mouseY >= (double)this.y && mouseY <= (double)(this.y + this.height)) {
            this.pressAction.accept(this);
            return true;
        } else {
            return false;
        }
    }

    public void render(int mouseX, int mouseY, float delta) {
        super.render(Minecraft.INSTANCE, mouseX, mouseY);
    }

    public boolean wrapDegrees(double mouseX, double mouseY) {
        return mouseX >= (double)this.x && mouseX <= (double)(this.x + this.width) && mouseY >= (double)this.y && mouseY <= (double)(this.y + this.height);
    }
}
