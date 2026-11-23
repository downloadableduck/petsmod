//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.passive;

import net.minecraft.core.world.World;
import com.jeff.pets.client.Central;
import com.jeff.pets.mob.GroundPet;

public class ClientCat extends GroundPet {
    public boolean isOnHead;

    public ClientCat(World world) {
        super(world);
        this.setBoundingBoxSpacing(0.6F, 0.7F);
    }

    public String getTexture() {
        switch (Central.CONFIG.catSkin) {
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

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 0.5F;
    }

    protected String getRandomSound() {
        return "mob.cat.meow";
    }
}
