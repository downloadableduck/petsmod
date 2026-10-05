package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.resource.Identifier;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerProfessionLayer implements PetRenderLayer {

    public static final Identifier ARMORER_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/profession/armorer.png");
    public static final Identifier BUTCHER_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/profession/butcher.png");
    public static final Identifier CARTOGRAPHER_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/profession/cartographer.png");
    public static final Identifier CLERIC_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/profession/cleric.png");
    public static final Identifier FARMER_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/profession/farmer.png");
    public static final Identifier FISHERMAN_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/profession/fisherman.png");
    public static final Identifier FLETCHER_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/profession/fletcher.png");
    public static final Identifier LEATHERWORKER_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/profession/leatherworker.png");
    public static final Identifier LIBRARIAN_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/profession/librarian.png");
    public static final Identifier MASON_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/profession/mason.png");
    public static final Identifier NITWIT_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/profession/nitwit.png");
    public static final Identifier SHEPHERD_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/profession/shepherd.png");
    public static final Identifier TOOLSMITH_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/profession/toolsmith.png");
    public static final Identifier WEAPONSMITH_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/profession/weaponsmith.png");

    private final PetRenderer renderer;

    public ClientZombieVillagerProfessionLayer(PetRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void render(net.minecraft.entity.living.LivingEntity entity, float f, float g, float h, float k, float l, float u) {
        ClientZombieVillager zombieVillager = (ClientZombieVillager) entity;
        com.jeff.pets.compat.GlStateManager.pushMatrix();
        com.jeff.pets.compat.GlStateManager.scalef(1.001f, 1.001f, 1.001f);
        if (CONFIG.zombieVillagerSkin.equals("armorer")) {
            this.renderer.bindTexture(ARMORER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("butcher")) {
            this.renderer.bindTexture(BUTCHER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("cartographer")) {
            this.renderer.bindTexture(CARTOGRAPHER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("cleric")) {
            this.renderer.bindTexture(CLERIC_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("farmer")) {
            this.renderer.bindTexture(FARMER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("fisherman")) {
            this.renderer.bindTexture(FISHERMAN_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("fletcher")) {
            this.renderer.bindTexture(FLETCHER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("leatherworker")) {
            this.renderer.bindTexture(LEATHERWORKER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("librarian")) {
            this.renderer.bindTexture(LIBRARIAN_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("mason")) {
            this.renderer.bindTexture(MASON_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("nitwit")) {
            this.renderer.bindTexture(NITWIT_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("shepherd")) {
            this.renderer.bindTexture(SHEPHERD_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("toolsmith")) {
            this.renderer.bindTexture(TOOLSMITH_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("weaponsmith")) {
            this.renderer.bindTexture(WEAPONSMITH_LOCATION);
        }
        com.jeff.pets.compat.GlStateManager.popMatrix();
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
