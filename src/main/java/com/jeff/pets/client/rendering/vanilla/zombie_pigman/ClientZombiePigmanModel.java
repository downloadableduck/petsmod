package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import com.jeff.pets.mob.vanilla.neutral.ClientZombiePigman;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.item.CrossbowItem;
import net.minecraft.util.math.MathHelper;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombiePigmanModel extends EntityModel<ClientZombiePigman> {
    private final Cuboid head;
    private final Cuboid headwear;
    private final Cuboid body;
    private final Cuboid left_arm;
    private final Cuboid right_arm;
    private final Cuboid left_leg;
    private final Cuboid right_leg;
    public BipedEntityModel.ArmPose leftArmPose = BipedEntityModel.ArmPose.EMPTY;
    public BipedEntityModel.ArmPose rightArmPose = BipedEntityModel.ArmPose.EMPTY;
    public boolean crouching;
    public float swimAmount;
    private float itemUseTicks;

    public ClientZombiePigmanModel() {
        textureWidth = 64;
        textureHeight = 64;

        head = new Cuboid(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false);

        headwear = new Cuboid(this);
        headwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        headwear.setTextureOffset(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F, false);

        body = new Cuboid(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F, false);

        left_arm = new Cuboid(this);
        left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
        left_arm.setTextureOffset(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false);

        right_arm = new Cuboid(this);
        right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        right_arm.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false);

        left_leg = new Cuboid(this);
        left_leg.setRotationPoint(1.9F, 12.0F, 0.0F);
        left_leg.setTextureOffset(0, 16).addBox(-1.9F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);

        right_leg = new Cuboid(this);
        right_leg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        right_leg.setTextureOffset(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);
    }

    @Override
    public void setAngles(ClientZombiePigman state, float f, float g, float h, float i, float j, float s) {
        boolean bl = false;
        boolean bl2 = state.isInSwimmingPose();
        this.head.yaw = i * (float) (Math.PI / 180.0);
        if (bl) {
            this.head.pitch = (float) (-Math.PI / 4);
        } else if (this.swimAmount > 0.0F) {
            if (bl2) {
                this.head.pitch = this.rotlerpRad(this.head.pitch, (float) (-Math.PI / 4), this.swimAmount);
            } else {
                this.head.pitch = this.rotlerpRad(this.head.pitch, j * (float) (Math.PI / 180.0), this.swimAmount);
            }
        } else {
            this.head.pitch = j * (float) (Math.PI / 180.0);
        }

        this.body.yaw = 0.0F;
        this.right_arm.rotationPointZ = 0.0F;
        this.right_arm.rotationPointX = -5.0F;
        this.left_arm.rotationPointZ = 0.0F;
        this.left_arm.rotationPointX = 5.0F;
        float k = 1.0F;
        if (bl) {
            k = (float) state.getVelocity().lengthSquared();
            k /= 0.2F;
            k *= k * k;
        }

        if (k < 1.0F) {
            k = 1.0F;
        }

        this.right_arm.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * g * 0.5F / k;
        this.left_arm.pitch = MathHelper.cos(f * 0.6662F) * 2.0F * g * 0.5F / k;
        this.right_arm.roll = 0.0F;
        this.left_arm.roll = 0.0F;
        this.right_leg.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g / k;
        this.left_leg.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g / k;
        this.right_leg.yaw = 0.0F;
        this.left_leg.yaw = 0.0F;
        this.right_leg.roll = 0.0F;
        this.left_leg.roll = 0.0F;
        if (state.isPassenger()) {
            this.right_arm.pitch += (float) (-Math.PI / 5);
            this.left_arm.pitch += (float) (-Math.PI / 5);
            this.right_leg.pitch = -1.4137167F;
            this.right_leg.yaw = (float) (Math.PI / 10);
            this.right_leg.roll = 0.07853982F;
            this.left_leg.pitch = -1.4137167F;
            this.left_leg.yaw = (float) (-Math.PI / 10);
            this.left_leg.roll = -0.07853982F;
        }

        this.right_arm.yaw = 0.0F;
        this.right_arm.roll = 0.0F;
        switch (this.leftArmPose) {
            case EMPTY:
                this.left_arm.yaw = 0.0F;
                break;
            case BLOCK:
                this.left_arm.pitch = this.left_arm.pitch * 0.5F - 0.9424779F;
                this.left_arm.yaw = (float) (Math.PI / 6);
                break;
            case ITEM:
                this.left_arm.pitch = this.left_arm.pitch * 0.5F - (float) (Math.PI / 10);
                this.left_arm.yaw = 0.0F;
        }

        switch (this.rightArmPose) {
            case EMPTY:
                this.right_arm.yaw = 0.0F;
                break;
            case BLOCK:
                this.right_arm.pitch = this.right_arm.pitch * 0.5F - 0.9424779F;
                this.right_arm.yaw = (float) (-Math.PI / 6);
                break;
            case ITEM:
                this.right_arm.pitch = this.right_arm.pitch * 0.5F - (float) (Math.PI / 10);
                this.right_arm.yaw = 0.0F;
                break;
            case THROW_SPEAR:
                this.right_arm.pitch = this.right_arm.pitch * 0.5F - (float) Math.PI;
                this.right_arm.yaw = 0.0F;
        }

        if (this.leftArmPose == BipedEntityModel.ArmPose.THROW_SPEAR
                && this.rightArmPose != BipedEntityModel.ArmPose.BLOCK
                && this.rightArmPose != BipedEntityModel.ArmPose.THROW_SPEAR
                && this.rightArmPose != BipedEntityModel.ArmPose.BOW_AND_ARROW) {
            this.left_arm.pitch = this.left_arm.pitch * 0.5F - (float) Math.PI;
            this.left_arm.yaw = 0.0F;
        }

        if (this.handSwingProgress > 0.0F) {
            //Arm humanoidArm = Arm.field_6183;
            Cuboid Cuboid = right_arm;
            float l = this.handSwingProgress;
            this.body.yaw = MathHelper.sin(MathHelper.sqrt(l) * (float) (Math.PI * 2)) * 0.2F;
            //if (humanoidArm == Arm.field_6182) {
            //this.body.yaw *= -1.0F;
            //}

            this.right_arm.rotationPointZ = MathHelper.sin(this.body.yaw) * 5.0F;
            this.right_arm.rotationPointX = -MathHelper.cos(this.body.yaw) * 5.0F;
            this.left_arm.rotationPointZ = -MathHelper.sin(this.body.yaw) * 5.0F;
            this.left_arm.rotationPointX = MathHelper.cos(this.body.yaw) * 5.0F;
            this.right_arm.yaw = this.right_arm.yaw + this.body.yaw;
            this.left_arm.yaw = this.left_arm.yaw + this.body.yaw;
            this.left_arm.pitch = this.left_arm.pitch + this.body.yaw;
            l = 1.0F - this.handSwingProgress;
            l *= l;
            l *= l;
            l = 1.0F - l;
            float m = MathHelper.sin(l * (float) Math.PI);
            float n = MathHelper.sin(this.handSwingProgress * (float) Math.PI) * -(this.head.pitch - 0.7F) * 0.75F;
            Cuboid.pitch = (float) (Cuboid.pitch - (m * 1.2 + n));
            Cuboid.yaw = Cuboid.yaw + this.body.yaw * 2.0F;
            Cuboid.roll = Cuboid.roll + MathHelper.sin(this.handSwingProgress * (float) Math.PI) * -0.4F;
        }

        if (this.crouching) {
            this.body.pitch = 0.5F;
            this.right_arm.pitch += 0.4F;
            this.left_arm.pitch += 0.4F;
            this.right_leg.rotationPointZ = 4.0F;
            this.left_leg.rotationPointZ = 4.0F;
            this.right_leg.rotationPointY = 12.2F;
            this.left_leg.rotationPointY = 12.2F;
            this.head.rotationPointY = 4.2F;
            this.body.rotationPointY = 3.2F;
            this.left_arm.rotationPointY = 5.2F;
            this.right_arm.rotationPointY = 5.2F;
        } else {
            this.body.pitch = 0.0F;
            this.right_leg.rotationPointZ = 0.1F;
            this.left_leg.rotationPointZ = 0.1F;
            this.right_leg.rotationPointY = 12.0F;
            this.left_leg.rotationPointY = 12.0F;
            this.head.rotationPointY = 0.0F;
            this.body.rotationPointY = 0.0F;
            this.left_arm.rotationPointY = 2.0F;
            this.right_arm.rotationPointY = 2.0F;
        }

        this.right_arm.roll = this.right_arm.roll + (MathHelper.cos(h * 0.09F) * 0.05F + 0.05F);
        this.left_arm.roll = this.left_arm.roll - (MathHelper.cos(h * 0.09F) * 0.05F + 0.05F);
        this.right_arm.pitch = this.right_arm.pitch + MathHelper.sin(h * 0.067F) * 0.05F;
        this.left_arm.pitch = this.left_arm.pitch - MathHelper.sin(h * 0.067F) * 0.05F;
        if (this.rightArmPose == BipedEntityModel.ArmPose.BOW_AND_ARROW) {
            this.right_arm.yaw = -0.1F + this.head.yaw;
            this.left_arm.yaw = 0.1F + this.head.yaw + 0.4F;
            this.right_arm.pitch = (float) (-Math.PI / 2) + this.head.pitch;
            this.left_arm.pitch = (float) (-Math.PI / 2) + this.head.pitch;
        } else if (this.leftArmPose == BipedEntityModel.ArmPose.BOW_AND_ARROW
                && this.rightArmPose != BipedEntityModel.ArmPose.THROW_SPEAR
                && this.rightArmPose != BipedEntityModel.ArmPose.BLOCK) {
            this.right_arm.yaw = -0.1F + this.head.yaw - 0.4F;
            this.left_arm.yaw = 0.1F + this.head.yaw;
            this.right_arm.pitch = (float) (-Math.PI / 2) + this.head.pitch;
            this.left_arm.pitch = (float) (-Math.PI / 2) + this.head.pitch;
        }

        float o = CrossbowItem.getPullTime(state.getActiveItem());
        if (this.rightArmPose == BipedEntityModel.ArmPose.CROSSBOW_CHARGE) {
            this.right_arm.yaw = -0.8F;
            this.right_arm.pitch = -0.97079635F;
            this.left_arm.pitch = -0.97079635F;
            float p = MathHelper.clamp(this.itemUseTicks, 0.0F, o);
            this.left_arm.yaw = MathHelper.lerp(p / o, 0.4F, 0.85F);
            this.left_arm.pitch = MathHelper.lerp(p / o, this.left_arm.pitch, (float) (-Math.PI / 2));
        } else if (this.leftArmPose == BipedEntityModel.ArmPose.CROSSBOW_CHARGE) {
            this.left_arm.yaw = 0.8F;
            this.right_arm.pitch = -0.97079635F;
            this.left_arm.pitch = -0.97079635F;
            float p = MathHelper.clamp(this.itemUseTicks, 0.0F, o);
            this.right_arm.yaw = MathHelper.lerp(p / o, -0.4F, -0.85F);
            this.right_arm.pitch = MathHelper.lerp(p / o, this.right_arm.pitch, (float) (-Math.PI / 2));
        }

        if (this.rightArmPose == BipedEntityModel.ArmPose.CROSSBOW_HOLD && this.handSwingProgress <= 0.0F) {
            this.right_arm.yaw = -0.3F + this.head.yaw;
            this.left_arm.yaw = 0.6F + this.head.yaw;
            this.right_arm.pitch = (float) (-Math.PI / 2) + this.head.pitch + 0.1F;
            this.left_arm.pitch = -1.5F + this.head.pitch;
        } else if (this.leftArmPose == BipedEntityModel.ArmPose.CROSSBOW_HOLD) {
            this.right_arm.yaw = -0.6F + this.head.yaw;
            this.left_arm.yaw = 0.3F + this.head.yaw;
            this.right_arm.pitch = -1.5F + this.head.pitch;
            this.left_arm.pitch = (float) (-Math.PI / 2) + this.head.pitch + 0.1F;
        }

        if (this.swimAmount > 0.0F) {
            float p = f % 26.0F;
            float l = this.handSwingProgress > 0.0F ? 0.0F : this.swimAmount;
            if (p < 14.0F) {
                this.left_arm.pitch = this.rotlerpRad(this.left_arm.pitch, 0.0F, this.swimAmount);
                this.right_arm.pitch = MathHelper.lerp(l, this.right_arm.pitch, 0.0F);
                this.left_arm.yaw = this.rotlerpRad(this.left_arm.yaw, (float) Math.PI, this.swimAmount);
                this.right_arm.yaw = MathHelper.lerp(l, this.right_arm.yaw, (float) Math.PI);
                this.left_arm.roll = this.rotlerpRad(
                        this.left_arm.roll, (float) Math.PI + 1.8707964F * this.quadraticArmUpdate(p) / this.quadraticArmUpdate(14.0F), this.swimAmount
                );
                this.right_arm.roll = MathHelper.lerp(l, this.right_arm.roll, (float) Math.PI - 1.8707964F * this.quadraticArmUpdate(p) / this.quadraticArmUpdate(14.0F));
            } else if (p >= 14.0F && p < 22.0F) {
                float m = (p - 14.0F) / 8.0F;
                this.left_arm.pitch = this.rotlerpRad(this.left_arm.pitch, (float) (Math.PI / 2) * m, this.swimAmount);
                this.right_arm.pitch = MathHelper.lerp(l, this.right_arm.pitch, (float) (Math.PI / 2) * m);
                this.left_arm.yaw = this.rotlerpRad(this.left_arm.yaw, (float) Math.PI, this.swimAmount);
                this.right_arm.yaw = MathHelper.lerp(l, this.right_arm.yaw, (float) Math.PI);
                this.left_arm.roll = this.rotlerpRad(this.left_arm.roll, 5.012389F - 1.8707964F * m, this.swimAmount);
                this.right_arm.roll = MathHelper.lerp(l, this.right_arm.roll, 1.2707963F + 1.8707964F * m);
            } else if (p >= 22.0F && p < 26.0F) {
                float m = (p - 22.0F) / 4.0F;
                this.left_arm.pitch = this.rotlerpRad(this.left_arm.pitch, (float) (Math.PI / 2) - (float) (Math.PI / 2) * m, this.swimAmount);
                this.right_arm.pitch = MathHelper.lerp(l, this.right_arm.pitch, (float) (Math.PI / 2) - (float) (Math.PI / 2) * m);
                this.left_arm.yaw = this.rotlerpRad(this.left_arm.yaw, (float) Math.PI, this.swimAmount);
                this.right_arm.yaw = MathHelper.lerp(l, this.right_arm.yaw, (float) Math.PI);
                this.left_arm.roll = this.rotlerpRad(this.left_arm.roll, (float) Math.PI, this.swimAmount);
                this.right_arm.roll = MathHelper.lerp(l, this.right_arm.roll, (float) Math.PI);
            }

            float m = 0.3F;
            float n = 0.33333334F;
            this.left_leg.pitch = MathHelper.lerp(this.swimAmount, this.left_leg.pitch, 0.3F * MathHelper.cos(f * 0.33333334F + (float) Math.PI));
            this.right_leg.pitch = MathHelper.lerp(this.swimAmount, this.right_leg.pitch, 0.3F * MathHelper.cos(f * 0.33333334F));
        }
    }

    @Override
    public void render(ClientZombiePigman zombie, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        this.render(zombie, packedLight, packedOverlay, red, blue, green, alpha, 0);
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

    public void render(ClientZombiePigman zombie, float b, float j, float f, float g, float h, float k, int i) {
        super.render(zombie, b, j, f, g, h, k);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(1.5f, 1.5f, 1.5f);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scalef(1, 1, 1);
        }
        //this.head.rotate(poseStack);
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }

    private float quadraticArmUpdate(float f) {
        return -65.0F * f + f * f;
    }
}
