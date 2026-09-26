package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.rendering.IPetRenderState;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Duck;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class DuckRenderer extends PetRenderer<@NotNull Duck, @NotNull DuckRenderState, @NotNull DuckModel> {
    public String duckTexturePath;

    public DuckRenderer(final EntityRendererProvider.Context context) {
        super(context, new DuckModel(context.bakeLayer(DuckModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    protected void scale(@NotNull DuckRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (livingEntityRenderState.isBaby) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
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
        state.isPassenger = duck.isPassenger() || duck.sitting;
    }

    @Override
    public @NotNull Identifier getTextureLocation(final DuckRenderState state) {
        if (!state.isServerEntity) {
            String skin = ((IPetRenderState) state).pets$getPetSkin();
            switch (skin) {
                case "pekin" -> duckTexturePath = "textures/entity/duck/pekin.png";
                case "rubber" -> duckTexturePath = "textures/entity/duck/rubber.png";
                case "bronze" -> duckTexturePath = "textures/entity/duck/bronze.png";
                case "silver" -> duckTexturePath = "textures/entity/duck/silver.png";
                case null, default -> duckTexturePath = "textures/entity/duck/mallard_male.png";
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