package com.jeff.pets.client.rendering.vanilla.chicken;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.MathHelper;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenRenderer extends PetRenderer {

    public ClientChickenRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientChickenModel(), 0.3F);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/chicken.png");
    }

    @Override
    public void preRenderCallback( final EntityLivingBase state, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    protected float handleRotationFloat(EntityLivingBase livingBase, float partialTicks) {
        float f = (livingBase.ticksExisted + partialTicks) * 0.4F;
        return (MathHelper.sin(f) + 1.0F) * 0.2f;
    }
}

