package com.jeff.pets.client.addon.impl;

import com.jeff.pets.mob.AbstractPet;
import net.minecraft.world.entity.EntityType;

import java.lang.reflect.Field;
import java.util.Optional;

@FunctionalInterface
public interface PetRunnable {
    void run(AbstractPet entity, String entityValue, Field entityName, Class<?> entityClass, EntityType<?> entityType, Optional<PetSkin> entitySkin) throws Exception;
}
