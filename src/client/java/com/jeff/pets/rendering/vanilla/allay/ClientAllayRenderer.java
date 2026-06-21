package com.jeff.pets.rendering.vanilla.allay;

import com.jeff.pets.mob.vanilla.passive.ClientAllay;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.AllayModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.AllayRenderState;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientAllayRenderer extends PetRenderer<@NotNull ClientAllay, @NotNull AllayRenderState, @NotNull AllayModel> {
    public static final ModelLayerLocation ALLAY_TEXTURE = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/allay/allay.png"), "main");

    public ClientAllayRenderer(EntityRendererProvider.Context context) {
        super(context, new AllayModel(context.bakeLayer(ModelLayers.ALLAY)), 0.4F);
        this.addLayer(new ItemInHandLayer<>(this));
    }

    public @NotNull ResourceLocation getTextureLocation(AllayRenderState allayRenderState) {
        return ALLAY_TEXTURE.model();
    }

    public AllayRenderState createRenderState() {
        return new AllayRenderState();
    }

    public void extractRenderState(ClientAllay allay, AllayRenderState state, float f) {
        super.extractRenderState(allay, state, f);
        ArmedEntityRenderState.extractArmedEntityRenderState(allay, state, this.itemModelResolver);
    }
}
