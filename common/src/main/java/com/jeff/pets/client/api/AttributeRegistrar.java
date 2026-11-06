package com.jeff.pets.client.api;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface AttributeRegistrar {
    void register(EntityType<? extends @NotNull LivingEntity> type, AttributeSupplier supplier);
}
