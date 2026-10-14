package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import net.minecraft.src.Entity;
import com.jeff.pets.client.rendering.PetRenderer;

public class  ClientZOmbiePigmanRenderer extends PetRenderer {
    public ClientZOmbiePigmanRenderer() {
        super(new ClientZombiePigmanModel(), 0.75f);
    }

    @Override
    protected String getEntityTexture( final Entity p_110775_1_) {
        return "textures/entity/zombie_pigman.png";
    }
}

