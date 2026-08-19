package com.jeff.pets.rendering.vanilla.zombievillager;

import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.zombie.ZombieVillagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieVillagerRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientZombieVillagerRenderer extends PetRenderer<@NotNull ClientZombieVillager, @NotNull ZombieVillagerRenderState, @NotNull ZombieVillagerModel<@NotNull ZombieVillagerRenderState>> {

    public static final ModelLayerLocation ZOMBIE_VILLAGER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientzombievillager"), "main");

    public ClientZombieVillagerRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientZombieVillagerModel(context.bakeLayer(ModelLayers.ZOMBIE_VILLAGER)), 0.75f);
        this.addLayer(new ClientZombieVillagerProfessionLayer(this));
    }

    @Override
    protected void scale(@NotNull ZombieVillagerRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ZombieVillagerRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/zombie_villager/zombie_villager.png");
    }

    @Override
    public ZombieVillagerRenderState createRenderState() {
        return new ZombieVillagerRenderState();
    }

    @Override
    public void setupRotations(ZombieVillagerRenderState state, @NotNull PoseStack poseStack, float f, float g) {
        super.setupRotations(state, poseStack, f, g);
        if (state.isPassenger) {
            poseStack.translate(0, -0.5, 0);
        }
    }

    @Override
    public void extractRenderState(ClientZombieVillager zombieVillager, ZombieVillagerRenderState state, float f) {
        super.extractRenderState(zombieVillager, state, f);
        state.isPassenger = zombieVillager.isPassenger();
    }
}
