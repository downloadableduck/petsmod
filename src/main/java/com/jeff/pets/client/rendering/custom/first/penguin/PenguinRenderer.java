package com.jeff.pets.client.rendering.custom.first.penguin;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.mob.custom.first.Penguin;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


import static com.jeff.pets.client.Central.CONFIG;

public class PenguinRenderer extends PetRenderer< Penguin,  PenguinModel> {

    public PenguinRenderer(EntityRendererProvider.Context context) {
        super(context, new PenguinModel(context.bakeLayer(PenguinModel.PENGUIN_LOCATION)), 0.5f);
    }

    @Override
    public  ResourceLocation getTextureLocation(Penguin livingEntityRenderState) {
        return new ResourceLocation(PetsInitializer.MOD_ID, "textures/entity/penguin/penguin.png");
    }

    @Override
    protected void scale( Penguin livingEntityRenderState,  PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public void render(final Penguin penguin, float f, float partialTicks, PoseStack poseStack, MultiBufferSource source, int i) {
        penguin.flap = Mth.lerp(partialTicks, penguin.oFlap, penguin.flap);
        penguin.flapSpeed = Mth.lerp(partialTicks, penguin.oFlapSpeed, penguin.flapSpeed);
        super.render(penguin, f, partialTicks, poseStack, source, i);
    }
}
