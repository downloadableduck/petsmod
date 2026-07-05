package com.jeff.pets.client.rendering.vanilla.husk;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.zombie.ClientZombieModel;
import com.jeff.pets.mob.vanilla.hostile.ClientHusk;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHuskRenderer extends PetRenderer<@NotNull ClientHusk, @NotNull ClientZombieModel<ClientHusk>> {

    public static final ModelLayerLocation HUSK_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clienthusk"), "main");

    public ClientHuskRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientZombieModel<>(context.bakeLayer(ModelLayers.HUSK)), 0.75F);
    }

    @Override
    protected void scale(ClientHusk state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientHusk livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/zombie/husk.png");
    }

    @Override
    public void setupRotations(ClientHusk husk, @NotNull PoseStack poseStack, float f, float g, float i, float j) {
        super.setupRotations(husk, poseStack, f, g, i, j);
        if (husk.isPassenger()) {
            poseStack.translate(0, -0.5, 0);
        }
    }
}
