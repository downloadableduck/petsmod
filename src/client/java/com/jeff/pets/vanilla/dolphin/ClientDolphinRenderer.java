package com.jeff.pets.vanilla.dolphin;

import com.jeff.pets.vanilla.neutral.ClientDolphin;
import net.minecraft.client.model.animal.dolphin.DolphinModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.DolphinRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientDolphinRenderer extends MobRenderer<@NotNull ClientDolphin, @NotNull DolphinRenderState, @NotNull DolphinModel> {
    public static final ModelLayerLocation DOLPHIN_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientdolphin"), "main");

    public ClientDolphinRenderer(EntityRendererProvider.Context context) {
        super(context, new DolphinModel(context.bakeLayer(ModelLayers.DOLPHIN)), 0.7f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(DolphinRenderState dolphinRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/dolphin.png");
    }

    @Override
    public DolphinRenderState createRenderState() {
        return new DolphinRenderState();
    }

}
