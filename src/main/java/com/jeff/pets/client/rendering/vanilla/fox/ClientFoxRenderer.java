package com.jeff.pets.client.rendering.vanilla.fox;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientFox;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientFoxRenderer extends PetRenderer<ClientFox, ClientFoxModel> {
    public String foxTexturePath;

    public ClientFoxRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientFoxModel(), 0.75f);
    }

    @Override
    protected void scale(ClientFox state, MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientFox livingEntityRenderState) {
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
    public void render(ClientFox fox, float f, float g, MatrixStack poseStack, IRenderTypeBuffer source, int i) {
        super.render(fox, f, g, poseStack, source, i);
        //fox.setPose(fox.isPassenger() ? Pose.SLEEPING  : fox.getPose());
    }
}
