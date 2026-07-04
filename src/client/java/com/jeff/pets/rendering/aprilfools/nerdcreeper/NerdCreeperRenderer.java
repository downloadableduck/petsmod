package com.jeff.pets.rendering.aprilfools.nerdcreeper;

import com.jeff.pets.mob.aprilfools.NerdCreeper;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Central.CONFIG;

public class NerdCreeperRenderer extends PetRenderer<@NotNull NerdCreeper, @NotNull CreeperModel<NerdCreeper>> {

    public static final ModelLayerLocation NERD_CREEPER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "nerdcreeper"), "main");

    public NerdCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperModel<>(context.bakeLayer(ModelLayers.CREEPER)), 0.75f);
        this.addLayer(new NerdCreeperNerdLayer(this, context));
        this.addLayer((RenderLayer) new CreeperPowerLayer((RenderLayerParent) this, context.getModelSet()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(NerdCreeper livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/creeper/creeper.png");
    }

    @Override
    public void render(NerdCreeper nerdCreeper, float f, float g, PoseStack poseStack, MultiBufferSource bufferSource, int i) {
        super.render(nerdCreeper, f, g, poseStack, bufferSource, i);
        if (Objects.equals(CONFIG.creeperSkin, "charged")) {
            nerdCreeper.isPowered = true;
        } else {
            nerdCreeper.isPowered = false;
        }
    }
}
