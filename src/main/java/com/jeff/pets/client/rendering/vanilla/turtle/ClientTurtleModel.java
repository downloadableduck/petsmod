package com.jeff.pets.client.rendering.vanilla.turtle;

import com.jeff.pets.mob.vanilla.passive.ClientTurtle;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.model.entity.QuadrupedModel;
import net.minecraft.util.math.MathHelper;

public class ClientTurtleModel extends QuadrupedModel<ClientTurtle> {
    private final ModelPart eggBelly;

    public ClientTurtleModel(float f) {
        super(12, f);
        this.f_35376783 /*textureWidth*/ = 128;
        this.f_50207596 /*textureHeight*/ = 64;
        this.head = new ModelPart(this, 3, 0);
        this.head.addBox(-3.0F, -1.0F, -3.0F, 6, 5, 6, 0.0F);
        this.head.setPos(0.0F, 19.0F, -10.0F);
        this.body = new ModelPart(this);
        this.body.setTextureCoords(7, 37).addBox(-9.5F, 3.0F, -10.0F, 19, 20, 6, 0.0F);
        this.body.setTextureCoords(31, 1).addBox(-5.5F, 3.0F, -13.0F, 11, 18, 3, 0.0F);
        this.body.setPos(0.0F, 11.0F, -10.0F);
        this.eggBelly = new ModelPart(this);
        this.eggBelly.setTextureCoords(70, 33).addBox(-4.5F, 3.0F, -14.0F, 9, 18, 1, 0.0F);
        this.eggBelly.setPos(0.0F, 11.0F, -10.0F);
        int i = 1;
        this.backRightLeg = new ModelPart(this, 1, 23);
        this.backRightLeg.addBox(-2.0F, 0.0F, 0.0F, 4, 1, 10, 0.0F);
        this.backRightLeg.setPos(-3.5F, 22.0F, 11.0F);
        this.backLeftLeg = new ModelPart(this, 1, 12);
        this.backLeftLeg.addBox(-2.0F, 0.0F, 0.0F, 4, 1, 10, 0.0F);
        this.backLeftLeg.setPos(3.5F, 22.0F, 11.0F);
        this.frontRightLeg = new ModelPart(this, 27, 30);
        this.frontRightLeg.addBox(-13.0F, 0.0F, -2.0F, 13, 1, 5, 0.0F);
        this.frontRightLeg.setPos(-5.0F, 21.0F, -4.0F);
        this.frontLeftLeg = new ModelPart(this, 27, 24);
        this.frontLeftLeg.addBox(0.0F, 0.0F, -2.0F, 13, 1, 5, 0.0F);
        this.frontLeftLeg.setPos(5.0F, 21.0F, -4.0F);
    }

    public void setup(ClientTurtle turtle, float f, float g, float h, float i, float j, float s) {
        super.setup(turtle, f, g, h, i, j, s);
        this.backRightLeg.rotationX = MathHelper.cos(f * 0.6662F * 0.6F) * 0.5F * g;
        this.backLeftLeg.rotationX = MathHelper.cos(f * 0.6662F * 0.6F + (float) Math.PI) * 0.5F * g;
        this.frontRightLeg.rotationZ = MathHelper.cos(f * 0.6662F * 0.6F + (float) Math.PI) * 0.5F * g;
        this.frontLeftLeg.rotationZ = MathHelper.cos(f * 0.6662F * 0.6F) * 0.5F * g;
        this.frontRightLeg.rotationX = 0.0F;
        this.frontLeftLeg.rotationX = 0.0F;
        this.frontRightLeg.rotationY = 0.0F;
        this.frontLeftLeg.rotationY = 0.0F;
        this.backRightLeg.rotationY = 0.0F;
        this.backLeftLeg.rotationY = 0.0F;
        this.eggBelly.rotationX = ((float) Math.PI / 2F);
        if (!turtle.isInWater() && turtle.onGround) {
            float k = 1.0F;
            float l = 1.0F;
            float m = 5.0F;
            this.frontRightLeg.rotationY = MathHelper.cos(k * f * 5.0F + (float) Math.PI) * 8.0F * g * l;
            this.frontRightLeg.rotationZ = 0.0F;
            this.frontLeftLeg.rotationY = MathHelper.cos(k * f * 5.0F) * 8.0F * g * l;
            this.frontLeftLeg.rotationZ = 0.0F;
            this.backRightLeg.rotationY = MathHelper.cos(f * 5.0F + (float) Math.PI) * 3.0F * g;
            this.backRightLeg.rotationX = 0.0F;
            this.backLeftLeg.rotationY = MathHelper.cos(f * 5.0F) * 3.0F * g;
            this.backLeftLeg.rotationX = 0.0F;
        }

        this.eggBelly.visible = false;
    }

    public void render(ClientTurtle turtle, float i, float j, float f, float g, float h, float k) {
        boolean bl = this.eggBelly.visible;
        if (bl) {
            com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
            com.mojang.blaze3d.platform.GlStateManager.translate(0.0F, -0.08F, 0.0F);
        }

        super.render(turtle, i, j, f, g, h, k);
        if (bl) {
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();
        }

    }
}
