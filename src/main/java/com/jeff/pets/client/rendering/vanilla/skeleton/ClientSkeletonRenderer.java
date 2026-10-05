package com.jeff.pets.client.rendering.vanilla.skeleton;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSkeleton;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.CoreModManager;
import org.lwjgl.opengl.GL11;

public class ClientSkeletonRenderer extends PetRenderer {

    public ClientSkeletonRenderer() {
        super(new ModelSkeleton(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/skeleton/skeleton.png");
    }

    @Override
    public void rotateCorpse(EntityLivingBase state, float f, float g, float h) {
        super.rotateCorpse(state, f, g, h);
        if (state.ridingEntity != null) {
            GL11.glTranslatef(0, -0.5f, 0);
        }
    }
}

