package com.jeff.pets.rendering.vanilla.zombie;

import com.jeff.pets.mob.vanilla.hostile.ClientZombie;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientZombieRenderer extends PetRenderer<@NotNull ClientZombie, @NotNull ClientZombieModel<ClientZombie>> {

    public static final ModelLayerLocation ZOMBIE_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientzombie"), "main");

    public ClientZombieRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientZombieModel(context.bakeLayer(ModelLayers.ZOMBIE)), 0.75f);
    }

    public static LayerDefinition createBaseZombieLayer() {
        ClientZombieModel.createMesh(CubeDeformation.NONE, 0);
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    @Override
    protected void scale(@NotNull ClientZombie livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientZombie livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/zombie/zombie.png");
    }
}
