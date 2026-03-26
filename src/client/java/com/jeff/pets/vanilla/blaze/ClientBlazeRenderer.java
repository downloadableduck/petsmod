package com.jeff.pets.vanilla.blaze;

import com.jeff.pets.vanilla.hostile.ClientBlaze;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.blaze.BlazeModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientBlazeRenderer extends MobRenderer<@NotNull ClientBlaze, @NotNull LivingEntityRenderState, @NotNull BlazeModel> {
    public static final ModelLayerLocation BLAZE_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientblaze"), "main");

    public ClientBlazeRenderer(EntityRendererProvider.Context context) {
        super(context, new BlazeModel(context.bakeLayer(ModelLayers.BLAZE)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/blaze.png");
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
