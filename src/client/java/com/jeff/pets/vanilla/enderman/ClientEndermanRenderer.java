package com.jeff.pets.vanilla.enderman;

import com.jeff.pets.vanilla.neutral.ClientEnderman;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.enderman.EndermanModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CarriedBlockLayer;
import net.minecraft.client.renderer.entity.layers.EnderEyesLayer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

public class ClientEndermanRenderer extends MobRenderer<@NotNull ClientEnderman, @NotNull EndermanRenderState, @NotNull EndermanModel<EndermanRenderState>> {
    public static final ModelLayerLocation ENDERMAN_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientenderman"), "main");
    private final RandomSource random = RandomSource.create();

    public ClientEndermanRenderer(EntityRendererProvider.Context context) {
        super(context, new EndermanModel(context.bakeLayer(ModelLayers.ENDERMAN)), 0.5f);
        this.addLayer(new EnderEyesLayer(this));
        this.addLayer(new CarriedBlockLayer(this));
    }

    @Override
    public @NotNull Identifier getTextureLocation(EndermanRenderState endermanRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/enderman/enderman.png");
    }

    @Override
    public EndermanRenderState createRenderState() {
        return new EndermanRenderState();
    }
}
