package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.AnimationUtils;
import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.model.entity.HumanoidModel;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedModel extends HumanoidModel<ClientDrowned> {

    public ClientDrownedModel(float f, float g, int i, int j) {
        super(f, g, i, j);
        this.rightArm = new ModelPart(this, 32, 48);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, f);
        this.rightArm.setPos(-5.0F, 2.0F + g, 0.0F);
        this.rightLeg = new ModelPart(this, 16, 48);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
        this.rightLeg.setPos(-1.9F, 12.0F + g, 0.0F);
    }

    @Override
    public void setup(@NotNull ClientDrowned state, float f, float g, float h, float i, float k, float s) {
        super.setup(state, f, g, h, i, k, s);
        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, true, this.handSwingProgress, h);
    }

    public void prepare(ClientDrowned zombie, float f, float g, float h) {
        this.rightArmPose = ArmPose.EMPTY;
        this.leftArmPose = ArmPose.EMPTY;

        super.prepare(zombie, f, g, h);

        if (this.rightArmPose == ArmPose.THROW_SPEAR) {
            this.rightArm.rotationX = this.rightArm.rotationX * 0.5F - (float) Math.PI;
            this.rightArm.rotationY = 0.0F;
        }

        if (this.swimAmount > 0.0F) {
            this.rightArm.rotationX = this.lerpAngle(this.swimAmount, this.rightArm.rotationX, -2.5132742F) + this.swimAmount * 0.35F * MathHelper.sin(0.1F * h);
            this.leftArm.rotationX = this.lerpAngle(this.swimAmount, this.leftArm.rotationX, -2.5132742F) - this.swimAmount * 0.35F * MathHelper.sin(0.1F * h);
            this.rightArm.rotationZ = this.lerpAngle(this.swimAmount, this.rightArm.rotationZ, -0.15F);
            this.leftArm.rotationZ = this.lerpAngle(this.swimAmount, this.leftArm.rotationZ, 0.15F);
            ModelPart var10000 = this.leftLeg;
            var10000.rotationX -= this.swimAmount * 0.55F * MathHelper.sin(0.1F * h);
            var10000 = this.rightLeg;
            var10000.rotationX += this.swimAmount * 0.55F * MathHelper.sin(0.1F * h);
            this.head.rotationX = 0.0F;
        }
    }

    @Override
    public void render(ClientDrowned drowned, float f, float j, float h, float i, float k, float l) {
        super.render(drowned, f, j, h, i, k, l);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(1.5f, 1.5f, 1.5f);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scale(1, 1, 1);
        }
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }
}
