package com.jeff.pets.client.rendering.custom.aquatic.koi;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Koi;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class KoiRenderer extends PetRenderer<Koi, KoiModel> {

    public KoiRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new KoiModel(), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Koi state) {
        return new ResourceLocation(MOD_ID, "textures/entity/koi/koi.png");
    }
}
