package com.jeff.pets.client.mixin.client;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.rendering.custom.first.duck.DuckRenderer;
import com.jeff.pets.client.rendering.custom.first.penguin.PenguinRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class EntityRenderersDelegate {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Map<EntityType<?>, EntityRenderer<?, ?>> appendCustomRenderers(
            Map<EntityType<?>, EntityRenderer<?, ?>> vanillaMap,
            EntityRendererProvider.Context context
    ) {
        // Force layer and renderer registration if not yet initialized

        Map<EntityType<?>, EntityRenderer<?, ?>> mutableMap = new HashMap<>(vanillaMap);

            try {
                mutableMap.put(PetsInitializer.PENGUIN, new PenguinRenderer(context));
                mutableMap.put(PetsInitializer.DUCK, new DuckRenderer(context));
                System.out.println("[Agent] Successfully attached PenguinRenderer!");
            } catch (Throwable t) {
                System.err.println("[Agent] Failed to instantiate PenguinRenderer:");
                t.printStackTrace();
            }

        return mutableMap;
    }
}