package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCat extends GroundPet {

    public boolean isOnHead;

    public ClientCat(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.6f, 0.7f);
    }

    @Override
    public String getTexture() {
        switch (CONFIG.catSkin) {
            case "black":
                return "textures/mob/cat/all_black.png";
            case "tuxedo":
                return "textures/mob/cat/black.png";
            case "british_shorthair":
                return "textures/mob/cat/british_shorthair.png";
            case "calico":
                return "textures/mob/cat/calico.png";
            case "jellie":
                return "textures/mob/cat/jellie.png";
            case "ocelot":
                return "textures/mob/cat/ocelot.png";
            case "persian":
                return "textures/mob/cat/persian.png";
            case "ragdoll":
                return "textures/mob/cat/ragdoll.png";
            case "red":
                return "textures/mob/cat/red.png";
            case "siamese":
                return "textures/mob/cat/siamese.png";
            case "tabby":
                return "textures/mob/cat/tabby.png";
            case "white":
                return "textures/mob/cat/white.png";
            default:
                return "textures/mob/cat/black.png";
        }
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 0.5f;
    }

    @Override
    protected String getAmbientSound() {
        return "mob.cat.meow";
    }
}
