package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class StingrayRenderer extends PetRenderer<@NotNull Stingray> {

    public StingrayRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new StingrayModel(), 0.3F);
    }

    @Override
    public @NotNull Identifier getTexture(Stingray livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/stingray.png");
    }

    @Override
    public void renderModel(Stingray stingray, float f, float g, float h, float i, float j, float k) {
        super.renderModel(stingray, f, g, h, i, j, k);
    }
}