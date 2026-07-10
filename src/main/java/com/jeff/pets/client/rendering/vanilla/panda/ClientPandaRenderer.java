package com.jeff.pets.client.rendering.vanilla.panda;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPanda;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.PandaRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPandaRenderer extends PetRenderer<@NotNull ClientPanda, @NotNull ClientPandaModel> {

    public ClientPandaRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new ClientPandaModel(9, 0), 0.75f);
    }

    @Override
    protected void scale(ClientPanda state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientPanda livingEntityRenderState) {
        String pandaTexturePath;
        switch (CONFIG.pandaSkin) {
            case "normal" -> pandaTexturePath = "textures/entity/panda/panda.png";
            case "lazy" -> pandaTexturePath = "textures/entity/panda/lazy_panda.png";
            case "agressive" -> pandaTexturePath = "textures/entity/panda/aggressive_panda.png";
            case "worried" -> pandaTexturePath = "textures/entity/panda/worried_panda.png";
            case "playful" -> pandaTexturePath = "textures/entity/panda/playful_panda.png";
            case "weak" -> pandaTexturePath = "textures/entity/panda/weak_panda.png";
            case "brown" -> pandaTexturePath = "textures/entity/panda/brown_panda.png";
            default -> pandaTexturePath = "textures/entity/panda/panda.png";
        }
        return new ResourceLocation("minecraft", pandaTexturePath);
    }
}
