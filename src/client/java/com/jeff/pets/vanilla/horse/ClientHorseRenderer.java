package com.jeff.pets.vanilla.horse;

import com.jeff.pets.vanilla.passive.ClientHorse;
import net.minecraft.client.model.animal.equine.HorseModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.EquineRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Pet.CONFIG;

public class ClientHorseRenderer extends MobRenderer<@NotNull ClientHorse, @NotNull EquineRenderState, @NotNull HorseModel> {
    public static final ModelLayerLocation HORSE_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clienthorse"), "main");
    public String horseTextureLocation;

    public ClientHorseRenderer(EntityRendererProvider.Context context) {
        super(context, new HorseModel(context.bakeLayer(ModelLayers.HORSE)), 0.5f);
    }

    public static LayerDefinition createBaseHorseLayer() {
        HorseModel.createBodyMesh(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    public @NotNull Identifier getTextureLocation(EquineRenderState horseRenderState) {
        switch (CONFIG.horseSkin) {
            case "black" -> horseTextureLocation = "textures/entity/horse/horse_black.png";
            case "brown" -> horseTextureLocation = "textures/entity/horse/horse_brown.png";
            case "chestnut" -> horseTextureLocation = "textures/entity/horse/horse_chestnut.png";
            case "creamy" -> horseTextureLocation = "textures/entity/horse/horse_creamy.png";
            case "dark_brown" -> horseTextureLocation = "textures/entity/horse/horse_brown.png";
            case "gray" -> horseTextureLocation = "textures/entity/horse/horse_gray.png";
            case "white" -> horseTextureLocation = "textures/entity/horse/horse_white.png";
            case "skeleton" -> horseTextureLocation = "textures/entity/horse/horse_skeleton.png";
            case "zombie" -> horseTextureLocation = "textures/entity/horse/horse_zombie.png";
            case null, default -> horseTextureLocation = "textures/entity/horse/horse_black.png";
        }
        return Identifier.withDefaultNamespace(horseTextureLocation);
    }

    public EquineRenderState createRenderState() {
        return new EquineRenderState();
    }

}
