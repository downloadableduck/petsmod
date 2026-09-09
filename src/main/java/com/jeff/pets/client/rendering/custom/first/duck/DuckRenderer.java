package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.rendering.IPetRenderState;
import com.jeff.pets.mob.custom.first.Duck;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class DuckRenderer extends PetRenderer<@NotNull Duck, @NotNull DuckRenderState, @NotNull DuckModel> {
    public String duckTexturePath;

    public DuckRenderer(final EntityRendererProvider.Context context) {
        super(context, new DuckModel(context.bakeLayer(DuckModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    protected void scale(@NotNull DuckRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (!livingEntityRenderState.isServerEntity) {
            if (livingEntityRenderState.isBaby) {
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
    public @NotNull Identifier getTextureLocation(final DuckRenderState state) {
        if (!state.isServerEntity) {
            String skin = ((IPetRenderState) state).pets$getPetSkin();
            if (Objects.equals(skin, "pekin")) {
                duckTexturePath = "textures/entity/duck/pekin.png";
            } else if (Objects.equals(skin, "mallard")) {
                duckTexturePath = "textures/entity/duck/mallard_male.png";
            } else if (Objects.equals(skin, "rubber")) {
                duckTexturePath = "textures/entity/duck/rubber.png";
            } else if (skin.equals("bronze")) {
                duckTexturePath = "textures/entity/duck/bronze.png";
            }
            return Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, duckTexturePath);
        } else {
            if (state.duckSpecies == 0) {
                return Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, "textures/entity/duck/mallard_male.png");
            } else if (state.duckSpecies == 1) {
                return Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, "textures/entity/duck/pekin.png");
            } else {
                return Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, "textures/entity/duck/yeahitdidntwork");
            }
        }
    }
}