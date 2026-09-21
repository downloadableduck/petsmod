package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import net.minecraft.resource.Identifier;
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
<<<<<<< HEAD
    protected void applyScale(@NotNull ClientZombieVillager livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
=======
    public void preRenderCallback(ClientZombieVillager livingEntityRenderState, float f) {
        if (livingEntityRenderState.isChild()) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
>>>>>>> 908063cb (backport the 0.8.x updates to 1.8.9, as well as allow users to place and break blocks on top of the pet)
        }
    }

    @Override
<<<<<<< HEAD
    public Identifier getTextureLocation(ClientZombieVillager villagerRenderState) {
        if (Objects.equals(CONFIG.zombieVillagerSkin, "butcher")) {
=======
    public ResourceLocation getEntityTexture(ClientZombieVillager villagerRenderState) {
        if (Objects.equals(villagerRenderState.petSkin, "butcher")) {
>>>>>>> 908063cb (backport the 0.8.x updates to 1.8.9, as well as allow users to place and break blocks on top of the pet)
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
    public void applyRotation(ClientZombieVillager state, float f, float g, float h) {
        super.applyRotation(state, f, g, h);
        if (state.isRiding()) {
            net.minecraft.client.render.platform.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
