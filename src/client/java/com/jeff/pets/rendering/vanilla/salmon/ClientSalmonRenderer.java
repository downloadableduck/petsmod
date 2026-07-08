package com.jeff.pets.rendering.vanilla.salmon;

import com.jeff.pets.mob.vanilla.passive.ClientSalmon;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class ClientSalmonRenderer extends PetRenderer<@NotNull ClientSalmon, @NotNull ClientSalmonModel> {

    public static final ModelLayerLocation SALMON_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientsalmon"), "main");

    public ClientSalmonRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientSalmonModel(context.bakeLayer(ModelLayers.SALMON)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientSalmon salmonRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/fish/salmon.png");
    }

    @Override
    protected void setupRotations(ClientSalmon salmonRenderState, @NotNull PoseStack poseStack, float ageInTicks, float g, float a) {
        super.setupRotations(salmonRenderState, poseStack, ageInTicks, g, a);
        float h = 1.0F;
        float i = 1.0F;

        float j = h * 4.3F * Mth.sin(i * 0.6F * ageInTicks);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(j));
    }
}
