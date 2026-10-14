package com.jeff.pets.mob.vanilla.passive;

import com.jeff.pets.mob.GroundPet;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelVillager;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientVillager extends GroundPet {

    public ClientVillager(net.minecraft.src.World level) {
        super(level);
        this.setSize(0.6f, 1.95f);
    }

    static final String BUTCHER_LOCATION = "textures/entity/villager/butcher.png";
    static final String FARMER_LOCATION = "textures/entity/villager/farmer.png";
    static final String LIBRARIAN_LOCATION = "textures/entity/villager/librarian.png";
    static final String NITWIT_LOCATION = "textures/entity/villager/villager.png";
    static final String TOOLSMITH_LOCATION = "textures/entity/villager/toolsmith.png";

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
    protected String getLivingSound() {
        return "mob.villager.idle";
    }
}
