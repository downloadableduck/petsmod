//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering;

import com.jeff.pets.compat.render.model.Model;
import com.jeff.pets.compat.render.model.ModelPart;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.util.helper.MathHelper;;

@Environment(EnvType.CLIENT)
public class QuadrupedModel extends Model {
    public ModelPart head = new ModelPart(this, 0, 0);
    public ModelPart body;
    public ModelPart backRightLeg;
    public ModelPart backLeftLeg;
    public ModelPart frontRightLeg;
    public ModelPart frontLeftLeg;

    public QuadrupedModel(int pivotPoint, float reduction) {
        this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, reduction);
        this.head.setPos(0.0F, (float)(18 - pivotPoint), -6.0F);
        this.body = new ModelPart(this, 28, 8);
        this.body.addBox(-5.0F, -10.0F, -7.0F, 10, 16, 8, reduction);
        this.body.setPos(0.0F, (float)(17 - pivotPoint), 2.0F);
        this.backRightLeg = new ModelPart(this, 0, 16);
        this.backRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, pivotPoint, 4, reduction);
        this.backRightLeg.setPos(-3.0F, (float)(24 - pivotPoint), 7.0F);
        this.backLeftLeg = new ModelPart(this, 0, 16);
        this.backLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, pivotPoint, 4, reduction);
        this.backLeftLeg.setPos(3.0F, (float)(24 - pivotPoint), 7.0F);
        this.frontRightLeg = new ModelPart(this, 0, 16);
        this.frontRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, pivotPoint, 4, reduction);
        this.frontRightLeg.setPos(-3.0F, (float)(24 - pivotPoint), -5.0F);
        this.frontLeftLeg = new ModelPart(this, 0, 16);
        this.frontLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, pivotPoint, 4, reduction);
        this.frontLeftLeg.setPos(3.0F, (float)(24 - pivotPoint), -5.0F);
    }

    public void render(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        this.setupAnimation(walkAnimationProgress, walkAnimationSpeed, bob, yaw, pitch, scale);
        this.head.render(scale);
        this.body.render(scale);
        this.backRightLeg.render(scale);
        this.backLeftLeg.render(scale);
        this.frontRightLeg.render(scale);
        this.frontLeftLeg.render(scale);
    }

    public void setupAnimation(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        this.head.rotationX = pitch / 57.295776F;
        this.head.rotationY = yaw / 57.295776F;
        this.body.rotationX = 1.5707964F;
        this.backRightLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F) * 1.4F * walkAnimationSpeed;
        this.backLeftLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F + 3.1415927F) * 1.4F * walkAnimationSpeed;
        this.frontRightLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F + 3.1415927F) * 1.4F * walkAnimationSpeed;
        this.frontLeftLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F) * 1.4F * walkAnimationSpeed;
    }
}
