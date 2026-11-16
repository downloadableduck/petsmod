package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ClientSheepFurModel extends PetModel {
    public ModelPart head;
    public ModelPart body;
    public ModelPart backRightLeg;
    public ModelPart backLeftLeg;
    public ModelPart frontRightLeg;
    public ModelPart frontLeftLeg;
    private float headAngle;

    public ClientSheepFurModel() {
        this.head = new ModelPart(this, 0, 0);
        this.head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
        this.head.setPos(0.0F, 6.0F, -8.0F);
        this.body = new ModelPart(this, 28, 8);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
        this.body.setPos(0.0F, 5.0F, 2.0F);
        this.backRightLeg = new ModelPart(this, 0, 16);
        this.backRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.backRightLeg.setPos(-3.0F, 12.0F, 7.0F);
        this.backLeftLeg = new ModelPart(this, 0, 16);
        this.backLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.backLeftLeg.setPos(3.0F, 12.0F, 7.0F);
        this.frontRightLeg = new ModelPart(this, 0, 16);
        this.frontRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.frontRightLeg.setPos(-3.0F, 12.0F, -5.0F);
        this.frontLeftLeg = new ModelPart(this, 0, 16);
        this.frontLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.frontLeftLeg.setPos(3.0F, 12.0F, -5.0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setupAnimation(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.head.render(scale);
        this.body.render(scale);
        this.backRightLeg.render(scale);
        this.backLeftLeg.render(scale);
        this.frontRightLeg.render(scale);
        this.frontLeftLeg.render(scale);
    }

    @Override
    public void prepare(MobEntity mob, float walkAnimationProgress, float walkAnimationSpeed, float tickDelta) {
    }

    @Override
    public void setupAnimation(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        this.head.rotationX = this.headAngle;
        this.head.rotationY = yaw * ((float)Math.PI / 180F);
        this.body.rotationX = (float)Math.PI / 2F;
        this.backRightLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F) * 1.4F * walkAnimationSpeed;
        this.backLeftLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F + (float)Math.PI) * 1.4F * walkAnimationSpeed;
        this.frontRightLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F + (float)Math.PI) * 1.4F * walkAnimationSpeed;
        this.frontLeftLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F) * 1.4F * walkAnimationSpeed;
    }
}
