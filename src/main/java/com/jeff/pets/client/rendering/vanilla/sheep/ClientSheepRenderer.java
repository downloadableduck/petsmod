package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.mob.vanilla.passive.ClientSheep;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


import static com.jeff.pets.client.Central.CONFIG;

public class ClientSheepRenderer extends PetRenderer< ClientSheep,  ClientSheepModel> {
    public static final ModelLayerLocation SHEEP_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientsheep"), "main");

    public ClientSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientSheepModel(context.bakeLayer(ModelLayers.SHEEP)), 0.7F);
        this.addLayer(new ClientSheepWoolLayer(this, context.getModelSet()));
    }

    @Override
    protected void scale( ClientSheep livingEntityRenderState,  PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public  ResourceLocation getTextureLocation( ClientSheep livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/sheep/sheep.png");
    }

    /*@Override
    public void extractRenderState(Sheep sheep, SheepRenderState state, float f) {
        super.extractRenderState(sheep, state, f);
        sheep.sheared = false;
    }*/
}
