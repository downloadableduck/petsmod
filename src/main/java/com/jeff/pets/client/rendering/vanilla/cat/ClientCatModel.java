package com.jeff.pets.client.rendering.vanilla.cat;

import com.jeff.pets.mob.vanilla.passive.ClientCat;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class ClientCatModel extends OcelotModel<ClientCat> {
    private final ModelPart root;
    private float lieDownAmount;
    private float lieDownAmountTail;
    private float relaxStateOneAmount;

    public ClientCatModel(ModelPart modelPart) {
        super(modelPart);
        this.root = modelPart;
    }

    public void setupAnim(ClientCat cat, float a, float b, float c, float d, float e) {
        super.setupAnim(cat, a, b, c, d, e);
        float f = cat.getAge();
        if (cat.isPassenger()) {
            this.body.y += 1.0F * f;
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

                this.leftHindLeg.xRot = Mth.cos(h * 0.6662F) * g;
                this.rightHindLeg.xRot = Mth.cos(h * 0.6662F + (float) Math.PI) * g;
                this.leftFrontLeg.xRot = (Mth.cos(h * 0.6662F + (float) Math.PI) * g);
                this.rightFrontLeg.xRot = (Mth.cos(h * 0.6662F) * g);
                if (!cat.isPassenger()) {
                    this.tail2.xRot = 1.7278761F + (float) (Math.PI / 4) * Mth.cos(h) * g;
                } else {
                    this.tail2.xRot = 1.7278761F + 0.47123894F * Mth.cos(h) * g;
                }
        }

        if (cat.isPassenger()) {
            this.body.xRot = (float) (Math.PI / 4);
            this.body.y += -4.0F * f;
            this.body.z += 5.0F * f;
            this.head.y += -3.3F * f;
            this.head.z += 1.0F * f;
            this.tail1.y += 8.0F * f;
            this.tail1.z += -2.0F * f;
            this.tail2.y += 2.0F * f;
            this.tail2.z += -0.8F * f;
            this.tail1.xRot = 1.7278761F;
            this.tail2.xRot = 2.670354F;
            this.leftFrontLeg.xRot = (float) (-Math.PI / 20);
            this.leftFrontLeg.y += 2.0F * f;
            this.leftFrontLeg.z -= 2.0F * f;
            this.rightFrontLeg.xRot = (float) (-Math.PI / 20);
            this.rightFrontLeg.y += 2.0F * f;
            this.rightFrontLeg.z -= 2.0F * f;
            this.leftHindLeg.xRot = (float) (-Math.PI / 2);
            this.leftHindLeg.y += 3.0F * f;
            this.leftHindLeg.z -= 4.0F * f;
            this.rightHindLeg.xRot = (float) (-Math.PI / 2);
            this.rightHindLeg.y += 3.0F * f;
            this.rightHindLeg.z -= 4.0F * f;
        }

        /*if (cat.lieDownAmount > 0.0F) {
            this.head.zRot = Mth.rotLerp(cat.lieDownAmount, this.head.zRot, -1.2707963F);
            this.head.yRot = Mth.rotLerp(cat.lieDownAmount, this.head.yRot, 1.2707963F);
            this.leftFrontLeg.xRot = -1.2707963F;
            this.rightFrontLeg.xRot = -0.47079635F;
            this.rightFrontLeg.zRot = -0.2F;
            this.rightFrontLeg.x += f;
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
