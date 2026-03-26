package com.jeff.pets.vanilla.ravager;

import com.jeff.pets.vanilla.hostile.ClientRavager;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.ravager.RavagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.RavagerRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientRavagerRenderer extends MobRenderer<@NotNull ClientRavager, @NotNull RavagerRenderState, @NotNull RavagerModel> {

    public static final ModelLayerLocation RAVAGER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientravager"), "main");

    public ClientRavagerRenderer(EntityRendererProvider.Context context) {
        super(context, new RavagerModel(context.bakeLayer(ModelLayers.RAVAGER)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(RavagerRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/illager/ravager.png");
    }

    @Override
    public RavagerRenderState createRenderState() {
        return new RavagerRenderState();
    }
}
