package com.jeff.pets.client.rendering.vanilla.magmacube;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientMagmaCube;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMagmaCubeRenderer extends PetRenderer<ClientMagmaCube, SlimeModel<ClientMagmaCube>> {

    public ClientMagmaCubeRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new SlimeModel<>(0), 0.75f);
    }

    @Override
    protected void scale(ClientMagmaCube slimeRenderState, MatrixStack poseStack, float a) {
        int magmaCubeScale;
        switch (CONFIG.magmaCubeSkin) {
            case "small":
                magmaCubeScale = 1;
                break;
            case "medium":
                magmaCubeScale = 2;
                break;
            case "large":
                magmaCubeScale = 4;
                break;
            default:
                magmaCubeScale = 1;
                break;
        }
        poseStack.scale(magmaCubeScale, magmaCubeScale, magmaCubeScale);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientMagmaCube livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/slime/magmacube.png");
    }
}
