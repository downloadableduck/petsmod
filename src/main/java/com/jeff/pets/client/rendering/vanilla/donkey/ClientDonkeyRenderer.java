package com.jeff.pets.client.rendering.vanilla.donkey;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseModel;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDonkeyRenderer extends PetRenderer {

    public ClientDonkeyRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ClientHorseModel(0), 0.5f);
    }

    public ResourceLocation getEntityTexture( final Entity donkeyRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/horse/donkey.png");
    }

    @Override
    public void preRenderCallback( final EntityLivingBase state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

    }
}

