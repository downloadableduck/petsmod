package com.jeff.pets.client.api;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

@FunctionalInterface
public interface ModelLayerRegistrar {
    void registerModelLayer(ModelLayerLocation location, LayerDefinitionRegistrar layerDefinition);

    @FunctionalInterface
    interface LayerDefinitionRegistrar {
        LayerDefinition getLayerDefinition();
    }
}
