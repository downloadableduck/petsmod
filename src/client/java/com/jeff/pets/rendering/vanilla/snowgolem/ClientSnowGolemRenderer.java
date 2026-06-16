package com.jeff.pets.rendering.vanilla.snowgolem;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.SnowGolemHeadLayer;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientSnowGolemRenderer extends PetRenderer<@NotNull ClientSnowGolem, @NotNull SnowGolemRenderState, @NotNull SnowGolemModel> {
    public static final ModelLayerLocation SNOW_GOLEM = new ModelLayerLocation(Identifier.withDefaultNamespace("clientsnowgolem"), "main");

    public ClientSnowGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new SnowGolemModel(context.bakeLayer(ModelLayers.SNOW_GOLEM)), 0.5F);
        this.addLayer(new SnowGolemHeadLayer(this, context.getBlockRenderDispatcher()));
    }

    @Override
    public @NotNull Identifier getTextureLocation(SnowGolemRenderState snowGolemRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/snow_golem.png");
    }

    @Override
    public SnowGolemRenderState createRenderState() {
        return new SnowGolemRenderState();
    }

    @Override
    public void extractRenderState(ClientSnowGolem snowGolem, SnowGolemRenderState state, float f) {
        super.extractRenderState(snowGolem, state, f);
        state.hasPumpkin = CONFIG.snowGolemSkin.equals("pumpkin_on");
    }
}
