package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.SnowGolemHeadLayer;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;


public class ClientSnowGolemRenderer extends PetRenderer<@NotNull ClientSnowGolem, @NotNull SnowGolemRenderState, @NotNull SnowGolemModel> {
    public static final ModelLayerLocation SNOW_GOLEM = new ModelLayerLocation(Identifier.withDefaultNamespace("clientsnowgolem"), "main");

    private final BlockModelResolver resolver;

    public ClientSnowGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new SnowGolemModel(context.bakeLayer(ModelLayers.SNOW_GOLEM)), 0.5F);
        this.resolver = context.getBlockModelResolver();
        this.addLayer(new SnowGolemHeadLayer(this));
    }

    @Override
    public @NotNull Identifier getTextureLocation(SnowGolemRenderState snowGolemRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/snow_golem/snow_golem.png");
    }

    @Override
    public SnowGolemRenderState createRenderState() {
        return new SnowGolemRenderState();
    }

    @Override
    public void extractRenderState(ClientSnowGolem snowGolem, SnowGolemRenderState state, float f) {
        super.extractRenderState(snowGolem, state, f);
        if (CONFIG.snowGolemSkin.equals("pumpkin_on")) {
            this.resolver.update(state.headBlock, Blocks.CARVED_PUMPKIN.defaultBlockState(), BlockDisplayContext.create());
        } else {
            state.headBlock.clear();
        }
    }
}
