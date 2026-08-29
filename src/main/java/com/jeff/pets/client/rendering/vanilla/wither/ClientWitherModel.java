package com.jeff.pets.client.rendering.vanilla.wither;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import java.util.Arrays;

public class ClientWitherModel extends ModelBase {
    private final ModelRenderer[] upperBodyParts;
    private final ModelRenderer[] heads;
    private final ImmutableList<ModelRenderer> parts;

    public ClientWitherModel(float f) {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.upperBodyParts = new ModelRenderer[3];
        this.upperBodyParts[0] = new ModelRenderer(this, 0, 16);
        this.upperBodyParts[0].addBox(-10.0F, 3.9F, -0.5F, (int) 20.0, (int) 3.0, (int) 3.0, f);
        this.upperBodyParts[1] = (new ModelRenderer(this)).setTextureSize(this.textureWidth, this.textureHeight);
        this.upperBodyParts[1].setRotationPoint(-2.0F, 6.9F, -0.5F);
        this.upperBodyParts[1].setTextureOffset(0, 22).addBox(0.0F, 0.0F, 0.0F, (int) 3.0, (int) 10.0, (int) 3.0, f);
        this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 1.5F, 0.5F, (int) 11.0, (int) 2.0, (int) 2.0, f);
        this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 4.0F, 0.5F, (int) 11.0, (int) 2.0, (int) 2.0, f);
        this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 6.5F, 0.5F, (int) 11.0, (int) 2.0, (int) 2.0, f);
        this.upperBodyParts[2] = new ModelRenderer(this, 12, 22);
        this.upperBodyParts[2].addBox(0.0F, 0.0F, 0.0F, (int) 3.0, (int) 6.0, (int) 3.0, f);
        this.heads = new ModelRenderer[3];
        this.heads[0] = new ModelRenderer(this, 0, 0);
        this.heads[0].addBox(-4.0F, -4.0F, -4.0F, (int) 8.0, (int) 8.0, (int) 8.0, f);
        this.heads[1] = new ModelRenderer(this, 32, 0);
        this.heads[1].addBox(-4.0F, -4.0F, -4.0F, (int) 6.0, (int) 6.0, (int) 6.0, f);
        this.heads[1].rotationPointX = -8.0F;
        this.heads[1].rotationPointY = 4.0F;
        this.heads[2] = new ModelRenderer(this, 32, 0);
        this.heads[2].addBox(-4.0F, -4.0F, -4.0F, (int) 6.0, (int) 6.0, (int) 6.0, f);
        this.heads[2].rotationPointX = 10.0F;
        this.heads[2].rotationPointY = 4.0F;
        ImmutableList.Builder<ModelRenderer> builder = ImmutableList.builder();
        builder.addAll(Arrays.asList(this.heads));
        builder.addAll(Arrays.asList(this.upperBodyParts));
        this.parts = builder.build();
    }

    public ImmutableList<ModelRenderer> parts() {
        return this.parts;
    }

    public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entityIn) {
        float l = net.minecraft.util.math.MathHelper.cos(h * 0.1F);
        this.upperBodyParts[1].rotateAngleX = (0.065F + 0.05F * l) * (float) Math.PI;
        this.upperBodyParts[2].setRotationPoint(-2.0F, 6.9F + net.minecraft.util.math.MathHelper.cos(this.upperBodyParts[1].rotateAngleX) * 10.0F, -0.5F + net.minecraft.util.math.MathHelper.sin(this.upperBodyParts[1].rotateAngleX) * 10.0F);
        this.upperBodyParts[2].rotateAngleX = (0.265F + 0.1F * l) * (float) Math.PI;
        this.heads[0].rotateAngleY = i * ((float) Math.PI / 180F);
        this.heads[0].rotateAngleX = j * ((float) Math.PI / 180F);
    }

    public void setLivingAnimations(EntityLivingBase witherBoss, float f, float g, float h) {
        for (int i = 1; i < 3; ++i) {
            this.heads[i].rotateAngleY = (witherBoss.rotationYawHead - witherBoss.renderYawOffset) * ((float) Math.PI / 180F);
            this.heads[i].rotateAngleX = witherBoss.rotationPitch * ((float) Math.PI / 180F);
        }

    }

    @Override
    public void render(Entity wither, float f, float g, float h, float i, float j, float k) {
        for (ModelRenderer head : this.parts()) {
            head.render(k);
        }
    }
}
