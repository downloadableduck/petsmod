package com.jeff.pets.client.rendering.vanilla.hoglin;

import com.jeff.pets.client.rendering.IPetRenderState;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientHoglin;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.HoglinRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ClientHoglinRenderer extends PetRenderer<@NotNull ClientHoglin, @NotNull HoglinRenderState, @NotNull ClientHoglinModel> {

    public static final ModelLayerLocation HOGLIN_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clienthoglin"), "main");

    public ClientHoglinRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientHoglinModel(context.bakeLayer(ModelLayers.HOGLIN)), 0.75f);
    }

    @Override
    protected void scale(HoglinRenderState state, @NotNull PoseStack poseStack) {
        if (state.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(HoglinRenderState livingEntityRenderState) {
        String skin = ((IPetRenderState) livingEntityRenderState).pets$getPetSkin();
        String hoglinTexturePath;
        if (Objects.equals(skin, "hoglin")) {
            hoglinTexturePath = "textures/entity/hoglin/hoglin.png";
        } else if (Objects.equals(skin, "zoglin")) {
            hoglinTexturePath = "textures/entity/hoglin/zoglin.png";
        } else {
            hoglinTexturePath = "textures/entity/hoglin/hoglin.png";
        }
        return Identifier.withDefaultNamespace(hoglinTexturePath);
    }

    @Override
    public HoglinRenderState createRenderState() {
        return new HoglinRenderState();
    }
}
