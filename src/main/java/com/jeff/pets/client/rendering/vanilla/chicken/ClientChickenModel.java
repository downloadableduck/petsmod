//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;
import net.minecraft.core.util.helper.MathHelper;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.Math2;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientChicken;

public class ClientChickenModel extends PetModel {
    public ModelPart head;
    public ModelPart body;
    public ModelPart rightLeg;
    public ModelPart leftLeg;
    public ModelPart rightWing;
    public ModelPart leftWing;
    public ModelPart beak;
    public ModelPart waddle;

    public ClientChickenModel() {
        int i = 16;
        this.head = new ModelPart(this, 0, 0);
        this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
        this.head.setPos(0.0F, (float)(-1 + i), -4.0F);
        this.beak = new ModelPart(this, 14, 0);
        this.beak.addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
        this.beak.setPos(0.0F, (float)(-1 + i), -4.0F);
        this.waddle = new ModelPart(this, 14, 4);
        this.waddle.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);
        this.waddle.setPos(0.0F, (float)(-1 + i), -4.0F);
        this.body = new ModelPart(this, 0, 9);
        this.body.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
        this.body.setPos(0.0F, (float)(0 + i), 0.0F);
        this.rightLeg = new ModelPart(this, 26, 0);
        this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
        this.rightLeg.setPos(-2.0F, (float)(3 + i), 1.0F);
        this.leftLeg = new ModelPart(this, 26, 0);
        this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
        this.leftLeg.setPos(1.0F, (float)(3 + i), 1.0F);
        this.rightWing = new ModelPart(this, 24, 13);
        this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6);
        this.rightWing.setPos(-4.0F, (float)(-3 + i), 0.0F);
        this.leftWing = new ModelPart(this, 24, 13);
        this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6);
        this.leftWing.setPos(4.0F, (float)(-3 + i), 0.0F);
    }

    public void setupAnimation(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        this.head.rotationX = -(pitch / 57.295776F);
        this.head.rotationY = yaw / 57.295776F;
        this.beak.rotationX = this.head.rotationX;
        this.beak.rotationY = this.head.rotationY;
        this.waddle.rotationX = this.head.rotationX;
        this.waddle.rotationY = this.head.rotationY;
        this.body.rotationX = 1.5707964F;
        this.rightLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F) * 1.4F * walkAnimationSpeed;
        this.leftLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F + 3.1415927F) * 1.4F * walkAnimationSpeed;
        this.rightWing.rotationZ = bob;
        this.leftWing.rotationZ = -bob;
    }

    protected float getBob(ClientChicken chicken, float f) {
        float g = Math2.lerp(f, chicken.oFlap, chicken.flap);
        float h = Math2.lerp(f, chicken.oFlapSpeed, chicken.flapSpeed);
        return (MathHelper.sin(g) + 1.0F) * h;
    }

    public void render(float f, float g, float h, float j, float k, float d) {
        this.setupAnimation(f, g, h, j, k, d);
        this.head.render(d);
        this.beak.render(d);
        this.waddle.render(d);
        this.body.render(d);
        this.rightLeg.render(d);
        this.leftLeg.render(d);
        this.rightWing.render(d);
        this.leftWing.render(d);
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
