package com.jeff.pets.rendering.vanilla.coppergolem;

import com.jeff.pets.mob.vanilla.passive.ClientCopperGolem;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.golem.CopperGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CopperGolemRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Central.CONFIG;

public class ClientCopperGolemRenderer extends PetRenderer<@NotNull ClientCopperGolem, @NotNull CopperGolemRenderState, @NotNull CopperGolemModel> {
    public static ModelLayerLocation COPPER_GOLEM_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientcoppergolem"), "main");

    public String copperGolemTexturePath;

    public ClientCopperGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new CopperGolemModel(context.bakeLayer(ModelLayers.COPPER_GOLEM)), 0.5F);
        this.addLayer(new RenderLayer<>(() -> model) {
            @Override
            public void submit(@NotNull PoseStack poseStack, @NotNull SubmitNodeCollector submitNodeCollector, int i, CopperGolemRenderState entityRenderState, float f, float g) {
                renderColoredCutoutModel(this.getParentModel(), Identifier.withDefaultNamespace("textures/entity/copper_golem/copper_golem_eyes.png"), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
            }
        });
        this.addLayer(new ItemInHandLayer(this));
        CopperGolemModel var10005 = this.model;
        Objects.requireNonNull(var10005);
        this.addLayer(new CustomHeadLayer(this, context.getModelSet(), context.getPlayerSkinRenderCache()));
    }

    @Override
    public @NotNull Identifier getTextureLocation(CopperGolemRenderState copperGolemRenderState) {
        if (Objects.equals(CONFIG.copperGolemSkin, "unoxidized")) {
            copperGolemTexturePath = "textures/entity/copper_golem/copper_golem.png";
        } else if (Objects.equals(CONFIG.copperGolemSkin, "exposed")) {
            copperGolemTexturePath = "textures/entity/copper_golem/copper_golem_exposed.png";
        } else if (Objects.equals(CONFIG.copperGolemSkin, "oxidized")) {
            copperGolemTexturePath = "textures/entity/copper_golem/copper_golem_oxidized.png";
        } else if (Objects.equals(CONFIG.copperGolemSkin, "weathered")) {
            copperGolemTexturePath = "textures/entity/copper_golem/copper_golem_weathered.png";
        }
        return Identifier.withDefaultNamespace(copperGolemTexturePath);
    }

    @Override
    public CopperGolemRenderState createRenderState() {
        return new CopperGolemRenderState();
    }
}

