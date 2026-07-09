package com.jeff.pets.client.rendering.aprilfools.mooncow;

import com.jeff.pets.mob.aprilfools.MoonCow;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


/**
 * Alright I give up. I'll try and add the glass helmet on the moon cow back if and when Minecraft
 * makes it easier to do, since this is rediculous.
 */
public class MoonCowRenderer extends PetRenderer< MoonCow,  LegacyCowModel> {

    public static final ModelLayerLocation MOON_COW_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "mooncow"), "main");

    public MoonCowRenderer(EntityRendererProvider.Context context) {
        super(context, new LegacyCowModel(context.bakeLayer(MOON_COW_LOCATION)), 0.75f);
        this.addLayer(new MoonCowHelmetLayer(this, Minecraft.getInstance().getBlockRenderer()));
    }

    @Override
    public  ResourceLocation getTextureLocation(MoonCow livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/cow/moon_cow.png");
    }
}
