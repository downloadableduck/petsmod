package com.jeff.pets.vanilla.squid;

import com.jeff.pets.vanilla.passive.ClientSquid;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.animal.squid.SquidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.SquidRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class ClientSquidRenderer extends MobRenderer<@NotNull ClientSquid, @NotNull SquidRenderState, @NotNull SquidModel> {
    public static final ModelLayerLocation SQUID_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientsquid"), "main");
    String squidTexturePath;

    public ClientSquidRenderer(EntityRendererProvider.Context context) {
        super(context, new SquidModel(context.bakeLayer(ModelLayers.SQUID)), 0.7F);
    }

    public @NotNull Identifier getTextureLocation(SquidRenderState squidRenderState) {
        if (Objects.equals(CONFIG.squidSkin, "squid")) {
            squidTexturePath = "textures/entity/squid/squid.png";
        } else if (Objects.equals(CONFIG.squidSkin, "glow_squid")) {
            squidTexturePath = "textures/entity/squid/glow_squid.png";
        }
        return Identifier.withDefaultNamespace(squidTexturePath);
    }

    public SquidRenderState createRenderState() {
        return new SquidRenderState();
    }

    public void extractRenderState(ClientSquid squid, SquidRenderState squidRenderState, float f) {
        super.extractRenderState(squid, squidRenderState, f);
    }

    protected void setupRotations(SquidRenderState squidRenderState, PoseStack poseStack, float f, float g) {
        poseStack.translate(0.0F, squidRenderState.isBaby ? 0.25F : 0.5F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - f));
        poseStack.mulPose(Axis.XP.rotationDegrees(squidRenderState.xBodyRot));
        poseStack.mulPose(Axis.YP.rotationDegrees(squidRenderState.zBodyRot));
        poseStack.translate(0.0F, squidRenderState.isBaby ? -0.6F : -1.2F, 0.0F);
    }
}
