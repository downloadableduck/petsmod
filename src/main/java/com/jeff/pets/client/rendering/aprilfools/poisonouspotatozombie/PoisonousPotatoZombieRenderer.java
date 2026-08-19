package com.jeff.pets.rendering.aprilfools.poisonouspotatozombie;

import com.jeff.pets.mob.aprilfools.PoisonousPotatoZombie;
import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.rendering.vanilla.zombie.ClientZombieModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class PoisonousPotatoZombieRenderer extends PetRenderer<@NotNull PoisonousPotatoZombie, @NotNull ZombieRenderState, @NotNull ClientZombieModel> {

    public static final ModelLayerLocation POISONOUS_POTATO_ZOMBIE_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("poisonosupotatozombie"), "main");

    public PoisonousPotatoZombieRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientZombieModel(context.bakeLayer(ModelLayers.ZOMBIE)), 0.75f);
    }

    @Override
    protected void scale(@NotNull ZombieRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ZombieRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/zombie/poisonous_potato_zombie.png");
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

    @Override
    public void setupRotations(ZombieRenderState state, @NotNull PoseStack poseStack, float f, float g) {
        super.setupRotations(state, poseStack, f, g);
        if (state.isPassenger) {
            poseStack.translate(0, -0.5, 0);
        }
    }
}
