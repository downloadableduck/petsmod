package com.jeff.pets.rendering.aprilfools.redstonebug;

import com.jeff.pets.mob.aprilfools.RedstoneBug;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.SilverfishModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RedstoneBugRenderer extends PetRenderer<@NotNull RedstoneBug, @NotNull LivingEntityRenderState, @NotNull SilverfishModel> {

    public static final ModelLayerLocation REDSTONE_BUG_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("redstone_bug"), "main");

    public RedstoneBugRenderer(EntityRendererProvider.Context context) {
        super(context, new SilverfishModel(context.bakeLayer(ModelLayers.SILVERFISH)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/redstone_bug.png");
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
