package com.jeff.pets.client.rendering.aprilfools.lovegolem;

import com.jeff.pets.mob.aprilfools.LoveGolem;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.IronGolemRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LoveGolemRenderer extends PetRenderer<@NotNull LoveGolem, @NotNull IronGolemRenderState, @NotNull IronGolemModel> {

    public static final ModelLayerLocation LOVE_GOLEM_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("lovegolem"), "main");

    public LoveGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new IronGolemModel(context.bakeLayer(LOVE_GOLEM_LOCATION)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(IronGolemRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/irongolem/love_golem.png");
    }

    @Override
    public IronGolemRenderState createRenderState() {
        return new IronGolemRenderState();
    }
}
