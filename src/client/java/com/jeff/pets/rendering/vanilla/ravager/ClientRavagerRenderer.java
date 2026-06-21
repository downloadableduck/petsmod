package com.jeff.pets.rendering.vanilla.ravager;

import com.jeff.pets.mob.vanilla.hostile.ClientRavager;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.RavagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.RavagerRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientRavagerRenderer extends PetRenderer<@NotNull ClientRavager, @NotNull RavagerRenderState, @NotNull RavagerModel> {

    public static final ModelLayerLocation RAVAGER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientravager"), "main");

    public ClientRavagerRenderer(EntityRendererProvider.Context context) {
        super(context, new RavagerModel(context.bakeLayer(ModelLayers.RAVAGER)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(RavagerRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/illager/ravager.png");
    }

    @Override
    public RavagerRenderState createRenderState() {
        return new RavagerRenderState();
    }
}
