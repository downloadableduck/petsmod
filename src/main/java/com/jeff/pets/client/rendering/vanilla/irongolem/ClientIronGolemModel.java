package com.jeff.pets.client.rendering.vanilla.irongolem;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.client.Utils;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ClientIronGolemModel extends ModelBase {
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer arm0;
    private final ModelRenderer arm1;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;

    public ClientIronGolemModel() {
        int i = 128;
        int j = 128;
        this.head = (new ModelRenderer(this)).setTextureSize(128, 128);
        this.head.setRotationPoint(0.0F, -7.0F, -2.0F);
        this.head.setTextureOffset(0, 0).addBox(-4.0F, -12.0F, -5.5F, (int) 8.0, (int) 10.0, (int) 8.0, 0.0F);
        this.head.setTextureOffset(24, 0).addBox(-1.0F, -5.0F, -7.5F, (int) 2.0, (int) 4.0, (int) 2.0, 0.0F);
        this.body = (new ModelRenderer(this)).setTextureSize(128, 128);
        this.body.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.body.setTextureOffset(0, 40).addBox(-9.0F, -2.0F, -6.0F, (int) 18.0, (int) 12.0, (int) 11.0, 0.0F);
        this.body.setTextureOffset(0, 70).addBox(-4.5F, 10.0F, -3.0F, (int) 9.0, (int) 5.0, (int) 6.0, 0.5F);
        this.arm0 = (new ModelRenderer(this)).setTextureSize(128, 128);
        this.arm0.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.arm0.setTextureOffset(60, 21).addBox(-13.0F, -2.5F, -3.0F, (int) 4.0, (int) 30.0, (int) 6.0, 0.0F);
        this.arm1 = (new ModelRenderer(this)).setTextureSize(128, 128);
        this.arm1.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.arm1.setTextureOffset(60, 58).addBox(9.0F, -2.5F, -3.0F, (int) 4.0, (int) 30.0, (int) 6.0, 0.0F);
        this.leg0 = (new ModelRenderer(this, 0, 22)).setTextureSize(128, 128);
        this.leg0.setRotationPoint(-4.0F, 11.0F, 0.0F);
        this.leg0.setTextureOffset(37, 0).addBox(-3.5F, -3.0F, -3.0F, (int) 6.0, (int) 16.0, (int) 5.0, 0.0F);
        this.leg1 = (new ModelRenderer(this, 0, 22)).setTextureSize(128, 128);
        this.leg1.mirror = true;
        this.leg1.setTextureOffset(60, 0).setRotationPoint(5.0F, 11.0F, 0.0F);
        this.leg1.addBox(-3.5F, -3.0F, -3.0F, (int) 6.0, (int) 16.0, (int) 5.0, 0.0F);
    }

    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.head, this.body, this.leg0, this.leg1, this.arm0, this.arm1);
    }

    @Override
    public void setRotationAngles(float f, float g, float h, float i, float j, float l, Entity ironGolem) {
        this.head.rotateAngleY = i * ((float) Math.PI / 180F);
        this.head.rotateAngleX = j * ((float) Math.PI / 180F);
        this.leg0.rotateAngleX = -1.5F * Utils.triangleWave(f, 13.0F) * g;
        this.leg1.rotateAngleX = 1.5F * Utils.triangleWave(f, 13.0F) * g;
        this.leg0.rotateAngleY = 0.0F;
        this.leg1.rotateAngleY = 0.0F;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase ironGolem, float f, float g, float h) {
        int i = 0;
        if (i > 0) {
            this.arm0.rotateAngleX = -2.0F + 1.5F * Utils.triangleWave((float) i - h, 10.0F);
            this.arm1.rotateAngleX = -2.0F + 1.5F * Utils.triangleWave((float) i - h, 10.0F);
        } else {
            int j = 0;
            if (j > 0) {
                this.arm0.rotateAngleX = -0.8F + 0.025F * Utils.triangleWave((float) j, 70.0F);
                this.arm1.rotateAngleX = 0.0F;
            } else {
                this.arm0.rotateAngleX = (-0.2F + 1.5F * Utils.triangleWave(f, 13.0F)) * g;
                this.arm1.rotateAngleX = (-0.2F - 1.5F * Utils.triangleWave(f, 13.0F)) * g;
            }
        }
    }

    @Override
    public void render(Entity ironGOlem, float f, float g, float h, float i, float j, float k) {
        super.render(ironGOlem, f, g, h, i, j, k);
        this.body.render(k);
        this.head.render(k);
        this.arm0.render(k);
        this.arm1.render(k);
        this.leg0.render(k);
        this.leg1.render(k);
    }
}
