package com.jeff.pets.client.rendering.custom.first.penguin;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Penguin;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class PenguinRenderer extends PetRenderer<Penguin, PenguinModel> {

    public PenguinRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new PenguinModel(), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Penguin livingEntityRenderState) {
        return new ResourceLocation(Central.MOD_ID, "textures/entity/penguin/penguin.png");
    }

    @Override
    protected void scale(Penguin livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public void render(final Penguin penguin, double f, double partialTicks, double d, float k, float i) {
        //penguin.flap = (float) net.minecraft.util.math.MathHelper.lerp(partialTicks, penguin.oFlap, penguin.flap);
        //penguin.flapSpeed = (float) net.minecraft.util.math.MathHelper.lerp(partialTicks, penguin.oFlapSpeed, penguin.flapSpeed);
        super.render(penguin, f, partialTicks, d, k, i);
    }
}
