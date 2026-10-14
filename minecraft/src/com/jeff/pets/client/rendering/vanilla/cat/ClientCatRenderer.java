package com.jeff.pets.client.rendering.vanilla.cat;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;
import com.jeff.pets.mob.vanilla.passive.ClientCat;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCatRenderer extends PetRenderer {

    public ClientCatRenderer() {
        super(new ClientCatModel(), 0.7F);
    }

    @Override
    public void preRenderCallback( final EntityLiving state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        switch (CONFIG.catSkin) {
            case "black":
                return "textures/entity/cat/all_black.png";
            case "tuxedo":
                return "textures/entity/cat/black.png";
            case "british_shorthair":
                return "textures/entity/cat/british_shorthair.png";
            case "calico":
                return "textures/entity/cat/calico.png";
            case "jellie":
                return "textures/entity/cat/jellie.png";
            case "ocelot":
                return "textures/entity/cat/ocelot.png";
            case "persian":
                return "textures/entity/cat/persian.png";
            case "ragdoll":
                return "textures/entity/cat/ragdoll.png";
            case "red":
                return "textures/entity/cat/red.png";
            case "siamese":
                return "textures/entity/cat/siamese.png";
            case "tabby":
                return "textures/entity/cat/tabby.png";
            case "white":
                return "textures/entity/cat/white.png";
            default:
                return "textures/entity/cat/black.png";
        }
    }

    @Override
    public void renderModel( final EntityLiving cat, float f, float g, float h, float i, float j, float k) {
        super.renderModel(cat, f, g, h, i, j, k);
        ((ClientCat) cat).setSitting(cat.ridingEntity != null);
    }
}

