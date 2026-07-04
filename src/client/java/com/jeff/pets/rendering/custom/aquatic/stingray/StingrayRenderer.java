package com.jeff.pets.rendering.custom.aquatic.stingray;

import com.jeff.pets.mob.custom.aquatic.Stingray;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class StingrayRenderer extends PetRenderer<Stingray, StingrayModel> {

    public static final ModelLayerLocation STINGRAY_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "stingray"), "main");

    public StingrayRenderer(EntityRendererProvider.Context context) {
        super(context, new StingrayModel(context.bakeLayer(STINGRAY_LOCATION)), 0.75f);
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
