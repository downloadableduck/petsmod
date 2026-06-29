package com.jeff.pets.client.rendering.vanilla.allay;

import com.jeff.pets.mob.vanilla.passive.ClientAllay;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientAllayRenderer extends PetRenderer<@NotNull ClientAllay, @NotNull ClientAllayModel> {
    public static final ModelLayerLocation ALLAY_TEXTURE = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/allay/allay.png"), "main");

    public ClientAllayRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientAllayModel(context.bakeLayer(ModelLayers.ALLAY)), 0.4F);
        //this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    public @NotNull ResourceLocation getTextureLocation(ClientAllay allayRenderState) {
        return ALLAY_TEXTURE.getModel();
    }

}
