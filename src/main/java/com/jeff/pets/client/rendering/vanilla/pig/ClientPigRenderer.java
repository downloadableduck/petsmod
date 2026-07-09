package com.jeff.pets.client.rendering.vanilla.pig;

import com.jeff.pets.mob.vanilla.passive.ClientPig;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigRenderer extends PetRenderer< ClientPig,  ClientPigModel> {
    public static final ModelLayerLocation PIG_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientpig"), "main");
    public String pigTexturePath;

    public ClientPigRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientPigModel(context.bakeLayer(ModelLayers.PIG)), 0.7F);
    }

    public static LayerDefinition createBasePigModel() {
        ClientPigModel.createBodyLayer(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    @Override
    protected void scale( ClientPig livingEntityRenderState,  PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    public  ResourceLocation getTextureLocation(ClientPig pigRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/pig/pig.png");
    }
}
