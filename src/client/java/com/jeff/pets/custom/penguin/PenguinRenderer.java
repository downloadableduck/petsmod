package com.jeff.pets.custom.penguin;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.custom.Penguin;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class PenguinRenderer extends MobRenderer<@NotNull Penguin, @NotNull PenguinRenderState, @NotNull PenguinModel> {

    public PenguinRenderer(EntityRendererProvider.Context context) {
        super(context, new PenguinModel(context.bakeLayer(PenguinModel.PENGUIN_LOCATION)), 0.5f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(PenguinRenderState livingEntityRenderState) {
        return Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, "textures/entity/penguin/penguin.png");
    }

    @Override
    public void extractRenderState(final Penguin entity, final PenguinRenderState state, final float partialTicks) {
        state.isServerEntity = entity.isServerEntity();
        state.flap = Mth.lerp(partialTicks, entity.oFlap, entity.flap);
        state.flapSpeed = Mth.lerp(partialTicks, entity.oFlapSpeed, entity.flapSpeed);
        super.extractRenderState(entity, state, partialTicks);
    }

    @Override
    public PenguinRenderState createRenderState() {
        return new PenguinRenderState();
    }
}
