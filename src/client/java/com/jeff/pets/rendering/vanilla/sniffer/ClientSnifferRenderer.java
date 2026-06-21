package com.jeff.pets.rendering.vanilla.sniffer;

import com.jeff.pets.mob.vanilla.passive.ClientSniffer;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SnifferModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SnifferRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientSnifferRenderer extends PetRenderer<@NotNull ClientSniffer, @NotNull SnifferRenderState, @NotNull SnifferModel> {
    public static final ModelLayerLocation SNIFFER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientsniffer"), "main");
    private static final ResourceLocation snifferTexturePath = ResourceLocation.withDefaultNamespace("textures/entity/sniffer/sniffer.png");

    public ClientSnifferRenderer(EntityRendererProvider.Context context) {
        super(context, new SnifferModel(context.bakeLayer(ModelLayers.SNIFFER)), 1.1F);
    }

    @Override
    protected void scale(@NotNull SnifferRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(SnifferRenderState snifferRenderState) {
        return snifferTexturePath;
    }

    @Override
    public SnifferRenderState createRenderState() {
        return new SnifferRenderState();
    }
}
