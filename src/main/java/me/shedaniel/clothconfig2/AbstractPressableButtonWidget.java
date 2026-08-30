package me.shedaniel.clothconfig2;

public abstract class AbstractPressableButtonWidget extends me.shedaniel.clothconfig2.ButtonWidget {
    public AbstractPressableButtonWidget(int i, int j, int k, int l, String string) {
        super(i, j, k, l, string, w -> {});
    }

    public abstract void onPress();

    @Override
    public void click(double mouseX, double mouseY) {
        this.onPress();
    }
}
