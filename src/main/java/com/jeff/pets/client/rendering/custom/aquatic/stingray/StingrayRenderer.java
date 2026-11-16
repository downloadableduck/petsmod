package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import org.jetbrains.annotations.NotNull;

public class StingrayRenderer extends PetRenderer<Stingray> {

    public StingrayRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new StingrayModel(), 0.75f);
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        Stingray state = (Stingray) entity;
        return ("textures/mob/stingray/stingray.png");
    }

    public void renderModel(net.minecraft.entity.mob.MobEntity entity, float f, float partialTick, float g, float h, float i, float j) {
        Stingray stingray = (Stingray) entity;
        // super.renderModel removed for b1.8 compat (render handled by PetRenderer)
        //stingray.flapTime = stingray.flap + state.ageInTicks;
    }
}
