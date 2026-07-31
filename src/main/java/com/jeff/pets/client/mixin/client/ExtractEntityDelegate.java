package com.jeff.pets.client.mixin.client;

import com.jeff.pets.PetsInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ExtractEntityDelegate {

    private static final Set<EntityType<?>> LOGGED_MISSING_TYPES = ConcurrentHashMap.newKeySet();

    @SuppressWarnings("unchecked")
    public static EntityRenderer<Entity, ?> guardRenderer(EntityRenderer<?, ?> renderer, Entity entity) {
        if (renderer == null) {
            if (entity != null) {
                EntityType<?> type = entity.getType();
                if (LOGGED_MISSING_TYPES.add(type)) {
                    System.err.println("[Agent] WARNING: Missing EntityRenderer in extractEntity for " + type);
                }
            }

            try {
                var dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
                // Fallback to a valid vanilla renderer (e.g. Pig) to build a valid state shell without crashing
                EntityRenderer<?, ?> fallback = dispatcher.getRenderer(PetsInitializer.DUCK.create(Minecraft.getInstance().level, EntitySpawnReason.COMMAND));
                return (EntityRenderer<Entity, ?>) fallback;
            } catch (Throwable t) {
                return null;
            }
        }

        return (EntityRenderer<Entity, ?>) renderer;
    }
}