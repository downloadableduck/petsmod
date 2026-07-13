package com.jeff.pets.client.rendering.vanilla.zombie;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombie;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieRenderer extends PetRenderer<ClientZombie, ClientZombieModel<ClientZombie>> {

    public ClientZombieRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new ClientZombieModel(0), 0.75f);
    }

    @Override
    protected void scale(ClientZombie livingEntityRenderState, com.mojang.blaze3d.matrix.MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientZombie livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/zombie/zombie.png");
    }
}
