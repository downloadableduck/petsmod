package com.jeff.pets.client.rendering.vanilla.donkey;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseModel;
import com.jeff.pets.mob.vanilla.passive.ClientDonkey;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDonkeyRenderer extends PetRenderer<ClientDonkey, ClientHorseModel> {

    public ClientDonkeyRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientHorseModel(0), 0.5f);
    }

    public ResourceLocation getEntityTexture(Entity __e) {
        ClientDonkey donkeyRenderState = (ClientDonkey) __e;
        return new ResourceLocation("minecraft", "textures/entity/horse/donkey.png");
    }

    @Override
    public void preRenderCallback(EntityLivingBase __e, float f) {
        ClientDonkey state = (ClientDonkey) __e;
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }

    }
}
