package com.jeff.pets.rendering.aprilfools.lovegolem;

import com.jeff.pets.mob.aprilfools.LoveGolem;
import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.rendering.vanilla.irongolem.ClientIronGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LoveGolemRenderer extends PetRenderer<@NotNull LoveGolem, @NotNull ClientIronGolemModel<LoveGolem>> {

    public static final ModelLayerLocation LOVE_GOLEM_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("lovegolem"), "main");

    public LoveGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientIronGolemModel<>(context.bakeLayer(LOVE_GOLEM_LOCATION)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(LoveGolem livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/irongolem/love_golem.png");
    }
}
