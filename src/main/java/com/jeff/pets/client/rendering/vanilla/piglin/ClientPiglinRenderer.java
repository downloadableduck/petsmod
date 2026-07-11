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
        if (CONFIG.piglinSkin.equals("zombified_piglin")) {
            piglinTexturePath = "textures/entity/piglin/zombified_piglin.png";
        } else if (CONFIG.piglinSkin.equals("piglin_brute")) {
            piglinTexturePath = "textures/entity/piglin/piglin_brute.png";
        } else if (CONFIG.piglinSkin.equals("piglin")) {
            piglinTexturePath = "textures/entity/piglin/piglin.png";
        } else {
            piglinTexturePath = "textures/entity/piglin/piglin.png";
        }
        return new ResourceLocation("minecraft", piglinTexturePath);
    }
}
