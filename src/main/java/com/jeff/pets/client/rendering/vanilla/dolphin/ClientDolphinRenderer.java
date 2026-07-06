package com.jeff.pets.client.rendering.vanilla.dolphin;

import com.jeff.pets.mob.vanilla.neutral.ClientDolphin;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.DolphinModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDolphinRenderer extends PetRenderer<@NotNull ClientDolphin, @NotNull DolphinModel<ClientDolphin>> {
    public static final ModelLayerLocation DOLPHIN_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientdolphin"), "main");

    public ClientDolphinRenderer(EntityRendererProvider.Context context) {
        super(context, new DolphinModel(context.bakeLayer(ModelLayers.DOLPHIN)), 0.7f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientDolphin dolphinRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/dolphin.png");
    }

    @Override
    protected void scale(ClientDolphin state, @NotNull PoseStack poseStack, float i) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }
}
