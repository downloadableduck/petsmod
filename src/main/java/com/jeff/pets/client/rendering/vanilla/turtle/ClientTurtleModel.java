package com.jeff.pets.client.rendering.vanilla.turtle;

import com.jeff.pets.mob.vanilla.passive.ClientTurtle;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.util.math.MathHelper;

public class ClientTurtleModel extends QuadrupedEntityModel<ClientTurtle> {
    private final Cuboid eggBelly;

    public ClientTurtleModel(float f) {
        super(12, f);
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.head = new Cuboid(this, 3, 0);
        this.head.addBox(-3.0F, -1.0F, -3.0F, 6, 5, 6, 0.0F);
        this.head.setRotationPoint(0.0F, 19.0F, -10.0F);
        this.body = new Cuboid(this);
        this.body.setTextureOffset(7, 37).addBox(-9.5F, 3.0F, -10.0F, 19, 20, 6, 0.0F);
        this.body.setTextureOffset(31, 1).addBox(-5.5F, 3.0F, -13.0F, 11, 18, 3, 0.0F);
        this.body.setRotationPoint(0.0F, 11.0F, -10.0F);
        this.eggBelly = new Cuboid(this);
        this.eggBelly.setTextureOffset(70, 33).addBox(-4.5F, 3.0F, -14.0F, 9, 18, 1, 0.0F);
        this.eggBelly.setRotationPoint(0.0F, 11.0F, -10.0F);
        int i = 1;
        this.leg1 = new Cuboid(this, 1, 23);
        this.leg1.addBox(-2.0F, 0.0F, 0.0F, 4, 1, 10, 0.0F);
        this.leg1.setRotationPoint(-3.5F, 22.0F, 11.0F);
        this.leg2 = new Cuboid(this, 1, 12);
        this.leg2.addBox(-2.0F, 0.0F, 0.0F, 4, 1, 10, 0.0F);
        this.leg2.setRotationPoint(3.5F, 22.0F, 11.0F);
        this.leg3 = new Cuboid(this, 27, 30);
        this.leg3.addBox(-13.0F, 0.0F, -2.0F, 13, 1, 5, 0.0F);
        this.leg3.setRotationPoint(-5.0F, 21.0F, -4.0F);
        this.leg4 = new Cuboid(this, 27, 24);
        this.leg4.addBox(0.0F, 0.0F, -2.0F, 13, 1, 5, 0.0F);
        this.leg4.setRotationPoint(5.0F, 21.0F, -4.0F);
    }

    public void setAngles(ClientTurtle turtle, float f, float g, float h, float i, float j, float s) {
        super.setAngles(turtle, f, g, h, i, j, s);
        this.leg1.pitch = MathHelper.cos(f * 0.6662F * 0.6F) * 0.5F * g;
        this.leg2.pitch = MathHelper.cos(f * 0.6662F * 0.6F + (float) Math.PI) * 0.5F * g;
        this.leg3.roll = MathHelper.cos(f * 0.6662F * 0.6F + (float) Math.PI) * 0.5F * g;
        this.leg4.roll = MathHelper.cos(f * 0.6662F * 0.6F) * 0.5F * g;
        this.leg3.pitch = 0.0F;
        this.leg4.pitch = 0.0F;
        this.leg3.yaw = 0.0F;
        this.leg4.yaw = 0.0F;
        this.leg1.yaw = 0.0F;
        this.leg2.yaw = 0.0F;
        this.eggBelly.pitch = ((float) Math.PI / 2F);
        if (!turtle.isInsideWater() && turtle.onGround) {
            float k = 1.0F;
            float l = 1.0F;
            float m = 5.0F;
            this.leg3.yaw = MathHelper.cos(k * f * 5.0F + (float) Math.PI) * 8.0F * g * l;
            this.leg3.roll = 0.0F;
            this.leg4.yaw = MathHelper.cos(k * f * 5.0F) * 8.0F * g * l;
            this.leg4.roll = 0.0F;
            this.leg1.yaw = MathHelper.cos(f * 5.0F + (float) Math.PI) * 3.0F * g;
            this.leg1.pitch = 0.0F;
            this.leg2.yaw = MathHelper.cos(f * 5.0F) * 3.0F * g;
            this.leg2.pitch = 0.0F;
        }

        this.eggBelly.visible = false;
    }

    public void render(ClientTurtle turtle, float i, float j, float f, float g, float h, float k) {
        boolean bl = this.eggBelly.visible;
        if (bl) {
            com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
            com.mojang.blaze3d.platform.GlStateManager.translatef(0.0F, -0.08F, 0.0F);
        }

        super.render(turtle, i, j, f, g, h, k);
        if (bl) {
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();
        }

    }
}
