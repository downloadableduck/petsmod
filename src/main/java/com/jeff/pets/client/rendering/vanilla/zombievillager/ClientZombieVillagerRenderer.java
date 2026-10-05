package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import net.minecraft.client.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerRenderer extends PetRenderer<ClientZombieVillager> {

    public static final Identifier BUTCHER_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/zombie_butcher.png");
    public static final Identifier FARMER_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/zombie_farmer.png");
    public static final Identifier LIBRARIAN_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/zombie_librarian.png");
    public static final Identifier NITWIT_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/zombie_villager.png");
    public static final Identifier TOOLSMITH_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/zombie_toolsmith.png");
    
    public ClientZombieVillagerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientZombieVillagerModel(0, false), 0.75f);
       //this.addLayer(new ClientZombieVillagerProfessionLayer(this));
    }

    @Override
    protected void applyScale(net.minecraft.entity.living.LivingEntity entity, float f) {
        ClientZombieVillager livingEntityRenderState = (ClientZombieVillager) entity;
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public Identifier getTextureLocation(net.minecraft.entity.Entity entity) {
        ClientZombieVillager villagerRenderState = (ClientZombieVillager) entity;
        if (Objects.equals(CONFIG.zombieVillagerSkin, "butcher")) {
            return (BUTCHER_LOCATION);
        } else if (Objects.equals(CONFIG.zombieVillagerSkin, "farmer")) {
            return (FARMER_LOCATION);
        } else if (Objects.equals(CONFIG.zombieVillagerSkin, "librarian")) {
            return (LIBRARIAN_LOCATION);
        } else if (Objects.equals(CONFIG.zombieVillagerSkin, "nitwit")) {
            return (NITWIT_LOCATION);
        } else if (Objects.equals(CONFIG.zombieVillagerSkin, "toolsmith") || Objects.equals(CONFIG.zombieVillagerSkin, "weaponsmith")) {
            return (TOOLSMITH_LOCATION);
        }
        return NITWIT_LOCATION;
    }

    @Override
    public void applyRotation(net.minecraft.entity.living.LivingEntity entity, float f, float g, float h) {
        ClientZombieVillager state = (ClientZombieVillager) entity;
        super.applyRotation(state, f, g, h);
        if (state.isRiding()) {
            com.jeff.pets.compat.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
