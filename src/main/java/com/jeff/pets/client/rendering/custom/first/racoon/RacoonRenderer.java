package com.jeff.pets.client.rendering.custom.first.racoon;

import com.jeff.pets.mob.custom.first.Racoon;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.MOD_ID;
import static com.jeff.pets.client.Central.CONFIG;

public class RacoonRenderer extends MobRenderer<Racoon, RacoonModel> {

    public RacoonRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new RacoonModel(), 0.75f);
    }

    @Override
    protected void scale(Racoon livingEntityRenderState, com.mojang.blaze3d.matrix.MatrixStack poseStack, float f) {
        if ((CONFIG.isBaby && !livingEntityRenderState.isServerEntity()) || (livingEntityRenderState.isBaby() && livingEntityRenderState.isServerEntity())) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Racoon state) {
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
    public void render(Racoon racoon, float g, float f, com.mojang.blaze3d.matrix.MatrixStack poseStack, IRenderTypeBuffer source, int i) {
        super.render(racoon, g, f, poseStack, source, i);
        racoon.setServerEntity(racoon.getEntityData().get(Racoon.IS_SERVER_ENTITY));
    }
}
