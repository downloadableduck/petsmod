package com.jeff.pets.client.rendering.vanilla.zombie;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombie;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieRenderer extends PetRenderer<ClientZombie, ClientZombieModel> {

    public ClientZombieRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientZombieModel(), 0.75f);
    }

    @Override
    public void preRenderCallback(EntityLivingBase __e, float f) {
        ClientZombie livingEntityRenderState = (ClientZombie) __e;
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture(Entity __e) {
        ClientZombie livingEntityRenderState = (ClientZombie) __e;
        return new ResourceLocation("minecraft", "textures/entity/zombie/zombie.png");
    }
}
