package com.jeff.pets.vanilla.cod;

import com.jeff.pets.vanilla.passive.ClientCod;
import net.minecraft.client.model.animal.fish.CodModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientCodRenderer extends MobRenderer<@NotNull ClientCod, @NotNull LivingEntityRenderState, @NotNull CodModel> {
    public static final ModelLayerLocation COD_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientcod"), "main");

    public ClientCodRenderer(EntityRendererProvider.Context context) {
        super(context, new CodModel(context.bakeLayer(ModelLayers.COD)), 0.3F);
    }

    public @NotNull Identifier getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/fish/cod.png");
    }

    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
