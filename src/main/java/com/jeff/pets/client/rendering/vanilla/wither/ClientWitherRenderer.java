package com.jeff.pets.client.rendering.vanilla.wither;

import com.jeff.pets.mob.vanilla.boss.ClientWither;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.WitherBossModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWitherRenderer extends PetRenderer<@NotNull ClientWither, @NotNull ClientWitherModel<ClientWither>> {

    public static final ModelLayerLocation WITHER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientwither"), "main");

    public ClientWitherRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientWitherModel<>(context.bakeLayer(ModelLayers.WITHER)), 0.75f);

    }

    public static LayerDefinition createBaseWitherLayer() {
        WitherBossModel.createBodyLayer(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientWither livingEntityRenderState) {
        String witherTexturePath;
        if (Objects.equals(CONFIG.witherSkin, "normal")) {
            witherTexturePath = "textures/entity/wither/wither.png";
        } else if (Objects.equals(CONFIG.witherSkin, "invulnerable")) {
            witherTexturePath = "textures/entity/wither/wither_invulnerable.png";
        } else {
            witherTexturePath = "textures/entity/wither/wither.png";
        }
        return new ResourceLocation("minecraft", witherTexturePath);
    }

    /*@Override
    public void extractRenderState(ClientWither wither, WitherRenderState state, float f) {
        super.extractRenderState(wither, state, f);
        state.yHeadRots = new float[]{wither.getYHeadRot(), wither.getYHeadRot(), wither.getYHeadRot()};
    }*/
}
