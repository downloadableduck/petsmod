package com.jeff.pets.client.rendering.vanilla.rabbit;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.passive.ClientRabbit;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.util.math.MathHelper;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientRabbitModel extends Model<ClientRabbit> {
    public final ModelPart head;
    private final ModelPart rearFootLeft = new ModelPart(this, 26, 24);
    private final ModelPart rearFootRight;
    private final ModelPart haunchLeft;
    private final ModelPart haunchRight;
    private final ModelPart body;
    private final ModelPart frontLegLeft;
    private final ModelPart frontLegRight;
    private final ModelPart earRight;
    private final ModelPart earLeft;
    private final ModelPart tail;
    private final ModelPart nose;
    private float jumpRotation;

    public ClientRabbitModel() {
        this.rearFootLeft.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 7);
        this.rearFootLeft.setPos(3.0F, 17.5F, 3.7F);
        this.rearFootLeft.flipped = true;
        this.setRotation(this.rearFootLeft, 0.0F, 0.0F, 0.0F);
        this.rearFootRight = new ModelPart(this, 8, 24);
        this.rearFootRight.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 7);
        this.rearFootRight.setPos(-3.0F, 17.5F, 3.7F);
        this.rearFootRight.flipped = true;
        this.setRotation(this.rearFootRight, 0.0F, 0.0F, 0.0F);
        this.haunchLeft = new ModelPart(this, 30, 15);
        this.haunchLeft.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5);
        this.haunchLeft.setPos(3.0F, 17.5F, 3.7F);
        this.haunchLeft.flipped = true;
        this.setRotation(this.haunchLeft, -0.34906584F, 0.0F, 0.0F);
        this.haunchRight = new ModelPart(this, 16, 15);
        this.haunchRight.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5);
        this.haunchRight.setPos(-3.0F, 17.5F, 3.7F);
        this.haunchRight.flipped = true;
        this.setRotation(this.haunchRight, -0.34906584F, 0.0F, 0.0F);
        this.body = new ModelPart(this, 0, 0);
        this.body.addBox(-3.0F, -2.0F, -10.0F, 6, 5, 10);
        this.body.setPos(0.0F, 19.0F, 8.0F);
        this.body.flipped = true;
        this.setRotation(this.body, -0.34906584F, 0.0F, 0.0F);
        this.frontLegLeft = new ModelPart(this, 8, 15);
        this.frontLegLeft.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
        this.frontLegLeft.setPos(3.0F, 17.0F, -1.0F);
        this.frontLegLeft.flipped = true;
        this.setRotation(this.frontLegLeft, -0.17453292F, 0.0F, 0.0F);
        this.frontLegRight = new ModelPart(this, 0, 15);
        this.frontLegRight.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
        this.frontLegRight.setPos(-3.0F, 17.0F, -1.0F);
        this.frontLegRight.flipped = true;
        this.setRotation(this.frontLegRight, -0.17453292F, 0.0F, 0.0F);
        this.head = new ModelPart(this, 32, 0);
        this.head.addBox(-2.5F, -4.0F, -5.0F, 5, 4, 5);
        this.head.setPos(0.0F, 16.0F, -1.0F);
        this.head.flipped = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.earRight = new ModelPart(this, 52, 0);
        this.earRight.addBox(-2.5F, -9.0F, -1.0F, 2, 5, 1);
        this.earRight.setPos(0.0F, 16.0F, -1.0F);
        this.earRight.flipped = true;
        this.setRotation(this.earRight, 0.0F, -0.2617994F, 0.0F);
        this.earLeft = new ModelPart(this, 58, 0);
        this.earLeft.addBox(0.5F, -9.0F, -1.0F, 2, 5, 1);
        this.earLeft.setPos(0.0F, 16.0F, -1.0F);
        this.earLeft.flipped = true;
        this.setRotation(this.earLeft, 0.0F, 0.2617994F, 0.0F);
        this.tail = new ModelPart(this, 52, 6);
        this.tail.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 2);
        this.tail.setPos(0.0F, 20.0F, 7.0F);
        this.tail.flipped = true;
        this.setRotation(this.tail, -0.3490659F, 0.0F, 0.0F);
        this.nose = new ModelPart(this, 32, 9);
        this.nose.addBox(-0.5F, -2.5F, -5.5F, 1, 1, 1);
        this.nose.setPos(0.0F, 16.0F, -1.0F);
        this.nose.flipped = true;
        this.setRotation(this.nose, 0.0F, 0.0F, 0.0F);
    }

    private void setRotation(ModelPart ModelPart, float f, float g, float h) {
        ModelPart.rotationX = f;
        ModelPart.rotationY = g;
        ModelPart.rotationZ = h;
    }

    @Override
    public void render(ClientRabbit rabbit, float i, float j, float f, float g, float h, float k) {
        if (CONFIG.isBaby) {
            float l = 1.5F;
            com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.56666666F, 0.56666666F, 0.56666666F);
            com.mojang.blaze3d.platform.GlStateManager.translatef(0.0F, 1.375F, 0.125F);
            ImmutableList.of(this.head, this.earLeft, this.earRight, this.nose).forEach((ModelPart) -> ModelPart.render(k));
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();
            com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.4F, 0.4F, 0.4F);
            com.mojang.blaze3d.platform.GlStateManager.translatef(0.0F, 2.25F, 0.0F);
            ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.haunchLeft, this.haunchRight, this.body, this.frontLegLeft, this.frontLegRight, this.tail).forEach((ModelPart) -> ModelPart.render(k));
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();
        } else {
            com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.6F, 0.6F, 0.6F);
            com.mojang.blaze3d.platform.GlStateManager.translatef(0.0F, 1.0F, 0.0F);
            ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.haunchLeft, this.haunchRight, this.body, this.frontLegLeft, this.frontLegRight, this.head, this.earRight, this.earLeft, this.tail, this.nose, new ModelPart[0]).forEach((ModelPart) -> ModelPart.render(k));
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();
        }

    }

    public void setup(ClientRabbit rabbit, float f, float g, float h, float i, float j, float s) {
        float k = h - (float) rabbit.ticks;
        this.nose.rotationX = j * ((float) Math.PI / 180F);
        this.head.rotationX = j * ((float) Math.PI / 180F);
        this.earRight.rotationX = j * ((float) Math.PI / 180F);
        this.earLeft.rotationX = j * ((float) Math.PI / 180F);
        this.nose.rotationY = i * ((float) Math.PI / 180F);
        this.head.rotationY = i * ((float) Math.PI / 180F);
        this.earRight.rotationY = this.nose.rotationY - 0.2617994F;
        this.earLeft.rotationY = this.nose.rotationY + 0.2617994F;
        this.jumpRotation = MathHelper.sin((float) Math.PI);
        this.haunchLeft.rotationX = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        this.haunchRight.rotationX = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        this.rearFootLeft.rotationX = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.rearFootRight.rotationX = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.frontLegLeft.rotationX = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
        this.frontLegRight.rotationX = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
    }
}
