package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import com.jeff.pets.mob.vanilla.neutral.ClientZombiePigman;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.item.CrossbowItem;
import net.minecraft.util.math.MathHelper;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombiePigmanModel extends EntityModel<ClientZombiePigman> {
    private final RendererModel head;
    private final RendererModel headwear;
    private final RendererModel body;
    private final RendererModel left_arm;
    private final RendererModel right_arm;
    private final RendererModel left_leg;
    private final RendererModel right_leg;
    public BipedModel.ArmPose leftArmPose = BipedModel.ArmPose.EMPTY;
    public BipedModel.ArmPose rightArmPose = BipedModel.ArmPose.EMPTY;
    public boolean crouching;
    public float swimAmount;
    private float itemUseTicks;

    public ClientZombiePigmanModel() {
        texWidth = 64;
        texHeight = 64;

        head = new RendererModel(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false);

        headwear = new RendererModel(this);
        headwear.setPos(0.0F, 0.0F, 0.0F);
        headwear.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F, false);

        body = new RendererModel(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F, false);

        left_arm = new RendererModel(this);
        left_arm.setPos(5.0F, 2.0F, 0.0F);
        left_arm.texOffs(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false);

        right_arm = new RendererModel(this);
        right_arm.setPos(-5.0F, 2.0F, 0.0F);
        right_arm.texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false);

        left_leg = new RendererModel(this);
        left_leg.setPos(1.9F, 12.0F, 0.0F);
        left_leg.texOffs(0, 16).addBox(-1.9F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);

        right_leg = new RendererModel(this);
        right_leg.setPos(-1.9F, 12.0F, 0.0F);
        right_leg.texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);
    }

    @Override
    public void setupAnim(ClientZombiePigman state, float f, float g, float h, float i, float j, float s) {
        boolean bl = false;
        boolean bl2 = state.isSwimming();
        this.head.yRot = i * (float) (Math.PI / 180.0);
        if (bl) {
            this.head.xRot = (float) (-Math.PI / 4);
        } else if (this.swimAmount > 0.0F) {
            if (bl2) {
                this.head.xRot = this.rotlerpRad(this.head.xRot, (float) (-Math.PI / 4), this.swimAmount);
            } else {
                this.head.xRot = this.rotlerpRad(this.head.xRot, j * (float) (Math.PI / 180.0), this.swimAmount);
            }
        } else {
            this.head.xRot = j * (float) (Math.PI / 180.0);
        }

        this.body.yRot = 0.0F;
        this.right_arm.z = 0.0F;
        this.right_arm.x = -5.0F;
        this.left_arm.z = 0.0F;
        this.left_arm.x = 5.0F;
        float k = 1.0F;
        if (bl) {
            k = (float) state.getDeltaMovement().lengthSqr();
            k /= 0.2F;
            k *= k * k;
        }

        if (k < 1.0F) {
            k = 1.0F;
        }

        this.right_arm.xRot = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * g * 0.5F / k;
        this.left_arm.xRot = MathHelper.cos(f * 0.6662F) * 2.0F * g * 0.5F / k;
        this.right_arm.zRot = 0.0F;
        this.left_arm.zRot = 0.0F;
        this.right_leg.xRot = MathHelper.cos(f * 0.6662F) * 1.4F * g / k;
        this.left_leg.xRot = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g / k;
        this.right_leg.yRot = 0.0F;
        this.left_leg.yRot = 0.0F;
        this.right_leg.zRot = 0.0F;
        this.left_leg.zRot = 0.0F;
        if (state.isPassenger()) {
            this.right_arm.xRot += (float) (-Math.PI / 5);
            this.left_arm.xRot += (float) (-Math.PI / 5);
            this.right_leg.xRot = -1.4137167F;
            this.right_leg.yRot = (float) (Math.PI / 10);
            this.right_leg.zRot = 0.07853982F;
            this.left_leg.xRot = -1.4137167F;
            this.left_leg.yRot = (float) (-Math.PI / 10);
            this.left_leg.zRot = -0.07853982F;
        }

        this.right_arm.yRot = 0.0F;
        this.right_arm.zRot = 0.0F;
        switch (this.leftArmPose) {
            case EMPTY:
                this.left_arm.yRot = 0.0F;
                break;
            case BLOCK:
                this.left_arm.xRot = this.left_arm.xRot * 0.5F - 0.9424779F;
                this.left_arm.yRot = (float) (Math.PI / 6);
                break;
            case ITEM:
                this.left_arm.xRot = this.left_arm.xRot * 0.5F - (float) (Math.PI / 10);
                this.left_arm.yRot = 0.0F;
        }

        switch (this.rightArmPose) {
            case EMPTY:
                this.right_arm.yRot = 0.0F;
                break;
            case BLOCK:
                this.right_arm.xRot = this.right_arm.xRot * 0.5F - 0.9424779F;
                this.right_arm.yRot = (float) (-Math.PI / 6);
                break;
            case ITEM:
                this.right_arm.xRot = this.right_arm.xRot * 0.5F - (float) (Math.PI / 10);
                this.right_arm.yRot = 0.0F;
                break;
            case THROW_SPEAR:
                this.right_arm.xRot = this.right_arm.xRot * 0.5F - (float) Math.PI;
                this.right_arm.yRot = 0.0F;
        }

        if (this.leftArmPose == BipedModel.ArmPose.THROW_SPEAR
                && this.rightArmPose != BipedModel.ArmPose.BLOCK
                && this.rightArmPose != BipedModel.ArmPose.THROW_SPEAR
                && this.rightArmPose != BipedModel.ArmPose.BOW_AND_ARROW) {
            this.left_arm.xRot = this.left_arm.xRot * 0.5F - (float) Math.PI;
            this.left_arm.yRot = 0.0F;
        }

        if (this.attackTime > 0.0F) {
            //Arm humanoidArm = Arm.field_6183;
            RendererModel RendererModel = right_arm;
            float l = this.attackTime;
            this.body.yRot = MathHelper.sin(MathHelper.sqrt(l) * (float) (Math.PI * 2)) * 0.2F;
            //if (humanoidArm == Arm.field_6182) {
            //this.body.yRot *= -1.0F;
            //}

            this.right_arm.z = MathHelper.sin(this.body.yRot) * 5.0F;
            this.right_arm.x = -MathHelper.cos(this.body.yRot) * 5.0F;
            this.left_arm.z = -MathHelper.sin(this.body.yRot) * 5.0F;
            this.left_arm.x = MathHelper.cos(this.body.yRot) * 5.0F;
            this.right_arm.yRot = this.right_arm.yRot + this.body.yRot;
            this.left_arm.yRot = this.left_arm.yRot + this.body.yRot;
            this.left_arm.xRot = this.left_arm.xRot + this.body.yRot;
            l = 1.0F - this.attackTime;
            l *= l;
            l *= l;
            l = 1.0F - l;
            float m = MathHelper.sin(l * (float) Math.PI);
            float n = MathHelper.sin(this.attackTime * (float) Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;
            RendererModel.xRot = (float) (RendererModel.xRot - (m * 1.2 + n));
            RendererModel.yRot = RendererModel.yRot + this.body.yRot * 2.0F;
            RendererModel.zRot = RendererModel.zRot + MathHelper.sin(this.attackTime * (float) Math.PI) * -0.4F;
        }

        if (this.crouching) {
            this.body.xRot = 0.5F;
            this.right_arm.xRot += 0.4F;
            this.left_arm.xRot += 0.4F;
            this.right_leg.z = 4.0F;
            this.left_leg.z = 4.0F;
            this.right_leg.y = 12.2F;
            this.left_leg.y = 12.2F;
            this.head.y = 4.2F;
            this.body.y = 3.2F;
            this.left_arm.y = 5.2F;
            this.right_arm.y = 5.2F;
        } else {
            this.body.xRot = 0.0F;
            this.right_leg.z = 0.1F;
            this.left_leg.z = 0.1F;
            this.right_leg.y = 12.0F;
            this.left_leg.y = 12.0F;
            this.head.y = 0.0F;
            this.body.y = 0.0F;
            this.left_arm.y = 2.0F;
            this.right_arm.y = 2.0F;
        }

        this.right_arm.zRot = this.right_arm.zRot + (MathHelper.cos(h * 0.09F) * 0.05F + 0.05F);
        this.left_arm.zRot = this.left_arm.zRot - (MathHelper.cos(h * 0.09F) * 0.05F + 0.05F);
        this.right_arm.xRot = this.right_arm.xRot + MathHelper.sin(h * 0.067F) * 0.05F;
        this.left_arm.xRot = this.left_arm.xRot - MathHelper.sin(h * 0.067F) * 0.05F;
        if (this.rightArmPose == BipedModel.ArmPose.BOW_AND_ARROW) {
            this.right_arm.yRot = -0.1F + this.head.yRot;
            this.left_arm.yRot = 0.1F + this.head.yRot + 0.4F;
            this.right_arm.xRot = (float) (-Math.PI / 2) + this.head.xRot;
            this.left_arm.xRot = (float) (-Math.PI / 2) + this.head.xRot;
        } else if (this.leftArmPose == BipedModel.ArmPose.BOW_AND_ARROW
                && this.rightArmPose != BipedModel.ArmPose.THROW_SPEAR
                && this.rightArmPose != BipedModel.ArmPose.BLOCK) {
            this.right_arm.yRot = -0.1F + this.head.yRot - 0.4F;
            this.left_arm.yRot = 0.1F + this.head.yRot;
            this.right_arm.xRot = (float) (-Math.PI / 2) + this.head.xRot;
            this.left_arm.xRot = (float) (-Math.PI / 2) + this.head.xRot;
        }

        float o = CrossbowItem.getChargeDuration(state.getMainHandItem());
        if (this.rightArmPose == BipedModel.ArmPose.CROSSBOW_CHARGE) {
            this.right_arm.yRot = -0.8F;
            this.right_arm.xRot = -0.97079635F;
            this.left_arm.xRot = -0.97079635F;
            float p = MathHelper.clamp(this.itemUseTicks, 0.0F, o);
            this.left_arm.yRot = MathHelper.lerp(p / o, 0.4F, 0.85F);
            this.left_arm.xRot = MathHelper.lerp(p / o, this.left_arm.xRot, (float) (-Math.PI / 2));
        } else if (this.leftArmPose == BipedModel.ArmPose.CROSSBOW_CHARGE) {
            this.left_arm.yRot = 0.8F;
            this.right_arm.xRot = -0.97079635F;
            this.left_arm.xRot = -0.97079635F;
            float p = MathHelper.clamp(this.itemUseTicks, 0.0F, o);
            this.right_arm.yRot = MathHelper.lerp(p / o, -0.4F, -0.85F);
            this.right_arm.xRot = MathHelper.lerp(p / o, this.right_arm.xRot, (float) (-Math.PI / 2));
        }

        if (this.rightArmPose == BipedModel.ArmPose.CROSSBOW_HOLD && this.attackTime <= 0.0F) {
            this.right_arm.yRot = -0.3F + this.head.yRot;
            this.left_arm.yRot = 0.6F + this.head.yRot;
            this.right_arm.xRot = (float) (-Math.PI / 2) + this.head.xRot + 0.1F;
            this.left_arm.xRot = -1.5F + this.head.xRot;
        } else if (this.leftArmPose == BipedModel.ArmPose.CROSSBOW_HOLD) {
            this.right_arm.yRot = -0.6F + this.head.yRot;
            this.left_arm.yRot = 0.3F + this.head.yRot;
            this.right_arm.xRot = -1.5F + this.head.xRot;
            this.left_arm.xRot = (float) (-Math.PI / 2) + this.head.xRot + 0.1F;
        }

        if (this.swimAmount > 0.0F) {
            float p = f % 26.0F;
            float l = this.attackTime > 0.0F ? 0.0F : this.swimAmount;
            if (p < 14.0F) {
                this.left_arm.xRot = this.rotlerpRad(this.left_arm.xRot, 0.0F, this.swimAmount);
                this.right_arm.xRot = MathHelper.lerp(l, this.right_arm.xRot, 0.0F);
                this.left_arm.yRot = this.rotlerpRad(this.left_arm.yRot, (float) Math.PI, this.swimAmount);
                this.right_arm.yRot = MathHelper.lerp(l, this.right_arm.yRot, (float) Math.PI);
                this.left_arm.zRot = this.rotlerpRad(
                        this.left_arm.zRot, (float) Math.PI + 1.8707964F * this.quadraticArmUpdate(p) / this.quadraticArmUpdate(14.0F), this.swimAmount
                );
                this.right_arm.zRot = MathHelper.lerp(l, this.right_arm.zRot, (float) Math.PI - 1.8707964F * this.quadraticArmUpdate(p) / this.quadraticArmUpdate(14.0F));
            } else if (p >= 14.0F && p < 22.0F) {
                float m = (p - 14.0F) / 8.0F;
                this.left_arm.xRot = this.rotlerpRad(this.left_arm.xRot, (float) (Math.PI / 2) * m, this.swimAmount);
                this.right_arm.xRot = MathHelper.lerp(l, this.right_arm.xRot, (float) (Math.PI / 2) * m);
                this.left_arm.yRot = this.rotlerpRad(this.left_arm.yRot, (float) Math.PI, this.swimAmount);
                this.right_arm.yRot = MathHelper.lerp(l, this.right_arm.yRot, (float) Math.PI);
                this.left_arm.zRot = this.rotlerpRad(this.left_arm.zRot, 5.012389F - 1.8707964F * m, this.swimAmount);
                this.right_arm.zRot = MathHelper.lerp(l, this.right_arm.zRot, 1.2707963F + 1.8707964F * m);
            } else if (p >= 22.0F && p < 26.0F) {
                float m = (p - 22.0F) / 4.0F;
                this.left_arm.xRot = this.rotlerpRad(this.left_arm.xRot, (float) (Math.PI / 2) - (float) (Math.PI / 2) * m, this.swimAmount);
                this.right_arm.xRot = MathHelper.lerp(l, this.right_arm.xRot, (float) (Math.PI / 2) - (float) (Math.PI / 2) * m);
                this.left_arm.yRot = this.rotlerpRad(this.left_arm.yRot, (float) Math.PI, this.swimAmount);
                this.right_arm.yRot = MathHelper.lerp(l, this.right_arm.yRot, (float) Math.PI);
                this.left_arm.zRot = this.rotlerpRad(this.left_arm.zRot, (float) Math.PI, this.swimAmount);
                this.right_arm.zRot = MathHelper.lerp(l, this.right_arm.zRot, (float) Math.PI);
            }

            float m = 0.3F;
            float n = 0.33333334F;
            this.left_leg.xRot = MathHelper.lerp(this.swimAmount, this.left_leg.xRot, 0.3F * MathHelper.cos(f * 0.33333334F + (float) Math.PI));
            this.right_leg.xRot = MathHelper.lerp(this.swimAmount, this.right_leg.xRot, 0.3F * MathHelper.cos(f * 0.33333334F));
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
