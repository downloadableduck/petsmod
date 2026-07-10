package com.jeff.pets.client.rendering.vanilla.creeper;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperRenderer extends PetRenderer<@NotNull ClientCreeper, @NotNull CreeperModel<ClientCreeper>> {

    public ClientCreeperRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new CreeperModel<>(), 0.75f);
        this.addLayer((RenderLayer) new CreeperPowerLayer((RenderLayerParent) this));
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
