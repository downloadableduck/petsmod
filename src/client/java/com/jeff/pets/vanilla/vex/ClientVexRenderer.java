package com.jeff.pets.vanilla.vex;

import com.jeff.pets.vanilla.hostile.ClientVex;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.vex.VexModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.VexRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientVexRenderer extends MobRenderer<@NotNull ClientVex, @NotNull VexRenderState, @NotNull VexModel> {

    public static final ModelLayerLocation VEX_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientvex"), "main");

    public ClientVexRenderer(EntityRendererProvider.Context context) {
        super(context, new VexModel(context.bakeLayer(ModelLayers.VEX)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(VexRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/illager/vex.png");
    }

    @Override
    public VexRenderState createRenderState() {
        return new VexRenderState();
    }
}
