package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientZombiePigman;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class ClientZombiePigmanRenderer extends PetRenderer {

    public ClientZombiePigmanRenderer(RenderManager context, PetsClientInitializer.Context context2) {
        super(context, new ClientZombiePigmanModel(), 0.5f);
    }

    public ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        ClientZombiePigman entity = (ClientZombiePigman) __e;
        return new ResourceLocation("minecraft", "textures/entity/zombie_pigman.png");
    }
}