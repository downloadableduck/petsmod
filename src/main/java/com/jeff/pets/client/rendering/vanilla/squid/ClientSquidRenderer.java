package com.jeff.pets.client.rendering.vanilla.squid;

import com.jeff.pets.mob.vanilla.passive.ClientSquid;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SquidRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSquidRenderer extends PetRenderer<@NotNull ClientSquid, @NotNull SquidRenderState, @NotNull SquidModel> {
    public static final ModelLayerLocation SQUID_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientsquid"), "main");
    String squidTexturePath;

    public ClientSquidRenderer(EntityRendererProvider.Context context) {
        super(context, new SquidModel(context.bakeLayer(ModelLayers.SQUID)), 0.7F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(SquidRenderState squidRenderState) {
        if (Objects.equals(CONFIG.squidSkin, "squid")) {
            squidTexturePath = "textures/entity/squid/squid.png";
        } else if (Objects.equals(CONFIG.squidSkin, "glow_squid")) {
            squidTexturePath = "textures/entity/squid/glow_squid.png";
        }
        return ResourceLocation.withDefaultNamespace(squidTexturePath);
    }

    @Override
    protected void scale(@NotNull SquidRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public SquidRenderState createRenderState() {
        return new SquidRenderState();
    }
}
