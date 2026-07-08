package com.jeff.pets.rendering.vanilla.creeper;

import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Central.CONFIG;

public class ClientCreeperRenderer extends PetRenderer<@NotNull ClientCreeper, @NotNull CreeperModel<ClientCreeper>> {
    public static final ModelLayerLocation CREEPER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientcreeper"), "main");

    public ClientCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperModel<>(context.bakeLayer(ModelLayers.CREEPER)), 0.75f);
        this.addLayer((RenderLayer) new CreeperPowerLayer((RenderLayerParent) this, context.getModelSet()));
    }

    public static LayerDefinition createBaseCreeperLayer() {
        CreeperModel.createBodyLayer(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 32);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientCreeper livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/creeper/creeper.png");
    }

    @Override
    public void render(ClientCreeper creeper, float f, float g, PoseStack poseStack, MultiBufferSource source, int i) {
        super.render(creeper, f, g, poseStack, source, i);
        creeper.isPowered = Objects.equals(CONFIG.creeperSkin, "charged");
    }
}
