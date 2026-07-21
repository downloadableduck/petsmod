package com.jeff.pets.client.rendering.vanilla.fox;

import com.jeff.pets.mob.vanilla.neutral.ClientFox;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.util.math.MathHelper;

public class ClientFoxModel extends Model<ClientFox> {
    public final ModelPart head;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart nose;
    private final ModelPart body;
    private final ModelPart frontLeftLeg;
    private final ModelPart frontRightLeg;
    private final ModelPart rearLeftLeg;
    private final ModelPart rearRightLeg;
    private final ModelPart tail;
    private float field_18025;

    public ClientFoxModel() {
        this.f_9972380 /*textureWidth*/ = 48;
        this.f_9233444 /*textureHeight*/ = 32;
        this.head = new ModelPart(this, 1, 5);
        this.head.addBox(-3.0F, -2.0F, -5.0F, 8, 6, 6);
        this.head.setPos(-1.0F, 16.5F, -3.0F);
        this.leftEar = new ModelPart(this, 8, 1);
        this.leftEar.addBox(-3.0F, -4.0F, -4.0F, 2, 2, 1);
        this.rightEar = new ModelPart(this, 15, 1);
        this.rightEar.addBox(3.0F, -4.0F, -4.0F, 2, 2, 1);
        this.nose = new ModelPart(this, 6, 18);
        this.nose.addBox(-1.0F, 2.01F, -8.0F, 4, 2, 3);
        this.head.addChild(this.leftEar);
        this.head.addChild(this.rightEar);
        this.head.addChild(this.nose);
        this.body = new ModelPart(this, 24, 15);
        this.body.addBox(-3.0F, 3.999F, -3.5F, 6, 11, 6);
        this.body.setPos(0.0F, 16.0F, -6.0F);
        float f = 0.001F;
        this.frontLeftLeg = new ModelPart(this, 13, 24);
        this.frontLeftLeg.addBox(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
        this.frontLeftLeg.setPos(-5.0F, 17.5F, 7.0F);
        this.frontRightLeg = new ModelPart(this, 4, 24);
        this.frontRightLeg.addBox(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
        this.frontRightLeg.setPos(-1.0F, 17.5F, 7.0F);
        this.rearLeftLeg = new ModelPart(this, 13, 24);
        this.rearLeftLeg.addBox(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
        this.rearLeftLeg.setPos(-5.0F, 17.5F, 0.0F);
        this.rearRightLeg = new ModelPart(this, 4, 24);
        this.rearRightLeg.addBox(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
        this.rearRightLeg.setPos(-1.0F, 17.5F, 0.0F);
        this.tail = new ModelPart(this, 30, 0);
        this.tail.addBox(2.0F, 0.0F, -1.0F, 4, 9, 5);
        this.tail.setPos(-4.0F, 15.0F, -1.0F);
        this.body.addChild(this.tail);
    }

    @Override
    public void prepare(ClientFox foxEntity, float f, float g, float h) {
        this.body.rotationX = ((float)Math.PI / 2F);
        this.tail.rotationX = -0.05235988F;
        this.frontLeftLeg.rotationX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.frontRightLeg.rotationX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * g;
        this.rearLeftLeg.rotationX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * g;
        this.rearRightLeg.rotationX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.head.setPos(-1.0F, 16.5F, -3.0F);
        this.head.rotationY = 0.0F;
        this.head.rotationZ = 0; //headrotationZ
        this.frontLeftLeg.visible = true;
        this.frontRightLeg.visible = true;
        this.rearLeftLeg.visible = true;
        this.rearRightLeg.visible = true;
        this.body.setPos(0.0F, 16.0F, -6.0F);
        this.body.rotationZ = 0.0F;
        this.frontLeftLeg.setPos(-5.0F, 17.5F, 7.0F);
        this.frontRightLeg.setPos(-1.0F, 17.5F, 7.0F);
        if (foxEntity.isSleeping()) {
            this.body.rotationZ = (-(float)Math.PI / 2F);
            this.body.setPos(0.0F, 21.0F, -6.0F);
            this.tail.rotationX = -2.6179938F;
            if (this.isBaby) {
                this.tail.rotationX = -2.1816616F;
                this.body.setPos(0.0F, 21.0F, -2.0F);
            }

            this.head.setPos(1.0F, 19.49F, -3.0F);
            this.head.rotationX = 0.0F;
            this.head.rotationY = -2.0943952F;
            this.head.rotationZ = 0.0F;
            this.frontLeftLeg.visible = false;
            this.frontRightLeg.visible = false;
            this.rearLeftLeg.visible = false;
            this.rearRightLeg.visible = false;
        } else if (foxEntity.isSitting()) {
            this.body.rotationX = ((float)Math.PI / 6F);
            this.body.setPos(0.0F, 9.0F, -3.0F);
            this.tail.rotationX = ((float)Math.PI / 4F);
            this.tail.setPos(-4.0F, 15.0F, -2.0F);
            this.head.setPos(-1.0F, 10.0F, -0.25F);
            this.head.rotationX = 0.0F;
            this.head.rotationY = 0.0F;
            if (this.isBaby) {
                this.head.setPos(-1.0F, 13.0F, -3.75F);
            }

            this.frontLeftLeg.rotationX = -1.3089969F;
            this.frontLeftLeg.setPos(-5.0F, 21.5F, 6.75F);
            this.frontRightLeg.rotationX = -1.3089969F;
            this.frontRightLeg.setPos(-1.0F, 21.5F, 6.75F);
            this.rearLeftLeg.rotationX = -0.2617994F;
            this.rearRightLeg.rotationX = -0.2617994F;
        }
    }

    @Override
    public void render(ClientFox foxEntity, float f, float g, float h, float i, float j, float k) {
        super.render(foxEntity, f, g, h, i, j, k);
        this.setup(foxEntity, f, g, h, i, j, k);
        if (this.isBaby) {
            GlStateManager.pushMatrix();
            float l = 0.75F;
            GlStateManager.scalef(0.75F, 0.75F, 0.75F);
            GlStateManager.translatef(0.0F, 8.0F * k, 3.35F * k);
            this.head.render(k);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            float m = 0.5F;
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translatef(0.0F, 24.0F * k, 0.0F);
            this.body.render(k);
            this.frontLeftLeg.render(k);
            this.frontRightLeg.render(k);
            this.rearLeftLeg.render(k);
            this.rearRightLeg.render(k);
            GlStateManager.popMatrix();
        } else {
            GlStateManager.pushMatrix();
            this.head.render(k);
            this.body.render(k);
            this.frontLeftLeg.render(k);
            this.frontRightLeg.render(k);
            this.rearLeftLeg.render(k);
            this.rearRightLeg.render(k);
            GlStateManager.popMatrix();
        }
    }

    @Override
    public void setup(ClientFox foxEntity, float f, float g, float h, float i, float j, float k) {
        super.setup(foxEntity, f, g, h, i, j, k);
        if (!foxEntity.isSleeping() && foxEntity.forwardSpeed < 0) {
            this.head.rotationX = j * ((float)Math.PI / 180F);
            this.head.rotationY = i * ((float)Math.PI / 180F);
        }

        if (foxEntity.isSleeping()) {
            this.head.rotationX = 0.0F;
            this.head.rotationY = -2.0943952F;
            this.head.rotationZ = MathHelper.cos(h * 0.027F) / 22.0F;
        }

        if (foxEntity.forwardSpeed > 0) {
            float l = 0.1F;
            this.field_18025 += 0.67F;
            this.frontLeftLeg.rotationX = MathHelper.cos(this.field_18025 * 0.4662F) * 0.1F;
            this.frontRightLeg.rotationX = MathHelper.cos(this.field_18025 * 0.4662F + (float)Math.PI) * 0.1F;
            this.rearLeftLeg.rotationX = MathHelper.cos(this.field_18025 * 0.4662F + (float)Math.PI) * 0.1F;
            this.rearRightLeg.rotationX = MathHelper.cos(this.field_18025 * 0.4662F) * 0.1F;
        }

    }
}
