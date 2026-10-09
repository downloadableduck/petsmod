package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerProfessionLayer {

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

    private final ClientZombieVillagerRenderer renderer;

    public ClientZombieVillagerProfessionLayer(ClientZombieVillagerRenderer renderer) {
        this.renderer = renderer;
    }

    public void render(ClientZombieVillager zombieVillager, float f, float g, float h, float k, float l, float u, float v) {
        GL11.glPushMatrix();
        GL11.glScalef(1.001f, 1.001f, 1.001f);
        if (CONFIG.zombieVillagerSkin.equals("armorer")) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(ARMORER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("butcher")) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(BUTCHER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("cartographer")) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(CARTOGRAPHER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("cleric")) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(CLERIC_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("farmer")) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(FARMER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("fisherman")) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(FISHERMAN_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("fletcher")) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(FLETCHER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("leatherworker")) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(LEATHERWORKER_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("librarian")) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(LIBRARIAN_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("mason")) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(MASON_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("nitwit")) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(NITWIT_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("shepherd")) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(SHEPHERD_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("toolsmith")) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(TOOLSMITH_LOCATION);
        } else if (CONFIG.zombieVillagerSkin.equals("weaponsmith")) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(WEAPONSMITH_LOCATION);
        }
        GL11.glPopMatrix();
    }

    public boolean combineTextures() {
        return false;
    }
}