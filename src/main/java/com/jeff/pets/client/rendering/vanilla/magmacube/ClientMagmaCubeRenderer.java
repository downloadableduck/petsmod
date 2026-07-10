package com.jeff.pets.client.rendering.vanilla.magmacube;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientMagmaCube;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMagmaCubeRenderer extends PetRenderer<@NotNull ClientMagmaCube, @NotNull SlimeModel<ClientMagmaCube>> {

    public ClientMagmaCubeRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new SlimeModel<>(0), 0.75f);
    }

    @Override
    protected void scale(ClientMagmaCube slimeRenderState, @NotNull PoseStack poseStack, float a) {
        int magmaCubeScale = switch (CONFIG.magmaCubeSkin) {
            case "small" -> 1;
            case "medium" -> 2;
            case "large" -> 4;
            default -> 1;
        };
        poseStack.scale(magmaCubeScale, magmaCubeScale, magmaCubeScale);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientMagmaCube livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/slime/magmacube.png");
    }
}
