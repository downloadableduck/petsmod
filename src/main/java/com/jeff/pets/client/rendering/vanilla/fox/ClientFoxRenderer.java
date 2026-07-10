package com.jeff.pets.client.rendering.vanilla.fox;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientFox;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientFoxRenderer extends PetRenderer<@NotNull ClientFox, @NotNull ClientFoxModel> {
    public String foxTexturePath;

    public ClientFoxRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new ClientFoxModel(), 0.75f);
    }

    @Override
    protected void scale(ClientFox state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientFox livingEntityRenderState) {
        if (Objects.equals(CONFIG.foxSkin, "red")) {
            foxTexturePath = "textures/entity/fox/fox.png";
        } else if (Objects.equals(CONFIG.foxSkin, "snow")) {
            foxTexturePath = "textures/entity/fox/fox_snow.png";
        } else {
            foxTexturePath = "textures/entity/fox/fox.png";
        }
        return new ResourceLocation("minecraft", foxTexturePath);
    }

    @Override
    public void render(ClientFox fox, float f, float g, PoseStack poseStack, MultiBufferSource source, int i) {
        super.render(fox, f, g, poseStack, source, i);
        //fox.setPose(fox.isPassenger() ? Pose.SLEEPING  : fox.getPose());
    }
}
