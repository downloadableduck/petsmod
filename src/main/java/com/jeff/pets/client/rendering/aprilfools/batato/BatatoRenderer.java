package com.jeff.pets.client.rendering.aprilfools.batato;

import com.jeff.pets.mob.aprilfools.Batato;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


import static com.jeff.pets.PetsInitializer.MOD_ID;

public class BatatoRenderer extends PetRenderer< Batato,  BatatoModel> {

    public static final ModelLayerLocation BATATO_LOCAITON = new ModelLayerLocation(new ResourceLocation(MOD_ID, "batato"), "main");

    public BatatoRenderer(EntityRendererProvider.Context context) {
        super(context, new BatatoModel(context.bakeLayer(BATATO_LOCAITON)), 0.25f);
    }

    @Override
    public  ResourceLocation getTextureLocation(Batato livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/batato.png");
    }
}
