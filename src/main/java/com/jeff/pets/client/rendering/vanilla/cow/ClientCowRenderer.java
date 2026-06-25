package com.jeff.pets.client.rendering.vanilla.cow;

import com.jeff.pets.mob.vanilla.passive.ClientCow;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowRenderer extends PetRenderer<@NotNull ClientCow, @NotNull LivingEntityRenderState, @NotNull ClientCowModel> {
    public static ModelLayerLocation COW_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientcow"), "main");
    String cowTexturePath;

    public ClientCowRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientCowModel(context.bakeLayer(ModelLayers.COW)), 0.7F);
    }

    public @NotNull ResourceLocation getTextureLocation(LivingEntityRenderState LivingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/cow/cow.png");
    }

    @Override
    protected void scale(LivingEntityRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
