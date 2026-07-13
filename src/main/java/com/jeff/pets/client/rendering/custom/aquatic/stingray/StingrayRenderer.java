package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class StingrayRenderer extends PetRenderer<Stingray, StingrayModel> {

    public StingrayRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new StingrayModel(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(Stingray state) {
        return new ResourceLocation(MOD_ID, "textures/entity/stingray/stingray.png");
    }

    @Override
    public void render(Stingray stingray, float f, float partialTick, com.mojang.blaze3d.matrix.MatrixStack poseStack, IRenderTypeBuffer source, int i) {
        super.render(stingray, f, partialTick, poseStack, source, i);
        //stingray.flapTime = stingray.flap + state.ageInTicks;
    }
}
