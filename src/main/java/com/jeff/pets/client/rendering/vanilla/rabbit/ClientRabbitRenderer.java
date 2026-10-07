package com.jeff.pets.client.rendering.vanilla.rabbit;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientRabbitRenderer extends PetRenderer {
    public String rabbitTextureLocation;

    public ClientRabbitRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ClientRabbitModel(), 0.3F);
    }

    @Override
    public void preRenderCallback( final EntityLivingBase livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture( final Entity rabbitRenderState) {
        if (CONFIG.activePet.equals("brown")) {
            rabbitTextureLocation = "textures/entity/rabbit/brown.png";
        } else if (CONFIG.activePet.equals("white")) {
            rabbitTextureLocation = "textures/entity/rabbit/white.png";
        } else if (CONFIG.activePet.equals("black")) {
            rabbitTextureLocation = "textures/entity/rabbit/black.png";
        } else if (CONFIG.activePet.equals("gold")) {
            rabbitTextureLocation = "textures/entity/rabbit/gold.png";
        } else if (CONFIG.activePet.equals("salt")) {
            rabbitTextureLocation = "textures/entity/rabbit/salt.png";
        } else if (CONFIG.activePet.equals("splotched")) {
            rabbitTextureLocation = "textures/entity/rabbit/white_splotched.png";
        } else if (CONFIG.activePet.equals("killer")) {
            rabbitTextureLocation = "textures/entity/rabbit/caerbannog.png";
        } else if (CONFIG.activePet.equals("toast")) {
            rabbitTextureLocation = "textures/entity/rabbit/toast.png";
        } else {
            rabbitTextureLocation = "textures/entity/rabbit/brown.png";
        }

        return new ResourceLocation("minecraft", rabbitTextureLocation);
    }
}

