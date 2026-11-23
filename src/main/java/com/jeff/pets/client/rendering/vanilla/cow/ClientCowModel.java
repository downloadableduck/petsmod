//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.cow;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.client.rendering.QuadrupedModel;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.compat.render.model.ModelPart;

public class ClientCowModel extends QuadrupedModel {
    ModelPart udder;
    ModelPart leftHorn;
    ModelPart rightHorn;

    public ClientCowModel() {
        super(12, 0.0F);
        this.head = new ModelPart(this, 0, 0);
        this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
        this.head.setPos(0.0F, 4.0F, -8.0F);
        this.leftHorn = new ModelPart(this, 22, 0);
        this.leftHorn.addBox(-4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        this.leftHorn.setPos(0.0F, 3.0F, -7.0F);
        this.rightHorn = new ModelPart(this, 22, 0);
        this.rightHorn.addBox(3.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        this.rightHorn.setPos(0.0F, 3.0F, -7.0F);
        this.udder = new ModelPart(this, 52, 0);
        this.udder.addBox(-2.0F, -3.0F, 0.0F, 4, 6, 2, 0.0F);
        this.udder.setPos(0.0F, 14.0F, 6.0F);
        this.udder.rotationX = 1.5707964F;
        this.body = new ModelPart(this, 18, 4);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
        this.body.setPos(0.0F, 5.0F, 2.0F);
        --this.backRightLeg.x;
        ++this.backLeftLeg.x;
        ModelPart var10000 = this.backRightLeg;
        var10000.z += 0.0F;
        var10000 = this.backLeftLeg;
        var10000.z += 0.0F;
        --this.frontRightLeg.x;
        ++this.frontLeftLeg.x;
        --this.frontRightLeg.z;
        --this.frontLeftLeg.z;
    }

    public void setupAnimation(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        super.setupAnimation(walkAnimationProgress, walkAnimationSpeed, bob, yaw, pitch, scale);
        this.leftHorn.rotationY = this.head.rotationY;
        this.leftHorn.rotationX = this.head.rotationX;
        this.rightHorn.rotationY = this.head.rotationY;
        this.rightHorn.rotationX = this.head.rotationX;
    }

    public void render(float f, float g, float h, float j, float k, float d) {
        super.render(f, g, h, j, k, d);
        this.leftHorn.render(d);
        this.rightHorn.render(d);
        this.udder.render(d);
        super.render(f, g, h, j, k, d);
        GlStateManager.pushMatrix();
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(2.0F, 2.0F, 2.0F);
        } else {
            GlStateManager.scalef(1.0F, 1.0F, 1.0F);
        }

        GlStateManager.popMatrix();
    }
}
