package com.jeff.pets.client.rendering.vanilla.vex;

import com.jeff.pets.mob.vanilla.hostile.ClientVex;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


public class ClientVexRenderer extends PetRenderer< ClientVex,  ClientVexModel> {

    public static final ModelLayerLocation VEX_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientvex"), "main");

    public ClientVexRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientVexModel(context.bakeLayer(VEX_LOCATION)), 0.75f);
    }

    @Override
    public  ResourceLocation getTextureLocation(ClientVex livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/vex.png");
    }
}
