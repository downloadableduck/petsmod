package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Duck;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class DuckRenderer extends PetRenderer<@NotNull Duck, @NotNull DuckModel> {
    public String duckTexturePath;

    public DuckRenderer(final net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new DuckModel(), 0.3F);
    }

    @Override
    protected void scale(@NotNull Duck livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }
    }

    @Override
    public void render(final Duck duck, float f, final float partialTicks, PoseStack poseStack, MultiBufferSource source, int i) {
        duck.flap = Mth.lerp(partialTicks, duck.oFlap, duck.flap);
        duck.flapSpeed = Mth.lerp(partialTicks, duck.oFlapSpeed, duck.flapSpeed);
        super.render(duck, f, partialTicks, poseStack, source, i);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(final Duck state) {
        if (Objects.equals(CONFIG.duckSkin, "pekin")) {
            duckTexturePath = "textures/entity/duck/pekin.png";
        } else if (Objects.equals(CONFIG.duckSkin, "mallard")) {
            duckTexturePath = "textures/entity/duck/mallard_male.png";
        } else if (Objects.equals(CONFIG.duckSkin, "rubber")) {
            duckTexturePath = "textures/entity/duck/rubber.png";
        } else if (CONFIG.duckSkin.equals("bronze")) {
            duckTexturePath = "textures/entity/duck/bronze.png";
        }
        return new ResourceLocation(PetsInitializer.MOD_ID, duckTexturePath);
    }
}