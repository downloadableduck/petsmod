package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientVillager;


import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

//villager layer is blue lmao
public class ClientVillagerProfessionLayer implements PetRenderLayer {

    private final PetRenderer renderer;

    public static final String ARMORER_LOCATION = ("textures/mob/villager/profession/armorer.png");
    public static final String BUTCHER_LOCATION = ("textures/mob/villager/profession/butcher.png");
    public static final String CARTOGRAPHER_LOCATION = ("textures/mob/villager/profession/cartographer.png");
    public static final String CLERIC_LOCATION = ("textures/mob/villager/profession/cleric.png");
    public static final String FARMER_LOCATION = ("textures/mob/villager/profession/farmer.png");
    public static final String FISHERMAN_LOCATION = ("textures/mob/villager/profession/fisherman.png");
    public static final String FLETCHER_LOCATION = ("textures/mob/villager/profession/fletcher.png");
    public static final String LEATHERWORKER_LOCATION = ("textures/mob/villager/profession/leatherworker.png");
    public static final String LIBRARIAN_LOCATION = ("textures/mob/villager/profession/librarian.png");
    public static final String MASON_LOCATION = ("textures/mob/villager/profession/mason.png");
    public static final String NITWIT_LOCATION = ("textures/mob/villager/profession/nitwit.png");
    public static final String SHEPHERD_LOCATION = ("textures/mob/villager/profession/shepherd.png");
    public static final String TOOLSMITH_LOCATION = ("textures/mob/villager/profession/toolsmith.png");
    public static final String WEAPONSMITH_LOCATION = ("textures/mob/villager/profession/weaponsmith.png");

    public ClientVillagerProfessionLayer(PetRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void render(net.minecraft.entity.mob.MobEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientVillager villager = (ClientVillager) entity;
        com.jeff.pets.compat.GlStateManager.pushMatrix();
        com.jeff.pets.compat.GlStateManager.scalef(1.001f, 1.001f, 1.001f);
        /*if (Objects.equals(CONFIG.villagerSkin, "armorer")) {
            this.renderer.bindTexture(ARMORER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "butcher")) {
            this.renderer.bindTexture(BUTCHER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "cartographer")) {
            this.renderer.bindTexture(CARTOGRAPHER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "cleric")) {
            this.renderer.bindTexture(CLERIC_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "farmer")) {
            this.renderer.bindTexture(FARMER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "fisherman")) {
            this.renderer.bindTexture(FISHERMAN_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "fletcher")) {
            this.renderer.bindTexture(FLETCHER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "leatherworker")) {
            this.renderer.bindTexture(LEATHERWORKER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "librarian")) {
            this.renderer.bindTexture(LIBRARIAN_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "mason")) {
            this.renderer.bindTexture(MASON_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "nitwit")) {
            this.renderer.bindTexture(NITWIT_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "shepherd")) {
            this.renderer.bindTexture(SHEPHERD_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "toolsmith")) {
            this.renderer.bindTexture(TOOLSMITH_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "weaponsmith")) {
            this.renderer.bindTexture(WEAPONSMITH_LOCATION);
        }*/
        this.renderer.getModel().render(villager, f, g, h, i, j, k);
        com.jeff.pets.compat.GlStateManager.popMatrix();
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
