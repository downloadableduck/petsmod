package com.jeff.pets.client.rendering.vanilla.wither;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.util.Arrays;

public class ClientWitherModel extends EntityModel {
    private final ModelPart[] upperBodyParts;
    private final ModelPart[] heads;
    private final ImmutableList<ModelPart> parts;

    public ClientWitherModel(float f) {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.upperBodyParts = new ModelPart[3];
        this.upperBodyParts[0] = new ModelPart(this, 0, 16);
        this.upperBodyParts[0].addCuboid(-10.0F, 3.9F, -0.5F, (int) 20.0, (int) 3.0, (int) 3.0, f);
        this.upperBodyParts[1] = (new ModelPart(this)).setTextureSize(this.textureWidth, this.textureHeight);
        this.upperBodyParts[1].setPivot(-2.0F, 6.9F, -0.5F);
        this.upperBodyParts[1].setTextureOffset(0, 22).addCuboid(0.0F, 0.0F, 0.0F, (int) 3.0, (int) 10.0, (int) 3.0, f);
        this.upperBodyParts[1].setTextureOffset(24, 22).addCuboid(-4.0F, 1.5F, 0.5F, (int) 11.0, (int) 2.0, (int) 2.0, f);
        this.upperBodyParts[1].setTextureOffset(24, 22).addCuboid(-4.0F, 4.0F, 0.5F, (int) 11.0, (int) 2.0, (int) 2.0, f);
        this.upperBodyParts[1].setTextureOffset(24, 22).addCuboid(-4.0F, 6.5F, 0.5F, (int) 11.0, (int) 2.0, (int) 2.0, f);
        this.upperBodyParts[2] = new ModelPart(this, 12, 22);
        this.upperBodyParts[2].addCuboid(0.0F, 0.0F, 0.0F, (int) 3.0, (int) 6.0, (int) 3.0, f);
        this.heads = new ModelPart[3];
        this.heads[0] = new ModelPart(this, 0, 0);
        this.heads[0].addCuboid(-4.0F, -4.0F, -4.0F, (int) 8.0, (int) 8.0, (int) 8.0, f);
        this.heads[1] = new ModelPart(this, 32, 0);
        this.heads[1].addCuboid(-4.0F, -4.0F, -4.0F, (int) 6.0, (int) 6.0, (int) 6.0, f);
        this.heads[1].pivotX = -8.0F;
        this.heads[1].pivotY = 4.0F;
        this.heads[2] = new ModelPart(this, 32, 0);
        this.heads[2].addCuboid(-4.0F, -4.0F, -4.0F, (int) 6.0, (int) 6.0, (int) 6.0, f);
        this.heads[2].pivotX = 10.0F;
        this.heads[2].pivotY = 4.0F;
        ImmutableList.Builder<ModelPart> builder = ImmutableList.builder();
        builder.addAll(Arrays.asList(this.heads));
        builder.addAll(Arrays.asList(this.upperBodyParts));
        this.parts = builder.build();
    }

    public ImmutableList<ModelPart> parts() {
        return this.parts;
    }

    public void setAngles(float f, float g, float h, float i, float j, float k, Entity entityIn) {
        float l = net.minecraft.util.math.MathHelper.cos(h * 0.1F);
        this.upperBodyParts[1].posX = (0.065F + 0.05F * l) * (float) Math.PI;
        this.upperBodyParts[2].setPivot(-2.0F, 6.9F + net.minecraft.util.math.MathHelper.cos(this.upperBodyParts[1].posX) * 10.0F, -0.5F + net.minecraft.util.math.MathHelper.sin(this.upperBodyParts[1].posX) * 10.0F);
        this.upperBodyParts[2].posX = (0.265F + 0.1F * l) * (float) Math.PI;
        this.heads[0].posY = i * ((float) Math.PI / 180F);
        this.heads[0].posX = j * ((float) Math.PI / 180F);
    }

    public void animateModel(LivingEntity witherBoss, float f, float g, float h) {
        for (int i = 1; i < 3; ++i) {
            this.heads[i].posY = (witherBoss.headYaw - witherBoss.bodyYaw) * ((float) Math.PI / 180F);
            this.heads[i].posX = witherBoss.pitch * ((float) Math.PI / 180F);
        }

    }

    @Override
    public void render(Entity wither, float f, float g, float h, float i, float j, float k) {
        for (ModelPart head : this.parts()) {
            head.render(k);
        }
    }
}
