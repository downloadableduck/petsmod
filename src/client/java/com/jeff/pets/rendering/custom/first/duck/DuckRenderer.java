package com.jeff.pets.rendering.custom.first.duck;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.mob.custom.first.Duck;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Central.CONFIG;

public class DuckRenderer extends PetRenderer<@NotNull Duck, @NotNull DuckRenderState, @NotNull DuckModel> {
    public String duckTexturePath;

    public DuckRenderer(final EntityRendererProvider.Context context) {
        super(context, new DuckModel(context.bakeLayer(DuckModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    protected void scale(@NotNull DuckRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (!livingEntityRenderState.isServerEntity) {
            if (CONFIG.isBaby) {
                poseStack.scale(0.6f, 0.6f, 0.6f);
            }
        } else {
            if (livingEntityRenderState.isBaby) {
                poseStack.scale(0.6f, 0.6f, 0.6f);
            }
        }
    }

    @Override
    public DuckRenderState createRenderState() {
        return new DuckRenderState();
    }

    @Override
    public void extractRenderState(final Duck duck, final DuckRenderState state, final float partialTicks) {
        state.isServerEntity = duck.isServerEntity();
        state.duckSpecies = duck.getEntityData().get(Duck.DUCK_SKIN);
        state.flap = Mth.lerp(partialTicks, duck.oFlap, duck.flap);
        state.flapSpeed = Mth.lerp(partialTicks, duck.oFlapSpeed, duck.flapSpeed);
        super.extractRenderState(duck, state, partialTicks);
        state.isPassenger = duck.isPassenger();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(final DuckRenderState state) {
        if (!state.isServerEntity) {
            if (Objects.equals(CONFIG.duckSkin, "pekin")) {
                duckTexturePath = "textures/entity/duck/pekin.png";
            } else if (Objects.equals(CONFIG.duckSkin, "mallard")) {
                duckTexturePath = "textures/entity/duck/mallard_male.png";
            } else if (Objects.equals(CONFIG.duckSkin, "rubber")) {
                duckTexturePath = "textures/entity/duck/rubber.png";
            } else if (CONFIG.duckSkin.equals("bronze")) {
                duckTexturePath = "textures/entity/duck/bronze.png";
            }
            return ResourceLocation.fromNamespaceAndPath(PetsInitializer.MOD_ID, duckTexturePath);
        } else {
            if (state.duckSpecies == 0) {
                return ResourceLocation.fromNamespaceAndPath(PetsInitializer.MOD_ID, "textures/entity/duck/mallard_male.png");
            } else if (state.duckSpecies == 1) {
                return ResourceLocation.fromNamespaceAndPath(PetsInitializer.MOD_ID, "textures/entity/duck/pekin.png");
            } else {
                return ResourceLocation.fromNamespaceAndPath(PetsInitializer.MOD_ID, "textures/entity/duck/yeahitdidntwork");
            }
        }
    }
}