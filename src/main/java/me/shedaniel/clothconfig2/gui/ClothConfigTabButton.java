//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui;

import me.shedaniel.clothconfig2.AbstractPressableButtonWidget;

public class ClothConfigTabButton extends AbstractPressableButtonWidget {
    private final int index;
    private final ClothConfigScreen screen;

    public ClothConfigTabButton(ClothConfigScreen screen, int index, int int_1, int int_2, int int_3, int int_4, String string_1) {
        super(int_1, int_2, int_3, int_4, string_1);
        this.index = index;
        this.screen = screen;
    }

    public void onPress() {
        if (this.index != -1) {
            this.screen.nextVertexTabIndex = this.index;
        }

        this.screen.tabsScrollVelocity = 0.0;
        this.screen.init();
    }

    public void render(int int_1, int int_2, float float_1) {
        this.active = this.index != this.screen.selectedTabIndex;
        super.render(int_1, int_2, float_1);
    }

    protected boolean onClick(double double_1, double double_2) {
        return this.visible && this.active && this.isMouseOver(double_1, double_2);
    }

    public boolean isMouseOver(double double_1, double double_2) {
        return this.active && this.visible && double_1 >= (double)this.x && double_2 >= (double)this.y && double_1 < (double)(this.x + this.width) && double_2 < (double)(this.y + this.height) && double_1 >= 20.0 && double_1 < (double)(this.screen.width - 20);
    }
}
