package com.jeff.pets.client.rendering.vanilla.parrot;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.passive.ClientParrot;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ClientParrotModel extends EntityModel {
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart wingLeft;
    private final ModelPart wingRight;
    private final ModelPart head;
    private final ModelPart head2;
    private final ModelPart beak1;
    private final ModelPart beak2;
    private final ModelPart feather;
    private final ModelPart legLeft;
    private final ModelPart legRight;
    private State state;

    public ClientParrotModel() {
        this.state = State.STANDING;
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.body = new ModelPart(this, 2, 8);
        this.body.addCuboid(-1.5F, 0.0F, -1.5F, 3, 6, 3);
        this.body.setPivot(0.0F, 16.5F, -3.0F);
        this.tail = new ModelPart(this, 22, 1);
        this.tail.addCuboid(-1.5F, -1.0F, -1.0F, 3, 4, 1);
        this.tail.setPivot(0.0F, 21.07F, 1.16F);
        this.wingLeft = new ModelPart(this, 19, 8);
        this.wingLeft.addCuboid(-0.5F, 0.0F, -1.5F, 1, 5, 3);
        this.wingLeft.setPivot(1.5F, 16.94F, -2.76F);
        this.wingRight = new ModelPart(this, 19, 8);
        this.wingRight.addCuboid(-0.5F, 0.0F, -1.5F, 1, 5, 3);
        this.wingRight.setPivot(-1.5F, 16.94F, -2.76F);
        this.head = new ModelPart(this, 2, 2);
        this.head.addCuboid(-1.0F, -1.5F, -1.0F, 2, 3, 2);
        this.head.setPivot(0.0F, 15.69F, -2.76F);
        this.head2 = new ModelPart(this, 10, 0);
        this.head2.addCuboid(-1.0F, -0.5F, -2.0F, 2, 1, 4);
        this.head2.setPivot(0.0F, -2.0F, -1.0F);
        this.head.add(this.head2);
        this.beak1 = new ModelPart(this, 11, 7);
        this.beak1.addCuboid(-0.5F, -1.0F, -0.5F, 1, 2, 1);
        this.beak1.setPivot(0.0F, -0.5F, -1.5F);
        this.head.add(this.beak1);
        this.beak2 = new ModelPart(this, 16, 7);
        this.beak2.addCuboid(-0.5F, 0.0F, -0.5F, 1, 2, 1);
        this.beak2.setPivot(0.0F, -1.75F, -2.45F);
        this.head.add(this.beak2);
        this.feather = new ModelPart(this, 2, 18);
        this.feather.addCuboid(0.0F, -4.0F, -2.0F, 0, 5, 4);
        this.feather.setPivot(0.0F, -2.15F, 0.15F);
        this.head.add(this.feather);
        this.legLeft = new ModelPart(this, 14, 18);
        this.legLeft.addCuboid(-0.5F, 0.0F, -0.5F, 1, 2, 1);
        this.legLeft.setPivot(1.0F, 22.0F, -1.05F);
        this.legRight = new ModelPart(this, 14, 18);
        this.legRight.addCuboid(-0.5F, 0.0F, -0.5F, 1, 2, 1);
        this.legRight.setPivot(-1.0F, 22.0F, -1.05F);
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
    public void setAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        float lvt_8_1_ = p_78087_3_ * 0.3F;
        this.head.posX = p_78087_5_ * ((float)Math.PI / 180F);
        this.head.posY = p_78087_4_ * ((float)Math.PI / 180F);
        this.head.posZ = 0.0F;
        this.head.pivotX = 0.0F;
        this.body.pivotX = 0.0F;
        this.tail.pivotX = 0.0F;
        this.wingRight.pivotX = -1.5F;
        this.wingLeft.pivotX = 1.5F;
        if (this.state != State.SITTING) {
            if (this.state == State.PARTY) {
                float lvt_9_1_ = MathHelper.cos((float)p_78087_7_.ticksAlive);
                float lvt_10_1_ = MathHelper.sin((float)p_78087_7_.ticksAlive);
                this.head.pivotX = lvt_9_1_;
                this.head.pivotY = 15.69F + lvt_10_1_;
                this.head.posX = 0.0F;
                this.head.posY = 0.0F;
                this.head.posZ = MathHelper.sin((float)p_78087_7_.ticksAlive) * 0.4F;
                this.body.pivotX = lvt_9_1_;
                this.body.pivotY = 16.5F + lvt_10_1_;
                this.wingLeft.posZ = -0.0873F - p_78087_3_;
                this.wingLeft.pivotX = 1.5F + lvt_9_1_;
                this.wingLeft.pivotY = 16.94F + lvt_10_1_;
                this.wingRight.posZ = 0.0873F + p_78087_3_;
                this.wingRight.pivotX = -1.5F + lvt_9_1_;
                this.wingRight.pivotY = 16.94F + lvt_10_1_;
                this.tail.pivotX = lvt_9_1_;
                this.tail.pivotY = 21.07F + lvt_10_1_;
            } else {
                if (this.state == State.STANDING) {
                    ModelPart var10000 = this.legLeft;
                    var10000.posX += MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
                    var10000 = this.legRight;
                    var10000.posX += MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
                }

                this.head.pivotY = 15.69F + lvt_8_1_;
                this.tail.posX = 1.015F + MathHelper.cos(p_78087_1_ * 0.6662F) * 0.3F * p_78087_2_;
                this.tail.pivotY = 21.07F + lvt_8_1_;
                this.body.pivotY = 16.5F + lvt_8_1_;
                this.wingLeft.posZ = -0.0873F - p_78087_3_;
                this.wingLeft.pivotY = 16.94F + lvt_8_1_;
                this.wingRight.posZ = 0.0873F + p_78087_3_;
                this.wingRight.pivotY = 16.94F + lvt_8_1_;
                this.legLeft.pivotY = 22.0F + lvt_8_1_;
                this.legRight.pivotY = 22.0F + lvt_8_1_;
            }
        }
    }

    @Override
    public void animateModel(LivingEntity p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
        this.feather.posX = -0.2214F;
        this.body.posX = 0.4937F;
        this.wingLeft.posX = -0.6981F;
        this.wingLeft.posY = -(float)Math.PI;
        this.wingRight.posX = -0.6981F;
        this.wingRight.posY = -(float)Math.PI;
        this.legLeft.posX = -0.0299F;
        this.legRight.posX = -0.0299F;
        this.legLeft.pivotY = 22.0F;
        this.legRight.pivotY = 22.0F;
    }

    static enum State {
        FLYING,
        STANDING,
        SITTING,
        PARTY,
        ON_SHOULDER;
    }
}
