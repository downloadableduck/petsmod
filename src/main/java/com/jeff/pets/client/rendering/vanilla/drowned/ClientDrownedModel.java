package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.ModelUtils;
import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedModel extends BipedModel<ClientDrowned> {

    public ClientDrownedModel(float f, float g, int i, int j) {
        super(f, g, i, j);
        this.rightArm = new RendererModel(this, 32, 48);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f);
        this.rightArm.setPos(-5.0F, 2.0F + g, 0.0F);
        this.rightLeg = new RendererModel(this, 16, 48);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f);
        this.rightLeg.setPos(-1.9F, 12.0F + g, 0.0F);
    }

    @Override
    public void setupAnim(ClientDrowned state, float f, float g, float h, float i, float k, float j) {
        super.setupAnim(state, f, g, h, i, k, j);
        ModelUtils.animateZombieArms(this.leftArm, this.rightArm, true, this.attackTime, h);
    }

    public void prepareMobModel(ClientDrowned zombie, float f, float g, float h) {
        this.rightArmPose = ArmPose.EMPTY;
        this.leftArmPose = ArmPose.EMPTY;

        super.prepareMobModel(zombie, f, g, h);

        if (this.rightArmPose == ArmPose.THROW_SPEAR) {
            this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float) Math.PI;
            this.rightArm.yRot = 0.0F;
        }

        if (this.swimAmount > 0.0F) {
            this.rightArm.xRot = this.rotlerpRad(this.swimAmount, this.rightArm.xRot, -2.5132742F) + this.swimAmount * 0.35F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            this.leftArm.xRot = this.rotlerpRad(this.swimAmount, this.leftArm.xRot, -2.5132742F) - this.swimAmount * 0.35F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            this.rightArm.zRot = this.rotlerpRad(this.swimAmount, this.rightArm.zRot, -0.15F);
            this.leftArm.zRot = this.rotlerpRad(this.swimAmount, this.leftArm.zRot, 0.15F);
            RendererModel var10000 = this.leftLeg;
            var10000.xRot -= this.swimAmount * 0.55F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            var10000 = this.rightLeg;
            var10000.xRot += this.swimAmount * 0.55F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            this.head.xRot = 0.0F;
        }
    }

    @Override
    public void render(ClientDrowned drowned, float i, float j, float f, float g, float h, float k) {
        super.render(drowned, i, j, f, g, h, k);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(1.5f, 1.5f, 1.5f);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scalef(1, 1, 1);
        }
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();;
    }
}
