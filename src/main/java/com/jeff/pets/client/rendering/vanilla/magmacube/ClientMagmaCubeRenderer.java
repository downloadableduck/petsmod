package com.jeff.pets.client.rendering.vanilla.magmacube;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientMagmaCube;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMagmaCubeRenderer extends PetRenderer<@NotNull ClientMagmaCube, @NotNull SlimeModel<ClientMagmaCube>> {

    public ClientMagmaCubeRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new SlimeModel<>(0), 0.75f);
    }

    @Override
    protected void scale(ClientMagmaCube slimeRenderState, @NotNull PoseStack poseStack, float a) {
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
    public @NotNull ResourceLocation getTextureLocation(ClientMagmaCube livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/slime/magmacube.png");
    }
}
