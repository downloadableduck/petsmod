package com.jeff.pets.rendering.vanilla.silverfish;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSilverfish;
import net.minecraft.client.model.SilverfishModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientSilverfishRenderer extends PetRenderer<@NotNull ClientSilverfish, @NotNull LivingEntityRenderState, @NotNull SilverfishModel> {

    public static final ModelLayerLocation SILVERFISH_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientsilverfish"), "main");

    public ClientSilverfishRenderer(EntityRendererProvider.Context context) {
        super(context, new SilverfishModel(context.bakeLayer(ModelLayers.SILVERFISH)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/silverfish.png");
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
