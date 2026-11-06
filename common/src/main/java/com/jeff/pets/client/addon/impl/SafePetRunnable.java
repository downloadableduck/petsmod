package com.jeff.pets.client.addon.impl;

import net.minecraft.world.entity.EntityType;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * Runnable that doesn't include the pet instance, so it can be executed when the player
 * is not in a level.
 */
@FunctionalInterface
public interface SafePetRunnable {
    void run(String entityValue, Field entityName, EntityType<?> entityType, Optional<PetSkin> entitySkin) throws Exception;
}
