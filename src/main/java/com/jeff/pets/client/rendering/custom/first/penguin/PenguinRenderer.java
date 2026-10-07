package com.jeff.pets.client.rendering.custom.first.penguin;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class PenguinRenderer extends PetRenderer {

    public PenguinRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new PenguinModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation(Central.MOD_ID, "textures/entity/penguin/penguin.png");
    }

    @Override
    public void preRenderCallback( final EntityLivingBase livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

    }
}

