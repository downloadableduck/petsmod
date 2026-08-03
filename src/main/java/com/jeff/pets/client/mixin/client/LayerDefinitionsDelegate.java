package com.jeff.pets.client.mixin.client;

import com.google.common.collect.ImmutableMap;
import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;

public class LayerDefinitionsDelegate {

    private static volatile boolean initialized = false;

    public static ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> populateBuilder(
            ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> builder) {

        if (!initialized) {
            try {
                PetsClientInitializer.registerRenderers();
            } catch (Throwable t) {
                t.printStackTrace();
            }
            initialized = true;
        }

        ModelLayersAccessor.CUSTOM_PROVIDERS.forEach((location, provider) -> {
            if (location == null || provider == null) return;

            try {
                LayerDefinition definition = provider.createLayerDefinition();
                if (definition != null) {
                    builder.put(location, definition);
                } else {
                    builder.put(location, createFallback());
                }
            } catch (Throwable t) {
                t.printStackTrace();
                builder.put(location, createFallback());
            }
        });

        return builder;
    }

    private static LayerDefinition createFallback() {
        return LayerDefinition.create(new MeshDefinition(), 16, 16);
    }
}