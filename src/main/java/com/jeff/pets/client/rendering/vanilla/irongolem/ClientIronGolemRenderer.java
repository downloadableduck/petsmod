package com.jeff.pets.client.rendering.vanilla.irongolem;

import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientIronGolem;
import net.minecraft.util.ResourceLocation;

public class ClientIronGolemRenderer extends PetRenderer {

    public ClientIronGolemRenderer() {
        super(new ClientIronGolemModel(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/iron_golem.png");
    }
}

