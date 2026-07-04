package com.jeff.pets.rendering.vanilla.camel;

import com.jeff.pets.mob.vanilla.passive.ClientCamel;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Central.CONFIG;

public class ClientCamelRenderer extends PetRenderer<@NotNull ClientCamel, @NotNull ClientCamelModel> {
    public static final ModelLayerLocation CAMEL_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientcamel"), "main");
    public String camelTexturePath;

    public ClientCamelRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientCamelModel(context.bakeLayer(ModelLayers.CAMEL)), 0.7F);
    }

    @Override
    protected void scale(ClientCamel state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientCamel camelRenderState) {
        if (Objects.equals(CONFIG.camelSkin, "camel")) {
            camelTexturePath = "textures/entity/camel/camel.png";
        } else if (Objects.equals(CONFIG.camelSkin, "husk")) {
            camelTexturePath = "textures/entity/camel/camel_husk.png";
        } else {
            camelTexturePath = "textures/entity/camel/camel.png";
        }
        return new ResourceLocation("minecraft", camelTexturePath);
    }
}
