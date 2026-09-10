package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import com.jeff.pets.client.Math2;
import net.minecraft.client.render.entity.model.BiPedModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombiePigmanModel extends EntityModel {
    private final ModelPart head;
    private final ModelPart headwear;
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart left_leg;
    private final ModelPart right_leg;
    public BiPedModel.class_2850 field_13384 = BiPedModel.class_2850.EMPTY;
    public BiPedModel.class_2850 field_13385 = BiPedModel.class_2850.EMPTY;
    public boolean crouching;
    public float swimAmount;
    private float itemUseTicks;

    public ClientZombiePigmanModel() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelPart(this);
        head.setPivot(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(0, 0).method_18947(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false);

        headwear = new ModelPart(this);
        headwear.setPivot(0.0F, 0.0F, 0.0F);
        headwear.setTextureOffset(32, 0).method_18947(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F, false);

        body = new ModelPart(this);
        body.setPivot(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(16, 16).method_18947(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F, false);

        left_arm = new ModelPart(this);
        left_arm.setPivot(5.0F, 2.0F, 0.0F);
        left_arm.setTextureOffset(40, 16).method_18947(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false);

        right_arm = new ModelPart(this);
        right_arm.setPivot(-5.0F, 2.0F, 0.0F);
        right_arm.setTextureOffset(40, 16).method_18947(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false);

        left_leg = new ModelPart(this);
        left_leg.setPivot(1.9F, 12.0F, 0.0F);
        left_leg.setTextureOffset(0, 16).method_18947(-1.9F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);

        right_leg = new ModelPart(this);
        right_leg.setPivot(-1.9F, 12.0F, 0.0F);
        right_leg.setTextureOffset(0, 16).method_18947(-2.1F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);
    }

    @Override
    public void setAngles(float f, float g, float h, float i, float j, float s, Entity state) {
        boolean bl = false;
        boolean bl2 = state.isTouchingWater();
        this.head.posY = i * (float) (Math.PI / 180.0);
        if (bl) {
            this.head.posX = (float) (-Math.PI / 4);
        } else if (this.swimAmount > 0.0F) {
            if (bl2) {
                this.head.posX = this.rotlerpRad(this.head.posX, (float) (-Math.PI / 4), this.swimAmount);
            } else {
                this.head.posX = this.rotlerpRad(this.head.posX, j * (float) (Math.PI / 180.0), this.swimAmount);
            }
        } else {
            this.head.posX = j * (float) (Math.PI / 180.0);
        }

        this.body.posY = 0.0F;
        this.right_arm.pivotZ = 0.0F;
        this.right_arm.pivotX = -5.0F;
        this.left_arm.pivotZ = 0.0F;
        this.left_arm.pivotX = 5.0F;
        float k = 1.0F;
        if (bl) {
            k = (float) ((float) state.velocityX * state.velocityX + state.velocityY * state.velocityY + state.velocityZ * state.velocityZ);
            k /= 0.2F;
            k *= k * k;
        }

        if (k < 1.0F) {
            k = 1.0F;
        }

        this.right_arm.posX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * g * 0.5F / k;
        this.left_arm.posX = MathHelper.cos(f * 0.6662F) * 2.0F * g * 0.5F / k;
        this.right_arm.posZ = 0.0F;
        this.left_arm.posZ = 0.0F;
        this.right_leg.posX = MathHelper.cos(f * 0.6662F) * 1.4F * g / k;
        this.left_leg.posX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g / k;
        this.right_leg.posY = 0.0F;
        this.left_leg.posY = 0.0F;
        this.right_leg.posZ = 0.0F;
        this.left_leg.posZ = 0.0F;
        if (state.hasMount()) {
            this.right_arm.posX += (float) (-Math.PI / 5);
            this.left_arm.posX += (float) (-Math.PI / 5);
            this.right_leg.posX = -1.4137167F;
            this.right_leg.posY = (float) (Math.PI / 10);
            this.right_leg.posZ = 0.07853982F;
            this.left_leg.posX = -1.4137167F;
            this.left_leg.posY = (float) (-Math.PI / 10);
            this.left_leg.posZ = -0.07853982F;
        }

        this.right_arm.posY = 0.0F;
        this.right_arm.posZ = 0.0F;
        switch (this.field_13384) {
            case EMPTY:
                this.left_arm.posY = 0.0F;
                break;
            case BLOCK:
                this.left_arm.posX = this.left_arm.posX * 0.5F - 0.9424779F;
                this.left_arm.posY = (float) (Math.PI / 6);
                break;
            case ITEM:
                this.left_arm.posX = this.left_arm.posX * 0.5F - (float) (Math.PI / 10);
                this.left_arm.posY = 0.0F;
        }

        switch (this.field_13385) {
            case EMPTY:
                this.right_arm.posY = 0.0F;
                break;
            case BLOCK:
                this.right_arm.posX = this.right_arm.posX * 0.5F - 0.9424779F;
                this.right_arm.posY = (float) (-Math.PI / 6);
                break;
            case ITEM:
                this.right_arm.posX = this.right_arm.posX * 0.5F - (float) (Math.PI / 10);
                this.right_arm.posY = 0.0F;
                break;
            case THROW_SPEAR:
                this.right_arm.posX = this.right_arm.posX * 0.5F - (float) Math.PI;
                this.right_arm.posY = 0.0F;
        }

        if (this.field_13384 == BiPedModel.class_2850.THROW_SPEAR
                && this.field_13385 != BiPedModel.class_2850.BLOCK
                && this.field_13385 != BiPedModel.class_2850.THROW_SPEAR
                && this.field_13385 != BiPedModel.class_2850.BOW_AND_ARROW) {
            this.left_arm.posX = this.left_arm.posX * 0.5F - (float) Math.PI;
            this.left_arm.posY = 0.0F;
        }

        if (this.handSwingProgress > 0.0F) {
            ModelPart ModelPart = right_arm;
            float l = this.handSwingProgress;
            this.body.posY = MathHelper.sin(MathHelper.sqrt(l) * (float) (Math.PI * 2)) * 0.2F;

            this.right_arm.pivotZ = MathHelper.sin(this.body.posY) * 5.0F;
            this.right_arm.pivotX = -MathHelper.cos(this.body.posY) * 5.0F;
            this.left_arm.pivotZ = -MathHelper.sin(this.body.posY) * 5.0F;
            this.left_arm.pivotX = MathHelper.cos(this.body.posY) * 5.0F;
            this.right_arm.posY = this.right_arm.posY + this.body.posY;
            this.left_arm.posY = this.left_arm.posY + this.body.posY;
            this.left_arm.posX = this.left_arm.posX + this.body.posY;
            l = 1.0F - this.handSwingProgress;
            l *= l;
            l *= l;
            l = 1.0F - l;
            float m = MathHelper.sin(l * (float) Math.PI);
            float n = MathHelper.sin(this.handSwingProgress * (float) Math.PI) * -(this.head.posX - 0.7F) * 0.75F;
            ModelPart.posX = (float) (ModelPart.posX - (m * 1.2 + n));
            ModelPart.posY = ModelPart.posY + this.body.posY * 2.0F;
            ModelPart.posZ = ModelPart.posZ + MathHelper.sin(this.handSwingProgress * (float) Math.PI) * -0.4F;
        }

        if (this.crouching) {
            this.body.posX = 0.5F;
            this.right_arm.posX += 0.4F;
            this.left_arm.posX += 0.4F;
            this.right_leg.pivotZ = 4.0F;
            this.left_leg.pivotZ = 4.0F;
            this.right_leg.pivotY = 12.2F;
            this.left_leg.pivotY = 12.2F;
            this.head.pivotY = 4.2F;
            this.body.pivotY = 3.2F;
            this.left_arm.pivotY = 5.2F;
            this.right_arm.pivotY = 5.2F;
        } else {
            this.body.posX = 0.0F;
            this.right_leg.pivotZ = 0.1F;
            this.left_leg.pivotZ = 0.1F;
            this.right_leg.pivotY = 12.0F;
            this.left_leg.pivotY = 12.0F;
            this.head.pivotY = 0.0F;
            this.body.pivotY = 0.0F;
            this.left_arm.pivotY = 2.0F;
            this.right_arm.pivotY = 2.0F;
        }

        this.right_arm.posZ = this.right_arm.posZ + (MathHelper.cos(h * 0.09F) * 0.05F + 0.05F);
        this.left_arm.posZ = this.left_arm.posZ - (MathHelper.cos(h * 0.09F) * 0.05F + 0.05F);
        this.right_arm.posX = this.right_arm.posX + MathHelper.sin(h * 0.067F) * 0.05F;
        this.left_arm.posX = this.left_arm.posX - MathHelper.sin(h * 0.067F) * 0.05F;
        if (this.field_13385 == BiPedModel.class_2850.BOW_AND_ARROW) {
            this.right_arm.posY = -0.1F + this.head.posY;
            this.left_arm.posY = 0.1F + this.head.posY + 0.4F;
            this.right_arm.posX = (float) (-Math.PI / 2) + this.head.posX;
            this.left_arm.posX = (float) (-Math.PI / 2) + this.head.posX;
        } else if (this.field_13384 == BiPedModel.class_2850.BOW_AND_ARROW
                && this.field_13385 != BiPedModel.class_2850.THROW_SPEAR
                && this.field_13385 != BiPedModel.class_2850.BLOCK) {
            this.right_arm.posY = -0.1F + this.head.posY - 0.4F;
            this.left_arm.posY = 0.1F + this.head.posY;
            this.right_arm.posX = (float) (-Math.PI / 2) + this.head.posX;
            this.left_arm.posX = (float) (-Math.PI / 2) + this.head.posX;
        }

        if (this.swimAmount > 0.0F) {
            float p = f % 26.0F;
            float l = this.handSwingProgress > 0.0F ? 0.0F : this.swimAmount;
            if (p < 14.0F) {
                this.left_arm.posX = this.rotlerpRad(this.left_arm.posX, 0.0F, this.swimAmount);
                this.right_arm.posX = Math2.lerp(l, this.right_arm.posX, 0.0F);
                this.left_arm.posY = this.rotlerpRad(this.left_arm.posY, (float) Math.PI, this.swimAmount);
                this.right_arm.posY = Math2.lerp(l, this.right_arm.posY, (float) Math.PI);
                this.left_arm.posZ = this.rotlerpRad(
                        this.left_arm.posZ, (float) Math.PI + 1.8707964F * this.quadraticArmUpdate(p) / this.quadraticArmUpdate(14.0F), this.swimAmount
                );
                this.right_arm.posZ = Math2.lerp(l, this.right_arm.posZ, (float) Math.PI - 1.8707964F * this.quadraticArmUpdate(p) / this.quadraticArmUpdate(14.0F));
            } else if (p >= 14.0F && p < 22.0F) {
                float m = (p - 14.0F) / 8.0F;
                this.left_arm.posX = this.rotlerpRad(this.left_arm.posX, (float) (Math.PI / 2) * m, this.swimAmount);
                this.right_arm.posX = Math2.lerp(l, this.right_arm.posX, (float) (Math.PI / 2) * m);
                this.left_arm.posY = this.rotlerpRad(this.left_arm.posY, (float) Math.PI, this.swimAmount);
                this.right_arm.posY = Math2.lerp(l, this.right_arm.posY, (float) Math.PI);
                this.left_arm.posZ = this.rotlerpRad(this.left_arm.posZ, 5.012389F - 1.8707964F * m, this.swimAmount);
                this.right_arm.posZ = Math2.lerp(l, this.right_arm.posZ, 1.2707963F + 1.8707964F * m);
            } else if (p >= 22.0F && p < 26.0F) {
                float m = (p - 22.0F) / 4.0F;
                this.left_arm.posX = this.rotlerpRad(this.left_arm.posX, (float) (Math.PI / 2) - (float) (Math.PI / 2) * m, this.swimAmount);
                this.right_arm.posX = Math2.lerp(l, this.right_arm.posX, (float) (Math.PI / 2) - (float) (Math.PI / 2) * m);
                this.left_arm.posY = this.rotlerpRad(this.left_arm.posY, (float) Math.PI, this.swimAmount);
                this.right_arm.posY = Math2.lerp(l, this.right_arm.posY, (float) Math.PI);
                this.left_arm.posZ = this.rotlerpRad(this.left_arm.posZ, (float) Math.PI, this.swimAmount);
                this.right_arm.posZ = Math2.lerp(l, this.right_arm.posZ, (float) Math.PI);
            }

            float m = 0.3F;
            float n = 0.33333334F;
            this.left_leg.posX = Math2.lerp(this.swimAmount, this.left_leg.posX, 0.3F * MathHelper.cos(f * 0.33333334F + (float) Math.PI));
            this.right_leg.posX = Math2.lerp(this.swimAmount, this.right_leg.posX, 0.3F * MathHelper.cos(f * 0.33333334F));
        }
    }

    @Override
    public void render(Entity entityIn, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        this.render(entityIn, packedLight, packedOverlay, red, blue, green, alpha, 0);
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

    public void render(Entity zombie, float b, float j, float f, float g, float h, float k, int i) {
        super.render(zombie, b, j, f, g, h, k);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(1.5f, 1.5f, 1.5f);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scale(1, 1, 1);
        }
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }

    private float quadraticArmUpdate(float f) {
        return -65.0F * f + f * f;
    }
}
