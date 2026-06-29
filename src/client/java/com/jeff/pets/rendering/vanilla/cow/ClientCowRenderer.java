package com.jeff.pets.rendering.vanilla.cow;

import com.jeff.pets.mob.vanilla.passive.ClientCow;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientCowRenderer extends PetRenderer<@NotNull ClientCow, @NotNull ClientCowModel<ClientCow>> {
    public static ModelLayerLocation COW_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientcow"), "main");
    String cowTexturePath;

    public ClientCowRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientCowModel<>(context.bakeLayer(ModelLayers.COW)), 0.7F);
    }

    public @NotNull ResourceLocation getTextureLocation(ClientCow LivingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/cow/cow.png");
    }

    @Override
    protected void scale(ClientCow state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }
}
