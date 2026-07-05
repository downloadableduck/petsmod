package com.jeff.pets.client.rendering.vanilla.rabbit;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientRabbit;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientRabbitRenderer extends PetRenderer<@NotNull ClientRabbit, @NotNull ClientRabbitModel> {
    public static final ModelLayerLocation RABBIT_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientrabbit"), "main");
    public String rabbitTextureLocation;

    public ClientRabbitRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientRabbitModel(context.bakeLayer(ModelLayers.RABBIT)), 0.3F);
    }

    public static LayerDefinition createBaseRabbitLayer() {
        ClientRabbitModel.createBodyLayer();
        return LayerDefinition.create(new MeshDefinition(), 64, 32);
    }

    @Override
    protected void scale(@NotNull ClientRabbit livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientRabbit rabbitRenderState) {
        switch (CONFIG.activePet) {
            case "brown" -> rabbitTextureLocation = "textures/entity/rabbit/brown.png";
            case "white" -> rabbitTextureLocation = "textures/entity/rabbit/white.png";
            case "black" -> rabbitTextureLocation = "textures/entity/rabbit/black.png";
            case "gold" -> rabbitTextureLocation = "textures/entity/rabbit/gold.png";
            case "salt" -> rabbitTextureLocation = "textures/entity/rabbit/salt.png";
            case "splotched" -> rabbitTextureLocation = "textures/entity/rabbit/white_splotched.png";
            case "killer" -> rabbitTextureLocation = "textures/entity/rabbit/caerbannog.png";
            case "toast" -> rabbitTextureLocation = "textures/entity/rabbit/toast.png";
            default -> rabbitTextureLocation = "textures/entity/rabbit/brown.png";
        }

        return new ResourceLocation("minecraft", rabbitTextureLocation);
    }
}
