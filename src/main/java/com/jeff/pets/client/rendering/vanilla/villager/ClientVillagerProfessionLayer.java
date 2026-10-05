package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.model.entity.VillagerModel;
import net.minecraft.client.resource.Identifier;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

//villager layer is blue lmao
public class ClientVillagerProfessionLayer implements PetRenderLayer {

    private final PetRenderer renderer;

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

    public ClientVillagerProfessionLayer(PetRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void render(net.minecraft.entity.living.LivingEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientVillager villager = (ClientVillager) entity;
        com.jeff.pets.compat.GlStateManager.pushMatrix();
        com.jeff.pets.compat.GlStateManager.scalef(1.001f, 1.001f, 1.001f);
        if (Objects.equals(CONFIG.villagerSkin, "armorer")) {
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
        }
        this.renderer.getModel().render(villager, f, g, h, i, j, k);
        com.jeff.pets.compat.GlStateManager.popMatrix();
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
