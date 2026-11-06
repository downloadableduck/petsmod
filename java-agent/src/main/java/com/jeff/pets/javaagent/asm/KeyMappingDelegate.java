package com.jeff.pets.javaagent.asm;

import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class KeyMappingDelegate {
    public static void createKeyBinding() {
        PetsClientInitializer.createKeyBinding((keyMapping -> {
            try {
                Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                Unsafe unsafe = (Unsafe) f.get(null);
                Options instance = Minecraft.getInstance().options;
                Field keyMappingsField = Options.class.getDeclaredField("keyMappings");
                long offset = unsafe.objectFieldOffset(keyMappingsField);
                KeyMapping[] currentMappings = (KeyMapping[]) unsafe.getObject(instance, offset);
                KeyMapping[] newMappings = new KeyMapping[currentMappings.length + 1];

                System.arraycopy(currentMappings, 0, newMappings, 0, currentMappings.length);
                newMappings[currentMappings.length] = keyMapping;
                unsafe.putObject(instance, offset, newMappings);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return keyMapping;
        }));
    }
}
