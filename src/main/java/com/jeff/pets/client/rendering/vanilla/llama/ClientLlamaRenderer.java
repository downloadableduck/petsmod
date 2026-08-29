package com.jeff.pets.client.rendering.vanilla.llama;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientLlama;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientLlamaRenderer extends PetRenderer<ClientLlama, ClientLlamaModel> {

    public String llamaTexturePath;

    public ClientLlamaRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientLlamaModel(0), 0.75F);
    }

    @Override
    public void preRenderCallback(ClientLlama state, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
        
    }

    @Override
    public ResourceLocation getEntityTexture(ClientLlama livingEntityRenderState) {
        if (CONFIG.llamaSkin.equals("brown")) {
            llamaTexturePath = "textures/entity/llama/brown.png";
        } else if (CONFIG.llamaSkin.equals("creamy")) {
            llamaTexturePath = "textures/entity/llama/creamy.png";
        } else if (CONFIG.llamaSkin.equals("gray")) {
            llamaTexturePath = "textures/entity/llama/gray.png";
        } else if (CONFIG.llamaSkin.equals("white")) {
            llamaTexturePath = "textures/entity/llama/white.png";
        } else {
            llamaTexturePath = "textures/entity/llama/brown.png";
        }
        return new ResourceLocation("minecraft", llamaTexturePath);
    }
}
