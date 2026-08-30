//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.skeleton;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.model.entity.HumanoidModel;
import net.minecraft.client.render.model.entity.HumanoidModel.ArmPose;
import net.minecraft.entity.Entity;
import net.minecraft.entity.living.Arm;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.mob.monster.AbstractSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.InteractionHand;

@Environment(EnvType.CLIENT)
public class SkeletonModel extends HumanoidModel {
    public SkeletonModel() {
        this(0.0F, false);
    }

    public SkeletonModel(float reduction, boolean stray) {
        super(reduction, 0.0F, 64, 32);
        if (!stray) {
            this.rightArm = new ModelPart(this, 40, 16);
            this.rightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, reduction);
            this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
            this.leftArm = new ModelPart(this, 40, 16);
            this.leftArm.flipped = true;
            this.leftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, reduction);
            this.leftArm.setPos(5.0F, 2.0F, 0.0F);
            this.rightLeg = new ModelPart(this, 0, 16);
            this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, reduction);
            this.rightLeg.setPos(-2.0F, 12.0F, 0.0F);
            this.leftLeg = new ModelPart(this, 0, 16);
            this.leftLeg.flipped = true;
            this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, reduction);
            this.leftLeg.setPos(2.0F, 12.0F, 0.0F);
        }

    }

    public void prepare(LivingEntity mob, float walkAnimationProgress, float walkAnimationSpeed, float tickDelta) {
        this.rightArmPose = ArmPose.EMPTY;
        this.leftArmPose = ArmPose.EMPTY;
        ItemStack itemStack = mob.getItemInHand(InteractionHand.MAIN_HAND);
        if (itemStack.getItem() == Items.BOW) {
            if (mob.getMainArm() == Arm.RIGHT) {
                this.rightArmPose = ArmPose.BOW_AND_ARROW;
            } else {
                this.leftArmPose = ArmPose.BOW_AND_ARROW;
            }
        }

        super.prepare(mob, walkAnimationProgress, walkAnimationSpeed, tickDelta);
    }

    public void setupAnimation(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale, Entity entity) {
        super.setupAnimation(walkAnimationProgress, walkAnimationSpeed, bob, yaw, pitch, scale, entity);
        ItemStack itemStack = ((LivingEntity)entity).getItemInMainHand();
        if ((itemStack.isEmpty() || itemStack.getItem() != Items.BOW)) {
            float f = MathHelper.sin(this.attackAnimationProgress * (float)Math.PI);
            float g = MathHelper.sin((1.0F - (1.0F - this.attackAnimationProgress) * (1.0F - this.attackAnimationProgress)) * (float)Math.PI);
            /*this.rightArm.rotationZ = 0.0F;
            this.leftArm.rotationZ = 0.0F;
            this.rightArm.rotationY = -(0.1F - f * 0.6F);
            this.leftArm.rotationY = 0.1F - f * 0.6F;
            this.rightArm.rotationX = (-(float)Math.PI / 2F);
            this.leftArm.rotationX = (-(float)Math.PI / 2F);*/
            ModelPart var10000 = this.rightArm;
            var10000.rotationX -= f * 1.2F - g * 0.4F;
            var10000 = this.leftArm;
            var10000.rotationX -= f * 1.2F - g * 0.4F;
            var10000 = this.rightArm;
            var10000.rotationZ += MathHelper.cos(bob * 0.09F) * 0.05F + 0.05F;
            var10000 = this.leftArm;
            var10000.rotationZ -= MathHelper.cos(bob * 0.09F) * 0.05F + 0.05F;
            var10000 = this.rightArm;
            var10000.rotationX += MathHelper.sin(bob * 0.067F) * 0.05F;
            var10000 = this.leftArm;
            var10000.rotationX -= MathHelper.sin(bob * 0.067F) * 0.05F;
        }

    }

    public void translateArm(float tickDelta, Arm arm) {
        /*float f = arm == Arm.RIGHT ? 1.0F : -1.0F;
        ModelPart modelPart = this.getArmModel(arm);
        modelPart.x += f;
        modelPart.transform(tickDelta);
        modelPart.x -= f;*/
    }
}
