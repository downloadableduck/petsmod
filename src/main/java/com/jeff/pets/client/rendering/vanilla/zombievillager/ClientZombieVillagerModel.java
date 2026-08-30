package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.client.rendering.AnimationUtils;
import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.model.entity.HumanoidModel;


public class ClientZombieVillagerModel extends HumanoidModel {
    private ModelPart hatRim;

    public ClientZombieVillagerModel(float f, boolean bl) {
        super(f, 0.0F, 64, bl ? 32 : 64);
        if (bl) {
            this.head = new ModelPart(this, 0, 0);
            this.head.addBox(-4.0F, -10.0F, -4.0F, 8, 8, 8, f);
            this.body = new ModelPart(this, 16, 16);
            this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, f + 0.1F);
            this.rightLeg = new ModelPart(this, 0, 16);
            this.rightLeg.setPos(-2.0F, 12.0F, 0.0F);
            this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f + 0.1F);
            this.leftLeg = new ModelPart(this, 0, 16);
            this.leftLeg.flipped = true;
            this.leftLeg.setPos(2.0F, 12.0F, 0.0F);
            this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f + 0.1F);
        } else {
            this.head = new ModelPart(this, 0, 0);
            this.head.setTextureCoords(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, f);
            this.head.setTextureCoords(24, 0).addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2, f);
            this.hat = new ModelPart(this, 32, 0);
            this.hat.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, f + 0.5F);
            this.hatRim = new ModelPart(this);
            this.hatRim.setTextureCoords(30, 47).addBox(-8.0F, -8.0F, -6.0F, 16, 16, 1, f);
            this.hatRim.rotationX = (-(float) Math.PI / 2F);
            this.hat.addChild(this.hatRim);
            this.body = new ModelPart(this, 16, 20);
            this.body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, f);
            this.body.setTextureCoords(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, f + 0.05F);
            this.rightArm = new ModelPart(this, 44, 22);
            this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, f);
            this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
            this.leftArm = new ModelPart(this, 44, 22);
            this.leftArm.flipped = true;
            this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, f);
            this.leftArm.setPos(5.0F, 2.0F, 0.0F);
            this.rightLeg = new ModelPart(this, 0, 22);
            this.rightLeg.setPos(-2.0F, 12.0F, 0.0F);
            this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
            this.leftLeg = new ModelPart(this, 0, 22);
            this.leftLeg.flipped = true;
            this.leftLeg.setPos(2.0F, 12.0F, 0.0F);
            this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
        }

    }

    @Override
    public void setupAnimation(float f, float g, float h, float i, float j, float s, net.minecraft.entity.Entity entity) {
        super.setupAnimation(f, g, h, i, j, s, entity);
        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, true, this.attackAnimationProgress, h);
    }

    public void setHatVisible(boolean bl) {
        this.head.visible = bl;
        this.hat.visible = bl;
        this.hatRim.visible = bl;
    }
}
