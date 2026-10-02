package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientZombiePigman;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class ClientZOmbiePigmanRenderer extends PetRenderer<ClientZombiePigman, ClientZombiePigmanModel> {
    public ClientZOmbiePigmanRenderer(RenderManager context, PetsClientInitializer.Context context2) {
        super(context, new ClientZombiePigmanModel(), 0.75f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Entity __e) {
        ClientZombiePigman p_110775_1_ = (ClientZombiePigman) __e;
        return new ResourceLocation("minecraft", "textures/entity/zombie_pigman.png");
    }
}
