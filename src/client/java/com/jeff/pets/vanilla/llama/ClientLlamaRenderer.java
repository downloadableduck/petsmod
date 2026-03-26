package com.jeff.pets.vanilla.llama;

import com.jeff.pets.vanilla.neutral.ClientLlama;
import net.minecraft.client.model.animal.llama.LlamaModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LlamaRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Pet.CONFIG;

public class ClientLlamaRenderer extends MobRenderer<@NotNull ClientLlama, @NotNull LlamaRenderState, @NotNull LlamaModel> {

    public static final ModelLayerLocation LLAMA_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientllama"), "main");
    public String llamaTexturePath;

    public ClientLlamaRenderer(EntityRendererProvider.Context context) {
        super(context, new LlamaModel(context.bakeLayer(ModelLayers.LLAMA)), 0.75F);
    }

    public static LayerDefinition createLlamaLayer() {
        LlamaModel.createBodyLayer(CubeDeformation.NONE);

        return LayerDefinition.create(new MeshDefinition(), 128, 64);
    }

    @Override
    public @NotNull Identifier getTextureLocation(LlamaRenderState livingEntityRenderState) {
        switch (CONFIG.llamaSkin) {
            case "brown" -> llamaTexturePath = "textures/entity/llama/brown.png";
            case "creamy" -> llamaTexturePath = "textures/entity/llama/creamy.png";
            case "gray" -> llamaTexturePath = "textures/entity/llama/gray.png";
            case "white" -> llamaTexturePath = "textures/entity/llama/white.png";
            case null, default -> llamaTexturePath = "textures/entity/llama/brown.png";
        }
        return Identifier.withDefaultNamespace(llamaTexturePath);
    }

    @Override
    public LlamaRenderState createRenderState() {
        return new LlamaRenderState();
    }
}
