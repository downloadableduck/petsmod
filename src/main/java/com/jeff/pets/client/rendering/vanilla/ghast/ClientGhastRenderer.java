package com.jeff.pets.client.rendering.vanilla.ghast;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ClientGhastRenderer extends PetRenderer {

    public ClientGhastRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ModelGhast(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/ghast/ghast.png");
    }

    @Override
    public void preRenderCallback( final EntityLivingBase ghast, float f) {
        GL11.glScalef(4.5F, 4.5F, 4.5F);

    }
}

