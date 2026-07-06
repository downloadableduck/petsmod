package com.jeff.pets.client.rendering.custom.first.racoon;

import com.jeff.pets.mob.custom.first.Racoon;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;
import static com.jeff.pets.PetsInitializer.MOD_ID;

public class RacoonRenderer extends MobRenderer<@NotNull Racoon, @NotNull RacoonModel> {
    public static final ModelLayerLocation RACOON_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "racoon"), "main");

    public RacoonRenderer(EntityRendererProvider.Context context) {
        super(context, new RacoonModel(context.bakeLayer(RACOON_LOCATION)), 0.75f);
    }

    @Override
    protected void scale(@NotNull Racoon livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if ((CONFIG.isBaby && !livingEntityRenderState.isServerEntity()) || (livingEntityRenderState.isBaby() && livingEntityRenderState.isServerEntity())) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Racoon state) {
        String racoonTexturePath;
        if (!state.isServerEntity()) {
            if (Objects.equals(CONFIG.racoonSkin, "normal")) {
                racoonTexturePath = "textures/entity/racoon/racoon.png";
            } else if (Objects.equals(CONFIG.racoonSkin, "albino")) {
                racoonTexturePath = "textures/entity/racoon/albino.png";
            } else {
                racoonTexturePath = "textures/entity/racoon/racoon.png";
            }
        } else {
            racoonTexturePath = "textures/entity/racoon/racoon.png";
        }
        return new ResourceLocation(MOD_ID, racoonTexturePath);
    }

    @Override
    public void render(Racoon racoon, float g, float f, PoseStack poseStack, MultiBufferSource source, int i) {
        super.render(racoon, g, f, poseStack, source, i);
        racoon.setServerEntity(racoon.getEntityData().get(Racoon.IS_SERVER_ENTITY));
    }
}
