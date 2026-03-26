package com.jeff.pets.vanilla.phantom;

import com.jeff.pets.vanilla.hostile.ClientPhantom;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.phantom.PhantomModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.PhantomRenderer;
import net.minecraft.client.renderer.entity.layers.PhantomEyesLayer;
import net.minecraft.client.renderer.entity.state.PhantomRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientPhantomRenderer extends MobRenderer<@NotNull ClientPhantom, @NotNull PhantomRenderState, @NotNull PhantomModel> {

    public static final ModelLayerLocation PHANTOM_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientphantom"), "main");

    public ClientPhantomRenderer(EntityRendererProvider.Context context) {
        super(context, new PhantomModel(context.bakeLayer(ModelLayers.PHANTOM)), 0.75f);
        this.addLayer(new PhantomEyesLayer(this));
    }

    @Override
    public @NotNull Identifier getTextureLocation(PhantomRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/phantom.png");
    }

    @Override
    public PhantomRenderState createRenderState() {
        return new PhantomRenderState();
    }
    @Override
    public void extractRenderState(ClientPhantom phantom, PhantomRenderState state, float f) {
        super.extractRenderState(phantom, state, f);
        state.flapTime = phantom.getId() * 3 + state.ageInTicks;
    }
}
