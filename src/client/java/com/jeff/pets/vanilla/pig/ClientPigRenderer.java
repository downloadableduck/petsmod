package com.jeff.pets.vanilla.pig;

import com.jeff.pets.vanilla.passive.ClientPig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.pig.PigModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Pet.CONFIG;

public class ClientPigRenderer extends MobRenderer<@NotNull ClientPig, @NotNull LivingEntityRenderState, @NotNull PigModel> {
    public static final ModelLayerLocation PIG_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientpig"), "main");
    public String pigTexturePath;

    public ClientPigRenderer(EntityRendererProvider.Context context) {
        super(context, new PigModel(context.bakeLayer(ModelLayers.PIG)), 0.7F);
    }

    public static LayerDefinition createBasePigModel() {
        PigModel.createBodyLayer(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    public void submit(LivingEntityRenderState pigRenderState, @NotNull PoseStack poseStack, @NotNull SubmitNodeCollector submitNodeCollector, @NotNull CameraRenderState cameraRenderState) {
        super.submit(pigRenderState, poseStack, submitNodeCollector, cameraRenderState);
    }

    public @NotNull Identifier getTextureLocation(LivingEntityRenderState pigRenderState) {
        switch (CONFIG.pigSkin) {
            case "temperate" -> pigTexturePath = "textures/entity/pig/temperate_pig.png";
            case "warm" -> pigTexturePath = "textures/entity/pig/warm_pig.png";
            case "cold" -> pigTexturePath = "textures/entity/pig/cold_pig.png";
            case null, default -> pigTexturePath = "textures/entity/pig/temperate_pig.png";
        }
        return Identifier.withDefaultNamespace(pigTexturePath);
    }

    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
