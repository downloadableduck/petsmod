package com.jeff.pets.client.rendering.vanilla.panda;

import com.jeff.pets.client.rendering.AnimationUtils;
import com.jeff.pets.mob.vanilla.neutral.ClientPanda;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.model.entity.QuadrupedModel;
import net.minecraft.util.math.MathHelper;

public class ClientPandaModel extends QuadrupedModel<ClientPanda> {
    private float sitAmount;
    private float lieOnBackAmount;
    private float rotationZAmount;

    public ClientPandaModel(int i, float f) {
        super(i, f);
        this.f_9972380 /*textureWidth*/ = 64;
        this.f_9233444 /*textureHeight*/ = 64;
        this.head = new ModelPart(this, 0, 6);
        this.head.addBox(-6.5F, -5.0F, -4.0F, 13, 10, 9);
        this.head.setPos(0.0F, 11.5F, -17.0F);
        this.head.setTextureCoords(45, 16).addBox(-3.5F, 0.0F, -6.0F, 7, 5, 2);
        this.head.setTextureCoords(52, 25).addBox(-8.5F, -8.0F, -1.0F, 5, 4, 1);
        this.head.setTextureCoords(52, 25).addBox(3.5F, -8.0F, -1.0F, 5, 4, 1);
        this.body = new ModelPart(this, 0, 25);
        this.body.addBox(-9.5F, -13.0F, -6.5F, 19, 26, 13);
        this.body.setPos(0.0F, 10.0F, 0.0F);
        int j = 9;
        int k = 6;
        this.backRightLeg = new ModelPart(this, 40, 0);
        this.backRightLeg.addBox(-3.0F, 0.0F, -3.0F, 6, 9, 6);
        this.backRightLeg.setPos(-5.5F, 15.0F, 9.0F);
        this.backLeftLeg = new ModelPart(this, 40, 0);
        this.backLeftLeg.addBox(-3.0F, 0.0F, -3.0F, 6, 9, 6);
        this.backLeftLeg.setPos(5.5F, 15.0F, 9.0F);
        this.frontRightLeg = new ModelPart(this, 40, 0);
        this.frontRightLeg.addBox(-3.0F, 0.0F, -3.0F, 6, 9, 6);
        this.frontRightLeg.setPos(-5.5F, 15.0F, -9.0F);
        this.frontLeftLeg = new ModelPart(this, 40, 0);
        this.frontLeftLeg.addBox(-3.0F, 0.0F, -3.0F, 6, 9, 6);
        this.frontLeftLeg.setPos(5.5F, 15.0F, -9.0F);
    }

    public void prepare(ClientPanda panda, float f, float g, float h) {
        super.prepare(panda, f, g, h);
        this.sitAmount = 0;
        this.lieOnBackAmount = 0;
        this.rotationZAmount = panda.isBaby() ? 0.0F : 0;
    }

    public void setup(ClientPanda panda, float f, float g, float h, float i, float j, float s) {
        super.setup(panda, f, g, h, i, j, s);
        boolean bl = false;
        boolean bl2 = false;
        int k = 0;
        boolean bl3 = false;
        boolean bl4 = false;
        if (bl) {
            this.head.rotationY = 0.35F * MathHelper.sin(0.6F * h);
            this.head.rotationZ = 0.35F * MathHelper.sin(0.6F * h);
            this.frontRightLeg.rotationX = -0.75F * MathHelper.sin(0.3F * h);
            this.frontLeftLeg.rotationX = 0.75F * MathHelper.sin(0.3F * h);
        } else {
            this.head.rotationZ = 0.0F;
        }

        if (bl2) {
            if (k < 15) {
                this.head.rotationX = (-(float) Math.PI / 4F) * (float) k / 14.0F;
            } else if (k < 20) {
                float l = (float) ((k - 15) / 5);
                this.head.rotationX = (-(float) Math.PI / 4F) + ((float) Math.PI / 4F) * l;
            }
        }

        if (this.sitAmount > 0.0F) {
            this.body.rotationX = AnimationUtils.rotlerpRad(this.body.rotationX, 1.7407963F, this.sitAmount);
            this.head.rotationX = AnimationUtils.rotlerpRad(this.head.rotationX, ((float) Math.PI / 2F), this.sitAmount);
            this.frontRightLeg.rotationZ = -0.27079642F;
            this.frontLeftLeg.rotationZ = 0.27079642F;
            this.backRightLeg.rotationZ = 0.5707964F;
            this.backLeftLeg.rotationZ = -0.5707964F;
            if (bl3) {
                this.head.rotationX = ((float) Math.PI / 2F) + 0.2F * MathHelper.sin(h * 0.6F);
                this.frontRightLeg.rotationX = -0.4F - 0.2F * MathHelper.sin(h * 0.6F);
                this.frontLeftLeg.rotationX = -0.4F - 0.2F * MathHelper.sin(h * 0.6F);
            }

            if (bl4) {
                this.head.rotationX = 2.1707964F;
                this.frontRightLeg.rotationX = -0.9F;
                this.frontLeftLeg.rotationX = -0.9F;
            }
        } else {
            this.backRightLeg.rotationZ = 0.0F;
            this.backLeftLeg.rotationZ = 0.0F;
            this.frontRightLeg.rotationZ = 0.0F;
            this.frontLeftLeg.rotationZ = 0.0F;
        }

        if (this.lieOnBackAmount > 0.0F) {
            this.backRightLeg.rotationX = -0.6F * MathHelper.sin(h * 0.15F);
            this.backLeftLeg.rotationX = 0.6F * MathHelper.sin(h * 0.15F);
            this.frontRightLeg.rotationX = 0.3F * MathHelper.sin(h * 0.25F);
            this.frontLeftLeg.rotationX = -0.3F * MathHelper.sin(h * 0.25F);
            this.head.rotationX = AnimationUtils.rotlerpRad(this.head.rotationX, ((float) Math.PI / 2F), this.lieOnBackAmount);
        }

        if (this.rotationZAmount > 0.0F) {
            this.head.rotationX = AnimationUtils.rotlerpRad(this.head.rotationX, 2.0561945F, this.rotationZAmount);
            this.backRightLeg.rotationX = -0.5F * MathHelper.sin(h * 0.5F);
            this.backLeftLeg.rotationX = 0.5F * MathHelper.sin(h * 0.5F);
            this.frontRightLeg.rotationX = 0.5F * MathHelper.sin(h * 0.5F);
            this.frontLeftLeg.rotationX = -0.5F * MathHelper.sin(h * 0.5F);
        }

    }
}
