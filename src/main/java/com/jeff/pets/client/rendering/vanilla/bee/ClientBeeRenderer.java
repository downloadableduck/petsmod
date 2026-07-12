package com.jeff.pets.client.rendering.vanilla.bee;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientBee;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientBeeRenderer extends PetRenderer<@NotNull ClientBee, @NotNull ClientBeeModel> {
    public String beeTexturePath;

    public ClientBeeRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new ClientBeeModel(), 0.4f);
    }

    @Override
    protected void scale(ClientBee state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientBee beeRenderState) {
        if (Objects.equals(CONFIG.beeSkin, "happy")) {
            beeTexturePath = "textures/entity/bee/bee.png";
        } else if (Objects.equals(CONFIG.beeSkin, "angry")) {
            beeTexturePath = "textures/entity/bee/bee_angry.png";
        }
        return new ResourceLocation("minecraft", beeTexturePath);
    }
}
