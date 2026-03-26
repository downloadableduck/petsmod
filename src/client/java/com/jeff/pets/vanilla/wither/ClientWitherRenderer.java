package com.jeff.pets.vanilla.wither;

import com.jeff.pets.vanilla.boss.ClientWither;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.monster.wither.WitherBossModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.WitherRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class ClientWitherRenderer extends MobRenderer<@NotNull ClientWither, @NotNull WitherRenderState, @NotNull WitherBossModel> {

    public static final ModelLayerLocation WITHER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientwither"), "main");

    public ClientWitherRenderer(EntityRendererProvider.Context context) {
        super(context, new WitherBossModel(context.bakeLayer(ModelLayers.WITHER)), 0.75f);

    }

    public static LayerDefinition createBaseWitherLayer() {
        WitherBossModel.createBodyLayer(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    @Override
    public @NotNull Identifier getTextureLocation(WitherRenderState livingEntityRenderState) {
        String witherTexturePath;
        if (Objects.equals(CONFIG.witherSkin, "normal")) {
            witherTexturePath = "textures/entity/wither/wither.png";
        } else if (Objects.equals(CONFIG.witherSkin, "invulnerable")) {
            witherTexturePath = "textures/entity/wither/wither_invulnerable.png";
        } else {
            witherTexturePath = "textures/entity/wither/wither.png";
        }
        return Identifier.withDefaultNamespace(witherTexturePath);
    }

    @Override
    public WitherRenderState createRenderState() {
        return new WitherRenderState();
    }

    @Override
    public void extractRenderState(ClientWither wither, WitherRenderState state, float f) {
        super.extractRenderState(wither, state, f);
        state.yHeadRots = new float[]{wither.getYHeadRot(), wither.getYHeadRot(), wither.getYHeadRot()};
    }
}
