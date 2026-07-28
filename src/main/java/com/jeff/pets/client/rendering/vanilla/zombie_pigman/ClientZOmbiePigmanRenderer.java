package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientZombiePigman;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class ClientZOmbiePigmanRenderer extends PetRenderer<ClientZombiePigman, ClientZombiePigmanModel> {
    public ClientZOmbiePigmanRenderer(EntityRendererManager context, PetsClientInitializer.Context context2) {
        super(context, new ClientZombiePigmanModel(), 0.75f);
    }

    @Nullable
    @Override
    protected ResourceLocation getTextureLocation(ClientZombiePigman p_110775_1_) {
        return new ResourceLocation("minecraft", "textures/entity/zombie_pigman.png");
    }
}
