package com.jeff.pets.client.rendering.custom.first.penguin;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Penguin;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class PenguinRenderer extends PetRenderer {

    public PenguinRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new PenguinModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation(Central.MOD_ID, "textures/entity/penguin/penguin.png");
    }

    @Override
    public void preRenderCallback( final EntityLivingBase livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }

    }
}

