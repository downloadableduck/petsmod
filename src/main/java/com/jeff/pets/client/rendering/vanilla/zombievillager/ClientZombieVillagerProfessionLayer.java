package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.entity.layer.EntityRenderLayerParent;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerProfessionLayer extends EntityRenderLayer<@NotNull ClientZombieVillager, ClientZombieVillagerModel> {

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

    public ClientZombieVillagerProfessionLayer(EntityRenderLayerParent<@NotNull ClientZombieVillager, @NotNull ClientZombieVillagerModel> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(ClientZombieVillager zombieVillager, float f, float g, float h, float k, float l, float u, float v) {
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        com.mojang.blaze3d.platform.GlStateManager.scale(1.001f, 1.001f, 1.001f);
        if (CONFIG.zombieVillagerSkin.equals("armorer")) {
            this.bindTexture(ARMORER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("butcher")) {
            this.bindTexture(BUTCHER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("cartographer")) {
            this.bindTexture(CARTOGRAPHER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("cleric")) {
            this.bindTexture(CLERIC_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("farmer")) {
            this.bindTexture(FARMER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("fisherman")) {
            this.bindTexture(FISHERMAN_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("fletcher")) {
            this.bindTexture(FLETCHER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("leatherworker")) {
            this.bindTexture(LEATHERWORKER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("librarian")) {
            this.bindTexture(LIBRARIAN_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("mason")) {
            this.bindTexture(MASON_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("nitwit")) {
            this.bindTexture(NITWIT_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("shepherd")) {
            this.bindTexture(SHEPHERD_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("toolsmith")) {
            this.bindTexture(TOOLSMITH_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("weaponsmith")) {
            this.bindTexture(WEAPONSMITH_LOCATION);
        }
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
