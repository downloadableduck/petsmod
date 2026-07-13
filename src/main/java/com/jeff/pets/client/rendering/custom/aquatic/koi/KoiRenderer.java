package com.jeff.pets.client.rendering.custom.aquatic.koi;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Koi;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class KoiRenderer extends PetRenderer<Koi, KoiModel> {

    public KoiRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new KoiModel(), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Koi state) {
        return new ResourceLocation(MOD_ID, "textures/entity/koi/koi.png");
    }
}
