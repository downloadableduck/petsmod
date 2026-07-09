package com.jeff.pets.client.rendering.aprilfools.traitor;

import com.jeff.pets.mob.aprilfools.Traitor;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.evoker.ClientEvokerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;


public class TraitorRenderer extends PetRenderer< Traitor,  ClientEvokerModel<Traitor>> {

    public static final ModelLayerLocation TRAITOR_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "traitor"), "main");

    public TraitorRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientEvokerModel(context.bakeLayer(ModelLayers.PILLAGER)), 0.75f);
        this.addLayer((RenderLayer) new TraitorBiomeLayer((RenderLayerParent) this));
    }

    @Override
    public  ResourceLocation getTextureLocation(Traitor livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/pillager.png");
    }
}
