package com.jeff.pets.client.rendering.vanilla.zombievillager;

import net.minecraft.client.render.entity.model.BiPedModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ClientZombieVillagerModel extends BiPedModel {
    private ModelPart hatRim;

    public ClientZombieVillagerModel() {
        this(0.0F, 0.0F, false);
    }

    public ClientZombieVillagerModel(float p_i1165_1_, float p_i1165_2_, boolean p_i1165_3_) {
        super(p_i1165_1_, 0.0F, 64, p_i1165_3_ ? 32 : 64);
        if (p_i1165_3_) {
            this.head = new ModelPart(this, 0, 0);
            this.head.addCuboid(-4.0F, -10.0F, -4.0F, 8, 8, 8, p_i1165_1_);
            this.head.setPivot(0.0F, 0.0F + p_i1165_2_, 0.0F);
            this.body = new ModelPart(this, 16, 16);
            this.body.setPivot(0.0F, 0.0F + p_i1165_2_, 0.0F);
            this.body.addCuboid(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i1165_1_ + 0.1F);
            this.field_1478 = new ModelPart(this, 0, 16);
            this.field_1478.setPivot(-2.0F, 12.0F + p_i1165_2_, 0.0F);
            this.field_1478.addCuboid(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1165_1_ + 0.1F);
            this.field_1479 = new ModelPart(this, 0, 16);
            this.field_1479.mirror = true;
            this.field_1479.setPivot(2.0F, 12.0F + p_i1165_2_, 0.0F);
            this.field_1479.addCuboid(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1165_1_ + 0.1F);
        } else {
            this.head = new ModelPart(this, 0, 0);
            this.head.setPivot(0.0F, p_i1165_2_, 0.0F);
            this.head.setTextureOffset(0, 0).addCuboid(-4.0F, -10.0F, -4.0F, 8, 10, 8, p_i1165_1_);
            this.head.setTextureOffset(24, 0).addCuboid(-1.0F, -3.0F, -6.0F, 2, 4, 2, p_i1165_1_);
            this.body = new ModelPart(this, 16, 20);
            this.body.setPivot(0.0F, 0.0F + p_i1165_2_, 0.0F);
            this.body.addCuboid(-4.0F, 0.0F, -3.0F, 8, 12, 6, p_i1165_1_);
            this.body.setTextureOffset(0, 38).addCuboid(-4.0F, 0.0F, -3.0F, 8, 18, 6, p_i1165_1_ + 0.05F);
            this.field_1476 = new ModelPart(this, 44, 38);
            this.field_1476.addCuboid(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i1165_1_);
            this.field_1476.setPivot(-5.0F, 2.0F + p_i1165_2_, 0.0F);
            this.field_1477 = new ModelPart(this, 44, 38);
            this.field_1477.mirror = true;
            this.field_1477.addCuboid(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i1165_1_);
            this.field_1477.setPivot(5.0F, 2.0F + p_i1165_2_, 0.0F);
            this.field_1478 = new ModelPart(this, 0, 22);
            this.field_1478.setPivot(-2.0F, 12.0F + p_i1165_2_, 0.0F);
            this.field_1478.addCuboid(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1165_1_);
            this.field_1479 = new ModelPart(this, 0, 22);
            this.field_1479.mirror = true;
            this.field_1479.setPivot(2.0F, 12.0F + p_i1165_2_, 0.0F);
            this.field_1479.addCuboid(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1165_1_);
        }

    }

    public void setAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        super.setAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        float lvt_9_1_ = MathHelper.sin(this.handSwingProgress * (float)Math.PI);
        float lvt_10_1_ = MathHelper.sin((1.0F - (1.0F - this.handSwingProgress) * (1.0F - this.handSwingProgress)) * (float)Math.PI);
        this.field_1476.posZ = 0.0F;
        this.field_1477.posZ = 0.0F;
        this.field_1476.posY = -(0.1F - lvt_9_1_ * 0.6F);
        this.field_1477.posY = 0.1F - lvt_9_1_ * 0.6F;
        float lvt_11_1_ = -(float)Math.PI / 1.5F;
        this.field_1476.posX = lvt_11_1_;
        this.field_1477.posX = lvt_11_1_;
        ModelPart var10000 = this.field_1476;
        var10000.posX += lvt_9_1_ * 1.2F - lvt_10_1_ * 0.4F;
        var10000 = this.field_1477;
        var10000.posX += lvt_9_1_ * 1.2F - lvt_10_1_ * 0.4F;
        var10000 = this.field_1476;
        var10000.posZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        var10000 = this.field_1477;
        var10000.posZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        var10000 = this.field_1476;
        var10000.posX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        var10000 = this.field_1477;
        var10000.posX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
    }
}
