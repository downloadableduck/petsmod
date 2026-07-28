package com.jeff.pets.client.rendering.vanilla.cat;

import com.jeff.pets.mob.vanilla.passive.ClientCat;
import net.minecraft.client.renderer.entity.model.OcelotModel;

public class ClientCatModel extends OcelotModel<ClientCat> {
    private float lieDownAmount;
    private float lieDownAmountTail;
    private float relaxStateOneAmount;

    public ClientCatModel(float f) {
        super(f);
    }

    @Override
    public void setupAnim(ClientCat cat, float a, float b, float c, float d, float e, float k) {
        super.setupAnim(cat, a, b, c, d, e, k);
        float f = cat.getAge();
        if (cat.isPassenger()) {
            this.body.y += f;
            this.head.y += 2.0F * f;
            //this.tail1.y += 1.0F * f;
            // this.tail2.y += -4.0F * f;
            // this.tail2.z += 2.0F * f;
            //this.tail1.xRot = (float) (Math.PI / 2);
            //this.tail2.xRot = (float) (Math.PI / 2);
        }

        //this.head.xRot = cat.getXRot() * (float) (Math.PI / 180.0);
        //this.head.yRot = cat.getYRot() * (float) (Math.PI / 180.0);
        if (!cat.isPassenger()) {
            this.body.xRot = (float) (Math.PI / 2);
            float g = cat.animationSpeed;
            float h = cat.animationPosition;

            this.backLegL.xRot = net.minecraft.util.math.MathHelper.cos(h * 0.6662F) * g;
            this.backLegR.xRot = net.minecraft.util.math.MathHelper.cos(h * 0.6662F + (float) Math.PI) * g;
            this.frontLegL.xRot = (net.minecraft.util.math.MathHelper.cos(h * 0.6662F + (float) Math.PI) * g);
            this.frontLegR.xRot = (net.minecraft.util.math.MathHelper.cos(h * 0.6662F) * g);
            if (!cat.isPassenger()) {
                this.tail2.xRot = 1.7278761F + (float) (Math.PI / 4) * net.minecraft.util.math.MathHelper.cos(h) * g;
            } else {
                this.tail2.xRot = 1.7278761F + 0.47123894F * net.minecraft.util.math.MathHelper.cos(h) * g;
            }
        }

        if (cat.isPassenger()) {
            this.body.xRot = (float) (Math.PI / 4);
            this.body.y += -4.0F * f;
            this.body.z += 5.0F * f;
            this.head.y += -3.3F * f;
            this.head.z += f;
            this.tail1.y += 8.0F * f;
            this.tail1.z += -2.0F * f;
            this.tail2.y += 2.0F * f;
            this.tail2.z += -0.8F * f;
            this.tail1.xRot = 1.7278761F;
            this.tail2.xRot = 2.670354F;
            this.frontLegL.xRot = (float) (-Math.PI / 20);
            this.frontLegL.y += 2.0F * f;
            this.frontLegL.z -= 2.0F * f;
            this.frontLegR.xRot = (float) (-Math.PI / 20);
            this.frontLegR.y += 2.0F * f;
            this.frontLegR.z -= 2.0F * f;
            this.backLegL.xRot = (float) (-Math.PI / 2);
            this.backLegL.y += 3.0F * f;
            this.backLegL.z -= 4.0F * f;
            this.backLegR.xRot = (float) (-Math.PI / 2);
            this.backLegR.y += 3.0F * f;
            this.backLegR.z -= 4.0F * f;
        }

        /*if (cat.lieDownAmount > 0.0F) {
            this.head.zRot = net.minecraft.util.math.MathHelper.rotLerp(cat.lieDownAmount, this.head.zRot, -1.2707963F);
            this.head.yRot = net.minecraft.util.math.MathHelper.rotLerp(cat.lieDownAmount, this.head.yRot, 1.2707963F);
            this.leftFrontLeg.xRot = -1.2707963F;
            this.rightFrontLeg.xRot = -0.47079635F;
            this.rightFrontLeg.zRot = -0.2F;
            this.rightFrontLeg.x += f;
            this.leftHindLeg.xRot = -0.4F;
            this.rightHindLeg.xRot = 0.5F;
            this.rightHindLeg.zRot = -0.5F;
            this.rightHindLeg.x += 0.8F * f;
            this.rightHindLeg.y += 2.0F * f;
            this.tail1.xRot = net.minecraft.util.math.MathHelper.rotLerp(cat.lieDownAmountTail, this.tail1.xRot, 0.8F);
            this.tail2.xRot = net.minecraft.util.math.MathHelper.rotLerp(cat.lieDownAmountTail, this.tail2.xRot, -0.4F);
        }

        if (cat.relaxStateOneAmount > 0.0F) {
            this.head.xRot = net.minecraft.util.math.MathHelper.rotLerp(cat.relaxStateOneAmount, this.head.xRot, -0.58177644F);
        }*/
    }
}
