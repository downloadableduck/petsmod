package com.jeff.pets.client.rendering.vanilla.pig;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigRenderer extends PetRenderer {
    public String pigTexturePath;

    public ClientPigRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ClientPigModel(), 0.7F);
    }

    @Override
    public void preRenderCallback( final EntityLivingBase livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

    }

    public ResourceLocation getEntityTexture( final Entity pigRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/pig/pig.png");
    }
}

