package com.jeff.pets.vanilla.allay;

import com.jeff.pets.vanilla.passive.ClientAllay;
import net.minecraft.client.model.animal.allay.AllayModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.AllayRenderState;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientAllayRenderer extends MobRenderer<@NotNull ClientAllay, @NotNull AllayRenderState, @NotNull AllayModel> {
    public static final ModelLayerLocation ALLAY_TEXTURE = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/allay/allay.png"), "main");

    public ClientAllayRenderer(EntityRendererProvider.Context context) {
        super(context, new AllayModel(context.bakeLayer(ModelLayers.ALLAY)), 0.4F);
        this.addLayer(new ItemInHandLayer<>(this));
    }

    public @NotNull Identifier getTextureLocation(AllayRenderState allayRenderState) {
        return ALLAY_TEXTURE.model();
    }

    public AllayRenderState createRenderState() {
        return new AllayRenderState();
    }

    public void extractRenderState(ClientAllay allay, AllayRenderState allayRenderState, float f) {
        super.extractRenderState(allay, allayRenderState, f);
        ArmedEntityRenderState.extractArmedEntityRenderState(allay, allayRenderState, this.itemModelResolver, f);
    }
}
