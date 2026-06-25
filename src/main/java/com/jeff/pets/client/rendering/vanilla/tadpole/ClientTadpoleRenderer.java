package com.jeff.pets.client.rendering.vanilla.tadpole;

import com.jeff.pets.mob.vanilla.passive.ClientTadpole;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.TadpoleModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientTadpoleRenderer extends PetRenderer<@NotNull ClientTadpole, @NotNull LivingEntityRenderState, @NotNull TadpoleModel> {
    public static final ModelLayerLocation TADPOLE_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clienttadpole"), "main");

    public ClientTadpoleRenderer(EntityRendererProvider.Context context) {
        super(context, new TadpoleModel(context.bakeLayer(ModelLayers.TADPOLE)), 0.75F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/tadpole/tadpole.png");
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
