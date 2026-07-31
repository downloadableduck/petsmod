package com.jeff.pets.client.mixin.client;

import net.minecraft.client.gui.components.Button;

import java.lang.reflect.Field;

public interface ButtonAccessor {
    public static void setOnPress(Button button, Button.OnPress onPress) {
        try {
            Field field = Button.class.getDeclaredField("onPress");
            field.setAccessible(true);
            field.set(button, onPress);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
