package com.jeff.pets.client.rendering.vanilla.cat;

import com.jeff.pets.mob.vanilla.passive.ClientCat;
import net.minecraft.client.render.model.entity.OcelotModel;
import net.minecraft.util.math.MathHelper;

//if the tail isn't animating correctly, swap lowerTail and upperTail
public class ClientCatModel extends OcelotModel<ClientCat> {
    private float lieDownAmount;
    private float lieDownAmountTail;
    private float relaxStateOneAmount;

    public ClientCatModel(float f) {
        super(f);
    }

    @Override
    public void setup(ClientCat cat, float a, float b, float c, float d, float e, float k) {
        super.setup(cat, a, b, c, d, e, k);
        float f = cat.getBreedingAge();
        if (cat.isRiding()) {
            this.body.y += f;
            this.head.y += 2.0F * f;
            //this.lowerTail.y += 1.0F * f;
            // this.upperTail.y += -4.0F * f;
            // this.upperTail.z += 2.0F * f;
            //this.lowerTail.xRot = (float) (Math.PI / 2);
            //this.upperTail.xRot = (float) (Math.PI / 2);
        }

        //this.head.xRot = cat.getXRot() * (float) (Math.PI / 180.0);
        //this.head.yRot = cat.getYRot() * (float) (Math.PI / 180.0);
        if (!cat.isRiding()) {
            this.body.rotationX = (float) (Math.PI / 2);
            float g = cat.walkAnimationSpeed;
            float h = cat.walkAnimationProgress;

            this.backLeftLeg.rotationX = MathHelper.cos(h * 0.6662F) * g;
            this.backRightLeg.rotationX = MathHelper.cos(h * 0.6662F + (float) Math.PI) * g;
            this.frontLeftLeg.rotationX = (MathHelper.cos(h * 0.6662F + (float) Math.PI) * g);
            this.frontrightLeg.rotationX = (MathHelper.cos(h * 0.6662F) * g);
            if (!cat.isRiding()) {
                this.upperTail.rotationX = 1.7278761F + (float) (Math.PI / 4) * MathHelper.cos(h) * g;
            } else {
                this.upperTail.rotationX = 1.7278761F + 0.47123894F * MathHelper.cos(h) * g;
            }
        }

        if (cat.isRiding()) {
            this.body.rotationX = (float) (Math.PI / 4);
            this.body.y += -4.0F * f;
            this.body.z += 5.0F * f;
            this.head.y += -3.3F * f;
            this.head.z += f;
            this.lowerTail.y += 8.0F * f;
            this.lowerTail.z += -2.0F * f;
            this.upperTail.y += 2.0F * f;
            this.upperTail.z += -0.8F * f;
            this.lowerTail.rotationX = 1.7278761F;
            this.upperTail.rotationX = 2.670354F;
            this.frontLeftLeg.rotationX = (float) (-Math.PI / 20);
            this.frontLeftLeg.y += 2.0F * f;
            this.frontLeftLeg.z -= 2.0F * f;
            this.frontrightLeg.rotationX = (float) (-Math.PI / 20);
            this.frontrightLeg.y += 2.0F * f;
            this.frontrightLeg.z -= 2.0F * f;
            this.backLeftLeg.rotationX = (float) (-Math.PI / 2);
            this.backLeftLeg.y += 3.0F * f;
            this.backLeftLeg.z -= 4.0F * f;
            this.backRightLeg.rotationX = (float) (-Math.PI / 2);
            this.backRightLeg.y += 3.0F * f;
            this.backRightLeg.z -= 4.0F * f;
        }

        /*if (cat.lieDownAmount > 0.0F) {
            this.head.zRot = Mth.rotLerp(cat.lieDownAmount, this.head.zRot, -1.2707963F);
            this.head.yRot = Mth.rotLerp(cat.lieDownAmount, this.head.yRot, 1.2707963F);
            this.frontLeftLeg.xRot = -1.2707963F;
            this.frontrightLeg.xRot = -0.47079635F;
            this.frontrightLeg.zRot = -0.2F;
            this.frontrightLeg.x += f;
            this.leftHindLeg.xRot = -0.4F;
            this.rightHindLeg.xRot = 0.5F;
            this.rightHindLeg.zRot = -0.5F;
            this.rightHindLeg.x += 0.8F * f;
            this.rightHindLeg.y += 2.0F * f;
            this.lowerTail.xRot = Mth.rotLerp(cat.lieDownAmountTail, this.lowerTail.xRot, 0.8F);
            this.upperTail.xRot = Mth.rotLerp(cat.lieDownAmountTail, this.upperTail.xRot, -0.4F);
        }

        if (cat.relaxStateOneAmount > 0.0F) {
            this.head.xRot = Mth.rotLerp(cat.relaxStateOneAmount, this.head.xRot, -0.58177644F);
        }*/
    }
}
