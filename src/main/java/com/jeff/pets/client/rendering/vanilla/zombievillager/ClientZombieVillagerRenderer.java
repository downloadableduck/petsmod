package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerRenderer extends PetRenderer<ClientZombieVillager, ClientZombieVillagerModel> {

    public ClientZombieVillagerRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientZombieVillagerModel(0, false), 0.75f);
        this.addLayer(new ClientZombieVillagerProfessionLayer(this));
    }

    @Override
    protected void scale(ClientZombieVillager livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientZombieVillager livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/zombie_villager/zombie_villager.png");
    }

    @Override
    public void setupRotations(ClientZombieVillager state, float f, float g, float h) {
        super.setupRotations(state, f, g, h);
        if (state.isPassenger()) {
            com.mojang.blaze3d.platform.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
