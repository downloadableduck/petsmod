package com.jeff.pets.client.rendering.vanilla.enderdragon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;


public class ClientEnderDragonRenderer extends PetRenderer {

    public ClientEnderDragonRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientEnderDragonModel(0), 0.75f);
    }

    @Override
    public void preRenderCallback( final EntityLivingBase livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.25f, 0.25f, 0.25f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/enderdragon/dragon.png");
    }
}

