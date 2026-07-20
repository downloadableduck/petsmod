package me.shedaniel.clothconfig2;

import net.minecraft.unmapped.C_18392283;

public abstract class AbstractPressableButtonWidget extends C_18392283 {
    public AbstractPressableButtonWidget(int i, int j, int k, int l, String string) {
        super(i, j, k, l, string);
    }

    public abstract void onPress();

    @Override
    public void m_83430595() {
        this.onPress();
    }
}
