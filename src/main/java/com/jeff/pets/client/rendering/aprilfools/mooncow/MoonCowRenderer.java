package com.jeff.pets.rendering.aprilfools.mooncow;

import com.jeff.pets.mob.aprilfools.MoonCow;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

/**
 * Alright I give up. I'll try and add the glass helmet on the moon cow back if and when Minecraft
 * makes it easier to do, since this is rediculous.
 */
public class MoonCowRenderer extends PetRenderer<@NotNull MoonCow, @NotNull MoonCowRenderState, @NotNull LegacyCowModel> {

    public static final ModelLayerLocation MOON_COW_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("mooncow"), "main");
    private final BlockModelResolver resolver;

    public MoonCowRenderer(EntityRendererProvider.Context context) {
        super(context, new LegacyCowModel(context.bakeLayer(MOON_COW_LOCATION)), 0.75f);
        this.resolver = context.getBlockModelResolver();
        this.addLayer(new MoonCowHelmetLayer(this));
    }

    @Override
    public @NotNull Identifier getTextureLocation(MoonCowRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/cow/moon_cow.png");
    }

    @Override
    public MoonCowRenderState createRenderState() {
        return new MoonCowRenderState();
    }

    @Override
    public void extractRenderState(MoonCow cow, MoonCowRenderState state, float f) {
        super.extractRenderState(cow, state, f);
        //state.blockOnHead.submit(poseStack, Minecraft.getInstance().gameRenderer.getSubmitNodeStorage(), state.lightCoords, LivingEntityRenderer.getOverlayCoords(state, 0), state.outlineColor);
        this.resolver.update(state.blockOnHead, Blocks.GLASS.defaultBlockState(), BlockDisplayContext.create());
    }
}
