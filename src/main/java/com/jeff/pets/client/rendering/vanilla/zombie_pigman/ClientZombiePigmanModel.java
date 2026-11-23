//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.util.helper.MathHelper;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.Math2;
import com.jeff.pets.client.Utils;
import com.jeff.pets.client.rendering.ArmPose;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.compat.render.model.ModelPart;
import com.jeff.pets.mob.vanilla.neutral.ClientZombiePigman;

public class ClientZombiePigmanModel extends PetModel {
    private final ModelPart head;
    private final ModelPart headwear;
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart left_leg;
    private final ModelPart right_leg;
    public ArmPose leftArmPose;
    public ArmPose rightArmPose;
    public boolean crouching;
    public float swimAmount;
    private float itemUseTicks;

    public ClientZombiePigmanModel() {
        this.leftArmPose = ArmPose.EMPTY;
        this.rightArmPose = ArmPose.EMPTY;
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelPart(this);
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.head.setTextureCoords(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.headwear = new ModelPart(this);
        this.headwear.setPos(0.0F, 0.0F, 0.0F);
        this.headwear.setTextureCoords(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.body = new ModelPart(this);
        this.body.setPos(0.0F, 0.0F, 0.0F);
        this.body.setTextureCoords(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.left_arm = new ModelPart(this);
        this.left_arm.setPos(5.0F, 2.0F, 0.0F);
        this.left_arm.setTextureCoords(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.right_arm = new ModelPart(this);
        this.right_arm.setPos(-5.0F, 2.0F, 0.0F);
        this.right_arm.setTextureCoords(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.left_leg = new ModelPart(this);
        this.left_leg.setPos(1.9F, 12.0F, 0.0F);
        this.left_leg.setTextureCoords(0, 16).addBox(-1.9F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.right_leg = new ModelPart(this);
        this.right_leg.setPos(-1.9F, 12.0F, 0.0F);
        this.right_leg.setTextureCoords(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
    }

    public void setupAnimation(float f, float g, float h, float i, float j, float s, Entity entity) {
        ClientZombiePigman state = (ClientZombiePigman)entity;
        boolean bl = false;
        boolean bl2 = state.isInWater();
        this.head.rotationY = i * 0.017453292F;
        if (bl) {
            this.head.rotationX = -0.7853982F;
        } else if (this.swimAmount > 0.0F) {
            if (bl2) {
                this.head.rotationX = this.rotlerpRad(this.head.rotationX, -0.7853982F, this.swimAmount);
            } else {
                this.head.rotationX = this.rotlerpRad(this.head.rotationX, j * 0.017453292F, this.swimAmount);
            }
        } else {
            this.head.rotationX = j * 0.017453292F;
        }

        this.body.rotationY = 0.0F;
        this.right_arm.z = 0.0F;
        this.right_arm.x = -5.0F;
        this.left_arm.z = 0.0F;
        this.left_arm.x = 5.0F;
        float k = 1.0F;
        if (bl) {
            k = (float) Utils.squaredDistanceToOrigin(state.getVelocity());
            k /= 0.2F;
            k *= k * k;
        }

        if (k < 1.0F) {
            k = 1.0F;
        }

        this.right_arm.rotationX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 2.0F * g * 0.5F / k;
        this.left_arm.rotationX = MathHelper.cos(f * 0.6662F) * 2.0F * g * 0.5F / k;
        this.right_arm.rotationZ = 0.0F;
        this.left_arm.rotationZ = 0.0F;
        this.right_leg.rotationX = MathHelper.cos(f * 0.6662F) * 1.4F * g / k;
        this.left_leg.rotationX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g / k;
        this.right_leg.rotationY = 0.0F;
        this.left_leg.rotationY = 0.0F;
        this.right_leg.rotationZ = 0.0F;
        this.left_leg.rotationZ = 0.0F;
        ModelPart var10000;
        if (state.isPassenger()) {
            var10000 = this.right_arm;
            var10000.rotationX += -0.62831855F;
            var10000 = this.left_arm;
            var10000.rotationX += -0.62831855F;
            this.right_leg.rotationX = -1.4137167F;
            this.right_leg.rotationY = 0.31415927F;
            this.right_leg.rotationZ = 0.07853982F;
            this.left_leg.rotationX = -1.4137167F;
            this.left_leg.rotationY = -0.31415927F;
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
                this.left_arm.rotationY = 0.5235988F;
                break;
            case ITEM:
                this.left_arm.rotationX = this.left_arm.rotationX * 0.5F - 0.31415927F;
                this.left_arm.rotationY = 0.0F;
        }

        switch (this.rightArmPose) {
            case EMPTY:
                this.right_arm.rotationY = 0.0F;
                break;
            case BLOCK:
                this.right_arm.rotationX = this.right_arm.rotationX * 0.5F - 0.9424779F;
                this.right_arm.rotationY = -0.5235988F;
                break;
            case ITEM:
                this.right_arm.rotationX = this.right_arm.rotationX * 0.5F - 0.31415927F;
                this.right_arm.rotationY = 0.0F;
        }

        if (this.leftArmPose == ArmPose.BOW_AND_ARROW && this.rightArmPose != ArmPose.BLOCK) {
            this.left_arm.rotationX = this.left_arm.rotationX * 0.5F - 3.1415927F;
            this.left_arm.rotationY = 0.0F;
        }

        float p;
        float l;
        float m;
        if (this.swingProgress > 0.0F) {
            ModelPart ModelPart = this.right_arm;
            p = this.swingProgress;
            this.body.rotationY = MathHelper.sin(MathHelper.sqrt(p) * 6.2831855F) * 0.2F;
            this.right_arm.z = MathHelper.sin(this.body.rotationY) * 5.0F;
            this.right_arm.x = -MathHelper.cos(this.body.rotationY) * 5.0F;
            this.left_arm.z = -MathHelper.sin(this.body.rotationY) * 5.0F;
            this.left_arm.x = MathHelper.cos(this.body.rotationY) * 5.0F;
            this.right_arm.rotationY += this.body.rotationY;
            this.left_arm.rotationY += this.body.rotationY;
            this.left_arm.rotationX += this.body.rotationY;
            p = 1.0F - this.swingProgress;
            p *= p;
            p *= p;
            p = 1.0F - p;
            l = MathHelper.sin(p * 3.1415927F);
            m = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotationX - 0.7F) * 0.75F;
            ModelPart.rotationX = (float)((double)ModelPart.rotationX - ((double)l * 1.2 + (double)m));
            ModelPart.rotationY += this.body.rotationY * 2.0F;
            ModelPart.rotationZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
        }

        if (this.crouching) {
            this.body.rotationX = 0.5F;
            var10000 = this.right_arm;
            var10000.rotationX += 0.4F;
            var10000 = this.left_arm;
            var10000.rotationX += 0.4F;
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

        this.right_arm.rotationZ += MathHelper.cos(h * 0.09F) * 0.05F + 0.05F;
        this.left_arm.rotationZ -= MathHelper.cos(h * 0.09F) * 0.05F + 0.05F;
        this.right_arm.rotationX += MathHelper.sin(h * 0.067F) * 0.05F;
        this.left_arm.rotationX -= MathHelper.sin(h * 0.067F) * 0.05F;
        if (this.rightArmPose == ArmPose.BOW_AND_ARROW) {
            this.right_arm.rotationY = -0.1F + this.head.rotationY;
            this.left_arm.rotationY = 0.1F + this.head.rotationY + 0.4F;
            this.right_arm.rotationX = -1.5707964F + this.head.rotationX;
            this.left_arm.rotationX = -1.5707964F + this.head.rotationX;
        } else if (this.leftArmPose == ArmPose.BOW_AND_ARROW && this.rightArmPose != ArmPose.BLOCK) {
            this.right_arm.rotationY = -0.1F + this.head.rotationY - 0.4F;
            this.left_arm.rotationY = 0.1F + this.head.rotationY;
            this.right_arm.rotationX = -1.5707964F + this.head.rotationX;
            this.left_arm.rotationX = -1.5707964F + this.head.rotationX;
        }

        float o = 64.0F;
        if (this.swimAmount > 0.0F) {
            p = f % 26.0F;
            l = this.swingProgress > 0.0F ? 0.0F : this.swimAmount;
            if (p < 14.0F) {
                this.left_arm.rotationX = this.rotlerpRad(this.left_arm.rotationX, 0.0F, this.swimAmount);
                this.right_arm.rotationX = Math2.lerp(l, this.right_arm.rotationX, 0.0F);
                this.left_arm.rotationY = this.rotlerpRad(this.left_arm.rotationY, 3.1415927F, this.swimAmount);
                this.right_arm.rotationY = Math2.lerp(l, this.right_arm.rotationY, 3.1415927F);
                this.left_arm.rotationZ = this.rotlerpRad(this.left_arm.rotationZ, 3.1415927F + 1.8707964F * this.quadraticArmUpdate(p) / this.quadraticArmUpdate(14.0F), this.swimAmount);
                this.right_arm.rotationZ = Math2.lerp(l, this.right_arm.rotationZ, 3.1415927F - 1.8707964F * this.quadraticArmUpdate(p) / this.quadraticArmUpdate(14.0F));
            } else if (p >= 14.0F && p < 22.0F) {
                m = (p - 14.0F) / 8.0F;
                this.left_arm.rotationX = this.rotlerpRad(this.left_arm.rotationX, 1.5707964F * m, this.swimAmount);
                this.right_arm.rotationX = Math2.lerp(l, this.right_arm.rotationX, 1.5707964F * m);
                this.left_arm.rotationY = this.rotlerpRad(this.left_arm.rotationY, 3.1415927F, this.swimAmount);
                this.right_arm.rotationY = Math2.lerp(l, this.right_arm.rotationY, 3.1415927F);
                this.left_arm.rotationZ = this.rotlerpRad(this.left_arm.rotationZ, 5.012389F - 1.8707964F * m, this.swimAmount);
                this.right_arm.rotationZ = Math2.lerp(l, this.right_arm.rotationZ, 1.2707963F + 1.8707964F * m);
            } else if (p >= 22.0F && p < 26.0F) {
                m = (p - 22.0F) / 4.0F;
                this.left_arm.rotationX = this.rotlerpRad(this.left_arm.rotationX, 1.5707964F - 1.5707964F * m, this.swimAmount);
                this.right_arm.rotationX = Math2.lerp(l, this.right_arm.rotationX, 1.5707964F - 1.5707964F * m);
                this.left_arm.rotationY = this.rotlerpRad(this.left_arm.rotationY, 3.1415927F, this.swimAmount);
                this.right_arm.rotationY = Math2.lerp(l, this.right_arm.rotationY, 3.1415927F);
                this.left_arm.rotationZ = this.rotlerpRad(this.left_arm.rotationZ, 3.1415927F, this.swimAmount);
                this.right_arm.rotationZ = Math2.lerp(l, this.right_arm.rotationZ, 3.1415927F);
            }

            m = 0.3F;
            float n = 0.33333334F;
            this.left_leg.rotationX = Math2.lerp(this.swimAmount, this.left_leg.rotationX, 0.3F * MathHelper.cos(f * 0.33333334F + 3.1415927F));
            this.right_leg.rotationX = Math2.lerp(this.swimAmount, this.right_leg.rotationX, 0.3F * MathHelper.cos(f * 0.33333334F));
        }

    }

    public void render(Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        this.render(entity, packedLight, packedOverlay, red, blue, green, alpha, 0);
        this.head.render(alpha);
        this.headwear.render(alpha);
        this.body.render(alpha);
        this.left_arm.render(alpha);
        this.right_arm.render(alpha);
        this.left_leg.render(alpha);
        this.right_leg.render(alpha);
    }

    protected float rotlerpRad(float f, float g, float h) {
        float i = (g - f) % 6.2831855F;
        if (i < -3.1415927F) {
            i += 6.2831855F;
        }

        if (i >= 3.1415927F) {
            i -= 6.2831855F;
        }

        return f + h * i;
    }

    public void render(Entity entity, float b, float j, float f, float g, float h, float k, int i) {
        super.render(entity, b, j, f, g, h, k);
        GlStateManager.pushMatrix();
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(1.5F, 1.5F, 1.5F);
        } else {
            GlStateManager.scalef(1.0F, 1.0F, 1.0F);
        }

        GlStateManager.popMatrix();
    }

    private float quadraticArmUpdate(float f) {
        return -65.0F * f + f * f;
    }
}
