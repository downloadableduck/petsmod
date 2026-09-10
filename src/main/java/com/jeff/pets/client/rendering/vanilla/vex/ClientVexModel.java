package com.jeff.pets.client.rendering.vanilla.vex;

import com.jeff.pets.mob.vanilla.hostile.ClientVex;
import net.minecraft.client.render.entity.model.BiPedModel;
import net.minecraft.client.render.model.ModelPart;

public class ClientVexModel extends BiPedModel {
    private final ModelPart leftWing;
    private final ModelPart rightWing;

    public ClientVexModel() {
        super(0.0F, 0.0F, 64, 64);
        this.leftLeg.visible = false;
        this.hat.visible = false;
        this.rightLeg = new ModelPart(this, 32, 0);
        this.rightLeg.addCuboid(-1.0F, -1.0F, -2.0F, (int) 6.0, (int) 10.0, (int) 4.0, 0.0F);
        this.rightLeg.setPivot(-1.9F, 12.0F, 0.0F);
        this.rightWing = new ModelPart(this, 0, 32);
        this.rightWing.addCuboid(-20.0F, 0.0F, 0.0F, (int) 20.0, (int) 12.0, (int) 1.0);
        this.leftWing = new ModelPart(this, 0, 32);
        this.leftWing.mirror = true;
        this.leftWing.addCuboid(0.0F, 0.0F, 0.0F, (int) 20.0, (int) 12.0, (int) 1.0);
    }

    public void setAngles(net.minecraft.entity.Entity entity, float f, float g, float h, float i, float j, float p) {
        ClientVex vex = (ClientVex) entity;

        this.rightLeg.posX += ((float) Math.PI / 5F);
        this.rightWing.pivotZ = 2.0F;
        this.leftWing.pivotZ = 2.0F;
        this.rightWing.pivotY = 1.0F;
        this.leftWing.pivotY = 1.0F;
        this.rightWing.posY = 0.47123894F + net.minecraft.util.math.MathHelper.cos(h * 0.8F) * (float) Math.PI * 0.05F;
        this.leftWing.posY = -this.rightWing.posY;
        this.leftWing.posZ = -0.47123894F;
        this.leftWing.posX = 0.47123894F;
        this.rightWing.posX = 0.47123894F;
        this.rightWing.posZ = 0.47123894F;
    }
}
