package com.jeff.pets.client.rendering.vanilla.panda;

import com.jeff.pets.client.rendering.IPetRenderState;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPanda;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.panda.PandaModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.PandaRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientPandaRenderer extends PetRenderer<@NotNull ClientPanda, @NotNull PandaRenderState, @NotNull PandaModel> {
    public static final ModelLayerLocation PANDA_LOCAITON = new ModelLayerLocation(Identifier.withDefaultNamespace("clientpanda"), "main");

    public ClientPandaRenderer(EntityRendererProvider.Context context) {
        super(context, new PandaModel(context.bakeLayer(ModelLayers.PANDA)), 0.75f);
    }

    @Override
    protected void scale(PandaRenderState state, @NotNull PoseStack poseStack) {
        if (state.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(PandaRenderState livingEntityRenderState) {
        String pandaTexturePath;
        String skin = ((IPetRenderState) livingEntityRenderState).pets$getPetSkin();
        switch (skin) {
            case "normal" -> pandaTexturePath = "textures/entity/panda/panda.png";
            case "lazy" -> pandaTexturePath = "textures/entity/panda/panda_lazy.png";
            case "agressive" -> pandaTexturePath = "textures/entity/panda/panda_aggressive.png";
            case "worried" -> pandaTexturePath = "textures/entity/panda/panda_worried.png";
            case "playful" -> pandaTexturePath = "textures/entity/panda/panda_playful.png";
            case "weak" -> pandaTexturePath = "textures/entity/panda/panda_weak.png";
            case "brown" -> pandaTexturePath = "textures/entity/panda/panda_brown.png";
            case null, default -> pandaTexturePath = "textures/entity/panda/panda.png";
        }
        return Identifier.withDefaultNamespace(pandaTexturePath);
    }

    @Override
    public PandaRenderState createRenderState() {
        return new PandaRenderState();
    }
}
