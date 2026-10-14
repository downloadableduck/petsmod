package com.jeff.pets.client.rendering.vanilla.bat;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

public class ClientBatRenderer extends PetRenderer {

    public ClientBatRenderer() {
        super(new ClientBatModel(), 0.25F);
    }

    @Override
    public void preRenderCallback( final EntityLiving bat, float f) {
        super.preRenderCallback(bat, f);
        GL11.glScalef(0.35F, 0.35F, 0.35F);
    }

    @Override
    public String getEntityTexture( final Entity batRenderState) {
        return "textures/entity/bat.png";
    }
}

