package com.jeff.pets.rendering.vanilla.fox;

import com.jeff.pets.mob.vanilla.neutral.ClientFox;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Central.CONFIG;

public class ClientFoxRenderer extends PetRenderer<@NotNull ClientFox, @NotNull ClientFoxModel> {
    public static final ModelLayerLocation FOX_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientfox"), "main");
    public String foxTexturePath;

    public ClientFoxRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientFoxModel(context.bakeLayer(ModelLayers.FOX)), 0.75f);
    }

    @Override
    protected void scale(ClientFox state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientFox livingEntityRenderState) {
        if (Objects.equals(CONFIG.foxSkin, "red")) {
            foxTexturePath = "textures/entity/fox/fox.png";
        } else if (Objects.equals(CONFIG.foxSkin, "snow")) {
            foxTexturePath = "textures/entity/fox/fox_snow.png";
        } else {
            foxTexturePath = "textures/entity/fox/fox.png";
        }
        return ResourceLocation.withDefaultNamespace(foxTexturePath);
    }

    @Override
    public void render(ClientFox fox, float f, float g, PoseStack poseStack, MultiBufferSource source, int i) {
        super.render(fox, f, g, poseStack, source, i);
        //fox.setPose(fox.isPassenger() ? Pose.SLEEPING  : fox.getPose());
    }
}
