package com.jeff.pets.rendering.aprilfools.smilingcreeper;

import com.jeff.pets.mob.aprilfools.SmilingCreeper;
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

public class SmilingCreeperRenderer extends PetRenderer<@NotNull SmilingCreeper, @NotNull CreeperModel<SmilingCreeper>> {

    public static final ModelLayerLocation SMILING_CREEPER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "smilingcreeper"), "main");

    public SmilingCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperModel<>(context.bakeLayer(ModelLayers.CREEPER)), 0.75f);
        this.addLayer((RenderLayer) new CreeperPowerLayer((RenderLayerParent) this, context.getModelSet()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(SmilingCreeper livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/creeper/smiling_creeper.png");
    }

    @Override
    public void render(SmilingCreeper smilingCreeper, float f, float g, PoseStack poseStack, MultiBufferSource bufferSource, int i) {
        super.render(smilingCreeper, f, g, poseStack, bufferSource, i);
        if (Objects.equals(CONFIG.creeperSkin, "charged")) {
            smilingCreeper.isPowered = true;
        } else {
            smilingCreeper.isPowered = false;
        }
    }
}
