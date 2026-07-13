package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMooshroomRenderer extends PetRenderer<ClientMooshroom, ClientCowModel<ClientMooshroom>> {

    String mooshroomTexturePath;

    public ClientMooshroomRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new ClientCowModel<>(), 0.7F);
        this.addLayer(new ClientMushroomCowMushroomLayer(this, Minecraft.getInstance().getBlockRenderer()));
    }

    @Override
    protected void scale(ClientMooshroom state, MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientMooshroom cowRenderState) {
        if (Objects.equals(CONFIG.mooshroomSkin, "red")) {
            mooshroomTexturePath = "textures/entity/cow/red_mooshroom.png";
        } else if (Objects.equals(CONFIG.mooshroomSkin, "brown")) {
            mooshroomTexturePath = "textures/entity/cow/brown_mooshroom.png";
        } else {
            mooshroomTexturePath = "textures/entity/cow/red_mooshroom.png";
        }
        return new ResourceLocation("minecraft", mooshroomTexturePath);
    }
}
