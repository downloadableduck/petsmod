package com.jeff.pets.rendering.aprilfools.traitor;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.rendering.vanilla.evoker.ClientEvokerModel;
import com.jeff.pets.mob.aprilfools.Traitor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EvokerRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class TraitorRenderer extends PetRenderer<@NotNull Traitor, @NotNull EvokerRenderState, @NotNull ClientEvokerModel> {

    public static final ModelLayerLocation TRAITOR_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("traitor"), "main");

    public TraitorRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientEvokerModel(context.bakeLayer(ModelLayers.PILLAGER)), 0.75f);
        this.addLayer(new TraitorBiomeLayer(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(EvokerRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/illager/pillager.png");
    }

    @Override
    public EvokerRenderState createRenderState() {
        return new EvokerRenderState();
    }
}
