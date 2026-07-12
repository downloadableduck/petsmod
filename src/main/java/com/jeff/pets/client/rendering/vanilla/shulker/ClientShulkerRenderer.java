package com.jeff.pets.client.rendering.vanilla.shulker;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientShulker;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientShulkerRenderer extends PetRenderer<@NotNull ClientShulker, @NotNull ClientShulkerModel> {

    public ClientShulkerRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new ClientShulkerModel(), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientShulker state) {
        String shulkerFile;
        String folderPath = "textures/entity/shulker/";
        if (CONFIG.shulkerSkin.equals("normal")) {
            shulkerFile = "shulker.png";
        } else if (CONFIG.shulkerSkin.equals("black")) {
            shulkerFile = "shulker_black.png";
        } else if (CONFIG.shulkerSkin.equals("brown")) {
            shulkerFile = "shulker_brown.png";
        } else if (CONFIG.shulkerSkin.equals("cyan")) {
            shulkerFile = "shulker_cyan.png";
        } else if (CONFIG.shulkerSkin.equals("light_blue")) {
            shulkerFile = "shulker_light_blue.png";
        } else if (CONFIG.shulkerSkin.equals("light_gray")) {
            shulkerFile = "shulker_light_gray.png";
        } else if (CONFIG.shulkerSkin.equals("lime")) {
            shulkerFile = "shulker_lime.png";
        } else if (CONFIG.shulkerSkin.equals("magenta")) {
            shulkerFile = "shulker_magenta.png";
        } else if (CONFIG.shulkerSkin.equals("orange")) {
            shulkerFile = "shulker_orange.png";
        } else if (CONFIG.shulkerSkin.equals("pink")) {
            shulkerFile = "shulker_pink.png";
        } else if (CONFIG.shulkerSkin.equals("purple")) {
            shulkerFile = "shulker_purple.png";
        } else if (CONFIG.shulkerSkin.equals("red")) {
            shulkerFile = "shulker_red.png";
        } else if (CONFIG.shulkerSkin.equals("white")) {
            shulkerFile = "shulker_white.png";
        } else if (CONFIG.shulkerSkin.equals("yellow")) {
            shulkerFile = "shulker_yellow.png";
        } else {
            shulkerFile = "shulker.png";
        }
        return new ResourceLocation("minecraft", folderPath + shulkerFile);
    }

    @Override
    public void render(ClientShulker shulker, float f, float g, PoseStack poseStack, MultiBufferSource source, int i) {
        super.render(shulker, f, g, poseStack, source, i);
        shulker.yBodyRot = 180;
        // shulker.peekAmount = 1;
    }
}
