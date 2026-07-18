package com.jeff.pets.client.rendering.vanilla.polarbear;

import com.jeff.pets.mob.vanilla.neutral.ClientPolarBear;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPolarBearModel extends QuadrupedEntityModel<ClientPolarBear> {
    public ClientPolarBearModel() {
        super(12, 0.0F);
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.head = new Cuboid(this, 0, 0);
        this.head.addBox(-3.5F, -3.0F, -3.0F, 7, 7, 7, 0.0F);
        this.head.setRotationPoint(0.0F, 10.0F, -16.0F);
        this.head.setTextureOffset(0, 44).addBox(-2.5F, 1.0F, -6.0F, 5, 3, 3, 0.0F);
        this.head.setTextureOffset(26, 0).addBox(-4.5F, -4.0F, -1.0F, 2, 2, 1, 0.0F);
        Cuboid Cuboid = this.head.setTextureOffset(26, 0);
        Cuboid.mirror = true;
        Cuboid.addBox(2.5F, -4.0F, -1.0F, 2, 2, 1, 0.0F);
        this.body = new Cuboid(this);
        this.body.setTextureOffset(0, 19).addBox(-5.0F, -13.0F, -7.0F, 14, 14, 11, 0.0F);
        this.body.setTextureOffset(39, 0).addBox(-4.0F, -25.0F, -7.0F, 12, 12, 10, 0.0F);
        this.body.setRotationPoint(-2.0F, 9.0F, 12.0F);
        int i = 10;
        this.leg1 = new Cuboid(this, 50, 22);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 8, 0.0F);
        this.leg1.setRotationPoint(-3.5F, 14.0F, 6.0F);
        this.leg2 = new Cuboid(this, 50, 22);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 8, 0.0F);
        this.leg2.setRotationPoint(3.5F, 14.0F, 6.0F);
        this.leg3 = new Cuboid(this, 50, 40);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 6, 0.0F);
        this.leg3.setRotationPoint(-2.5F, 14.0F, -7.0F);
        this.leg4 = new Cuboid(this, 50, 40);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 6, 0.0F);
        this.leg4.setRotationPoint(2.5F, 14.0F, -7.0F);
        --this.leg1.rotationPointX;
        ++this.leg2.rotationPointX;
        Cuboid var10000 = this.leg1;
        var10000.rotationPointZ += 0.0F;
        var10000 = this.leg2;
        var10000.rotationPointZ += 0.0F;
        --this.leg3.rotationPointX;
        ++this.leg4.rotationPointX;
        --this.leg3.rotationPointZ;
        --this.leg4.rotationPointZ;
    }

    public void setAngles(ClientPolarBear polarBear, float f, float g, float h, float i, float j, float s) {
        super.setAngles(polarBear, f, g, h, i, j, s);
        float k = h - (float) polarBear.age;
        float l = 0;
        l *= l;
        float m = 1.0F - l;
        this.body.pitch = ((float) Math.PI / 2F) - l * (float) Math.PI * 0.35F;
        this.body.rotationPointY = 9.0F * m + 11.0F * l;
        this.leg3.rotationPointY = 14.0F * m - 6.0F * l;
        this.leg3.rotationPointZ = -8.0F * m - 4.0F * l;
        Cuboid var10000 = this.leg3;
        var10000.pitch -= l * (float) Math.PI * 0.45F;
        this.leg4.rotationPointY = this.leg3.rotationPointY;
        this.leg4.rotationPointZ = this.leg3.rotationPointZ;
        var10000 = this.leg4;
        var10000.pitch -= l * (float) Math.PI * 0.45F;
        if (CONFIG.isBaby) {
            this.head.rotationPointY = 10.0F * m - 9.0F * l;
            this.head.rotationPointZ = -16.0F * m - 7.0F * l;
        } else {
            this.head.rotationPointY = 10.0F * m - 14.0F * l;
            this.head.rotationPointZ = -16.0F * m - 3.0F * l;
        }

        var10000 = this.head;
        var10000.pitch += l * (float) Math.PI * 0.15F;
    }
}
