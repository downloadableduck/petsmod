package com.jeff.pets.client.rendering.vanilla.enderdragon;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;


public class ClientEnderDragonRenderer extends PetRenderer {

    public ClientEnderDragonRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ClientEnderDragonModel(0), 0.75f);
    }

    @Override
    public void preRenderCallback( final EntityLivingBase livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.25f, 0.25f, 0.25f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/enderdragon/dragon.png");
    }
}

