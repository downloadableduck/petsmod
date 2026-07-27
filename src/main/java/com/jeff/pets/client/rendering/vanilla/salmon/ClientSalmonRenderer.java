package com.jeff.pets.client.rendering.vanilla.salmon;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSalmon;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.SalmonModel;
import net.minecraft.util.ResourceLocation;

public class ClientSalmonRenderer extends PetRenderer<ClientSalmon, SalmonModel<ClientSalmon>> {

    public ClientSalmonRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SalmonModel<>(), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientSalmon salmonRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/fish/salmon.png");
    }

    @Override
    protected void setupRotations(ClientSalmon salmonRenderState, com.mojang.blaze3d.matrix.MatrixStack poseStack, float ageInTicks, float g, float a) {
        super.setupRotations(salmonRenderState, poseStack, ageInTicks, g, a);
        float h = 1.0F;
        float i = 1.0F;

        float j = h * 4.3F * net.minecraft.util.math.MathHelper.sin(i * 0.6F * ageInTicks);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(j));
    }
}
