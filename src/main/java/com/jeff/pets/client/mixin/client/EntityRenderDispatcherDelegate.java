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

    public static final Map<EntityType<?>, EntityRenderer<?, ?>> CUSTOM_RENDERERS = new ConcurrentHashMap<>();

    public static Map<EntityType<?>, EntityRenderer<?, ?>> appendCustomRenderers(Map<EntityType<?>, EntityRenderer<?, ?>> vanillaMap) {
        Map<EntityType<?>, EntityRenderer<?, ?>> merged = new java.util.HashMap<>(vanillaMap);

        BuiltInRegistries.ENTITY_TYPE.forEach(type -> {
            Identifier id = BuiltInRegistries.ENTITY_TYPE.getKey(type);
            if (id != null && CUSTOM_RENDERERS_BY_ID.containsKey(id.toString())) {
                merged.put(type, CUSTOM_RENDERERS_BY_ID.get(id.toString()));
            }
        });

        return Map.copyOf(merged);
    }

    public static EntityRenderer<?, ?> getFallbackRenderer(EntityType<?> type) {
        return CUSTOM_RENDERERS.get(type);
    }

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