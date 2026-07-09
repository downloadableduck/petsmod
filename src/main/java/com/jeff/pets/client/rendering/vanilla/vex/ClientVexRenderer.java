package com.jeff.pets.client.rendering.vanilla.vex;

import com.jeff.pets.mob.vanilla.hostile.ClientVex;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientVexRenderer extends PetRenderer<@NotNull ClientVex, @NotNull ClientVexModel> {

    public static final ModelLayerLocation VEX_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientvex"), "main");

    public ClientVexRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientVexModel(context.bakeLayer(VEX_LOCATION)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientVex livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/vex.png");
    }
}
