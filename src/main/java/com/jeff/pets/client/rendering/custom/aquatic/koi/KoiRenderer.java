package com.jeff.pets.client.rendering.custom.aquatic.koi;

import com.jeff.pets.mob.custom.aquatic.Koi;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.custom.PetRenderState;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class KoiRenderer extends PetRenderer<Koi, PetRenderState, KoiModel> {

    public static final ModelLayerLocation KOI_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(MOD_ID, "koi"), "main");

    public KoiRenderer(EntityRendererProvider.Context context) {
        super(context, new KoiModel(context.bakeLayer(KOI_LOCATION)), 0.5f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull PetRenderState state) {
        return Identifier.fromNamespaceAndPath(MOD_ID, "textures/entity/koi/koi.png");
    }

    @Override
    public @NotNull PetRenderState createRenderState() {
        return new PetRenderState();
    }
}
