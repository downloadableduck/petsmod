package com.jeff.pets.rendering.aprilfools.mooncow;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.aprilfools.MoonCow;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * Alright I give up. I'll try and add the glass helmet on the moon cow back if and when Minecraft
 * makes it easier to do, since this is rediculous.
 */
public class MoonCowRenderer extends PetRenderer<@NotNull MoonCow, @NotNull MoonCowRenderState, @NotNull LegacyCowModel> {

    public static final ModelLayerLocation MOON_COW_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("mooncow"), "main");

    public MoonCowRenderer(EntityRendererProvider.Context context) {
        super(context, new LegacyCowModel(context.bakeLayer(MOON_COW_LOCATION)), 0.75f);
        this.addLayer(new MoonCowHelmetLayer(this, context.getBlockRenderDispatcher()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(MoonCowRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/cow/moon_cow.png");
    }

    @Override
    public MoonCowRenderState createRenderState() {
        return new MoonCowRenderState();
    }
}
