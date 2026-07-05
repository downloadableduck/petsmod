package com.jeff.pets.client.rendering.vanilla.llama;

import com.jeff.pets.mob.vanilla.neutral.ClientLlama;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientLlamaRenderer extends PetRenderer<@NotNull ClientLlama, @NotNull ClientLlamaModel> {

    public static final ModelLayerLocation LLAMA_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientllama"), "main");
    public String llamaTexturePath;

    public ClientLlamaRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientLlamaModel(context.bakeLayer(ModelLayers.LLAMA)), 0.75F);
    }

    public static LayerDefinition createLlamaLayer() {
        ClientLlamaModel.createBodyLayer(CubeDeformation.NONE);

        return LayerDefinition.create(new MeshDefinition(), 128, 64);
    }

    @Override
    protected void scale(ClientLlama state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientLlama livingEntityRenderState) {
        switch (CONFIG.llamaSkin) {
            case "brown" -> llamaTexturePath = "textures/entity/llama/brown.png";
            case "creamy" -> llamaTexturePath = "textures/entity/llama/creamy.png";
            case "gray" -> llamaTexturePath = "textures/entity/llama/gray.png";
            case "white" -> llamaTexturePath = "textures/entity/llama/white.png";
            default -> llamaTexturePath = "textures/entity/llama/brown.png";
        }
        return new ResourceLocation("minecraft", llamaTexturePath);
    }
}
