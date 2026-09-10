package com.jeff.pets.client.rendering.vanilla.pig;

import com.jeff.pets.client.rendering.IPetRenderState;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientPig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientPigRenderer extends PetRenderer<@NotNull ClientPig, @NotNull LivingEntityRenderState, @NotNull ClientPigModel> {
    public static final ModelLayerLocation PIG_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientpig"), "main");
    public String pigTexturePath;

    public ClientPigRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientPigModel(context.bakeLayer(ModelLayers.PIG)), 0.7F);
    }

    public static LayerDefinition createBasePigModel() {
        ClientPigModel.createBodyLayer(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    @Override
    protected void scale(@NotNull LivingEntityRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (livingEntityRenderState.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(LivingEntityRenderState pigRenderState) {
        String skin = ((IPetRenderState) pigRenderState).pets$getPetSkin();
        switch (skin) {
            case "temperate" -> pigTexturePath = "textures/entity/pig/pig_temperate.png";
            case "warm" -> pigTexturePath = "textures/entity/pig/pig_warm.png";
            case "cold" -> pigTexturePath = "textures/entity/pig/pig_cold.png";
            case null, default -> pigTexturePath = "textures/entity/pig/pig_temperate.png";
        }
        return Identifier.withDefaultNamespace(pigTexturePath);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
