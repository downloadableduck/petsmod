package com.jeff.pets.client.rendering.vanilla.skeleton;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import static net.ornithemc.osl.blocks.api.block.Blocks.AIR;

public class SkeletonModel extends PetModel {
    public ModelPart head;
    public ModelPart hat;
    public ModelPart body;
    public ModelPart rightArm;
    public ModelPart leftArm;
    public ModelPart rightLeg;
    public ModelPart leftLeg;

    public SkeletonModel() {
        this(0.0F, false);
    }

    public SkeletonModel(float reduction, boolean stray) {
        this.head = new ModelPart(this, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, reduction);
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.hat = new ModelPart(this, 32, 0);
        this.hat.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, reduction + 0.5F);
        this.hat.setPos(0.0F, 0.0F, 0.0F);
        this.body = new ModelPart(this, 16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, reduction);
        this.body.setPos(0.0F, 0.0F, 0.0F);
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

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setupAnimation(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        if (this.head != null) this.head.render(scale);
        if (this.hat != null) this.hat.render(scale);
        if (this.body != null) this.body.render(scale);
        if (this.rightArm != null) this.rightArm.render(scale);
        if (this.leftArm != null) this.leftArm.render(scale);
        if (this.rightLeg != null) this.rightLeg.render(scale);
        if (this.leftLeg != null) this.leftLeg.render(scale);
    }

    public void prepare(MobEntity mob, float walkAnimationProgress, float walkAnimationSpeed, float tickDelta) {
        super.prepare(mob, walkAnimationProgress, walkAnimationSpeed, tickDelta);
    }

    @Override
    public void setupAnimation(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        if (this.head != null) {
            this.head.rotationY = yaw * ((float)Math.PI / 180F);
            this.head.rotationX = pitch * ((float)Math.PI / 180F);
        }
        if (this.hat != null && this.head != null) {
            this.hat.rotationY = this.head.rotationY;
            this.hat.rotationX = this.head.rotationX;
        }
        if (this.rightLeg != null) this.rightLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F) * 1.4F * walkAnimationSpeed;
        if (this.leftLeg != null) this.leftLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F + (float)Math.PI) * 1.4F * walkAnimationSpeed;
        if (this.rightArm != null) this.rightArm.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F + (float)Math.PI) * 2.0F * walkAnimationSpeed * 0.5F;
        if (this.leftArm != null) this.leftArm.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F) * 2.0F * walkAnimationSpeed * 0.5F;

        ItemStack itemStack = new ItemStack(AIR);
        if ((itemStack == null || itemStack.getItem() != Item.BOW)) {
            float f = MathHelper.sin(this.attackAnimationProgress * (float)Math.PI);
            float g = MathHelper.sin((1.0F - (1.0F - this.attackAnimationProgress) * (1.0F - this.attackAnimationProgress)) * (float)Math.PI);
            if (this.rightArm != null) {
                this.rightArm.rotationX -= f * 1.2F - g * 0.4F;
                this.rightArm.rotationZ += MathHelper.cos(bob * 0.09F) * 0.05F + 0.05F;
                this.rightArm.rotationX += MathHelper.sin(bob * 0.067F) * 0.05F;
            }
            if (this.leftArm != null) {
                this.leftArm.rotationX -= f * 1.2F - g * 0.4F;
                this.leftArm.rotationZ -= MathHelper.cos(bob * 0.09F) * 0.05F + 0.05F;
                this.leftArm.rotationX -= MathHelper.sin(bob * 0.067F) * 0.05F;
            }
        }
    }
}
