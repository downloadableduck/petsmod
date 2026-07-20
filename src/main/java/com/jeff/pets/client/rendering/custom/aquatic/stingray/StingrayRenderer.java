package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class StingrayRenderer extends PetRenderer<Stingray, StingrayModel> {

    public StingrayRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new StingrayModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull Stingray state) {
        return new Identifier(MOD_ID, "textures/entity/stingray/stingray.png");
    }

    @Override
    public void renderModel(Stingray stingray, float f, float partialTick, float g, float h, float i, float j) {
        super.renderModel(stingray, f, partialTick, g, h, i, j);
        //stingray.flapTime = stingray.flap + state.ageInTicks;
    }
}
