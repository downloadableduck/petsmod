package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import net.minecraft.client.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class StingrayRenderer extends PetRenderer<Stingray> {

    public StingrayRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new StingrayModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(net.minecraft.entity.Entity entity) {
        Stingray state = (Stingray) entity;
        return new Identifier(MOD_ID, "textures/entity/stingray/stingray.png");
    }

    @Override
    public void renderModel(net.minecraft.entity.living.LivingEntity entity, float f, float partialTick, float g, float h, float i, float j) {
        Stingray stingray = (Stingray) entity;
        super.renderModel(stingray, f, partialTick, g, h, i, j);
        //stingray.flapTime = stingray.flap + state.ageInTicks;
    }
}
