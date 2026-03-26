package com.jeff.pets.vanilla.donkey;

import com.jeff.pets.vanilla.passive.ClientDonkey;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.EquineRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientDonkeyRenderer<T extends ClientDonkey> extends MobRenderer<@NotNull ClientDonkey, @NotNull EquineRenderState, @NotNull ClientDonkeyModel> {
    public static ModelLayerLocation DONKEY_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientdonkey"), "main");

    public ClientDonkeyRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientDonkeyModel(context.bakeLayer(ModelLayers.DONKEY)), 0.5f);
    }

    public @NotNull Identifier getTextureLocation(EquineRenderState donkeyRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/horse/donkey.png");
    }

    public EquineRenderState createRenderState() {
        return new EquineRenderState();
    }

    public void extractRenderState(T abstractChestedHorse, EquineRenderState donkeyRenderState, float f) {
        super.extractRenderState(abstractChestedHorse, donkeyRenderState, f);
    }
}
