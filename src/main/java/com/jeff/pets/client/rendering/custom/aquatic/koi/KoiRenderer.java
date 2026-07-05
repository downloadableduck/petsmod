package com.jeff.pets.client.rendering.custom.aquatic.koi;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Koi;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class KoiRenderer extends PetRenderer<Koi, KoiModel> {

    public static final ModelLayerLocation KOI_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "koi"), "main");

    public KoiRenderer(EntityRendererProvider.Context context) {
        super(context, new KoiModel(context.bakeLayer(KOI_LOCATION)), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Koi state) {
        return new ResourceLocation(MOD_ID, "textures/entity/koi/koi.png");
    }
}
