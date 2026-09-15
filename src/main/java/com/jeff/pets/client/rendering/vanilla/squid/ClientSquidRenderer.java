package com.jeff.pets.client.rendering.vanilla.squid;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import com.jeff.pets.client.Math2;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSquid;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSquidRenderer extends PetRenderer {
    String squidTexturePath;

    public ClientSquidRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSquid(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity squidRenderState) {
        squidTexturePath = "textures/entity/squid.png";
        return new ResourceLocation("minecraft", squidTexturePath);
    }

    @Override
    public void preRenderCallback( final EntityLivingBase livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    protected void applyRotations(EntityLivingBase squid, float f, float g, float h) {
        float j = Math2.lerp(h, ((ClientSquid) squid).xBodyRotO, ((ClientSquid) squid).xBodyRot);
        float k = Math2.lerp(h, ((ClientSquid) squid).zBodyRotO, ((ClientSquid) squid).zBodyRot);
        net.minecraft.client.renderer.GlStateManager.translatef(0.0F, 0.5F, 0.0F);
        //poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - g));
        //poseStack.mulPose(Vector3f.XP.rotationDegrees(j));
        //poseStack.mulPose(Vector3f.YP.rotationDegrees(k));
        net.minecraft.client.renderer.GlStateManager.translatef(0.0F, -1.2F, 0.0F);
    }

    protected float getBob(ClientSquid squid, float f) {
        return Math2.lerp(f, squid.oldTentacleAngle, squid.tentacleAngle);
    }
}

