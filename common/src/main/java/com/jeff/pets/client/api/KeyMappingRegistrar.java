package com.jeff.pets.client.api;

import net.minecraft.client.KeyMapping;

@FunctionalInterface
public interface KeyMappingRegistrar {
    KeyMapping registerKeyBinding(KeyMapping keyMapping);
}
