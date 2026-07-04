package com.jeff.pets.rendering.vanilla.horse;

import com.jeff.pets.mob.vanilla.passive.ClientHorse;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientHorseRenderer extends PetRenderer<@NotNull ClientHorse, @NotNull ClientHorseModel<ClientHorse>> {
    public static final ModelLayerLocation HORSE_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clienthorse"), "main");
    public String horseTextureLocation;

    public ClientHorseRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientHorseModel<>(context.bakeLayer(ModelLayers.HORSE)), 0.5f);
    }

    public static LayerDefinition createBaseHorseLayer() {
        HorseModel.createBodyMesh(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    @Override
    protected void scale(ClientHorse state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientHorse horseRenderState) {
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
        return new ResourceLocation("minecraft", horseTextureLocation);
    }
}
