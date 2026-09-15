package com.jeff.pets.client.rendering.vanilla.zombievillager;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerProfessionLayer implements LayerRenderer {

    public static final ResourceLocation ARMORER_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/armorer.png");
    public static final ResourceLocation BUTCHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/butcher.png");
    public static final ResourceLocation CARTOGRAPHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/cartographer.png");
    public static final ResourceLocation CLERIC_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/cleric.png");
    public static final ResourceLocation FARMER_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/farmer.png");
    public static final ResourceLocation FISHERMAN_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/fisherman.png");
    public static final ResourceLocation FLETCHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/fletcher.png");
    public static final ResourceLocation LEATHERWORKER_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/leatherworker.png");
    public static final ResourceLocation LIBRARIAN_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/librarian.png");
    public static final ResourceLocation MASON_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/mason.png");
    public static final ResourceLocation NITWIT_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/nitwit.png");
    public static final ResourceLocation SHEPHERD_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/shepherd.png");
    public static final ResourceLocation TOOLSMITH_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/toolsmith.png");
    public static final ResourceLocation WEAPONSMITH_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/weaponsmith.png");

    private final RendererLivingEntity renderer;

    public ClientZombieVillagerProfessionLayer(RendererLivingEntity renderLayerParent) {
        this.renderer = renderLayerParent;
    }

    @Override
    public void render( final EntityLivingBase zombieVillager, float f, float g, float h, float k, float l, float u, float v) {
        net.minecraft.client.renderer.GlStateManager.pushMatrix();
        net.minecraft.client.renderer.GlStateManager.scalef(1.001f, 1.001f, 1.001f);
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
        net.minecraft.client.renderer.GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}


