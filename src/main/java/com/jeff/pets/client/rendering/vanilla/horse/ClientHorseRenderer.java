package com.jeff.pets.client.rendering.vanilla.horse;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientHorse;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHorseRenderer extends PetRenderer<@NotNull ClientHorse, @NotNull ClientHorseModel<ClientHorse>> {
    public String horseTextureLocation;

    public ClientHorseRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new ClientHorseModel<>(0), 0.5f);
    }

    @Override
    protected void scale(ClientHorse state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientHorse horseRenderState) {
        if (CONFIG.horseSkin.equals("black")) {
            horseTextureLocation = "textures/entity/horse/horse_black.png";
        } else if (CONFIG.horseSkin.equals("brown")) {
            horseTextureLocation = "textures/entity/horse/horse_brown.png";
        } else if (CONFIG.horseSkin.equals("chestnut")) {
            horseTextureLocation = "textures/entity/horse/horse_chestnut.png";
        } else if (CONFIG.horseSkin.equals("creamy")) {
            horseTextureLocation = "textures/entity/horse/horse_creamy.png";
        } else if (CONFIG.horseSkin.equals("dark_brown")) {
            horseTextureLocation = "textures/entity/horse/horse_brown.png";
        } else if (CONFIG.horseSkin.equals("gray")) {
            horseTextureLocation = "textures/entity/horse/horse_gray.png";
        } else if (CONFIG.horseSkin.equals("white")) {
            horseTextureLocation = "textures/entity/horse/horse_white.png";
        } else if (CONFIG.horseSkin.equals("skeleton")) {
            horseTextureLocation = "textures/entity/horse/horse_skeleton.png";
        } else if (CONFIG.horseSkin.equals("zombie")) {
            horseTextureLocation = "textures/entity/horse/horse_zombie.png";
        } else {
            horseTextureLocation = "textures/entity/horse/horse_black.png";
        }
        return new ResourceLocation("minecraft", horseTextureLocation);
    }
}
