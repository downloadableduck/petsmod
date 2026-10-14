package com.jeff.pets.client.rendering.vanilla.cow;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowRenderer extends PetRenderer {

    public ClientCowRenderer() {
        super(new ClientCowModel(), 0.7F);
    }

    public String getEntityTexture( final Entity LivingEntityRenderState) {
        return "textures/entity/cow/cow.png";
    }

    @Override
    public void preRenderCallback( final EntityLiving state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }
}

