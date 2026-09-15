package com.jeff.pets.client.rendering.vanilla.bat;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientBat;
import net.minecraft.util.ResourceLocation;

public class ClientBatRenderer extends PetRenderer {

    public ClientBatRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientBatModel(), 0.25F);
    }

    @Override
    public void preRenderCallback( final EntityLivingBase bat, float f) {
        super.preRenderCallback(bat, f);
        net.minecraft.client.renderer.GlStateManager.scalef(0.35F, 0.35F, 0.35F);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity batRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/bat.png");
    }
}

