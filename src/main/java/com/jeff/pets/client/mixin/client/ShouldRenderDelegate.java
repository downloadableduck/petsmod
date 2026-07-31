package com.jeff.pets.client.mixin.client;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;

public class ShouldRenderDelegate {

    @SuppressWarnings("unchecked")
    public static EntityRenderer<Entity, ?> guardRenderer(EntityRenderer<?, ?> renderer, Entity entity) {
        if (renderer != null) {
            return (EntityRenderer<Entity, ?>) renderer;
        }

        if (entity != null) {
            // 1. Try looking up by ResourceLocation ID in case object instances didn't match
            Identifier id = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
            if (id != null) {
                // Search CUSTOM_RENDERERS map by matching ResourceLocation
                for (var entry : EntityRenderDispatcherDelegate.CUSTOM_RENDERERS.entrySet()) {
                    Identifier registeredId = BuiltInRegistries.ENTITY_TYPE.getKey(entry.getKey());
                    if (id.equals(registeredId)) {
                        return (EntityRenderer<Entity, ?>) entry.getValue();
                    }
                }
            }
        }

        // 2. Return fallback or null if truly unregistered
        return null;
    }
}