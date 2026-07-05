package com.jeff.pets.client.rendering.aprilfools.redstonebug;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.aprilfools.RedstoneBug;
import net.minecraft.client.model.SilverfishModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RedstoneBugRenderer extends PetRenderer<@NotNull RedstoneBug, @NotNull SilverfishModel<RedstoneBug>> {

    public static final ModelLayerLocation REDSTONE_BUG_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "redstone_bug"), "main");

    public RedstoneBugRenderer(EntityRendererProvider.Context context) {
        super(context, new SilverfishModel(context.bakeLayer(ModelLayers.SILVERFISH)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(RedstoneBug livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/redstone_bug.png");
    }
}
