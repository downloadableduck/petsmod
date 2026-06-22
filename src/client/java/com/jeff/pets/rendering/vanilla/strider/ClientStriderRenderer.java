package com.jeff.pets.rendering.vanilla.strider;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientStrider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.StriderModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.StriderRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Central.CONFIG;

public class ClientStriderRenderer extends PetRenderer<@NotNull ClientStrider, @NotNull StriderRenderState, @NotNull StriderModel> {
    public static ModelLayerLocation STRIDER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientstrider"), "main");

    public String striderTexturePath;

    public ClientStriderRenderer(EntityRendererProvider.Context context) {
        super(context, new StriderModel(context.bakeLayer(ModelLayers.STRIDER)), 0.5F);
    }

    @Override
    protected void scale(@NotNull StriderRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public StriderRenderState createRenderState() {
        return new StriderRenderState();
    }

    public @NotNull ResourceLocation getTextureLocation(StriderRenderState striderRenderState) {
        if (Objects.equals(CONFIG.striderSkin, "warm")) {
            striderTexturePath = "textures/entity/strider/strider.png";
        } else if (Objects.equals(CONFIG.striderSkin, "cold")) {
            striderTexturePath = "textures/entity/strider/strider_cold.png";
        }
        return ResourceLocation.withDefaultNamespace(striderTexturePath);
    }
}
