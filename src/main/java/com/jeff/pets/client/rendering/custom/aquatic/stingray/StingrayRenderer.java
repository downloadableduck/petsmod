package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.MOD_ID;

public class StingrayRenderer extends PetRenderer<Stingray, StingrayModel> {

    public StingrayRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new StingrayModel(), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(Stingray state) {
        return new ResourceLocation(MOD_ID, "textures/entity/stingray/stingray.png");
    }

    @Override
    public void render(Stingray stingray, double f, double partialTick, double h, float i, float j) {
        super.render(stingray, f, partialTick, h, i, j);
        //stingray.flapTime = stingray.flap + state.ageInTicks;
    }
}
