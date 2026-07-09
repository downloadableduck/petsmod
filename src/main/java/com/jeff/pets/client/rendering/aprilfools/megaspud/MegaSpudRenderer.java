package com.jeff.pets.client.rendering.aprilfools.megaspud;

import com.jeff.pets.mob.aprilfools.MegaSpud;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;


public class MegaSpudRenderer extends PetRenderer< MegaSpud,  MegaSpudModel> {

    public static final ModelLayerLocation MEGA_SPUD_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "megaspoud"), "main");

    public MegaSpudRenderer(EntityRendererProvider.Context context) {
        super(context, new MegaSpudModel(context.bakeLayer(MEGA_SPUD_LOCATION)), 0.75f);
        this.addLayer((RenderLayer) new MegaSpudOuterLayer((RenderLayerParent) this, context));
    }

    @Override
    protected void scale(MegaSpud state, PoseStack poseStack, float f) {
        poseStack.scale(9, 12, 9);
    }

    @Override
    public  ResourceLocation getTextureLocation(MegaSpud livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/slime/mega_spud.png");
    }
}
