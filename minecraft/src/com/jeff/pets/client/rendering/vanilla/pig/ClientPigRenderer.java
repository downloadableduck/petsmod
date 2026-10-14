package com.jeff.pets.client.rendering.vanilla.pig;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigRenderer extends PetRenderer {
    public String pigTexturePath;

    public ClientPigRenderer() {
        super(new ClientPigModel(), 0.7F);
    }

    @Override
    public void preRenderCallback( final EntityLiving livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

    }

    public String getEntityTexture( final Entity pigRenderState) {
        return "textures/entity/pig/pig.png";
    }
}

