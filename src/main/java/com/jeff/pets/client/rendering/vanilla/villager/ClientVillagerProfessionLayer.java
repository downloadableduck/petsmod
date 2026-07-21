package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.entity.layer.EntityRenderLayerParent;
import net.minecraft.client.render.model.entity.VillagerModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

//villager layer is blue lmao
public class ClientVillagerProfessionLayer extends EntityRenderLayer<@NotNull ClientVillager, @NotNull VillagerModel<ClientVillager>> {

    public static final Identifier ARMORER_LOCATION = new Identifier("minecraft", "textures/entity/villager/profession/armorer.png");
    public static final Identifier BUTCHER_LOCATION = new Identifier("minecraft", "textures/entity/villager/profession/butcher.png");
    public static final Identifier CARTOGRAPHER_LOCATION = new Identifier("minecraft", "textures/entity/villager/profession/cartographer.png");
    public static final Identifier CLERIC_LOCATION = new Identifier("minecraft", "textures/entity/villager/profession/cleric.png");
    public static final Identifier FARMER_LOCATION = new Identifier("minecraft", "textures/entity/villager/profession/farmer.png");
    public static final Identifier FISHERMAN_LOCATION = new Identifier("minecraft", "textures/entity/villager/profession/fisherman.png");
    public static final Identifier FLETCHER_LOCATION = new Identifier("minecraft", "textures/entity/villager/profession/fletcher.png");
    public static final Identifier LEATHERWORKER_LOCATION = new Identifier("minecraft", "textures/entity/villager/profession/leatherworker.png");
    public static final Identifier LIBRARIAN_LOCATION = new Identifier("minecraft", "textures/entity/villager/profession/librarian.png");
    public static final Identifier MASON_LOCATION = new Identifier("minecraft", "textures/entity/villager/profession/mason.png");
    public static final Identifier NITWIT_LOCATION = new Identifier("minecraft", "textures/entity/villager/profession/nitwit.png");
    public static final Identifier SHEPHERD_LOCATION = new Identifier("minecraft", "textures/entity/villager/profession/shepherd.png");
    public static final Identifier TOOLSMITH_LOCATION = new Identifier("minecraft", "textures/entity/villager/profession/toolsmith.png");
    public static final Identifier WEAPONSMITH_LOCATION = new Identifier("minecraft", "textures/entity/villager/profession/weaponsmith.png");

    public ClientVillagerProfessionLayer(EntityRenderLayerParent<@NotNull ClientVillager, @NotNull VillagerModel<ClientVillager>> renderLayerParent) {
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
        this.getModel().render(villager, f, g, i, j, k, l);
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
