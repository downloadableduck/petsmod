package com.jeff.pets.client.rendering.aprilfools.diamondchicken;

import com.jeff.pets.mob.aprilfools.DiamondChicken;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.chicken.ClientChickenModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


import static com.jeff.pets.client.Central.CONFIG;

public class DiamondChickenRenderer extends PetRenderer< DiamondChicken,  ClientChickenModel<DiamondChicken>> {

    public static final ModelLayerLocation DIAMOND_CHICKEN_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "diamondchicken"), "main");

    public DiamondChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientChickenModel<>(context.bakeLayer(DIAMOND_CHICKEN_LOCATION)), 0.75f);
    }

    @Override
    protected void scale( DiamondChicken livingEntityRenderState,  PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public  ResourceLocation getTextureLocation(DiamondChicken livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/diamond_chicken.png");
    }
}
