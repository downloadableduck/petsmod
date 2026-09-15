package com.jeff.pets.client.rendering.custom.aquatic.koi;

import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Koi;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.MOD_ID;

public class KoiRenderer extends PetRenderer {

    public KoiRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new KoiModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity state) {
        return new ResourceLocation(MOD_ID, "textures/entity/koi/koi.png");
    }
}

