package com.jeff.pets.rendering.vanilla.wolf;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientWolfRenderer extends PetRenderer<@NotNull ClientWolf, @NotNull WolfRenderState, @NotNull ClientWolfModel> {

    public static final ModelLayerLocation WOLF_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientwolf"), "main");

    public ClientWolfRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientWolfModel(context.bakeLayer(ModelLayers.WOLF)), 0.75f);
    }

    public static LayerDefinition createBodyLayer() {
        ClientWolfModel.createMeshDefinition(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 32);
    }

    @Override
    protected void scale(@NotNull WolfRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(WolfRenderState livingEntityRenderState) {
        String wolfTexturePath;

        switch (CONFIG.wolfSkin) {
            case "pale" -> wolfTexturePath = "textures/entity/wolf/wolf.png";
            case "ashen" -> wolfTexturePath = "textures/entity/wolf/wolf_ashen.png";
            case "black" -> wolfTexturePath = "textures/entity/wolf/wolf_black.png";
            case "chestnut" -> wolfTexturePath = "textures/entity/wolf/wolf_chestnut.png";
            case "rusty" -> wolfTexturePath = "textures/entity/wolf/wolf_rusty.png";
            case "snowy" -> wolfTexturePath = "textures/entity/wolf/wolf_snowy.png";
            case "spotted" -> wolfTexturePath = "textures/entity/wolf/wolf_spotted.png";
            case "striped" -> wolfTexturePath = "textures/entity/wolf/wolf_striped.png";
            case "woods" -> wolfTexturePath = "textures/entity/wolf/wolf_woods.png";
            case null, default -> wolfTexturePath = "textures/entity/wolf/wolf.png";
        }

        return Identifier.withDefaultNamespace(wolfTexturePath);
    }

    @Override
    public WolfRenderState createRenderState() {
        return new WolfRenderState();
    }

    @Override
    public void extractRenderState(ClientWolf wolf, WolfRenderState state, float f) {
        super.extractRenderState(wolf, state, f);
        state.isSitting = wolf.isPassenger();
    }
}
