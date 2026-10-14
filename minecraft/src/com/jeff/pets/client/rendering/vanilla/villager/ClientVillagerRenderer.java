package com.jeff.pets.client.rendering.vanilla.villager;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelVillager;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientVillagerRenderer extends PetRenderer {

    static final String BUTCHER_LOCATION = "textures/entity/villager/butcher.png";
    static final String FARMER_LOCATION = "textures/entity/villager/farmer.png";
    static final String LIBRARIAN_LOCATION = "textures/entity/villager/librarian.png";
    static final String NITWIT_LOCATION = "textures/entity/villager/villager.png";
    static final String TOOLSMITH_LOCATION = "textures/entity/villager/toolsmith.png";

    public ClientVillagerRenderer() {
        super(new ModelVillager(0), 0.5F);
    }

    @Override
    public void preRenderCallback( final EntityLiving state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public String getEntityTexture( final Entity villagerRenderState) {
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
}

