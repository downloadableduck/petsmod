package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.mob.vanilla.passive.ClientSheep;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.model.entity.QuadrupedModel;

public class ClientSheepModel extends QuadrupedModel<ClientSheep> {
    private float headXRot;

    public ClientSheepModel() {
        super(12, 0.0F);
        this.head = new ModelPart(this, 0, 0);
        this.head.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
        this.head.setPos(0.0F, 6.0F, -8.0F);
        this.body = new ModelPart(this, 28, 8);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
        this.body.setPos(0.0F, 5.0F, 2.0F);
    }

    public void prepare(ClientSheep sheep, float f, float g, float h) {
        super.prepare(sheep, f, g, h);
        //this.head.y = 6.0F * 9.0F;
        //this.headXRot = 0;
    }

    public void setup(ClientSheep sheep, float f, float g, float h, float i, float j, float s) {
        super.setup(sheep, f, g, h, i, j, s);
        //this.head.rotationX = this.headXRot;
    }
}
