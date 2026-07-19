package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.mob.vanilla.passive.ClientSheep;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.model.entity.QuadrupedModel;

public class ClientSheepFurModel extends QuadrupedModel<ClientSheep> {
    private float headXRot;

    public ClientSheepFurModel() {
        super(12, 0.0F);
        this.head = new ModelPart(this, 0, 0);
        this.head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
        this.head.setPos(0.0F, 6.0F, -8.0F);
        this.body = new ModelPart(this, 28, 8);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
        this.body.setPos(0.0F, 5.0F, 2.0F);
        float f = 0.5F;
        this.backRightLeg = new ModelPart(this, 0, 16);
        this.backRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.backRightLeg.setPos(-3.0F, 12.0F, 7.0F);
        this.backLeftLeg = new ModelPart(this, 0, 16);
        this.backLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.backLeftLeg.setPos(3.0F, 12.0F, 7.0F);
        this.frontRightLeg = new ModelPart(this, 0, 16);
        this.frontRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.frontRightLeg.setPos(-3.0F, 12.0F, -5.0F);
        this.frontLeftLeg = new ModelPart(this, 0, 16);
        this.frontLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.frontLeftLeg.setPos(3.0F, 12.0F, -5.0F);
    }

    public void prepare(ClientSheep sheep, float f, float g, float h) {
        super.prepare(sheep, f, g, h);
    }

    public void setup(ClientSheep sheep, float f, float g, float h, float i, float j, float s) {
        super.setup(sheep, f, g, h, i, j, s);
    }
}
