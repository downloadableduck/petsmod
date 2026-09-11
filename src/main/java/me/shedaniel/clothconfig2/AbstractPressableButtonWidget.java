package me.shedaniel.clothconfig2;

public abstract class AbstractPressableButtonWidget extends me.shedaniel.clothconfig2.ButtonWidget {
    public AbstractPressableButtonWidget(int i, int j, int k, int l, String string) {
        super(i, j, k, l, string, w -> {});
    }

    public abstract void onPress();

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.x <= mouseX && mouseX <= this.x + this.width && this.y <= mouseY && mouseY <= this.y + this.height) {
            this.onPress();
            return true;
        }
        return false;
    }
}
