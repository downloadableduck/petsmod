package com.jeff.pets.vanilla.sniffer;

import com.jeff.pets.vanilla.passive.ClientSniffer;
import net.minecraft.client.model.animal.sniffer.SnifferModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.SnifferRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientSnifferRenderer extends MobRenderer<@NotNull ClientSniffer, @NotNull SnifferRenderState, @NotNull SnifferModel> {
    public static final ModelLayerLocation SNIFFER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientsniffer"), "main");
    private static final Identifier snifferTexturePath = Identifier.withDefaultNamespace("textures/entity/sniffer/sniffer.png");

    public ClientSnifferRenderer(EntityRendererProvider.Context context) {
        super(context, new SnifferModel(context.bakeLayer(ModelLayers.SNIFFER)), 1.1F);
    }

    public @NotNull Identifier getTextureLocation(SnifferRenderState snifferRenderState) {
        return snifferTexturePath;
    }

    public SnifferRenderState createRenderState() {
        return new SnifferRenderState();
    }

}
