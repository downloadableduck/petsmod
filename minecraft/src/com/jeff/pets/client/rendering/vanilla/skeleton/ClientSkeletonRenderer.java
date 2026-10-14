package com.jeff.pets.client.rendering.vanilla.skeleton;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

public class ClientSkeletonRenderer extends PetRenderer {

    public ClientSkeletonRenderer() {
        super(new ModelSkeleton(), 0.75f);
    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/skeleton/skeleton.png";
    }

    @Override
    public void rotateCorpse(EntityLiving state, float f, float g, float h) {
        super.rotateCorpse(state, f, g, h);
        if (state.ridingEntity != null) {
            GL11.glTranslatef(0, -0.5f, 0);
        }
    }
}

