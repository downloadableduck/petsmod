package com.jeff.pets.rendering.vanilla.panda;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPanda;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.panda.PandaModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.PandaRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientPandaRenderer extends PetRenderer<@NotNull ClientPanda, @NotNull PandaRenderState, @NotNull PandaModel> {
    public static final ModelLayerLocation PANDA_LOCAITON = new ModelLayerLocation(Identifier.withDefaultNamespace("clientpanda"), "main");

    public ClientPandaRenderer(EntityRendererProvider.Context context) {
        super(context, new PandaModel(context.bakeLayer(ModelLayers.PANDA)), 0.75f);
    }

    @Override
    protected void scale(PandaRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(PandaRenderState livingEntityRenderState) {
        String pandaTexturePath;
        switch (CONFIG.pandaSkin) {
            case "normal" -> pandaTexturePath = "textures/entity/panda/panda.png";
            case "lazy" -> pandaTexturePath = "textures/entity/panda/lazy_panda.png";
            case "agressive" -> pandaTexturePath = "textures/entity/panda/aggressive_panda.png";
            case "worried" -> pandaTexturePath = "textures/entity/panda/worried_panda.png";
            case "playful" -> pandaTexturePath = "textures/entity/panda/playful_panda.png";
            case "weak" -> pandaTexturePath = "textures/entity/panda/weak_panda.png";
            case "brown" -> pandaTexturePath = "textures/entity/panda/brown_panda.png";
            case null, default -> pandaTexturePath = "textures/entity/panda/panda.png";
        }
        return Identifier.withDefaultNamespace(pandaTexturePath);
    }

    @Override
    public PandaRenderState createRenderState() {
        return new PandaRenderState();
    }
}
