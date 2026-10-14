package com.jeff.pets.client.rendering.vanilla.enderdragon;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;


public class ClientEnderDragonRenderer extends PetRenderer {

    public ClientEnderDragonRenderer() {
        super(new ClientEnderDragonModel(0), 0.75f);
    }

    @Override
    public void preRenderCallback( final EntityLiving livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.25f, 0.25f, 0.25f);
        }

    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/enderdragon/dragon.png";
    }
}

