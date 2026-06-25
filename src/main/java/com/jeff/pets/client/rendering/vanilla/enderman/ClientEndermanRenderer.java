package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.CarriedBlockLayer;
import net.minecraft.client.renderer.entity.layers.EnderEyesLayer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientEndermanRenderer extends PetRenderer<@NotNull ClientEnderman, @NotNull EndermanRenderState, @NotNull EndermanModel<EndermanRenderState>> {
    public static final ModelLayerLocation ENDERMAN_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientenderman"), "main");

    public ClientEndermanRenderer(EntityRendererProvider.Context context) {
        super(context, new EndermanModel(context.bakeLayer(ModelLayers.ENDERMAN)), 0.5f);
        this.addLayer(new EnderEyesLayer(this));
        this.addLayer(new CarriedBlockLayer(this, context.getBlockRenderDispatcher()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(EndermanRenderState endermanRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/enderman/enderman.png");
    }

    @Override
    public EndermanRenderState createRenderState() {
        return new EndermanRenderState();
    }
}
