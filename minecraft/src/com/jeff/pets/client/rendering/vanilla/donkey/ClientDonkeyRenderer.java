package com.jeff.pets.client.rendering.vanilla.donkey;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseModel;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDonkeyRenderer extends PetRenderer {

    public ClientDonkeyRenderer() {
        super(new ClientHorseModel(0), 0.5f);
    }

    public String getEntityTexture( final Entity donkeyRenderState) {
        return "textures/entity/horse/donkey.png";
    }

    @Override
    public void preRenderCallback( final EntityLiving state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

    }
}

