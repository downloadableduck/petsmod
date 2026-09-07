package com.jeff.pets.client.rendering.vanilla.evoker;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.vanilla.hostile.ClientEvoker;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ClientEvokerModel extends PetModel {
    private final ModelRenderer head;
    private final ModelRenderer hat;
    private final ModelRenderer body;
    private final ModelRenderer arms;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer nose;
    private final ModelRenderer rightArm;
    private final ModelRenderer leftArm;

    public ClientEvokerModel(float p_i47227_1_, float p_i47227_2_, int p_i47227_3_, int p_i47227_4_) {
        this.head = (new ModelRenderer(this)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.head.setRotationPoint(0.0F, 0.0F + p_i47227_2_, 0.0F);
        this.head.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, p_i47227_1_);
        this.hat = (new ModelRenderer(this, 32, 0)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.hat.addBox(-4.0F, -10.0F, -4.0F, 8, 12, 8, p_i47227_1_ + 0.45F);
        this.head.addChild(this.hat);
        this.hat.showModel = false;
        this.nose = (new ModelRenderer(this)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.nose.setRotationPoint(0.0F, p_i47227_2_ - 2.0F, 0.0F);
        this.nose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, p_i47227_1_);
        this.head.addChild(this.nose);
        this.body = (new ModelRenderer(this)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.body.setRotationPoint(0.0F, 0.0F + p_i47227_2_, 0.0F);
        this.body.setTextureOffset(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, p_i47227_1_);
        this.body.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, p_i47227_1_ + 0.5F);
        this.arms = (new ModelRenderer(this)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.arms.setRotationPoint(0.0F, 0.0F + p_i47227_2_ + 2.0F, 0.0F);
        this.arms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, p_i47227_1_);
        ModelRenderer lvt_5_1_ = (new ModelRenderer(this, 44, 22)).setTextureSize(p_i47227_3_, p_i47227_4_);
        lvt_5_1_.mirror = true;
        lvt_5_1_.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, p_i47227_1_);
        this.arms.addChild(lvt_5_1_);
        this.arms.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, p_i47227_1_);
        this.leg0 = (new ModelRenderer(this, 0, 22)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.leg0.setRotationPoint(-2.0F, 12.0F + p_i47227_2_, 0.0F);
        this.leg0.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i47227_1_);
        this.leg1 = (new ModelRenderer(this, 0, 22)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.leg1.mirror = true;
        this.leg1.setRotationPoint(2.0F, 12.0F + p_i47227_2_, 0.0F);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i47227_1_);
        this.rightArm = (new ModelRenderer(this, 40, 46)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i47227_1_);
        this.rightArm.setRotationPoint(-5.0F, 2.0F + p_i47227_2_, 0.0F);
        this.leftArm = (new ModelRenderer(this, 40, 46)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.leftArm.mirror = true;
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i47227_1_);
        this.leftArm.setRotationPoint(5.0F, 2.0F + p_i47227_2_, 0.0F);
    }

    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        this.head.render(p_78088_7_);
        this.body.render(p_78088_7_);
        this.leg0.render(p_78088_7_);
        this.leg1.render(p_78088_7_);
        ClientEvoker lvt_8_1_ = (ClientEvoker) p_78088_1_;
        this.arms.render(p_78088_7_);
    }

    @Override
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        this.head.rotateAngleY = p_78087_4_ * ((float) Math.PI / 180F);
        this.head.rotateAngleX = p_78087_5_ * ((float) Math.PI / 180F);
        this.arms.rotationPointY = 3.0F;
        this.arms.rotationPointZ = -1.0F;
        this.arms.rotateAngleX = -0.75F;
        this.leg0.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_ * 0.5F;
        this.leg1.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float) Math.PI) * 1.4F * p_78087_2_ * 0.5F;
        this.leg0.rotateAngleY = 0.0F;
        this.leg1.rotateAngleY = 0.0F;
    }

    public ModelRenderer getArm(EnumHandSide p_191216_1_) {
        return p_191216_1_ == EnumHandSide.LEFT ? this.leftArm : this.rightArm;
    }

    public ModelRenderer func_205062_a() {
        return this.hat;
    }
}
