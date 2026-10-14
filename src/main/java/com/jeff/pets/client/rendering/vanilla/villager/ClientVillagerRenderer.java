package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.client.render.model.entity.VillagerModel;


import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientVillagerRenderer extends PetRenderer<ClientVillager> {

    public static final String BUTCHER_LOCATION = ("textures/mob/villager/butcher.png");
    public static final String FARMER_LOCATION = ("textures/mob/villager/farmer.png");
    public static final String LIBRARIAN_LOCATION = ("textures/mob/villager/librarian.png");
    public static final String NITWIT_LOCATION = ("textures/mob/villager/villager.png");
    public static final String TOOLSMITH_LOCATION = ("textures/mob/villager/toolsmith.png");

    public ClientVillagerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new VillagerModel(0), 0.5F);
       // this.addLayer(new ClientVillagerDefaultLayer(this));
        //this.addLayer(new ClientVillagerProfessionLayer(this));
    }

    @Override
    protected void applyScale(net.minecraft.entity.mob.MobEntity entity, float f) {
        ClientVillager state = (ClientVillager) entity;
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public String getTexture(net.minecraft.entity.Entity entity) {
        ClientVillager villagerRenderState = (ClientVillager) entity;
        if (Objects.equals(CONFIG.villagerSkin, "butcher")) {
            return (BUTCHER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "farmer")) {
            return (FARMER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "librarian")) {
            return (LIBRARIAN_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "nitwit")) {
            return (NITWIT_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "toolsmith") || Objects.equals(CONFIG.villagerSkin, "weaponsmith")) {
            return (TOOLSMITH_LOCATION);
        }
        return NITWIT_LOCATION;
    }
}
