package com.jeff.pets.client.rendering.vanilla.chicken;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.MathHelper;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenRenderer extends PetRenderer<ClientChicken, ClientChickenModel> {

    public ClientChickenRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientChickenModel(), 0.3F);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity __e) {
        ClientChicken livingEntityRenderState = (ClientChicken) __e;
        return new ResourceLocation("minecraft", "textures/entity/chicken.png");
    }

    @Override
    public void preRenderCallback(EntityLivingBase __e, float f) {
        ClientChicken state = (ClientChicken) __e;
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    protected float handleRotationFloat(EntityLivingBase __e, float partialTicks) {
        ClientChicken livingBase = (ClientChicken) __e;
        float f = (livingBase.ticksExisted + partialTicks) * 0.4F;
        return (MathHelper.sin(f) + 1.0F) * 0.2f;
    }
}
