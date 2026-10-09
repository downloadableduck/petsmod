package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerRenderer extends PetRenderer {

    public static final Identifier BUTCHER_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/zombie_butcher.png");
    public static final Identifier FARMER_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/zombie_farmer.png");
    public static final Identifier LIBRARIAN_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/zombie_librarian.png");
    public static final Identifier NITWIT_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/zombie_villager.png");
    public static final Identifier TOOLSMITH_LOCATION = new Identifier("minecraft", "textures/entity/zombie_villager/zombie_toolsmith.png");

    public ClientZombieVillagerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientZombieVillagerModel(), 0.75f);
    }

    public void setLivingAnimations(ClientZombieVillager state, float f, float g, float h) {
        if (state.vehicle != null) {
            GL11.glTranslatef(0, -0.5f, 0);
        }
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientZombieVillager livingEntityRenderState = (ClientZombieVillager) __e;
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
}