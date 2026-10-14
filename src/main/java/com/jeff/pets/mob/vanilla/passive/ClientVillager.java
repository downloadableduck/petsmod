package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientVillager extends GroundPet {

    public ClientVillager(net.minecraft.world.World level) {
        super(level);
        this.setSize(0.6f, 1.95f);
    }

    static final String BUTCHER_LOCATION = "textures/mob/villager/butcher.png";
    static final String FARMER_LOCATION = "textures/mob/villager/farmer.png";
    static final String LIBRARIAN_LOCATION = "textures/mob/villager/librarian.png";
    static final String NITWIT_LOCATION = "textures/mob/villager/villager.png";
    static final String TOOLSMITH_LOCATION = "textures/mob/villager/toolsmith.png";

    @Override
    public String getTexture() {
        if (Objects.equals(CONFIG.villagerSkin, "butcher")) {
            return (BUTCHER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "farmer")) {
            return (FARMER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "librarian")) {
            return (LIBRARIAN_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "nitwit")) {
            return (NITWIT_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "toolsmith") || Objects.equals(CONFIG.villagerSkin, "weaponsmith")) {
            return (TOOLSMITH_LOCATION);
        }
        return NITWIT_LOCATION;
    }

    @Override
    protected int stopDistance() {
        return 2;
    }

    @Override
    protected float heartHeight() {
        return 2;
    }

    @Override
    protected String getAmbientSound() {
        return "mob.villager.idle";
    }
}
