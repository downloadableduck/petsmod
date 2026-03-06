package com.jeff.pets.vanilla.wolf;

import com.jeff.pets.vanilla.neutral.ClientWolf;
import net.minecraft.client.model.animal.wolf.WolfModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Pet.CONFIG;

public class ClientWolfRenderer extends MobRenderer<@NotNull ClientWolf, @NotNull WolfRenderState, @NotNull WolfModel> {

    public static final ModelLayerLocation WOLF_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientwolf"), "main");

    public ClientWolfRenderer(EntityRendererProvider.Context context) {
        super(context, new WolfModel(context.bakeLayer(ModelLayers.WOLF)), 0.75f);
    }

    public static LayerDefinition createBodyLayer() {
        WolfModel.createMeshDefinition(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 32);
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
}
