package com.jeff.pets.rendering.vanilla.shulker;

import com.jeff.pets.mob.vanilla.hostile.ClientShulker;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientShulkerRenderer extends PetRenderer<@NotNull ClientShulker, @NotNull ClientShulkerModel> {

    public static final ModelLayerLocation SHULKER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientshulker"), "main");

    public ClientShulkerRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientShulkerModel(context.bakeLayer(ModelLayers.SHULKER)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientShulker state) {
        String shulkerFile;
        String folderPath = "textures/entity/shulker/";
        switch (CONFIG.shulkerSkin) {
            case "normal" -> shulkerFile = "shulker.png";
            case "black" -> shulkerFile = "shulker_black.png";
            case "brown" -> shulkerFile = "shulker_brown.png";
            case "cyan" -> shulkerFile = "shulker_cyan.png";
            case "light_blue" -> shulkerFile = "shulker_light_blue.png";
            case "light_gray" -> shulkerFile = "shulker_light_gray.png";
            case "lime" -> shulkerFile = "shulker_lime.png";
            case "magenta" -> shulkerFile = "shulker_magenta.png";
            case "orange" -> shulkerFile = "shulker_orange.png";
            case "pink" -> shulkerFile = "shulker_pink.png";
            case "purple" -> shulkerFile = "shulker_purple.png";
            case "red" -> shulkerFile = "shulker_red.png";
            case "white" -> shulkerFile = "shulker_white.png";
            case "yellow" -> shulkerFile = "shulker_yellow.png";
            case null, default -> shulkerFile = "shulker.png";
        }
        return ResourceLocation.withDefaultNamespace(folderPath + shulkerFile);
    }

    @Override
    public void render(ClientShulker shulker, float f, float g, PoseStack poseStack, MultiBufferSource source, int i) {
        super.render(shulker, f, g, poseStack, source, i);
        shulker.yBodyRot = 180;
        // shulker.peekAmount = 1;
    }
}
