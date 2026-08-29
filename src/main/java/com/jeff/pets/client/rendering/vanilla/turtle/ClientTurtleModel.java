package com.jeff.pets.client.rendering.vanilla.turtle;

import com.jeff.pets.mob.vanilla.passive.ClientTurtle;
import net.minecraft.client.renderer.entity.model.ModelQuadruped;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ClientTurtleModel extends ModelQuadruped {
    private final ModelRenderer eggBelly;

    public ClientTurtleModel(float f) {
        super(12, f);
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.head = new ModelRenderer(this, 3, 0);
        this.head.addBox(-3.0F, -1.0F, -3.0F, (int) 6.0, (int) 5.0, (int) 6.0, 0.0F);
        this.head.setRotationPoint(0.0F, 19.0F, -10.0F);
        this.body = new ModelRenderer(this);
        this.body.setTextureOffset(7, 37).addBox(-9.5F, 3.0F, -10.0F, (int) 19.0, (int) 20.0, (int) 6.0, 0.0F);
        this.body.setTextureOffset(31, 1).addBox(-5.5F, 3.0F, -13.0F, (int) 11.0, (int) 18.0, (int) 3.0, 0.0F);
        this.body.setRotationPoint(0.0F, 11.0F, -10.0F);
        this.eggBelly = new ModelRenderer(this);
        this.eggBelly.setTextureOffset(70, 33).addBox(-4.5F, 3.0F, -14.0F, (int) 9.0, (int) 18.0, (int) 1.0, 0.0F);
        this.eggBelly.setRotationPoint(0.0F, 11.0F, -10.0F);
        int i = 1;
        this.leg1 = new ModelRenderer(this, 1, 23);
        this.leg1.addBox(-2.0F, 0.0F, 0.0F, (int) 4.0, (int) 1.0, (int) 10.0, 0.0F);
        this.leg1.setRotationPoint(-3.5F, 22.0F, 11.0F);
        this.leg2 = new ModelRenderer(this, 1, 12);
        this.leg2.addBox(-2.0F, 0.0F, 0.0F, (int) 4.0, (int) 1.0, (int) 10.0, 0.0F);
        this.leg2.setRotationPoint(3.5F, 22.0F, 11.0F);
        this.leg3 = new ModelRenderer(this, 27, 30);
        this.leg3.addBox(-13.0F, 0.0F, -2.0F, (int) 13.0, (int) 1.0, (int) 5.0, 0.0F);
        this.leg3.setRotationPoint(-5.0F, 21.0F, -4.0F);
        this.leg4 = new ModelRenderer(this, 27, 24);
        this.leg4.addBox(0.0F, 0.0F, -2.0F, (int) 13.0, (int) 1.0, (int) 5.0, 0.0F);
        this.leg4.setRotationPoint(5.0F, 21.0F, -4.0F);
    }

    @Override
    public void setRotationAngles(float f, float g, float h, float i, float j, float e, Entity entity) {
        ClientTurtle turtle = (ClientTurtle) entity;
        super.setRotationAngles(f, g, h, i, j, e, entity);
        this.leg1.rotateAngleX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F * 0.6F) * 0.5F * g;
        this.leg2.rotateAngleX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F * 0.6F + (float) Math.PI) * 0.5F * g;
        this.leg3.rotateAngleZ = net.minecraft.util.math.MathHelper.cos(f * 0.6662F * 0.6F + (float) Math.PI) * 0.5F * g;
        this.leg4.rotateAngleZ = net.minecraft.util.math.MathHelper.cos(f * 0.6662F * 0.6F) * 0.5F * g;
        this.leg3.rotateAngleX = 0.0F;
        this.leg4.rotateAngleX = 0.0F;
        this.leg3.rotateAngleY = 0.0F;
        this.leg4.rotateAngleY = 0.0F;
        this.leg1.rotateAngleY = 0.0F;
        this.leg2.rotateAngleY = 0.0F;
        this.eggBelly.rotateAngleX = ((float) Math.PI / 2F);
        if (!turtle.isInWater() && turtle.onGround) {
            float k = 1.0F;
            float l = 1.0F;
            float m = 5.0F;
            this.leg3.rotateAngleY = net.minecraft.util.math.MathHelper.cos(k * f * 5.0F + (float) Math.PI) * 8.0F * g * l;
            this.leg3.rotateAngleZ = 0.0F;
            this.leg4.rotateAngleY = net.minecraft.util.math.MathHelper.cos(k * f * 5.0F) * 8.0F * g * l;
            this.leg4.rotateAngleZ = 0.0F;
            this.leg1.rotateAngleY = net.minecraft.util.math.MathHelper.cos(f * 5.0F + (float) Math.PI) * 3.0F * g;
            this.leg1.rotateAngleX = 0.0F;
            this.leg2.rotateAngleY = net.minecraft.util.math.MathHelper.cos(f * 5.0F) * 3.0F * g;
            this.leg2.rotateAngleX = 0.0F;
        }

        this.eggBelly.showModel = false;
    }

    public void render(ClientTurtle turtle, float i, float j, float f, float g, float h, float k) {
        boolean bl = this.eggBelly.showModel;
        if (bl) {
            net.minecraft.client.renderer.GlStateManager.pushMatrix();
            net.minecraft.client.renderer.GlStateManager.translatef(0.0F, -0.08F, 0.0F);
        }

        super.render(turtle, i, j, f, g, h, k);
        if (bl) {
            net.minecraft.client.renderer.GlStateManager.popMatrix();
        }

    }
}
