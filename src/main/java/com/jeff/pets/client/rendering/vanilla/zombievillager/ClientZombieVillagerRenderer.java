package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerRenderer extends PetRenderer<ClientZombieVillager, ClientZombieVillagerModel> {

    public static final ResourceLocation BUTCHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/zombie_butcher.png");
    public static final ResourceLocation FARMER_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/zombie_farmer.png");
    public static final ResourceLocation LIBRARIAN_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/zombie_librarian.png");
    public static final ResourceLocation NITWIT_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/zombie_villager.png");
    public static final ResourceLocation TOOLSMITH_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/zombie_toolsmith.png");


    public ClientZombieVillagerRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientZombieVillagerModel(0, 0, false), 0.75f);
        this.addLayer(new ClientZombieVillagerProfessionLayer(this));
    }

    @Override
    public void preRenderCallback(ClientZombieVillager livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
        
    }

    @Override
    public ResourceLocation getEntityTexture(ClientZombieVillager villagerRenderState) {
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
    public void applyRotations(ClientZombieVillager state, float f, float g, float h) {
        super.applyRotations(state, f, g, h);
        if (state.isRiding()) {
            net.minecraft.client.renderer.GlStateManager.translate(0, -0.5f, 0);
        }
    }
}
