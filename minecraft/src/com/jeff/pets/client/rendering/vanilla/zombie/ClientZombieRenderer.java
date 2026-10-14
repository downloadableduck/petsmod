package com.jeff.pets.client.rendering.vanilla.zombie;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieRenderer extends PetRenderer {

    public ClientZombieRenderer() {
        super(new ClientZombieModel(), 0.75f);
    }

    @Override
    public void preRenderCallback( final EntityLiving livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/zombie/zombie.png";
    }
}

