package com.jeff.pets.rendering.vanilla.piglin;

import com.jeff.pets.mob.vanilla.neutral.ClientPiglin;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientPiglinRenderer extends PetRenderer<@NotNull ClientPiglin, @NotNull ClientPiglinModel> {

    public static ModelLayerLocation PIGLIN_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientpiglin"), "main");
    private String piglinTexturePath;

    public ClientPiglinRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientPiglinModel(context.bakeLayer(ModelLayers.PIGLIN)), 0.75f);
    }

    public static LayerDefinition createBodyLayer() {
        PiglinModel.createMesh(CubeDeformation.NONE, 0f);
        PiglinModel.addHead(CubeDeformation.NONE, new MeshDefinition());
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    @Override
    protected void scale(ClientPiglin state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientPiglin livingEntityRenderState) {
        switch (CONFIG.piglinSkin) {
            case "zombified_piglin" -> {
                piglinTexturePath = "textures/entity/piglin/zombified_piglin.png";
            }
            case "piglin_brute" -> {
                piglinTexturePath = "textures/entity/piglin/piglin_brute.png";
            }
            case "piglin" -> piglinTexturePath = "textures/entity/piglin/piglin.png";
            default -> piglinTexturePath =  "textures/entity/piglin/piglin.png";
        }
        return new ResourceLocation("minecraft", piglinTexturePath);
    }
}
