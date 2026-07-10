package com.jeff.pets.client.rendering.vanilla.piglin;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPiglin;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPiglinRenderer extends PetRenderer<@NotNull ClientPiglin, @NotNull ClientPiglinModel> {

    private String piglinTexturePath;

    public ClientPiglinRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new ClientPiglinModel(0.0F, 64, 64), 0.75f);
    }

    @Override
    protected void scale(ClientPiglin state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientPiglin livingEntityRenderState) {
        switch (CONFIG.piglinSkin) {
            case "zombified_piglin" -> {
                piglinTexturePath = "textures/entity/piglin/zombified_piglin.png";
            }
            case "piglin_brute" -> {
                piglinTexturePath = "textures/entity/piglin/piglin_brute.png";
            }
            case "piglin" -> piglinTexturePath = "textures/entity/piglin/piglin.png";
            default -> piglinTexturePath = "textures/entity/piglin/piglin.png";
        }
        return new ResourceLocation("minecraft", piglinTexturePath);
    }
}
