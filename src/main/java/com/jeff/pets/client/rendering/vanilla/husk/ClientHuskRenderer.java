package com.jeff.pets.client.rendering.vanilla.husk;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.zombie.ClientZombieModel;
import com.jeff.pets.mob.vanilla.hostile.ClientHusk;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHuskRenderer extends PetRenderer<ClientHusk, ClientZombieModel> {

    public ClientHuskRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientZombieModel(), 0.75F);
    }

    @Override
    public void preRenderCallback(ClientHusk state, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture(ClientHusk livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/zombie/husk.png");
    }

    @Override
    public void applyRotations(ClientHusk husk, float f, float g, float i) {
        super.applyRotations(husk, f, g, i);
        if (husk.isPassenger()) {
            net.minecraft.client.renderer.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
