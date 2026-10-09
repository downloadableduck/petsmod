package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import net.minecraft.client.render.entity.model.VillagerEntityModel;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientVillagerRenderer extends PetRenderer {

    public static final Identifier BUTCHER_LOCATION = new Identifier("minecraft", "textures/entity/villager/butcher.png");
    public static final Identifier FARMER_LOCATION = new Identifier("minecraft", "textures/entity/villager/farmer.png");
    public static final Identifier LIBRARIAN_LOCATION = new Identifier("minecraft", "textures/entity/villager/librarian.png");
    public static final Identifier NITWIT_LOCATION = new Identifier("minecraft", "textures/entity/villager/villager.png");
    public static final Identifier TOOLSMITH_LOCATION = new Identifier("minecraft", "textures/entity/villager/toolsmith.png");

    public ClientVillagerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new VillagerEntityModel(0), 0.5F);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientVillager livingEntityRenderState = (ClientVillager) __e;
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

    protected void preRenderCallback(ClientVillager state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }
}