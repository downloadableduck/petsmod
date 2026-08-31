package com.jeff.pets.client.rendering.vanilla.zombie;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombie;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieRenderer extends PetRenderer<ClientZombie, ClientZombieModel> {

    public ClientZombieRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientZombieModel(), 0.75f);
    }

    @Override
    public void preRenderCallback(ClientZombie livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
        
    }

    @Override
    public ResourceLocation getEntityTexture(ClientZombie livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/zombie/zombie.png");
    }
}
