package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class StingrayRenderer extends PetRenderer<Stingray, StingrayModel> {

    public StingrayRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new StingrayModel(), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Stingray state) {
        return new ResourceLocation(MOD_ID, "textures/entity/stingray/stingray.png");
    }

    @Override
    public void render(Stingray stingray, float f, float partialTick, PoseStack poseStack, MultiBufferSource source, int i) {
        super.render(stingray, f, partialTick, poseStack, source, i);
        //stingray.flapTime = stingray.flap + state.ageInTicks;
    }
}
