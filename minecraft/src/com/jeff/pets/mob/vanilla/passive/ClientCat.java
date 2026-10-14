package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCat extends GroundPet {

    public boolean isOnHead;

    public ClientCat(net.minecraft.src.World level) {
        super(level);
        this.setSize(0.6f, 0.7f);
    }

    @Override
    public String getTexture() {
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
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 0.5f;
    }

    @Override
    protected String getLivingSound() {
        return "mob.cat.meow";
    }
}
