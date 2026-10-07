package com.jeff.pets.client.rendering.custom.aquatic.koi;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.MOD_ID;

public class KoiRenderer extends PetRenderer {

    public KoiRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new KoiModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity state) {
        return new ResourceLocation(MOD_ID, "textures/entity/koi/koi.png");
    }
}

