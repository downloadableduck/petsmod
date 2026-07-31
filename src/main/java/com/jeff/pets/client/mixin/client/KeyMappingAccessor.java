package com.jeff.pets.client.mixin.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;

import java.lang.reflect.Field;

public interface KeyMappingAccessor {
    public static InputConstants.Key getKey(KeyMapping keyMapping) {
        try {
            Field field = KeyMapping.class.getDeclaredField("key");
            field.setAccessible(true);
            return (InputConstants.Key) field.get(keyMapping);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
