package com.jeff.pets.client.rendering.custom.first.racoon;

import com.jeff.pets.mob.custom.first.Racoon;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;
import static com.jeff.pets.PetsInitializer.MOD_ID;

public class RacoonRenderer extends MobRenderer<@NotNull Racoon, @NotNull RacoonRenderState, @NotNull RacoonModel> {
    public static final ModelLayerLocation RACOON_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(MOD_ID, "racoon"), "main");

    public RacoonRenderer(EntityRendererProvider.Context context) {
        super(context, new RacoonModel(context.bakeLayer(RACOON_LOCATION)), 0.75f);
    }

    @Override
    protected void scale(@NotNull RacoonRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if ((CONFIG.isBaby && !livingEntityRenderState.isServerEntity) || (livingEntityRenderState.isBaby && livingEntityRenderState.isServerEntity)) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(RacoonRenderState state) {
        String racoonTexturePath;
        if (!state.isServerEntity) {
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
        return Identifier.fromNamespaceAndPath(MOD_ID, racoonTexturePath);
    }

    @Override
    public RacoonRenderState createRenderState() {
        return new RacoonRenderState();
    }

    @Override
    public void extractRenderState(Racoon racoon, RacoonRenderState state, float f) {
        super.extractRenderState(racoon, state, f);
        state.isPassenger = racoon.isPassenger();
        state.isServerEntity = racoon.getEntityData().get(Racoon.IS_SERVER_ENTITY);
    }
}
