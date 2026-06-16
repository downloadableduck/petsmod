package com.jeff.pets.rendering.vanilla.vex;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientVex;
import net.minecraft.client.model.VexModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.VexRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientVexRenderer extends PetRenderer<@NotNull ClientVex, @NotNull VexRenderState, @NotNull VexModel> {

    public static final ModelLayerLocation VEX_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientvex"), "main");

    public ClientVexRenderer(EntityRendererProvider.Context context) {
        super(context, new VexModel(context.bakeLayer(ModelLayers.VEX)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(VexRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/illager/vex.png");
    }

    @Override
    public VexRenderState createRenderState() {
        return new VexRenderState();
    }
}
