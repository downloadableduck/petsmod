package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerRenderer extends PetRenderer<@NotNull ClientZombieVillager, @NotNull ClientZombieVillagerModel> {

    public ClientZombieVillagerRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new ClientZombieVillagerModel(0, false), 0.75f);
        this.addLayer(new ClientZombieVillagerProfessionLayer(this));
    }

    @Override
    protected void scale(@NotNull ClientZombieVillager livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientZombieVillager livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/zombie_villager/zombie_villager.png");
    }

    @Override
    public void setupRotations(ClientZombieVillager state, @NotNull PoseStack poseStack, float f, float g, float h) {
        super.setupRotations(state, poseStack, f, g, h);
        if (state.isPassenger()) {
            poseStack.translate(0, -0.5, 0);
        }
    }
}
