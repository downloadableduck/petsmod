package com.jeff.pets.client.rendering.vanilla.wolf;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWolfRenderer extends PetRenderer {

    public ClientWolfRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientWolfModel(), 0.75f);
    }

    @Override
    public void preRenderCallback( final EntityLivingBase livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        String wolfTexturePath = "textures/entity/wolf/wolf.png";

        return new ResourceLocation("minecraft", wolfTexturePath);
    }

    @Override
    public void renderModel( final EntityLivingBase wolf, float f, float g, float h, float i, float j, float k) {
        super.renderModel(wolf, f, g, h, i, j, k);
        ((ClientWolf) wolf).setSitting(wolf.field_70154_o != null);
    }
}

