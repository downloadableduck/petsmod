package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class DuckRenderer extends PetRenderer<Duck, DuckModel> {
    public String duckTexturePath;

    public DuckRenderer(final net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new DuckModel(), 0.3F);
    }

    @Override
    protected void scale(Duck livingEntityRenderState, com.mojang.blaze3d.matrix.MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }
    }

    @Override
    public void render(final Duck duck, float f, final float partialTicks, com.mojang.blaze3d.matrix.MatrixStack poseStack, IRenderTypeBuffer source, int i) {
        duck.flap = net.minecraft.util.math.MathHelper.lerp(partialTicks, duck.oFlap, duck.flap);
        duck.flapSpeed = net.minecraft.util.math.MathHelper.lerp(partialTicks, duck.oFlapSpeed, duck.flapSpeed);
        super.render(duck, f, partialTicks, poseStack, source, i);
    }

    @Override
    public ResourceLocation getTextureLocation(final Duck state) {
        if (Objects.equals(CONFIG.duckSkin, "pekin")) {
            duckTexturePath = "textures/entity/duck/pekin.png";
        } else if (Objects.equals(CONFIG.duckSkin, "mallard")) {
            duckTexturePath = "textures/entity/duck/mallard_male.png";
        } else if (Objects.equals(CONFIG.duckSkin, "rubber")) {
            duckTexturePath = "textures/entity/duck/rubber.png";
        } else if (CONFIG.duckSkin.equals("bronze")) {
            duckTexturePath = "textures/entity/duck/bronze.png";
        } else {
            duckTexturePath = "textures/entity/duck/mallard_male.png";
        }
        return new ResourceLocation(Central.MOD_ID, duckTexturePath);
    }
}