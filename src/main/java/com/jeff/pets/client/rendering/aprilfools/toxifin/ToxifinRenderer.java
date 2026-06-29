package com.jeff.pets.client.rendering.aprilfools.toxifin;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.aprilfools.ToxifinSlab;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class ToxifinRenderer extends PetRenderer<@NotNull ToxifinSlab, @NotNull ToxifinSlabModel<ToxifinSlab>> {

    public static final ModelLayerLocation TOXIFIN_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MOD_ID, "toxifin_slab"), "main");

    public ToxifinRenderer(EntityRendererProvider.Context context) {
        super(context, new ToxifinSlabModel<>(context.bakeLayer(TOXIFIN_LOCATION)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ToxifinSlab livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/toxifin.png");
    }
}
