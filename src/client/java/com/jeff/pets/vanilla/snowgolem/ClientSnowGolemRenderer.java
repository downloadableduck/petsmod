package com.jeff.pets.vanilla.snowgolem;

import com.jeff.pets.vanilla.passive.ClientSnowGolem;
import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientSnowGolemRenderer extends MobRenderer<@NotNull ClientSnowGolem, @NotNull SnowGolemRenderState, @NotNull SnowGolemModel> {
    public static final ModelLayerLocation SNOW_GOLEM = new ModelLayerLocation(Identifier.withDefaultNamespace("clientsnowgolem"), "main");
    private static final Identifier snowGolemTexturePath = Identifier.withDefaultNamespace("textures/entity/snow_golem.png");

    public ClientSnowGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new SnowGolemModel(context.bakeLayer(ModelLayers.SNOW_GOLEM)), 0.5F);
        this.addLayer(new ClientSnowGolemHeadLayer(this, context.getBlockRenderDispatcher()));
    }

    public @NotNull Identifier getTextureLocation(SnowGolemRenderState snowGolemRenderState) {
        return snowGolemTexturePath;
    }

    public SnowGolemRenderState createRenderState() {
        return new SnowGolemRenderState();
    }
}
