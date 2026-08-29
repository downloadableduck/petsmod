package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import com.jeff.pets.client.Math2;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombiePigmanModel extends ModelBase {
    private final ModelRenderer head;
    private final ModelRenderer headwear;
    private final ModelRenderer body;
    private final ModelRenderer left_arm;
    private final ModelRenderer right_arm;
    private final ModelRenderer left_leg;
    private final ModelRenderer right_leg;
    public ModelBiped.ArmPose leftArmPose = ModelBiped.ArmPose.EMPTY;
    public ModelBiped.ArmPose rightArmPose = ModelBiped.ArmPose.EMPTY;
    public boolean crouching;
    public float swimAmount;
    private float itemUseTicks;

    public ClientZombiePigmanModel() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false);

        headwear = new ModelRenderer(this);
        headwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        headwear.setTextureOffset(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F, false);

        left_arm = new ModelRenderer(this);
        left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
        left_arm.setTextureOffset(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false);

        right_arm = new ModelRenderer(this);
        right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        right_arm.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false);

        left_leg = new ModelRenderer(this);
        left_leg.setRotationPoint(1.9F, 12.0F, 0.0F);
        left_leg.setTextureOffset(0, 16).addBox(-1.9F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);

        right_leg = new ModelRenderer(this);
        right_leg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        right_leg.setTextureOffset(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);
    }

    @Override
    public void setRotationAngles(float f, float g, float h, float i, float j, float s, Entity state) {
        boolean bl = false;
        boolean bl2 = state.isSwimming();
        this.head.rotateAngleY = i * (float) (Math.PI / 180.0);
        if (bl) {
            this.head.rotateAngleX = (float) (-Math.PI / 4);
        } else if (this.swimAmount > 0.0F) {
            if (bl2) {
                this.head.rotateAngleX = this.rotlerpRad(this.head.rotateAngleX, (float) (-Math.PI / 4), this.swimAmount);
            } else {
                this.head.rotateAngleX = this.rotlerpRad(this.head.rotateAngleX, j * (float) (Math.PI / 180.0), this.swimAmount);
            }
        } else {
            this.head.rotateAngleX = j * (float) (Math.PI / 180.0);
        }

        this.body.rotateAngleY = 0.0F;
        this.right_arm.rotationPointZ = 0.0F;
        this.right_arm.rotationPointX = -5.0F;
        this.left_arm.rotationPointZ = 0.0F;
        this.left_arm.rotationPointX = 5.0F;
        float k = 1.0F;
        if (bl) {
            k = (float) ((float) state.motionX * state.motionX + state.motionY * state.motionY + state.motionZ * state.motionZ);
            k /= 0.2F;
            k *= k * k;
        }

        if (k < 1.0F) {
            k = 1.0F;
        }

        this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * g * 0.5F / k;
        this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * g * 0.5F / k;
        this.right_arm.rotateAngleZ = 0.0F;
        this.left_arm.rotateAngleZ = 0.0F;
        this.right_leg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * g / k;
        this.left_leg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g / k;
        this.right_leg.rotateAngleY = 0.0F;
        this.left_leg.rotateAngleY = 0.0F;
        this.right_leg.rotateAngleZ = 0.0F;
        this.left_leg.rotateAngleZ = 0.0F;
        if (state.isPassenger()) {
            this.right_arm.rotateAngleX += (float) (-Math.PI / 5);
            this.left_arm.rotateAngleX += (float) (-Math.PI / 5);
            this.right_leg.rotateAngleX = -1.4137167F;
            this.right_leg.rotateAngleY = (float) (Math.PI / 10);
            this.right_leg.rotateAngleZ = 0.07853982F;
            this.left_leg.rotateAngleX = -1.4137167F;
            this.left_leg.rotateAngleY = (float) (-Math.PI / 10);
            this.left_leg.rotateAngleZ = -0.07853982F;
        }

        this.right_arm.rotateAngleY = 0.0F;
        this.right_arm.rotateAngleZ = 0.0F;
        switch (this.leftArmPose) {
            case EMPTY:
                this.left_arm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.left_arm.rotateAngleX = this.left_arm.rotateAngleX * 0.5F - 0.9424779F;
                this.left_arm.rotateAngleY = (float) (Math.PI / 6);
                break;
            case ITEM:
                this.left_arm.rotateAngleX = this.left_arm.rotateAngleX * 0.5F - (float) (Math.PI / 10);
                this.left_arm.rotateAngleY = 0.0F;
        }

        switch (this.rightArmPose) {
            case EMPTY:
                this.right_arm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.right_arm.rotateAngleX = this.right_arm.rotateAngleX * 0.5F - 0.9424779F;
                this.right_arm.rotateAngleY = (float) (-Math.PI / 6);
                break;
            case ITEM:
                this.right_arm.rotateAngleX = this.right_arm.rotateAngleX * 0.5F - (float) (Math.PI / 10);
                this.right_arm.rotateAngleY = 0.0F;
                break;
            case THROW_SPEAR:
                this.right_arm.rotateAngleX = this.right_arm.rotateAngleX * 0.5F - (float) Math.PI;
                this.right_arm.rotateAngleY = 0.0F;
        }

        if (this.leftArmPose == ModelBiped.ArmPose.THROW_SPEAR
                && this.rightArmPose != ModelBiped.ArmPose.BLOCK
                && this.rightArmPose != ModelBiped.ArmPose.THROW_SPEAR
                && this.rightArmPose != ModelBiped.ArmPose.BOW_AND_ARROW) {
            this.left_arm.rotateAngleX = this.left_arm.rotateAngleX * 0.5F - (float) Math.PI;
            this.left_arm.rotateAngleY = 0.0F;
        }

        if (this.swingProgress > 0.0F) {
            ModelRenderer ModelRenderer = right_arm;
            float l = this.swingProgress;
            this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(l) * (float) (Math.PI * 2)) * 0.2F;

            this.right_arm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 5.0F;
            this.right_arm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 5.0F;
            this.left_arm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
            this.left_arm.rotationPointX = MathHelper.cos(this.body.rotateAngleY) * 5.0F;
            this.right_arm.rotateAngleY = this.right_arm.rotateAngleY + this.body.rotateAngleY;
            this.left_arm.rotateAngleY = this.left_arm.rotateAngleY + this.body.rotateAngleY;
            this.left_arm.rotateAngleX = this.left_arm.rotateAngleX + this.body.rotateAngleY;
            l = 1.0F - this.swingProgress;
            l *= l;
            l *= l;
            l = 1.0F - l;
            float m = MathHelper.sin(l * (float) Math.PI);
            float n = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
            ModelRenderer.rotateAngleX = (float) (ModelRenderer.rotateAngleX - (m * 1.2 + n));
            ModelRenderer.rotateAngleY = ModelRenderer.rotateAngleY + this.body.rotateAngleY * 2.0F;
            ModelRenderer.rotateAngleZ = ModelRenderer.rotateAngleZ + MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
        }

        if (this.crouching) {
            this.body.rotateAngleX = 0.5F;
            this.right_arm.rotateAngleX += 0.4F;
            this.left_arm.rotateAngleX += 0.4F;
            this.right_leg.rotationPointZ = 4.0F;
            this.left_leg.rotationPointZ = 4.0F;
            this.right_leg.rotationPointY = 12.2F;
            this.left_leg.rotationPointY = 12.2F;
            this.head.rotationPointY = 4.2F;
            this.body.rotationPointY = 3.2F;
            this.left_arm.rotationPointY = 5.2F;
            this.right_arm.rotationPointY = 5.2F;
        } else {
            this.body.rotateAngleX = 0.0F;
            this.right_leg.rotationPointZ = 0.1F;
            this.left_leg.rotationPointZ = 0.1F;
            this.right_leg.rotationPointY = 12.0F;
            this.left_leg.rotationPointY = 12.0F;
            this.head.rotationPointY = 0.0F;
            this.body.rotationPointY = 0.0F;
            this.left_arm.rotationPointY = 2.0F;
            this.right_arm.rotationPointY = 2.0F;
        }

        this.right_arm.rotateAngleZ = this.right_arm.rotateAngleZ + (MathHelper.cos(h * 0.09F) * 0.05F + 0.05F);
        this.left_arm.rotateAngleZ = this.left_arm.rotateAngleZ - (MathHelper.cos(h * 0.09F) * 0.05F + 0.05F);
        this.right_arm.rotateAngleX = this.right_arm.rotateAngleX + MathHelper.sin(h * 0.067F) * 0.05F;
        this.left_arm.rotateAngleX = this.left_arm.rotateAngleX - MathHelper.sin(h * 0.067F) * 0.05F;
        if (this.rightArmPose == ModelBiped.ArmPose.BOW_AND_ARROW) {
            this.right_arm.rotateAngleY = -0.1F + this.head.rotateAngleY;
            this.left_arm.rotateAngleY = 0.1F + this.head.rotateAngleY + 0.4F;
            this.right_arm.rotateAngleX = (float) (-Math.PI / 2) + this.head.rotateAngleX;
            this.left_arm.rotateAngleX = (float) (-Math.PI / 2) + this.head.rotateAngleX;
        } else if (this.leftArmPose == ModelBiped.ArmPose.BOW_AND_ARROW
                && this.rightArmPose != ModelBiped.ArmPose.THROW_SPEAR
                && this.rightArmPose != ModelBiped.ArmPose.BLOCK) {
            this.right_arm.rotateAngleY = -0.1F + this.head.rotateAngleY - 0.4F;
            this.left_arm.rotateAngleY = 0.1F + this.head.rotateAngleY;
            this.right_arm.rotateAngleX = (float) (-Math.PI / 2) + this.head.rotateAngleX;
            this.left_arm.rotateAngleX = (float) (-Math.PI / 2) + this.head.rotateAngleX;
        }

        if (this.swimAmount > 0.0F) {
            float p = f % 26.0F;
            float l = this.swingProgress > 0.0F ? 0.0F : this.swimAmount;
            if (p < 14.0F) {
                this.left_arm.rotateAngleX = this.rotlerpRad(this.left_arm.rotateAngleX, 0.0F, this.swimAmount);
                this.right_arm.rotateAngleX = Math2.lerp(l, this.right_arm.rotateAngleX, 0.0F);
                this.left_arm.rotateAngleY = this.rotlerpRad(this.left_arm.rotateAngleY, (float) Math.PI, this.swimAmount);
                this.right_arm.rotateAngleY = Math2.lerp(l, this.right_arm.rotateAngleY, (float) Math.PI);
                this.left_arm.rotateAngleZ = this.rotlerpRad(
                        this.left_arm.rotateAngleZ, (float) Math.PI + 1.8707964F * this.quadraticArmUpdate(p) / this.quadraticArmUpdate(14.0F), this.swimAmount
                );
                this.right_arm.rotateAngleZ = Math2.lerp(l, this.right_arm.rotateAngleZ, (float) Math.PI - 1.8707964F * this.quadraticArmUpdate(p) / this.quadraticArmUpdate(14.0F));
            } else if (p >= 14.0F && p < 22.0F) {
                float m = (p - 14.0F) / 8.0F;
                this.left_arm.rotateAngleX = this.rotlerpRad(this.left_arm.rotateAngleX, (float) (Math.PI / 2) * m, this.swimAmount);
                this.right_arm.rotateAngleX = Math2.lerp(l, this.right_arm.rotateAngleX, (float) (Math.PI / 2) * m);
                this.left_arm.rotateAngleY = this.rotlerpRad(this.left_arm.rotateAngleY, (float) Math.PI, this.swimAmount);
                this.right_arm.rotateAngleY = Math2.lerp(l, this.right_arm.rotateAngleY, (float) Math.PI);
                this.left_arm.rotateAngleZ = this.rotlerpRad(this.left_arm.rotateAngleZ, 5.012389F - 1.8707964F * m, this.swimAmount);
                this.right_arm.rotateAngleZ = Math2.lerp(l, this.right_arm.rotateAngleZ, 1.2707963F + 1.8707964F * m);
            } else if (p >= 22.0F && p < 26.0F) {
                float m = (p - 22.0F) / 4.0F;
                this.left_arm.rotateAngleX = this.rotlerpRad(this.left_arm.rotateAngleX, (float) (Math.PI / 2) - (float) (Math.PI / 2) * m, this.swimAmount);
                this.right_arm.rotateAngleX = Math2.lerp(l, this.right_arm.rotateAngleX, (float) (Math.PI / 2) - (float) (Math.PI / 2) * m);
                this.left_arm.rotateAngleY = this.rotlerpRad(this.left_arm.rotateAngleY, (float) Math.PI, this.swimAmount);
                this.right_arm.rotateAngleY = Math2.lerp(l, this.right_arm.rotateAngleY, (float) Math.PI);
                this.left_arm.rotateAngleZ = this.rotlerpRad(this.left_arm.rotateAngleZ, (float) Math.PI, this.swimAmount);
                this.right_arm.rotateAngleZ = Math2.lerp(l, this.right_arm.rotateAngleZ, (float) Math.PI);
            }

            float m = 0.3F;
            float n = 0.33333334F;
            this.left_leg.rotateAngleX = Math2.lerp(this.swimAmount, this.left_leg.rotateAngleX, 0.3F * MathHelper.cos(f * 0.33333334F + (float) Math.PI));
            this.right_leg.rotateAngleX = Math2.lerp(this.swimAmount, this.right_leg.rotateAngleX, 0.3F * MathHelper.cos(f * 0.33333334F));
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
        net.minecraft.client.renderer.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(1.5f, 1.5f, 1.5f);
        } else {
            net.minecraft.client.renderer.GlStateManager.scalef(1, 1, 1);
        }
        net.minecraft.client.renderer.GlStateManager.popMatrix();
    }

    private float quadraticArmUpdate(float f) {
        return -65.0F * f + f * f;
    }
}
