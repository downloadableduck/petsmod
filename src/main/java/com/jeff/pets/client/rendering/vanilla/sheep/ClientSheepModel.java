package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ClientSheepModel extends PetModel {
    public ModelPart head;
    public ModelPart body;
    public ModelPart backRightLeg;
    public ModelPart backLeftLeg;
    public ModelPart frontRightLeg;
    public ModelPart frontLeftLeg;

    public ClientSheepModel() {
        this.head = new ModelPart(this, 0, 0);
        this.head.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
        this.head.setPos(0.0F, 6.0F, -8.0F);
        this.body = new ModelPart(this, 28, 8);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
        this.body.setPos(0.0F, 5.0F, 2.0F);
        this.backRightLeg = new ModelPart(this, 0, 16);
        this.backRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.backRightLeg.setPos(-3.0F, 12.0F, 7.0F);
        this.backLeftLeg = new ModelPart(this, 0, 16);
        this.backLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.backLeftLeg.setPos(3.0F, 12.0F, 7.0F);
        this.frontRightLeg = new ModelPart(this, 0, 16);
        this.frontRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.frontRightLeg.setPos(-3.0F, 12.0F, -5.0F);
        this.frontLeftLeg = new ModelPart(this, 0, 16);
        this.frontLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.frontLeftLeg.setPos(3.0F, 12.0F, -5.0F);
    }

    @Override
    protected void renderModel(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setupAnimation(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.head.render(scale);
        this.body.render(scale);
        this.backRightLeg.render(scale);
        this.backLeftLeg.render(scale);
        this.frontRightLeg.render(scale);
        this.frontLeftLeg.render(scale);
    }

    @Override
    public void setupAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.head.rotationX = headPitch * ((float)Math.PI / 180F);
        this.head.rotationY = netHeadYaw * ((float)Math.PI / 180F);
        this.body.rotationX = (float)Math.PI / 2F;
        this.backRightLeg.rotationX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.backLeftLeg.rotationX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.frontRightLeg.rotationX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.frontLeftLeg.rotationX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void prepare(MobEntity entity, float limbSwing, float limbSwingAmount, float tickDelta) {
    }
}
