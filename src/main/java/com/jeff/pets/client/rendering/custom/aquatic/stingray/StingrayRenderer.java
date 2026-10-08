package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class StingrayRenderer extends PetRenderer {

    public StingrayRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new StingrayModel(), 0.3F);
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        Stingray livingEntityRenderState = (Stingray) __e;
        return new ResourceLocation("minecraft", "textures/entity/stingray.png");
    }

    public void renderModel(Stingray stingray, float f, float g, float h, float i, float j, float k) {
        super.renderModel(stingray, f, g, h, i, j, k);
    }
}