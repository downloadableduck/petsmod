package com.jeff.pets.client.rendering.vanilla.evoker;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.vanilla.hostile.ClientEvoker;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ClientEvokerModel extends PetModel {
    private final ModelPart head;
    private final ModelPart hat;
    private final ModelPart body;
    private final ModelPart arms;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart nose;
    private final ModelPart rightArm;
    private final ModelPart leftArm;

    public ClientEvokerModel(float p_i47227_1_, float p_i47227_2_, int p_i47227_3_, int p_i47227_4_) {
        this.head = (new ModelPart(this)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.head.setPivot(0.0F, 0.0F + p_i47227_2_, 0.0F);
        this.head.setTextureOffset(0, 0).addCuboid(-4.0F, -10.0F, -4.0F, 8, 10, 8, p_i47227_1_);
        this.hat = (new ModelPart(this, 32, 0)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.hat.addCuboid(-4.0F, -10.0F, -4.0F, 8, 12, 8, p_i47227_1_ + 0.45F);
        this.head.add(this.hat);
        this.hat.visible = false;
        this.nose = (new ModelPart(this)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.nose.setPivot(0.0F, p_i47227_2_ - 2.0F, 0.0F);
        this.nose.setTextureOffset(24, 0).addCuboid(-1.0F, -1.0F, -6.0F, 2, 4, 2, p_i47227_1_);
        this.head.add(this.nose);
        this.body = (new ModelPart(this)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.body.setPivot(0.0F, 0.0F + p_i47227_2_, 0.0F);
        this.body.setTextureOffset(16, 20).addCuboid(-4.0F, 0.0F, -3.0F, 8, 12, 6, p_i47227_1_);
        this.body.setTextureOffset(0, 38).addCuboid(-4.0F, 0.0F, -3.0F, 8, 18, 6, p_i47227_1_ + 0.5F);
        this.arms = (new ModelPart(this)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.arms.setPivot(0.0F, 0.0F + p_i47227_2_ + 2.0F, 0.0F);
        this.arms.setTextureOffset(44, 22).addCuboid(-8.0F, -2.0F, -2.0F, 4, 8, 4, p_i47227_1_);
        ModelPart lvt_5_1_ = (new ModelPart(this, 44, 22)).setTextureSize(p_i47227_3_, p_i47227_4_);
        lvt_5_1_.mirror = true;
        lvt_5_1_.addCuboid(4.0F, -2.0F, -2.0F, 4, 8, 4, p_i47227_1_);
        this.arms.add(lvt_5_1_);
        this.arms.setTextureOffset(40, 38).addCuboid(-4.0F, 2.0F, -2.0F, 8, 4, 4, p_i47227_1_);
        this.leg0 = (new ModelPart(this, 0, 22)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.leg0.setPivot(-2.0F, 12.0F + p_i47227_2_, 0.0F);
        this.leg0.addCuboid(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i47227_1_);
        this.leg1 = (new ModelPart(this, 0, 22)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.leg1.mirror = true;
        this.leg1.setPivot(2.0F, 12.0F + p_i47227_2_, 0.0F);
        this.leg1.addCuboid(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i47227_1_);
        this.rightArm = (new ModelPart(this, 40, 46)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.rightArm.addCuboid(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i47227_1_);
        this.rightArm.setPivot(-5.0F, 2.0F + p_i47227_2_, 0.0F);
        this.leftArm = (new ModelPart(this, 40, 46)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.leftArm.mirror = true;
        this.leftArm.addCuboid(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i47227_1_);
        this.leftArm.setPivot(5.0F, 2.0F + p_i47227_2_, 0.0F);
    }

    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.setAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        this.head.render(p_78088_7_);
        this.body.render(p_78088_7_);
        this.leg0.render(p_78088_7_);
        this.leg1.render(p_78088_7_);
        ClientEvoker lvt_8_1_ = (ClientEvoker) p_78088_1_;
        this.arms.render(p_78088_7_);
    }

    @Override
    public void setAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        this.head.posY = p_78087_4_ * ((float)Math.PI / 180F);
        this.head.posX = p_78087_5_ * ((float)Math.PI / 180F);
        this.arms.pivotY = 3.0F;
        this.arms.pivotZ = -1.0F;
        this.arms.posX = -0.75F;
        this.leg0.posX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_ * 0.5F;
        this.leg1.posX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_ * 0.5F;
        this.leg0.posY = 0.0F;
        this.leg1.posY = 0.0F;
    }

    public ModelPart func_205062_a() {
        return this.hat;
    }
}
