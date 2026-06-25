package com.jeff.pets.client.rendering.vanilla.camel;

import com.jeff.pets.mob.vanilla.passive.ClientCamel;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.CamelModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CamelRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCamelRenderer extends PetRenderer<@NotNull ClientCamel, @NotNull CamelRenderState, @NotNull CamelModel> {
    public static final ModelLayerLocation CAMEL_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientcamel"), "main");
    public String camelTexturePath;

    public ClientCamelRenderer(EntityRendererProvider.Context context) {
        super(context, new CamelModel(context.bakeLayer(ModelLayers.CAMEL)), 0.7F);
    }

    @Override
    protected void scale(CamelRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(CamelRenderState camelRenderState) {
        if (Objects.equals(CONFIG.camelSkin, "camel")) {
            camelTexturePath = "textures/entity/camel/camel.png";
        } else if (Objects.equals(CONFIG.camelSkin, "husk")) {
            camelTexturePath = "textures/entity/camel/camel_husk.png";
        } else {
            camelTexturePath = "textures/entity/camel/camel.png";
        }
        return ResourceLocation.withDefaultNamespace(camelTexturePath);
    }

    @Override
    public CamelRenderState createRenderState() {
        return new CamelRenderState();
    }

    @Override
    public void extractRenderState(ClientCamel camel, CamelRenderState state, float f) {
        super.extractRenderState(camel, state, f);
        if (camel.isOnHead) {
            state.sitAnimationState.start(0);
        } else {
            state.sitAnimationState.stop();
        }
    }
}
