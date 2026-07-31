package com.jeff.pets.client.mixin.client;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityRenderDispatcherDelegate {

    // Store custom entity type -> renderer mappings registered by your mod
    public static final Map<EntityType<?>, EntityRenderer<?, ?>> CUSTOM_RENDERERS = new ConcurrentHashMap<>();

    /**
     * Intercepts the Map assigned to EntityRenderDispatcher.renderers
     * and appends all modded renderers.
     */
    public static Map<EntityType<?>, EntityRenderer<?, ?>> appendCustomRenderers(Map<EntityType<?>, EntityRenderer<?, ?>> vanillaMap) {
        Map<EntityType<?>, EntityRenderer<?, ?>> merged = new java.util.HashMap<>(vanillaMap);

        // Inject all ID-registered renderers back into the entity type map
        BuiltInRegistries.ENTITY_TYPE.forEach(type -> {
            Identifier id = BuiltInRegistries.ENTITY_TYPE.getKey(type);
            if (id != null && CUSTOM_RENDERERS_BY_ID.containsKey(id.toString())) {
                merged.put(type, CUSTOM_RENDERERS_BY_ID.get(id.toString()));
                System.out.println("[Agent] Successfully re-merged renderer for: " + id);
            }
        });

        return Map.copyOf(merged);
    }
    /**
     * Fallback lookup if getRenderer returns null.
     */
    public static EntityRenderer<?, ?> getFallbackRenderer(EntityType<?> type) {
        EntityRenderer<?, ?> renderer = CUSTOM_RENDERERS.get(type);
        if (renderer == null) {
            System.err.println("[Agent] CRITICAL: No EntityRenderer registered for EntityType: " + type);
        }
        return renderer;
    }

        // Map using String ID (e.g., "pets-mod:duck") to avoid ClassLoader/instance mismatch issues
        public static final Map<String, EntityRenderer<?, ?>> CUSTOM_RENDERERS_BY_ID = new ConcurrentHashMap<>();

        public static void registerRenderer(Identifier id, EntityRenderer<?, ?> renderer) {
            CUSTOM_RENDERERS_BY_ID.put(id.toString(), renderer);
        }

        @SuppressWarnings("unchecked")
        public static EntityRenderer<Entity, ?> getFallbackRenderer(Entity entity) {
            if (entity == null) return null;

            EntityType<?> type = entity.getType();
            Identifier id = BuiltInRegistries.ENTITY_TYPE.getKey(type);

            if (id != null) {
                EntityRenderer<?, ?> renderer = CUSTOM_RENDERERS_BY_ID.get(id.toString());
                if (renderer != null) {
                    return (EntityRenderer<Entity, ?>) renderer;
                }
            }

            return null;
        }
    }