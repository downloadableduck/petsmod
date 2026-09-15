package com.jeff.pets.client.rendering.vanilla.cow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCow;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowRenderer extends PetRenderer {

    public ClientCowRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientCowModel(), 0.7F);
    }

    public ResourceLocation getEntityTexture( final Entity LivingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/cow/cow.png");
    }

    @Override
    public void preRenderCallback( final EntityLivingBase state, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }
}

