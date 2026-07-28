package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenRenderer extends PetRenderer<ClientChicken, ClientChickenModel<ClientChicken>> {

    public ClientChickenRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientChickenModel<>(), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientChicken livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/chicken.png");
    }

    @Override
    protected void scale(ClientChicken state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }
}
