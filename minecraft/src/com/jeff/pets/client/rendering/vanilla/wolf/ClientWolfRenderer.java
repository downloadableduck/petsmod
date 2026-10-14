package com.jeff.pets.client.rendering.vanilla.wolf;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWolfRenderer extends PetRenderer {

    public ClientWolfRenderer() {
        super(new ClientWolfModel(), 0.75f);
    }

    @Override
    public void preRenderCallback( final EntityLiving livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        String wolfTexturePath = "textures/entity/wolf/wolf.png";

        return wolfTexturePath;
    }

    @Override
    public void renderModel( final EntityLiving wolf, float f, float g, float h, float i, float j, float k) {
        super.renderModel(wolf, f, g, h, i, j, k);
        ((ClientWolf) wolf).setSitting(wolf.ridingEntity != null);
    }
}

