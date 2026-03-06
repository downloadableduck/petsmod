package com.jeff.pets.vanilla.breeze;

import com.jeff.pets.vanilla.hostile.ClientBreeze;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.breeze.BreezeModel;
import net.minecraft.client.renderer.entity.BreezeRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BreezeWindLayer;
import net.minecraft.client.renderer.entity.state.BreezeRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientBreezeRenderer extends MobRenderer<@NotNull ClientBreeze, @NotNull BreezeRenderState, @NotNull BreezeModel> {

    public static final ModelLayerLocation BREEZE_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientbreeze"), "main");

    public ClientBreezeRenderer(EntityRendererProvider.Context context) {
        super(context, new BreezeModel(context.bakeLayer(ModelLayers.BREEZE)), 0.75f);
        this.addLayer(new BreezeWindLayer(this, EntityModelSet.vanilla()));
    }

    @Override
    public @NotNull Identifier getTextureLocation(BreezeRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/breeze/breeze.png");
    }

    @Override
    public BreezeRenderState createRenderState() {
        return new BreezeRenderState();
    }
}
