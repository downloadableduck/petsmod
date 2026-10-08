package com.jeff.pets.client.rendering.custom.aquatic.koi;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Koi;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class KoiRenderer extends PetRenderer {

    public KoiRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new KoiModel(), 0.3F);
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        Koi koiRenderState = (Koi) __e;
        return new ResourceLocation(PetsInitializer.MOD_ID, "textures/entity/koi/koi.png");
    }
}