package com.jeff.pets.client.rendering.vanilla.llama;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientLlama;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientLlamaRenderer extends PetRenderer<@NotNull ClientLlama, @NotNull ClientLlamaModel> {

    public String llamaTexturePath;

    public ClientLlamaRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientLlamaModel(0), 0.75F);
    }

    @Override
    protected void scale(ClientLlama state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientLlama livingEntityRenderState) {
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
        return new Identifier("minecraft", llamaTexturePath);
    }
}
