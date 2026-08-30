package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import com.jeff.pets.client.Math2;
import com.jeff.pets.mob.vanilla.neutral.ClientZombiePigman;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.model.entity.HumanoidModel;
import net.minecraft.entity.Entity;

import net.minecraft.util.math.MathHelper;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombiePigmanModel extends Model {
    private final ModelPart head;
    private final ModelPart headwear;
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart left_leg;
    private final ModelPart right_leg;
    public HumanoidModel.ArmPose leftArmPose = HumanoidModel.ArmPose.EMPTY;
    public HumanoidModel.ArmPose rightArmPose = HumanoidModel.ArmPose.EMPTY;
    public boolean crouching;
    public float swimAmount;
    private float itemUseTicks;

    public ClientZombiePigmanModel() {
        textureWidth /*textureWidth*/ = 64;
        textureHeight /*textureHeight*/ = 64;

        head = new ModelPart(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.setTextureCoords(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false);

        headwear = new ModelPart(this);
        headwear.setPos(0.0F, 0.0F, 0.0F);
        headwear.setTextureCoords(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F, false);

        body = new ModelPart(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.setTextureCoords(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F, false);

        left_arm = new ModelPart(this);
        left_arm.setPos(5.0F, 2.0F, 0.0F);
        left_arm.setTextureCoords(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false);

        right_arm = new ModelPart(this);
        right_arm.setPos(-5.0F, 2.0F, 0.0F);
        right_arm.setTextureCoords(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false);

        left_leg = new ModelPart(this);
        left_leg.setPos(1.9F, 12.0F, 0.0F);
        left_leg.setTextureCoords(0, 16).addBox(-1.9F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);

        right_leg = new ModelPart(this);
        right_leg.setPos(-1.9F, 12.0F, 0.0F);
        right_leg.setTextureCoords(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);
    }

    @Override
    public void setupAnimation(float f, float g, float h, float i, float j, float s, net.minecraft.entity.Entity entity) {
        ClientZombiePigman state = (ClientZombiePigman) entity;
        boolean bl = false;
        boolean bl2 = state.isSwimming();
        this.head.rotationY = i * (float) (Math.PI / 180.0);
        if (bl) {
            this.head.rotationX = (float) (-Math.PI / 4);
        } else if (this.swimAmount > 0.0F) {
            if (bl2) {
                this.head.rotationX = this.rotlerpRad(this.head.rotationX, (float) (-Math.PI / 4), this.swimAmount);
            } else {
                this.head.rotationX = this.rotlerpRad(this.head.rotationX, j * (float) (Math.PI / 180.0), this.swimAmount);
            }
        } else {
            this.head.rotationX = j * (float) (Math.PI / 180.0);
        }

        this.body.rotationY = 0.0F;
        this.right_arm.z = 0.0F;
        this.right_arm.x = -5.0F;
        this.left_arm.z = 0.0F;
        this.left_arm.x = 5.0F;
        float k = 1.0F;
        if (bl) {
            k = (float) state.getVelocity().squaredDistanceToOrigin();
            k /= 0.2F;
            k *= k * k;
        }

        if (k < 1.0F) {
            k = 1.0F;
        }

        this.right_arm.rotationX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * g * 0.5F / k;
        this.left_arm.rotationX = MathHelper.cos(f * 0.6662F) * 2.0F * g * 0.5F / k;
        this.right_arm.rotationZ = 0.0F;
        this.left_arm.rotationZ = 0.0F;
        this.right_leg.rotationX = MathHelper.cos(f * 0.6662F) * 1.4F * g / k;
        this.left_leg.rotationX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g / k;
        this.right_leg.rotationY = 0.0F;
        this.left_leg.rotationY = 0.0F;
        this.right_leg.rotationZ = 0.0F;
        this.left_leg.rotationZ = 0.0F;
        if (state.isPassenger()) {
            this.right_arm.rotationX += (float) (-Math.PI / 5);
            this.left_arm.rotationX += (float) (-Math.PI / 5);
            this.right_leg.rotationX = -1.4137167F;
            this.right_leg.rotationY = (float) (Math.PI / 10);
            this.right_leg.rotationZ = 0.07853982F;
            this.left_leg.rotationX = -1.4137167F;
            this.left_leg.rotationY = (float) (-Math.PI / 10);
            this.left_leg.rotationZ = -0.07853982F;
        }

        this.right_arm.rotationY = 0.0F;
        this.right_arm.rotationZ = 0.0F;
        switch (this.leftArmPose) {
            case EMPTY:
                this.left_arm.rotationY = 0.0F;
                break;
            case BLOCK:
                this.left_arm.rotationX = this.left_arm.rotationX * 0.5F - 0.9424779F;
                this.left_arm.rotationY = (float) (Math.PI / 6);
                break;
            case ITEM:
                this.left_arm.rotationX = this.left_arm.rotationX * 0.5F - (float) (Math.PI / 10);
                this.left_arm.rotationY = 0.0F;
        }

        switch (this.rightArmPose) {
            case EMPTY:
                this.right_arm.rotationY = 0.0F;
                break;
            case BLOCK:
                this.right_arm.rotationX = this.right_arm.rotationX * 0.5F - 0.9424779F;
                this.right_arm.rotationY = (float) (-Math.PI / 6);
                break;
            case ITEM:
                this.right_arm.rotationX = this.right_arm.rotationX * 0.5F - (float) (Math.PI / 10);
                this.right_arm.rotationY = 0.0F;
                break;
            case THROW_SPEAR:
                this.right_arm.rotationX = this.right_arm.rotationX * 0.5F - (float) Math.PI;
                this.right_arm.rotationY = 0.0F;
        }

        if (this.leftArmPose == HumanoidModel.ArmPose.THROW_SPEAR
                && this.rightArmPose != HumanoidModel.ArmPose.BLOCK
                && this.rightArmPose != HumanoidModel.ArmPose.THROW_SPEAR
                && this.rightArmPose != HumanoidModel.ArmPose.BOW_AND_ARROW) {
            this.left_arm.rotationX = this.left_arm.rotationX * 0.5F - (float) Math.PI;
            this.left_arm.rotationY = 0.0F;
        }

        if (this.attackAnimationProgress > 0.0F) {
            //Arm humanoidArm = Arm.field_6183;
            ModelPart ModelPart = right_arm;
            float l = this.attackAnimationProgress;
            this.body.rotationY = MathHelper.sin(MathHelper.sqrt(l) * (float) (Math.PI * 2)) * 0.2F;
            //if (humanoidArm == Arm.field_6182) {
            //this.body.rotationY *= -1.0F;
            //}

            this.right_arm.z = MathHelper.sin(this.body.rotationY) * 5.0F;
            this.right_arm.x = -MathHelper.cos(this.body.rotationY) * 5.0F;
            this.left_arm.z = -MathHelper.sin(this.body.rotationY) * 5.0F;
            this.left_arm.x = MathHelper.cos(this.body.rotationY) * 5.0F;
            this.right_arm.rotationY = this.right_arm.rotationY + this.body.rotationY;
            this.left_arm.rotationY = this.left_arm.rotationY + this.body.rotationY;
            this.left_arm.rotationX = this.left_arm.rotationX + this.body.rotationY;
            l = 1.0F - this.attackAnimationProgress;
            l *= l;
            l *= l;
            l = 1.0F - l;
            float m = MathHelper.sin(l * (float) Math.PI);
            float n = MathHelper.sin(this.attackAnimationProgress * (float) Math.PI) * -(this.head.rotationX - 0.7F) * 0.75F;
            ModelPart.rotationX = (float) (ModelPart.rotationX - (m * 1.2 + n));
            ModelPart.rotationY = ModelPart.rotationY + this.body.rotationY * 2.0F;
            ModelPart.rotationZ = ModelPart.rotationZ + MathHelper.sin(this.attackAnimationProgress * (float) Math.PI) * -0.4F;
        }

        if (this.crouching) {
            this.body.rotationX = 0.5F;
            this.right_arm.rotationX += 0.4F;
            this.left_arm.rotationX += 0.4F;
            this.right_leg.z = 4.0F;
            this.left_leg.z = 4.0F;
            this.right_leg.y = 12.2F;
            this.left_leg.y = 12.2F;
            this.head.y = 4.2F;
            this.body.y = 3.2F;
            this.left_arm.y = 5.2F;
            this.right_arm.y = 5.2F;
        } else {
            this.body.rotationX = 0.0F;
            this.right_leg.z = 0.1F;
            this.left_leg.z = 0.1F;
            this.right_leg.y = 12.0F;
            this.left_leg.y = 12.0F;
            this.head.y = 0.0F;
            this.body.y = 0.0F;
            this.left_arm.y = 2.0F;
            this.right_arm.y = 2.0F;
        }

        this.right_arm.rotationZ = this.right_arm.rotationZ + (MathHelper.cos(h * 0.09F) * 0.05F + 0.05F);
        this.left_arm.rotationZ = this.left_arm.rotationZ - (MathHelper.cos(h * 0.09F) * 0.05F + 0.05F);
        this.right_arm.rotationX = this.right_arm.rotationX + MathHelper.sin(h * 0.067F) * 0.05F;
        this.left_arm.rotationX = this.left_arm.rotationX - MathHelper.sin(h * 0.067F) * 0.05F;
        if (this.rightArmPose == HumanoidModel.ArmPose.BOW_AND_ARROW) {
            this.right_arm.rotationY = -0.1F + this.head.rotationY;
            this.left_arm.rotationY = 0.1F + this.head.rotationY + 0.4F;
            this.right_arm.rotationX = (float) (-Math.PI / 2) + this.head.rotationX;
            this.left_arm.rotationX = (float) (-Math.PI / 2) + this.head.rotationX;
        } else if (this.leftArmPose == HumanoidModel.ArmPose.BOW_AND_ARROW
                && this.rightArmPose != HumanoidModel.ArmPose.THROW_SPEAR
                && this.rightArmPose != HumanoidModel.ArmPose.BLOCK) {
            this.right_arm.rotationY = -0.1F + this.head.rotationY - 0.4F;
            this.left_arm.rotationY = 0.1F + this.head.rotationY;
            this.right_arm.rotationX = (float) (-Math.PI / 2) + this.head.rotationX;
            this.left_arm.rotationX = (float) (-Math.PI / 2) + this.head.rotationX;
        }

        float o = 64;
        /* Crossbows don't exist in 1.13
        if (this.rightArmPose == HumanoidModel.ArmPose.CROSSBOW_CHARGE) {
            this.right_arm.rotationY = -0.8F;
            this.right_arm.rotationX = -0.97079635F;
            this.left_arm.rotationX = -0.97079635F;
            float p = MathHelper.clamp(this.itemUseTicks, 0.0F, o);
            this.left_arm.rotationY = (float) Math2.lerp(p / o, 0.4F, 0.85F);
            this.left_arm.rotationX = (float) Math2.lerp(p / o, this.left_arm.rotationX, (float) (-Math.PI / 2));
        } else if (this.leftArmPose == HumanoidModel.ArmPose.CROSSBOW_CHARGE) {
            this.left_arm.rotationY = 0.8F;
            this.right_arm.rotationX = -0.97079635F;
            this.left_arm.rotationX = -0.97079635F;
            float p = MathHelper.clamp(this.itemUseTicks, 0.0F, o);
            this.right_arm.rotationY = (float) Math2.lerp(p / o, -0.4F, -0.85F);
            this.right_arm.rotationX = (float) Math2.lerp(p / o, this.right_arm.rotationX, (float) (-Math.PI / 2));
        }

        if (this.rightArmPose == HumanoidModel.ArmPose.CROSSBOW_HOLD && this.attackAnimationProgress <= 0.0F) {
            this.right_arm.rotationY = -0.3F + this.head.rotationY;
            this.left_arm.rotationY = 0.6F + this.head.rotationY;
            this.right_arm.rotationX = (float) (-Math.PI / 2) + this.head.rotationX + 0.1F;
            this.left_arm.rotationX = -1.5F + this.head.rotationX;
        } else if (this.leftArmPose == HumanoidModel.ArmPose.CROSSBOW_HOLD) {
            this.right_arm.rotationY = -0.6F + this.head.rotationY;
            this.left_arm.rotationY = 0.3F + this.head.rotationY;
            this.right_arm.rotationX = -1.5F + this.head.rotationX;
            this.left_arm.rotationX = (float) (-Math.PI / 2) + this.head.rotationX + 0.1F;
        }
        */
        if (this.swimAmount > 0.0F) {
            float p = f % 26.0F;
            float l = this.attackAnimationProgress > 0.0F ? 0.0F : this.swimAmount;
            if (p < 14.0F) {
                this.left_arm.rotationX = this.rotlerpRad(this.left_arm.rotationX, 0.0F, this.swimAmount);
                this.right_arm.rotationX = (float) Math2.lerp(l, this.right_arm.rotationX, 0.0F);
                this.left_arm.rotationY = this.rotlerpRad(this.left_arm.rotationY, (float) Math.PI, this.swimAmount);
                this.right_arm.rotationY = (float) Math2.lerp(l, this.right_arm.rotationY, (float) Math.PI);
                this.left_arm.rotationZ = this.rotlerpRad(
                        this.left_arm.rotationZ, (float) Math.PI + 1.8707964F * this.quadraticArmUpdate(p) / this.quadraticArmUpdate(14.0F), this.swimAmount
                );
                this.right_arm.rotationZ = (float) Math2.lerp(l, this.right_arm.rotationZ, (float) Math.PI - 1.8707964F * this.quadraticArmUpdate(p) / this.quadraticArmUpdate(14.0F));
            } else if (p >= 14.0F && p < 22.0F) {
                float m = (p - 14.0F) / 8.0F;
                this.left_arm.rotationX = this.rotlerpRad(this.left_arm.rotationX, (float) (Math.PI / 2) * m, this.swimAmount);
                this.right_arm.rotationX = (float) Math2.lerp(l, this.right_arm.rotationX, (float) (Math.PI / 2) * m);
                this.left_arm.rotationY = this.rotlerpRad(this.left_arm.rotationY, (float) Math.PI, this.swimAmount);
                this.right_arm.rotationY = (float) Math2.lerp(l, this.right_arm.rotationY, (float) Math.PI);
                this.left_arm.rotationZ = this.rotlerpRad(this.left_arm.rotationZ, 5.012389F - 1.8707964F * m, this.swimAmount);
                this.right_arm.rotationZ = (float) Math2.lerp(l, this.right_arm.rotationZ, 1.2707963F + 1.8707964F * m);
            } else if (p >= 22.0F && p < 26.0F) {
                float m = (p - 22.0F) / 4.0F;
                this.left_arm.rotationX = this.rotlerpRad(this.left_arm.rotationX, (float) (Math.PI / 2) - (float) (Math.PI / 2) * m, this.swimAmount);
                this.right_arm.rotationX = (float) Math2.lerp(l, this.right_arm.rotationX, (float) (Math.PI / 2) - (float) (Math.PI / 2) * m);
                this.left_arm.rotationY = this.rotlerpRad(this.left_arm.rotationY, (float) Math.PI, this.swimAmount);
                this.right_arm.rotationY = (float) Math2.lerp(l, this.right_arm.rotationY, (float) Math.PI);
                this.left_arm.rotationZ = this.rotlerpRad(this.left_arm.rotationZ, (float) Math.PI, this.swimAmount);
                this.right_arm.rotationZ = (float) Math2.lerp(l, this.right_arm.rotationZ, (float) Math.PI);
            }

            float m = 0.3F;
            float n = 0.33333334F;
            this.left_leg.rotationX = (float) Math2.lerp(this.swimAmount, this.left_leg.rotationX, 0.3F * MathHelper.cos(f * 0.33333334F + (float) Math.PI));
            this.right_leg.rotationX = (float) Math2.lerp(this.swimAmount, this.right_leg.rotationX, 0.3F * MathHelper.cos(f * 0.33333334F));
        }
    }

    @Override
    public void render(Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        this.render(entity, packedLight, packedOverlay, red, blue, green, alpha, 0);
        head.render(alpha);
        headwear.render(alpha);
        body.render(alpha);
        left_arm.render(alpha);
        right_arm.render(alpha);
        left_leg.render(alpha);
        right_leg.render(alpha);
    }

    protected float rotlerpRad(float f, float g, float h) {
        float i = (g - f) % (float) (Math.PI * 2);
        if (i < (float) -Math.PI) {
            i += (float) (Math.PI * 2);
        }

        if (i >= (float) Math.PI) {
            i -= (float) (Math.PI * 2);
        }

        return f + h * i;
    }

    public void render(net.minecraft.entity.Entity entity, float b, float j, float f, float g, float h, float k, int i) {
        super.render(entity, b, j, f, g, h, k);
        net.minecraft.client.render.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(1.5f, 1.5f, 1.5f);
        } else {
            net.minecraft.client.render.platform.GlStateManager.scalef(1, 1, 1);
        }
        //this.head.rotate(poseStack);
        net.minecraft.client.render.platform.GlStateManager.popMatrix();
    }

    private float quadraticArmUpdate(float f) {
        return -65.0F * f + f * f;
    }
}