package com.jeff.pets.client.rendering.vanilla.panda;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPanda;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPandaRenderer extends PetRenderer<ClientPanda, ClientPandaModel> {

    public ClientPandaRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientPandaModel(9, 0), 0.75f);
    }

    @Override
    protected void scale(ClientPanda state, MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientPanda livingEntityRenderState) {
        String pandaTexturePath;
        if (CONFIG.pandaSkin.equals("normal")) {
            pandaTexturePath = "textures/entity/panda/panda.png";
        } else if (CONFIG.pandaSkin.equals("lazy")) {
            pandaTexturePath = "textures/entity/panda/lazy_panda.png";
        } else if (CONFIG.pandaSkin.equals("agressive")) {
            pandaTexturePath = "textures/entity/panda/aggressive_panda.png";
        } else if (CONFIG.pandaSkin.equals("worried")) {
            pandaTexturePath = "textures/entity/panda/worried_panda.png";
        } else if (CONFIG.pandaSkin.equals("playful")) {
            pandaTexturePath = "textures/entity/panda/playful_panda.png";
        } else if (CONFIG.pandaSkin.equals("weak")) {
            pandaTexturePath = "textures/entity/panda/weak_panda.png";
        } else if (CONFIG.pandaSkin.equals("brown")) {
            pandaTexturePath = "textures/entity/panda/brown_panda.png";
        } else {
            pandaTexturePath = "textures/entity/panda/panda.png";
        }
        return new ResourceLocation("minecraft", pandaTexturePath);
    }
}
