package com.jeff.pets.rendering.vanilla.husk;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.rendering.vanilla.zombie.ClientZombieModel;
import com.jeff.pets.mob.vanilla.hostile.ClientHusk;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;


public class ClientHuskRenderer extends PetRenderer<@NotNull ClientHusk, @NotNull ZombieRenderState, @NotNull ClientZombieModel> {

    public static final ModelLayerLocation HUSK_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clienthusk"), "main");

    public ClientHuskRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientZombieModel(context.bakeLayer(ModelLayers.HUSK)), 0.75F);
    }

    protected void scale(ZombieRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ZombieRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/zombie/husk.png");
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

    @Override
    public void extractRenderState(ClientHusk husk, ZombieRenderState state, float f) {
        super.extractRenderState(husk, state, f);
        state.isPassenger = husk.isPassenger();
    }
}
