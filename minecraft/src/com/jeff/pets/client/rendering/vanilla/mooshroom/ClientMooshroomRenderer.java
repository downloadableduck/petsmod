package com.jeff.pets.client.rendering.vanilla.mooshroom;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMooshroomRenderer extends PetRenderer {

    String mooshroomTexturePath;

    public ClientMooshroomRenderer() {
        super(new ClientCowModel(), 0.7F);
        this.setPetLayer(new ClientMushroomCowMushroomLayer(this));
    }

    @Override
    public void preRenderCallback( final EntityLiving state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public String getEntityTexture( final Entity cowRenderState) {
        mooshroomTexturePath = "textures/entity/cow/mooshroom.png";
        return mooshroomTexturePath;
    }
}

