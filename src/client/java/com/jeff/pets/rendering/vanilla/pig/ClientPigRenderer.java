package com.jeff.pets.rendering.vanilla.pig;

import com.jeff.pets.mob.vanilla.passive.ClientPig;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientPigRenderer extends PetRenderer<@NotNull ClientPig, @NotNull ClientPigModel> {
    public static final ModelLayerLocation PIG_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientpig"), "main");
    public String pigTexturePath;

    public ClientPigRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientPigModel(context.bakeLayer(ModelLayers.PIG)), 0.7F);
    }

    public static LayerDefinition createBasePigModel() {
        ClientPigModel.createBodyLayer(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    @Override
    protected void scale(@NotNull ClientPig livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    public @NotNull ResourceLocation getTextureLocation(ClientPig pigRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/pig/pig.png");
    }
}
