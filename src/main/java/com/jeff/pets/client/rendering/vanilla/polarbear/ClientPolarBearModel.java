package com.jeff.pets.client.rendering.vanilla.polarbear;

import com.jeff.pets.mob.vanilla.neutral.ClientPolarBear;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.model.entity.QuadrupedModel;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPolarBearModel extends QuadrupedModel {
    public ClientPolarBearModel() {
        super(12, 0.0F);
        this.textureWidth /*textureWidth*/ = 128;
        this.textureHeight /*textureHeight*/ = 64;
        this.head = new ModelPart(this, 0, 0);
        this.head.addBox(-3.5F, -3.0F, -3.0F, 7, 7, 7, 0.0F);
        this.head.setPos(0.0F, 10.0F, -16.0F);
        this.head.setTextureCoords(0, 44).addBox(-2.5F, 1.0F, -6.0F, 5, 3, 3, 0.0F);
        this.head.setTextureCoords(26, 0).addBox(-4.5F, -4.0F, -1.0F, 2, 2, 1, 0.0F);
        ModelPart ModelPart = this.head.setTextureCoords(26, 0);
        ModelPart.flipped = true;
        ModelPart.addBox(2.5F, -4.0F, -1.0F, 2, 2, 1, 0.0F);
        this.body = new ModelPart(this);
        this.body.setTextureCoords(0, 19).addBox(-5.0F, -13.0F, -7.0F, 14, 14, 11, 0.0F);
        this.body.setTextureCoords(39, 0).addBox(-4.0F, -25.0F, -7.0F, 12, 12, 10, 0.0F);
        this.body.setPos(-2.0F, 9.0F, 12.0F);
        int i = 10;
        this.backRightLeg = new ModelPart(this, 50, 22);
        this.backRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 8, 0.0F);
        this.backRightLeg.setPos(-3.5F, 14.0F, 6.0F);
        this.backLeftLeg = new ModelPart(this, 50, 22);
        this.backLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 8, 0.0F);
        this.backLeftLeg.setPos(3.5F, 14.0F, 6.0F);
        this.frontRightLeg = new ModelPart(this, 50, 40);
        this.frontRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 6, 0.0F);
        this.frontRightLeg.setPos(-2.5F, 14.0F, -7.0F);
        this.frontLeftLeg = new ModelPart(this, 50, 40);
        this.frontLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 6, 0.0F);
        this.frontLeftLeg.setPos(2.5F, 14.0F, -7.0F);
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

    public void setupAnimation(float f, float g, float h, float i, float j, float s, net.minecraft.entity.Entity entity) {
        ClientPolarBear polarBear = (ClientPolarBear) entity;
        super.setupAnimation(f, g, h, i, j, s, entity);
        float k = h - (float) polarBear.ticks;
        float l = 0;
        l *= l;
        float m = 1.0F - l;
        this.body.rotationX = ((float) Math.PI / 2F) - l * (float) Math.PI * 0.35F;
        this.body.y = 9.0F * m + 11.0F * l;
        this.frontRightLeg.y = 14.0F * m - 6.0F * l;
        this.frontRightLeg.z = -8.0F * m - 4.0F * l;
        ModelPart var10000 = this.frontRightLeg;
        var10000.rotationX -= l * (float) Math.PI * 0.45F;
        this.frontLeftLeg.y = this.frontRightLeg.y;
        this.frontLeftLeg.z = this.frontRightLeg.z;
        var10000 = this.frontLeftLeg;
        var10000.rotationX -= l * (float) Math.PI * 0.45F;
        if (CONFIG.isBaby) {
            this.head.y = 10.0F * m - 9.0F * l;
            this.head.z = -16.0F * m - 7.0F * l;
        } else {
            this.head.y = 10.0F * m - 14.0F * l;
            this.head.z = -16.0F * m - 3.0F * l;
        }

        var10000 = this.head;
        var10000.rotationX += l * (float) Math.PI * 0.15F;
    }
}
