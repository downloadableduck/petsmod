package com.jeff.pets.client.rendering.vanilla.turtle;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.jeff.pets.mob.vanilla.passive.ClientTurtle;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class ClientTurtleModel extends QuadrupedModel<ClientTurtle> {
    private final RendererModel eggBelly;

    public ClientTurtleModel(float f) {
        super(12, f);
        this.texWidth = 128;
        this.texHeight = 64;
        this.head = new RendererModel(this, 3, 0);
        this.head.addBox(-3.0F, -1.0F, -3.0F, (int) 6.0, (int) 5.0, (int) 6.0, 0.0F);
        this.head.setPos(0.0F, 19.0F, -10.0F);
        this.body = new RendererModel(this);
        this.body.texOffs(7, 37).addBox(-9.5F, 3.0F, -10.0F, (int) 19.0, (int) 20.0, (int) 6.0, 0.0F);
        this.body.texOffs(31, 1).addBox(-5.5F, 3.0F, -13.0F, (int) 11.0, (int) 18.0, (int) 3.0, 0.0F);
        this.body.setPos(0.0F, 11.0F, -10.0F);
        this.eggBelly = new RendererModel(this);
        this.eggBelly.texOffs(70, 33).addBox(-4.5F, 3.0F, -14.0F, (int) 9.0, (int) 18.0, (int) 1.0, 0.0F);
        this.eggBelly.setPos(0.0F, 11.0F, -10.0F);
        int i = 1;
        this.leg0 = new RendererModel(this, 1, 23);
        this.leg0.addBox(-2.0F, 0.0F, 0.0F, (int) 4.0, (int) 1.0, (int) 10.0, 0.0F);
        this.leg0.setPos(-3.5F, 22.0F, 11.0F);
        this.leg1 = new RendererModel(this, 1, 12);
        this.leg1.addBox(-2.0F, 0.0F, 0.0F, (int) 4.0, (int) 1.0, (int) 10.0, 0.0F);
        this.leg1.setPos(3.5F, 22.0F, 11.0F);
        this.leg2 = new RendererModel(this, 27, 30);
        this.leg2.addBox(-13.0F, 0.0F, -2.0F, (int) 13.0, (int) 1.0, (int) 5.0, 0.0F);
        this.leg2.setPos(-5.0F, 21.0F, -4.0F);
        this.leg3 = new RendererModel(this, 27, 24);
        this.leg3.addBox(0.0F, 0.0F, -2.0F, (int) 13.0, (int) 1.0, (int) 5.0, 0.0F);
        this.leg3.setPos(5.0F, 21.0F, -4.0F);
    }

    @Override
    public void setupAnim(ClientTurtle turtle, float f, float g, float h, float i, float j, float e) {
        super.setupAnim(turtle, f, g, h, i, j, e);
        this.leg0.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F * 0.6F) * 0.5F * g;
        this.leg1.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F * 0.6F + (float) Math.PI) * 0.5F * g;
        this.leg2.zRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F * 0.6F + (float) Math.PI) * 0.5F * g;
        this.leg3.zRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F * 0.6F) * 0.5F * g;
        this.leg2.xRot = 0.0F;
        this.leg3.xRot = 0.0F;
        this.leg2.yRot = 0.0F;
        this.leg3.yRot = 0.0F;
        this.leg0.yRot = 0.0F;
        this.leg1.yRot = 0.0F;
        this.eggBelly.xRot = ((float) Math.PI / 2F);
        if (!turtle.isInWater() && turtle.onGround) {
            float k = 1.0F;
            float l = 1.0F;
            float m = 5.0F;
            this.leg2.yRot = net.minecraft.util.math.MathHelper.cos(k * f * 5.0F + (float) Math.PI) * 8.0F * g * l;
            this.leg2.zRot = 0.0F;
            this.leg3.yRot = net.minecraft.util.math.MathHelper.cos(k * f * 5.0F) * 8.0F * g * l;
            this.leg3.zRot = 0.0F;
            this.leg0.yRot = net.minecraft.util.math.MathHelper.cos(f * 5.0F + (float) Math.PI) * 3.0F * g;
            this.leg0.xRot = 0.0F;
            this.leg1.yRot = net.minecraft.util.math.MathHelper.cos(f * 5.0F) * 3.0F * g;
            this.leg1.xRot = 0.0F;
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
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();;
        }

    }
}
