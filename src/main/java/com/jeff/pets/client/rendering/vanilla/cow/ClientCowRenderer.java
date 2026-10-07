package com.jeff.pets.client.rendering.vanilla.cow;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowRenderer extends PetRenderer {

    public ClientCowRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ClientCowModel(), 0.7F);
    }

    public ResourceLocation getEntityTexture( final Entity LivingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/cow/cow.png");
    }

    @Override
    public void preRenderCallback( final EntityLivingBase state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }
}

