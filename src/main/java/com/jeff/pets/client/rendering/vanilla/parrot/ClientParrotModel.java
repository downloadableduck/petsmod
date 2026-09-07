package com.jeff.pets.client.rendering.vanilla.parrot;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.util.math.MathHelper;


public class ClientParrotModel extends ModelBase {
    private final ModelRenderer body;
    private final ModelRenderer tail;
    private final ModelRenderer wingLeft;
    private final ModelRenderer wingRight;
    private final ModelRenderer head;
    private final ModelRenderer head2;
    private final ModelRenderer beak1;
    private final ModelRenderer beak2;
    private final ModelRenderer feather;
    private final ModelRenderer legLeft;
    private final ModelRenderer legRight;
    private State state;

    public ClientParrotModel() {
        this.state = State.STANDING;
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.body = new ModelRenderer(this, 2, 8);
        this.body.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3);
        this.body.setRotationPoint(0.0F, 16.5F, -3.0F);
        this.tail = new ModelRenderer(this, 22, 1);
        this.tail.addBox(-1.5F, -1.0F, -1.0F, 3, 4, 1);
        this.tail.setRotationPoint(0.0F, 21.07F, 1.16F);
        this.wingLeft = new ModelRenderer(this, 19, 8);
        this.wingLeft.addBox(-0.5F, 0.0F, -1.5F, 1, 5, 3);
        this.wingLeft.setRotationPoint(1.5F, 16.94F, -2.76F);
        this.wingRight = new ModelRenderer(this, 19, 8);
        this.wingRight.addBox(-0.5F, 0.0F, -1.5F, 1, 5, 3);
        this.wingRight.setRotationPoint(-1.5F, 16.94F, -2.76F);
        this.head = new ModelRenderer(this, 2, 2);
        this.head.addBox(-1.0F, -1.5F, -1.0F, 2, 3, 2);
        this.head.setRotationPoint(0.0F, 15.69F, -2.76F);
        this.head2 = new ModelRenderer(this, 10, 0);
        this.head2.addBox(-1.0F, -0.5F, -2.0F, 2, 1, 4);
        this.head2.setRotationPoint(0.0F, -2.0F, -1.0F);
        this.head.addChild(this.head2);
        this.beak1 = new ModelRenderer(this, 11, 7);
        this.beak1.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1);
        this.beak1.setRotationPoint(0.0F, -0.5F, -1.5F);
        this.head.addChild(this.beak1);
        this.beak2 = new ModelRenderer(this, 16, 7);
        this.beak2.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
        this.beak2.setRotationPoint(0.0F, -1.75F, -2.45F);
        this.head.addChild(this.beak2);
        this.feather = new ModelRenderer(this, 2, 18);
        this.feather.addBox(0.0F, -4.0F, -2.0F, 0, 5, 4);
        this.feather.setRotationPoint(0.0F, -2.15F, 0.15F);
        this.head.addChild(this.feather);
        this.legLeft = new ModelRenderer(this, 14, 18);
        this.legLeft.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
        this.legLeft.setRotationPoint(1.0F, 22.0F, -1.05F);
        this.legRight = new ModelRenderer(this, 14, 18);
        this.legRight.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
        this.legRight.setRotationPoint(-1.0F, 22.0F, -1.05F);
    }

    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.body.render(p_78088_7_);
        this.wingLeft.render(p_78088_7_);
        this.wingRight.render(p_78088_7_);
        this.tail.render(p_78088_7_);
        this.head.render(p_78088_7_);
        this.legLeft.render(p_78088_7_);
        this.legRight.render(p_78088_7_);
    }

    @Override
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        float lvt_8_1_ = p_78087_3_ * 0.3F;
        this.head.rotateAngleX = p_78087_5_ * ((float) Math.PI / 180F);
        this.head.rotateAngleY = p_78087_4_ * ((float) Math.PI / 180F);
        this.head.rotateAngleZ = 0.0F;
        this.head.rotationPointX = 0.0F;
        this.body.rotationPointX = 0.0F;
        this.tail.rotationPointX = 0.0F;
        this.wingRight.rotationPointX = -1.5F;
        this.wingLeft.rotationPointX = 1.5F;
        if (this.state != State.SITTING) {
            if (this.state == State.PARTY) {
                float lvt_9_1_ = MathHelper.cos((float) p_78087_7_.ticksExisted);
                float lvt_10_1_ = MathHelper.sin((float) p_78087_7_.ticksExisted);
                this.head.rotationPointX = lvt_9_1_;
                this.head.rotationPointY = 15.69F + lvt_10_1_;
                this.head.rotateAngleX = 0.0F;
                this.head.rotateAngleY = 0.0F;
                this.head.rotateAngleZ = MathHelper.sin((float) p_78087_7_.ticksExisted) * 0.4F;
                this.body.rotationPointX = lvt_9_1_;
                this.body.rotationPointY = 16.5F + lvt_10_1_;
                this.wingLeft.rotateAngleZ = -0.0873F - p_78087_3_;
                this.wingLeft.rotationPointX = 1.5F + lvt_9_1_;
                this.wingLeft.rotationPointY = 16.94F + lvt_10_1_;
                this.wingRight.rotateAngleZ = 0.0873F + p_78087_3_;
                this.wingRight.rotationPointX = -1.5F + lvt_9_1_;
                this.wingRight.rotationPointY = 16.94F + lvt_10_1_;
                this.tail.rotationPointX = lvt_9_1_;
                this.tail.rotationPointY = 21.07F + lvt_10_1_;
            } else {
                if (this.state == State.STANDING) {
                    ModelRenderer var10000 = this.legLeft;
                    var10000.rotateAngleX += MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
                    var10000 = this.legRight;
                    var10000.rotateAngleX += MathHelper.cos(p_78087_1_ * 0.6662F + (float) Math.PI) * 1.4F * p_78087_2_;
                }

                this.head.rotationPointY = 15.69F + lvt_8_1_;
                this.tail.rotateAngleX = 1.015F + MathHelper.cos(p_78087_1_ * 0.6662F) * 0.3F * p_78087_2_;
                this.tail.rotationPointY = 21.07F + lvt_8_1_;
                this.body.rotationPointY = 16.5F + lvt_8_1_;
                this.wingLeft.rotateAngleZ = -0.0873F - p_78087_3_;
                this.wingLeft.rotationPointY = 16.94F + lvt_8_1_;
                this.wingRight.rotateAngleZ = 0.0873F + p_78087_3_;
                this.wingRight.rotationPointY = 16.94F + lvt_8_1_;
                this.legLeft.rotationPointY = 22.0F + lvt_8_1_;
                this.legRight.rotationPointY = 22.0F + lvt_8_1_;
            }
        }
    }

    @Override
    public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
        this.feather.rotateAngleX = -0.2214F;
        this.body.rotateAngleX = 0.4937F;
        this.wingLeft.rotateAngleX = -0.6981F;
        this.wingLeft.rotateAngleY = -(float) Math.PI;
        this.wingRight.rotateAngleX = -0.6981F;
        this.wingRight.rotateAngleY = -(float) Math.PI;
        this.legLeft.rotateAngleX = -0.0299F;
        this.legRight.rotateAngleX = -0.0299F;
        this.legLeft.rotationPointY = 22.0F;
        this.legRight.rotationPointY = 22.0F;
        if (p_78086_1_ instanceof EntityParrot) {
            EntityParrot lvt_5_1_ = (EntityParrot) p_78086_1_;
            if (lvt_5_1_.isPartying()) {
                this.legLeft.rotateAngleZ = -0.34906584F;
                this.legRight.rotateAngleZ = 0.34906584F;
                this.state = State.PARTY;
                return;
            }

            if (lvt_5_1_.isSitting()) {
                float lvt_6_1_ = 1.9F;
                this.head.rotationPointY = 17.59F;
                this.tail.rotateAngleX = 1.5388988F;
                this.tail.rotationPointY = 22.97F;
                this.body.rotationPointY = 18.4F;
                this.wingLeft.rotateAngleZ = -0.0873F;
                this.wingLeft.rotationPointY = 18.84F;
                this.wingRight.rotateAngleZ = 0.0873F;
                this.wingRight.rotationPointY = 18.84F;
                ++this.legLeft.rotationPointY;
                ++this.legRight.rotationPointY;
                ++this.legLeft.rotateAngleX;
                ++this.legRight.rotateAngleX;
                this.state = State.SITTING;
            } else if (lvt_5_1_.isFlying()) {
                ModelRenderer var10000 = this.legLeft;
                var10000.rotateAngleX += 0.6981317F;
                var10000 = this.legRight;
                var10000.rotateAngleX += 0.6981317F;
                this.state = State.FLYING;
            } else {
                this.state = State.STANDING;
            }

            this.legLeft.rotateAngleZ = 0.0F;
            this.legRight.rotateAngleZ = 0.0F;
        } else {
            this.state = State.ON_SHOULDER;
        }

    }

    
    enum State {
        FLYING,
        STANDING,
        SITTING,
        PARTY,
        ON_SHOULDER
    }
}
