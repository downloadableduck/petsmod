package com.jeff.pets.client.rendering.vanilla.llama;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientLlama;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientLlamaRenderer extends PetRenderer<@NotNull ClientLlama> {

    public ClientLlamaRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientLlamaModel(0), 0.7F);
    }

    @Override
    protected void scale(ClientLlama state, float f) {
        super.scale(state, f);
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(0.85f, 0.85f, 0.85f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientLlama livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/llama/creamy.png");
    }
}