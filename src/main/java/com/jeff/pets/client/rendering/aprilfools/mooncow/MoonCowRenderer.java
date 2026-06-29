package com.jeff.pets.client.rendering.aprilfools.mooncow;

import com.jeff.pets.mob.aprilfools.MoonCow;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * Alright I give up. I'll try and add the glass helmet on the moon cow back if and when Minecraft
 * makes it easier to do, since this is rediculous.
 */
public class MoonCowRenderer extends PetRenderer<@NotNull MoonCow, @NotNull LegacyCowModel> {

    public static final ModelLayerLocation MOON_COW_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("mooncow"), "main");

    public MoonCowRenderer(EntityRendererProvider.Context context) {
        super(context, new LegacyCowModel(context.bakeLayer(MOON_COW_LOCATION)), 0.75f);
        this.addLayer((RenderLayer) new MoonCowHelmetLayer((RenderLayerParent) this, context.getBlockRenderDispatcher()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(MoonCow livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/cow/moon_cow.png");
    }
}
