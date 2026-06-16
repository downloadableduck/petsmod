package com.jeff.pets.client.rendering.aprilfools.mooncow;

import com.jeff.pets.mob.aprilfools.MoonCow;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SnowGolemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

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

    @Override
    public void extractRenderState(MoonCow cow, MoonCowRenderState state, float f) {
        super.extractRenderState(cow, state, f);
        //state.blockOnHead.submit(poseStack, Minecraft.getInstance().gameRenderer.getSubmitNodeStorage(), state.lightCoords, LivingEntityRenderer.getOverlayCoords(state, 0), state.outlineColor);
    }
}
