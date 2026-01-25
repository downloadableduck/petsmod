package com.jeff.duck;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class DuckClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(DuckInitializer.DUCK, DuckRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(DuckModel.LAYER_LOCATION, DuckModel::getTexturedModelData);
    }
}