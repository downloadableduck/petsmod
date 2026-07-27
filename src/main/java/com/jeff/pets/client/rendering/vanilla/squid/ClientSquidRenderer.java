package com.jeff.pets.client.rendering.vanilla.squid;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSquid;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.SquidModel;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSquidRenderer extends PetRenderer<ClientSquid, SquidModel<ClientSquid>> {
    String squidTexturePath;

    public ClientSquidRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SquidModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientSquid squidRenderState) {
        if (Objects.equals(CONFIG.squidSkin, "squid")) {
            squidTexturePath = "textures/entity/squid/squid.png";
        } else if (Objects.equals(CONFIG.squidSkin, "glow_squid")) {
            squidTexturePath = "textures/entity/squid/glow_squid.png";
        }
        return new ResourceLocation("minecraft", squidTexturePath);
    }

    @Override
    protected void scale(ClientSquid livingEntityRenderState, com.mojang.blaze3d.matrix.MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    protected void setupRotations(ClientSquid squid, com.mojang.blaze3d.matrix.MatrixStack poseStack, float f, float g, float h) {
        float j = net.minecraft.util.math.MathHelper.lerp(h, squid.xBodyRotO, squid.xBodyRot);
        float k = net.minecraft.util.math.MathHelper.lerp(h, squid.zBodyRotO, squid.zBodyRot);
        poseStack.translate(0.0F, 0.5F, 0.0F);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - g));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(j));
        poseStack.mulPose(Vector3f.YP.rotationDegrees(k));
        poseStack.translate(0.0F, -1.2F, 0.0F);
    }

    protected float getBob(ClientSquid squid, float f) {
        return net.minecraft.util.math.MathHelper.lerp(f, squid.oldTentacleAngle, squid.tentacleAngle);
    }
}
