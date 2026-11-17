//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2;

public abstract class AbstractPressableButtonWidget extends ButtonWidget {
    public AbstractPressableButtonWidget(int i, int j, int k, int l, String string) {
        super(i, j, k, l, string, (w) -> {
        });
    }

    public abstract void onPress();

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if ((double)this.x <= mouseX && mouseX <= (double)(this.x + this.width) && (double)this.y <= mouseY && mouseY <= (double)(this.y + this.height)) {
            this.onPress();
            return true;
        } else {
            return false;
        }
    }
}
