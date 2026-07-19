package com.jeff.pets.client.rendering.vanilla.cat;

import com.jeff.pets.mob.vanilla.passive.ClientCat;
import net.minecraft.client.render.model.entity.OcelotModel;
import net.minecraft.util.math.MathHelper;

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
            //this.tail1.y += 1.0F * f;
            // this.tail2.y += -4.0F * f;
            // this.tail2.z += 2.0F * f;
            //this.tail1.xRot = (float) (Math.PI / 2);
            //this.tail2.xRot = (float) (Math.PI / 2);
        }

        //this.head.xRot = cat.getXRot() * (float) (Math.PI / 180.0);
        //this.head.yRot = cat.getYRot() * (float) (Math.PI / 180.0);
        if (!cat.isRiding()) {
            this.body.rotationX = (float) (Math.PI / 2);
            float g = cat.walkAnimationSpeed;
            float h = cat.walkAnimationProgress;

            this.backLegLeft.rotationX = MathHelper.cos(h * 0.6662F) * g;
            this.backLegRight.rotationX = MathHelper.cos(h * 0.6662F + (float) Math.PI) * g;
            this.frontLegLeft.rotationX = (MathHelper.cos(h * 0.6662F + (float) Math.PI) * g);
            this.frontLegRight.rotationX = (MathHelper.cos(h * 0.6662F) * g);
            if (!cat.isRiding()) {
                this.tail2.rotationX = 1.7278761F + (float) (Math.PI / 4) * MathHelper.cos(h) * g;
            } else {
                this.tail2.rotationX = 1.7278761F + 0.47123894F * MathHelper.cos(h) * g;
            }
        }

        if (cat.isRiding()) {
            this.body.rotationX = (float) (Math.PI / 4);
            this.body.y += -4.0F * f;
            this.body.z += 5.0F * f;
            this.head.y += -3.3F * f;
            this.head.z += f;
            this.tail1.y += 8.0F * f;
            this.tail1.z += -2.0F * f;
            this.tail2.y += 2.0F * f;
            this.tail2.z += -0.8F * f;
            this.tail1.rotationX = 1.7278761F;
            this.tail2.rotationX = 2.670354F;
            this.frontLegLeft.rotationX = (float) (-Math.PI / 20);
            this.frontLegLeft.y += 2.0F * f;
            this.frontLegLeft.z -= 2.0F * f;
            this.frontLegRight.rotationX = (float) (-Math.PI / 20);
            this.frontLegRight.y += 2.0F * f;
            this.frontLegRight.z -= 2.0F * f;
            this.backLegLeft.rotationX = (float) (-Math.PI / 2);
            this.backLegLeft.y += 3.0F * f;
            this.backLegLeft.z -= 4.0F * f;
            this.backLegRight.rotationX = (float) (-Math.PI / 2);
            this.backLegRight.y += 3.0F * f;
            this.backLegRight.z -= 4.0F * f;
        }

        /*if (cat.lieDownAmount > 0.0F) {
            this.head.zRot = Mth.rotLerp(cat.lieDownAmount, this.head.zRot, -1.2707963F);
            this.head.yRot = Mth.rotLerp(cat.lieDownAmount, this.head.yRot, 1.2707963F);
            this.frontLegLeft.xRot = -1.2707963F;
            this.frontLegRight.xRot = -0.47079635F;
            this.frontLegRight.zRot = -0.2F;
            this.frontLegRight.x += f;
            this.leftHindLeg.xRot = -0.4F;
            this.rightHindLeg.xRot = 0.5F;
            this.rightHindLeg.zRot = -0.5F;
            this.rightHindLeg.x += 0.8F * f;
            this.rightHindLeg.y += 2.0F * f;
            this.tail1.xRot = Mth.rotLerp(cat.lieDownAmountTail, this.tail1.xRot, 0.8F);
            this.tail2.xRot = Mth.rotLerp(cat.lieDownAmountTail, this.tail2.xRot, -0.4F);
        }

        if (cat.relaxStateOneAmount > 0.0F) {
            this.head.xRot = Mth.rotLerp(cat.relaxStateOneAmount, this.head.xRot, -0.58177644F);
        }*/
    }
}
