package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerProfessionLayer {

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

    private final ClientZombieVillagerRenderer renderer;

    public ClientZombieVillagerProfessionLayer(ClientZombieVillagerRenderer renderer) {
        this.renderer = renderer;
    }

    public void render(ClientZombieVillager zombieVillager, float f, float g, float h, float k, float l, float u, float v) {
        GL11.glPushMatrix();
        GL11.glScalef(1.001f, 1.001f, 1.001f);
        if (CONFIG.zombieVillagerSkin.equals("armorer")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(ARMORER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("butcher")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(BUTCHER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("cartographer")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(CARTOGRAPHER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("cleric")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(CLERIC_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("farmer")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(FARMER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("fisherman")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(FISHERMAN_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("fletcher")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(FLETCHER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("leatherworker")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(LEATHERWORKER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("librarian")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(LIBRARIAN_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("mason")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(MASON_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("nitwit")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(NITWIT_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("shepherd")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(SHEPHERD_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("toolsmith")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(TOOLSMITH_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("weaponsmith")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(WEAPONSMITH_LOCATION);
        }
        GL11.glPopMatrix();
    }

    public boolean combineTextures() {
        return false;
    }
}