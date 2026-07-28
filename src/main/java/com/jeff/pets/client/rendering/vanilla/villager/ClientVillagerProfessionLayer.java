package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

//villager layer is blue lmao
public class ClientVillagerProfessionLayer extends LayerRenderer<ClientVillager, VillagerModel<ClientVillager>> {

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

    public ClientVillagerProfessionLayer(IEntityRenderer<ClientVillager, VillagerModel<ClientVillager>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(ClientVillager villager, float f, float g, float h, float i, float j, float k, float l) {
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        com.mojang.blaze3d.platform.GlStateManager.scalef(1.001f, 1.001f, 1.001f);
        if (Objects.equals(CONFIG.villagerSkin, "armorer")) {
            this.bindTexture(ARMORER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "butcher")) {
            bindTexture(BUTCHER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "cartographer")) {
            this.bindTexture(CARTOGRAPHER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "cleric")) {
            this.bindTexture(CLERIC_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "farmer")) {
            this.bindTexture(FARMER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "fisherman")) {
            this.bindTexture(FISHERMAN_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "fletcher")) {
            this.bindTexture(FLETCHER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "leatherworker")) {
            this.bindTexture(LEATHERWORKER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "librarian")) {
            this.bindTexture(LIBRARIAN_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "mason")) {
            this.bindTexture(MASON_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "nitwit")) {
            this.bindTexture(NITWIT_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "shepherd")) {
            this.bindTexture(SHEPHERD_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "toolsmith")) {
            this.bindTexture(TOOLSMITH_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "weaponsmith")) {
            this.bindTexture(WEAPONSMITH_LOCATION);
        }
        this.getParentModel().render(villager, f, g, i, j, k, l);
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }

    @Override
    public boolean colorsOnDamage() {
        return false;
    }
}
