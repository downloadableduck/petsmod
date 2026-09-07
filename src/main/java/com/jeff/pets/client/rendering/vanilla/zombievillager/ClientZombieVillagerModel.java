package com.jeff.pets.client.rendering.vanilla.zombievillager;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ClientZombieVillagerModel extends ModelBiped {
    private ModelRenderer hatRim;

    public ClientZombieVillagerModel() {
        this(0.0F, 0.0F, false);
    }

    public ClientZombieVillagerModel(float p_i1165_1_, float p_i1165_2_, boolean p_i1165_3_) {
        super(p_i1165_1_, 0.0F, 64, p_i1165_3_ ? 32 : 64);
        if (p_i1165_3_) {
            this.bipedHead = new ModelRenderer(this, 0, 0);
            this.bipedHead.addBox(-4.0F, -10.0F, -4.0F, 8, 8, 8, p_i1165_1_);
            this.bipedHead.setRotationPoint(0.0F, 0.0F + p_i1165_2_, 0.0F);
            this.bipedBody = new ModelRenderer(this, 16, 16);
            this.bipedBody.setRotationPoint(0.0F, 0.0F + p_i1165_2_, 0.0F);
            this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i1165_1_ + 0.1F);
            this.bipedRightLeg = new ModelRenderer(this, 0, 16);
            this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F + p_i1165_2_, 0.0F);
            this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1165_1_ + 0.1F);
            this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
            this.bipedLeftLeg.mirror = true;
            this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F + p_i1165_2_, 0.0F);
            this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1165_1_ + 0.1F);
        } else {
            this.bipedHead = new ModelRenderer(this, 0, 0);
            this.bipedHead.setRotationPoint(0.0F, p_i1165_2_, 0.0F);
            this.bipedHead.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, p_i1165_1_);
            this.bipedHead.setTextureOffset(24, 0).addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2, p_i1165_1_);
            this.bipedBody = new ModelRenderer(this, 16, 20);
            this.bipedBody.setRotationPoint(0.0F, 0.0F + p_i1165_2_, 0.0F);
            this.bipedBody.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, p_i1165_1_);
            this.bipedBody.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, p_i1165_1_ + 0.05F);
            this.bipedRightArm = new ModelRenderer(this, 44, 38);
            this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i1165_1_);
            this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + p_i1165_2_, 0.0F);
            this.bipedLeftArm = new ModelRenderer(this, 44, 38);
            this.bipedLeftArm.mirror = true;
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i1165_1_);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + p_i1165_2_, 0.0F);
            this.bipedRightLeg = new ModelRenderer(this, 0, 22);
            this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F + p_i1165_2_, 0.0F);
            this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1165_1_);
            this.bipedLeftLeg = new ModelRenderer(this, 0, 22);
            this.bipedLeftLeg.mirror = true;
            this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F + p_i1165_2_, 0.0F);
            this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1165_1_);
        }

    }

    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        float lvt_9_1_ = MathHelper.sin(this.swingProgress * (float) Math.PI);
        float lvt_10_1_ = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightArm.rotateAngleY = -(0.1F - lvt_9_1_ * 0.6F);
        this.bipedLeftArm.rotateAngleY = 0.1F - lvt_9_1_ * 0.6F;
        float lvt_11_1_ = -(float) Math.PI / 1.5F;
        this.bipedRightArm.rotateAngleX = lvt_11_1_;
        this.bipedLeftArm.rotateAngleX = lvt_11_1_;
        ModelRenderer var10000 = this.bipedRightArm;
        var10000.rotateAngleX += lvt_9_1_ * 1.2F - lvt_10_1_ * 0.4F;
        var10000 = this.bipedLeftArm;
        var10000.rotateAngleX += lvt_9_1_ * 1.2F - lvt_10_1_ * 0.4F;
        var10000 = this.bipedRightArm;
        var10000.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        var10000 = this.bipedLeftArm;
        var10000.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        var10000 = this.bipedRightArm;
        var10000.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        var10000 = this.bipedLeftArm;
        var10000.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
    }
}
