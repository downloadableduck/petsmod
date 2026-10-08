package com.jeff.pets.client.rendering.vanilla.shulker;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientShulker;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientShulkerRenderer extends PetRenderer {

    public ClientShulkerRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientShulkerModel(), 0.75f);
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        ClientShulker livingEntityRenderState = (ClientShulker) __e;
        return new ResourceLocation("minecraft", "textures/entity/shulker/shulker_purple.png");
    }

    public void renderModel(ClientShulker shulker, float f, float g, float h, float i, float j, float k) {
        super.renderModel(shulker, f, g, h, i, j, k);
    }
}