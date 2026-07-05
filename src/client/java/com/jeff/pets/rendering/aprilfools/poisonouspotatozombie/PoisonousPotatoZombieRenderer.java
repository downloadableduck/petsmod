package com.jeff.pets.rendering.aprilfools.poisonouspotatozombie;

import com.jeff.pets.mob.aprilfools.PoisonousPotatoZombie;
import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.rendering.vanilla.zombie.ClientZombieModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class PoisonousPotatoZombieRenderer extends PetRenderer<@NotNull PoisonousPotatoZombie, @NotNull ClientZombieModel<PoisonousPotatoZombie>> {

    public static final ModelLayerLocation POISONOUS_POTATO_ZOMBIE_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "poisonosupotatozombie"), "main");

    public PoisonousPotatoZombieRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)), 0.75f);
    }

    @Override
    protected void scale(@NotNull PoisonousPotatoZombie livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        super.scale(livingEntityRenderState, poseStack, f);
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(PoisonousPotatoZombie livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/zombie/poisonous_potato_zombie.png");
    }

    @Override
    public void setupRotations(PoisonousPotatoZombie state, @NotNull PoseStack poseStack, float f, float g, float h) {
        super.setupRotations(state, poseStack, f, g, h);
        if (state.isPassenger()) {
            poseStack.translate(0, -0.5, 0);
        }
    }
}
