package com.jeff.pets.client.rendering.aprilfools.potatohusk;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.zombie.ClientZombieModel;
import com.jeff.pets.mob.aprilfools.PotatoHusk;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class PotatoHuskRenderer extends PetRenderer<@NotNull PotatoHusk, @NotNull ClientZombieModel<PotatoHusk>> {

    public static final ModelLayerLocation POTATO_HUSK_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("potatohusk"), "main");

    public PotatoHuskRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientZombieModel<>(context.bakeLayer(ModelLayers.HUSK)), 0.75f);
    }

    protected void scale(PotatoHusk state, @NotNull PoseStack poseStack, float f) {
        super.scale(state, poseStack, f);
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(PotatoHusk livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/zombie/husk_potato.png");
    }
}
