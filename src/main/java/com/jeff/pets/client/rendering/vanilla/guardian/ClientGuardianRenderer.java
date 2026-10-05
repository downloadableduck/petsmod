package com.jeff.pets.client.rendering.vanilla.guardian;

import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGuardian;
import net.minecraft.util.ResourceLocation;


public class ClientGuardianRenderer extends PetRenderer {

    public ClientGuardianRenderer() {
        super(new ClientGuardianModel(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/guardian.png");
    }
}

