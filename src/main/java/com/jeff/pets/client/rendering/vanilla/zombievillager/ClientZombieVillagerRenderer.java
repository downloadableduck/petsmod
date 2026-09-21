package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
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
        if (livingEntityRenderState.isChild()) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture(ClientZombieVillager villagerRenderState) {
        if (Objects.equals(villagerRenderState.petSkin, "butcher")) {
            return (BUTCHER_LOCATION);
        } else if (Objects.equals(villagerRenderState.petSkin, "farmer")) {
            return (FARMER_LOCATION);
        } else if (Objects.equals(villagerRenderState.petSkin, "librarian")) {
            return (LIBRARIAN_LOCATION);
        } else if (Objects.equals(villagerRenderState.petSkin, "nitwit")) {
            return (NITWIT_LOCATION);
        } else if (Objects.equals(villagerRenderState.petSkin, "toolsmith") || Objects.equals(villagerRenderState.petSkin, "weaponsmith")) {
            return (TOOLSMITH_LOCATION);
        }
        return NITWIT_LOCATION;
    }

    @Override
    public void applyRotations(ClientZombieVillager state, float f, float g, float h) {
        super.applyRotations(state, f, g, h);
        if (state.field_70154_o != null) {
            net.minecraft.client.renderer.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
