package com.jeff.pets.client.rendering.vanilla.wolf;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWolfRenderer extends PetRenderer<ClientWolf, ClientWolfModel> {

    public ClientWolfRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientWolfModel(), 0.75f);
    }

    @Override
    public void preRenderCallback(EntityLivingBase __e, float f) {
        ClientWolf livingEntityRenderState = (ClientWolf) __e;
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture(Entity __e) {
        ClientWolf livingEntityRenderState = (ClientWolf) __e;
        String wolfTexturePath = "textures/entity/wolf/wolf.png";

        return new ResourceLocation("minecraft", wolfTexturePath);
    }

    @Override
    public void renderModel(EntityLivingBase __e, float f, float g, float h, float i, float j, float k) {
        ClientWolf wolf = (ClientWolf) __e;
        super.renderModel(wolf, f, g, h, i, j, k);
        wolf.setSitting(wolf.field_70153_n != null);
    }
}
