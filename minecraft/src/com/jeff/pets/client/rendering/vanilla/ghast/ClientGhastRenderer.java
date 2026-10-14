package com.jeff.pets.client.rendering.vanilla.ghast;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelGhast;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

public class ClientGhastRenderer extends PetRenderer {

    public ClientGhastRenderer() {
        super(new ModelGhast(), 0.75f);
    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/ghast/ghast.png";
    }

    @Override
    public void preRenderCallback( final EntityLiving ghast, float f) {
        GL11.glScalef(4.5F, 4.5F, 4.5F);

    }
}

