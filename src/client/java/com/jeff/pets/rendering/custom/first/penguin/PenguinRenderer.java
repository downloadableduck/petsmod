package com.jeff.pets.rendering.custom.first.penguin;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Penguin;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class PenguinRenderer extends PetRenderer<@NotNull Penguin, @NotNull PenguinRenderState, @NotNull PenguinModel> {

    public PenguinRenderer(EntityRendererProvider.Context context) {
        super(context, new PenguinModel(context.bakeLayer(PenguinModel.PENGUIN_LOCATION)), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(PenguinRenderState livingEntityRenderState) {
        return ResourceLocation.fromNamespaceAndPath(PetsInitializer.MOD_ID, "textures/entity/penguin/penguin.png");
    }

    @Override
    protected void scale(@NotNull PenguinRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if ((CONFIG.isBaby && !livingEntityRenderState.isServerEntity) || (livingEntityRenderState.isBaby && livingEntityRenderState.isServerEntity)) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public void extractRenderState(final Penguin penguin, final PenguinRenderState state, final float partialTicks) {
        state.isServerEntity = penguin.isServerEntity();
        state.flap = Mth.lerp(partialTicks, penguin.oFlap, penguin.flap);
        state.flapSpeed = Mth.lerp(partialTicks, penguin.oFlapSpeed, penguin.flapSpeed);
        super.extractRenderState(penguin, state, partialTicks);
        state.isPassenger = penguin.isPassenger();
    }

    @Override
    public PenguinRenderState createRenderState() {
        return new PenguinRenderState();
    }
}
