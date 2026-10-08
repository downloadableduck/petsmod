package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import net.minecraft.client.Minecraft;
import static com.jeff.pets.client.Central.CONFIG;

//villager layer is blue lmao
public class ClientVillagerProfessionLayer {

    public static final ResourceLocation ARMORER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/armorer.png");
    public static final ResourceLocation BUTCHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/butcher.png");
    public static final ResourceLocation CARTOGRAPHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/cartographer.png");
    public static final ResourceLocation CLERIC_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/cleric.png");
    public static final ResourceLocation FARMER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/farmer.png");
    public static final ResourceLocation FISHERMAN_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/fisherman.png");
    public static final ResourceLocation FLETCHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/fletcher.png");
    public static final ResourceLocation LEATHERWORKER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/leatherworker.png");
    public static final ResourceLocation LIBRARIAN_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/librarian.png");
    public static final ResourceLocation MASON_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/mason.png");
    public static final ResourceLocation NITWIT_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/nitwit.png");
    public static final ResourceLocation SHEPHERD_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/shepherd.png");
    public static final ResourceLocation TOOLSMITH_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/toolsmith.png");
    public static final ResourceLocation WEAPONSMITH_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/weaponsmith.png");

    private final ClientVillagerRenderer renderer;

    public ClientVillagerProfessionLayer(ClientVillagerRenderer renderer) {
        this.renderer = renderer;
    }

    public void render(@NotNull ClientVillager villager, float f, float g, float h, float i, float j, float k, float l) {
        GL11.glPushMatrix();
        GL11.glScalef(1.001f, 1.001f, 1.001f);
        if (Objects.equals(CONFIG.villagerSkin, "armorer")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(ARMORER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "butcher")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(BUTCHER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "cartographer")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(CARTOGRAPHER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "cleric")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(CLERIC_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "farmer")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(FARMER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "fisherman")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(FISHERMAN_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "fletcher")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(FLETCHER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "leatherworker")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(LEATHERWORKER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "librarian")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(LIBRARIAN_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "mason")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(MASON_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "nitwit")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(NITWIT_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "shepherd")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(SHEPHERD_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "toolsmith")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(TOOLSMITH_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "weaponsmith")) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(WEAPONSMITH_LOCATION);
        }
        this.renderer.getMainModel().render(villager, f, g, i, j, k, l);
        GL11.glPopMatrix();
    }

    public boolean combineTextures() {
        return false;
    }
}