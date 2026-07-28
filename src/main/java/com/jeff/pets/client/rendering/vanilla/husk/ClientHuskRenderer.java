package com.jeff.pets.client.rendering.vanilla.husk;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.zombie.ClientZombieModel;
import com.jeff.pets.mob.vanilla.hostile.ClientHusk;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHuskRenderer extends PetRenderer<ClientHusk, ClientZombieModel<ClientHusk>> {

    public ClientHuskRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientZombieModel<>(), 0.75F);
    }

    @Override
    protected void scale(ClientHusk state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientHusk livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/zombie/husk.png");
    }

    @Override
    public void setupRotations(ClientHusk husk, float f, float g, float i) {
        super.setupRotations(husk, f, g, i);
        if (husk.isPassenger()) {
            com.mojang.blaze3d.platform.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
