package com.jeff.pets.vanilla.salmon;

import com.jeff.pets.vanilla.passive.ClientSalmon;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.SalmonRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class ClientSalmonRenderer extends MobRenderer<@NotNull ClientSalmon, @NotNull LivingEntityRenderState, @NotNull ClientSalmonModel> {

    public static final ModelLayerLocation SALMON_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientsalmon"), "main");
    private static final Identifier salmonTexturePath = Identifier.withDefaultNamespace("textures/entity/fish/salmon.png");

    public ClientSalmonRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientSalmonModel(context.bakeLayer(ModelLayers.SALMON)), 0.4F);
    }

    public @NotNull Identifier getTextureLocation(LivingEntityRenderState salmonRenderState) {
        return salmonTexturePath;
    }

    public SalmonRenderState createRenderState() {
        return new SalmonRenderState();
    }

    protected void setupRotations(LivingEntityRenderState salmonRenderState, @NotNull PoseStack poseStack, float f, float g) {
        super.setupRotations(salmonRenderState, poseStack, f, g);
        float h = 1.0F;
        float i = 1.0F;

        float j = h * 4.3F * Mth.sin(i * 0.6F * salmonRenderState.ageInTicks);
        poseStack.mulPose(Axis.YP.rotationDegrees(j));
    }
}
