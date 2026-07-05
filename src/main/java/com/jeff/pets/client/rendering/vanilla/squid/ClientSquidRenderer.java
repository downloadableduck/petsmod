package com.jeff.pets.client.rendering.vanilla.squid;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSquid;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSquidRenderer extends PetRenderer<@NotNull ClientSquid, @NotNull SquidModel<ClientSquid>> {
    public static final ModelLayerLocation SQUID_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientsquid"), "main");
    String squidTexturePath;

    public ClientSquidRenderer(EntityRendererProvider.Context context) {
        super(context, new SquidModel<>(context.bakeLayer(ModelLayers.SQUID)), 0.7F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientSquid squidRenderState) {
        if (Objects.equals(CONFIG.squidSkin, "squid")) {
            squidTexturePath = "textures/entity/squid/squid.png";
        } else if (Objects.equals(CONFIG.squidSkin, "glow_squid")) {
            squidTexturePath = "textures/entity/squid/glow_squid.png";
        }
        return new ResourceLocation("minecraft", squidTexturePath);
    }

    @Override
    protected void scale(@NotNull ClientSquid livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    protected void setupRotations(ClientSquid squid, PoseStack poseStack, float f, float g, float h, float i) {
        float j = Mth.lerp(h, squid.xBodyRotO, squid.xBodyRot);
        float k = Mth.lerp(h, squid.zBodyRotO, squid.zBodyRot);
        poseStack.translate(0.0F, 0.5F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - g));
        poseStack.mulPose(Axis.XP.rotationDegrees(j));
        poseStack.mulPose(Axis.YP.rotationDegrees(k));
        poseStack.translate(0.0F, -1.2F, 0.0F);
    }

    protected float getBob(ClientSquid squid, float f) {
        return Mth.lerp(f, squid.oldTentacleAngle, squid.tentacleAngle);
    }
}
