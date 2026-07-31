package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ModelLayersAccessor {

    public static final Map<ModelLayerLocation, PetsClientInitializer.TexturedLayerDefinitionProvider> CUSTOM_PROVIDERS = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static void registerModelLayer(ModelLayerLocation layer, PetsClientInitializer.TexturedLayerDefinitionProvider provider) {
        if (layer == null || provider == null) return;

        try {
            Field field = ModelLayers.class.getDeclaredField("ALL_MODELS");
            field.setAccessible(true);

            Set<ModelLayerLocation> currentSet = (Set<ModelLayerLocation>) field.get(null);

            if (currentSet != null) {
                // Synchronize on the set to prevent ConcurrentModificationException
                // while Worker threads stream over ALL_MODELS
                synchronized (currentSet) {
                    // Re-create as a CopyOnWrite/Concurrent set if it isn't already
                    Set<ModelLayerLocation> newSet = new HashSet<>(currentSet);
                    newSet.add(layer);

                    // Replace the set instance atomically
                    field.set(null, Collections.synchronizedSet(newSet));
                }
            }

            CUSTOM_PROVIDERS.put(layer, provider);
            System.out.println("[Agent] Successfully registered layer to ALL_MODELS: " + layer);

        } catch (Throwable t) {
            System.err.println("[Agent] Failed to register layer: " + layer);
            t.printStackTrace();
        }
    }
}