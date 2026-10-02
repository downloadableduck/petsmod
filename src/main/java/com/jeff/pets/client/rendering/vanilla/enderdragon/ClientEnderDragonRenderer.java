package com.jeff.pets.client.rendering.vanilla.enderdragon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;


public class ClientEnderDragonRenderer extends PetRenderer<ClientEnderDragon, ClientEnderDragonModel> {

    public ClientEnderDragonRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientEnderDragonModel(0), 0.75f);
    }

    @Override
    public void preRenderCallback(EntityLivingBase __e, float f) {
        ClientEnderDragon livingEntityRenderState = (ClientEnderDragon) __e;
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.25f, 0.25f, 0.25f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture(Entity __e) {
        ClientEnderDragon livingEntityRenderState = (ClientEnderDragon) __e;
        return new ResourceLocation("minecraft", "textures/entity/enderdragon/dragon.png");
    }
}
