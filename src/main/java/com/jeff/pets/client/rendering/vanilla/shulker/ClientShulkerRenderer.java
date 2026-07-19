package com.jeff.pets.client.rendering.vanilla.shulker;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientShulker;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientShulkerRenderer extends PetRenderer<@NotNull ClientShulker, @NotNull ClientShulkerModel> {

    public ClientShulkerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context) {
        super(context, new ClientShulkerModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientShulker state) {
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
        return new Identifier("minecraft", folderPath + shulkerFile);
    }

    @Override
    public void renderModel(ClientShulker shulker, float f, float g, float h, float i, float j, float k) {
        super.renderModel(shulker, f, g, h, i, j, k);
        //shulker.bodyrotationY = 180;
        // shulker.peekAmount = 1;
    }
}
