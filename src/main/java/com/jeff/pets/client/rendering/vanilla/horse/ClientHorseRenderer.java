package com.jeff.pets.client.rendering.vanilla.horse;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientHorse;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHorseRenderer extends PetRenderer<@NotNull ClientHorse, @NotNull ClientHorseModel<ClientHorse>> {
    public String horseTextureLocation;

    public ClientHorseRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
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
            default -> horseTextureLocation = "textures/entity/horse/horse_black.png";
        }
        return new ResourceLocation("minecraft", horseTextureLocation);
    }
}
