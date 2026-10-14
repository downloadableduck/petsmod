package com.jeff.pets.client.rendering.vanilla.zombievillager;

import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

/**
 * Zombie villager profession overlay, drawn very slightly scaled up so it sits over the base
 * texture without z-fighting. 1.7.10 has no {@code LayerRenderer}, so this is driven from
 * {@link PetRenderer#renderModel}.
 *
 * <p>The 1.8 original bound the profession texture but never issued a second model draw, so
 * the overlay was invisible. The draw is restored here.
 */
public class ClientZombieVillagerProfessionLayer implements PetLayer {
    static final String ARMORER_LOCATION = "textures/entity/zombie_villager/profession/armorer.png";
    static final String BUTCHER_LOCATION = "textures/entity/zombie_villager/profession/butcher.png";
    static final String CARTOGRAPHER_LOCATION = "textures/entity/zombie_villager/profession/cartographer.png";
    static final String CLERIC_LOCATION = "textures/entity/zombie_villager/profession/cleric.png";
    static final String FARMER_LOCATION = "textures/entity/zombie_villager/profession/farmer.png";
    static final String FISHERMAN_LOCATION = "textures/entity/zombie_villager/profession/fisherman.png";
    static final String FLETCHER_LOCATION = "textures/entity/zombie_villager/profession/fletcher.png";
    static final String LEATHERWORKER_LOCATION = "textures/entity/zombie_villager/profession/leatherworker.png";
    static final String LIBRARIAN_LOCATION = "textures/entity/zombie_villager/profession/librarian.png";
    static final String MASON_LOCATION = "textures/entity/zombie_villager/profession/mason.png";
    static final String NITWIT_LOCATION = "textures/entity/zombie_villager/profession/nitwit.png";
    static final String SHEPHERD_LOCATION = "textures/entity/zombie_villager/profession/shepherd.png";
    static final String TOOLSMITH_LOCATION = "textures/entity/zombie_villager/profession/toolsmith.png";
    static final String WEAPONSMITH_LOCATION = "textures/entity/zombie_villager/profession/weaponsmith.png";

    private final PetRenderer parent;

    public ClientZombieVillagerProfessionLayer(PetRenderer parent) {
        this.parent = parent;
    }

    private String resolveProfessionTexture() {
        String skin = CONFIG.zombieVillagerSkin;
        if (Objects.equals(skin, "armorer")) {
            return ARMORER_LOCATION;
        } else if (Objects.equals(skin, "butcher")) {
            return BUTCHER_LOCATION;
        } else if (Objects.equals(skin, "cartographer")) {
            return CARTOGRAPHER_LOCATION;
        } else if (Objects.equals(skin, "cleric")) {
            return CLERIC_LOCATION;
        } else if (Objects.equals(skin, "farmer")) {
            return FARMER_LOCATION;
        } else if (Objects.equals(skin, "fisherman")) {
            return FISHERMAN_LOCATION;
        } else if (Objects.equals(skin, "fletcher")) {
            return FLETCHER_LOCATION;
        } else if (Objects.equals(skin, "leatherworker")) {
            return LEATHERWORKER_LOCATION;
        } else if (Objects.equals(skin, "librarian")) {
            return LIBRARIAN_LOCATION;
        } else if (Objects.equals(skin, "mason")) {
            return MASON_LOCATION;
        } else if (Objects.equals(skin, "nitwit")) {
            return NITWIT_LOCATION;
        } else if (Objects.equals(skin, "shepherd")) {
            return SHEPHERD_LOCATION;
        } else if (Objects.equals(skin, "toolsmith")) {
            return TOOLSMITH_LOCATION;
        } else if (Objects.equals(skin, "weaponsmith")) {
            return WEAPONSMITH_LOCATION;
        }
        return null;
    }

    @Override
    public void render(EntityLiving zombieVillager, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        String texture = this.resolveProfessionTexture();
        if (texture == null) {
            return;
        }

        GL11.glPushMatrix();
        GL11.glScalef(1.001F, 1.001F, 1.001F);
        this.parent.bindTexturePublic(texture);
        this.parent.getMainModel().render(zombieVillager, limbSwing, limbSwingAmount, ageInTicks,
                netHeadYaw, headPitch, scale);
        GL11.glPopMatrix();
    }
}
