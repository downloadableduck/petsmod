package com.jeff.pets.client.rendering.vanilla.bat;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ClientBatRenderer extends PetRenderer {

    public ClientBatRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ClientBatModel(), 0.25F);
    }

    @Override
    public void preRenderCallback( final EntityLivingBase bat, float f) {
        super.preRenderCallback(bat, f);
        GL11.glScalef(0.35F, 0.35F, 0.35F);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity batRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/bat.png");
    }
}

