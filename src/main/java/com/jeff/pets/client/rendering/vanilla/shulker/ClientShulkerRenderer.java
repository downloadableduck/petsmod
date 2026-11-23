//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.shulker;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientShulker;

public class ClientShulkerRenderer extends PetRenderer<ClientShulker> {
    public ClientShulkerRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientShulkerModel(), 0.75F);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientShulker state = (ClientShulker)entity;
        String folderPath = "/mob/shulker/";
        String shulkerFile;
        if (Central.CONFIG.shulkerSkin.equals("normal")) {
            shulkerFile = "shulker.png";
        } else if (Central.CONFIG.shulkerSkin.equals("black")) {
            shulkerFile = "shulker_black.png";
        } else if (Central.CONFIG.shulkerSkin.equals("brown")) {
            shulkerFile = "shulker_brown.png";
        } else if (Central.CONFIG.shulkerSkin.equals("cyan")) {
            shulkerFile = "shulker_cyan.png";
        } else if (Central.CONFIG.shulkerSkin.equals("light_blue")) {
            shulkerFile = "shulker_light_blue.png";
        } else if (Central.CONFIG.shulkerSkin.equals("light_gray")) {
            shulkerFile = "shulker_light_gray.png";
        } else if (Central.CONFIG.shulkerSkin.equals("lime")) {
            shulkerFile = "shulker_lime.png";
        } else if (Central.CONFIG.shulkerSkin.equals("magenta")) {
            shulkerFile = "shulker_magenta.png";
        } else if (Central.CONFIG.shulkerSkin.equals("orange")) {
            shulkerFile = "shulker_orange.png";
        } else if (Central.CONFIG.shulkerSkin.equals("pink")) {
            shulkerFile = "shulker_pink.png";
        } else if (Central.CONFIG.shulkerSkin.equals("purple")) {
            shulkerFile = "shulker_purple.png";
        } else if (Central.CONFIG.shulkerSkin.equals("red")) {
            shulkerFile = "shulker_red.png";
        } else if (Central.CONFIG.shulkerSkin.equals("white")) {
            shulkerFile = "shulker_white.png";
        } else if (Central.CONFIG.shulkerSkin.equals("yellow")) {
            shulkerFile = "shulker_yellow.png";
        } else {
            shulkerFile = "shulker.png";
        }

        return folderPath + shulkerFile;
    }

    public void renderModel(Mob entity, float f, float g, float h, float i, float j, float k) {
        ClientShulker shulker = (ClientShulker)entity;
    }
}
