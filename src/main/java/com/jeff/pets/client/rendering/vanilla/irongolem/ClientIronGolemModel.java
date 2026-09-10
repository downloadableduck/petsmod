package com.jeff.pets.client.rendering.vanilla.irongolem;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.client.Utils;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class ClientIronGolemModel extends EntityModel {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart arm0;
    private final ModelPart arm1;
    private final ModelPart leg0;
    private final ModelPart leg1;

    public ClientIronGolemModel() {
        int i = 128;
        int j = 128;
        this.head = (new ModelPart(this)).setTextureSize(128, 128);
        this.head.setPivot(0.0F, -7.0F, -2.0F);
        this.head.setTextureOffset(0, 0).addCuboid(-4.0F, -12.0F, -5.5F, (int) 8.0, (int) 10.0, (int) 8.0, 0.0F);
        this.head.setTextureOffset(24, 0).addCuboid(-1.0F, -5.0F, -7.5F, (int) 2.0, (int) 4.0, (int) 2.0, 0.0F);
        this.body = (new ModelPart(this)).setTextureSize(128, 128);
        this.body.setPivot(0.0F, -7.0F, 0.0F);
        this.body.setTextureOffset(0, 40).addCuboid(-9.0F, -2.0F, -6.0F, (int) 18.0, (int) 12.0, (int) 11.0, 0.0F);
        this.body.setTextureOffset(0, 70).addCuboid(-4.5F, 10.0F, -3.0F, (int) 9.0, (int) 5.0, (int) 6.0, 0.5F);
        this.arm0 = (new ModelPart(this)).setTextureSize(128, 128);
        this.arm0.setPivot(0.0F, -7.0F, 0.0F);
        this.arm0.setTextureOffset(60, 21).addCuboid(-13.0F, -2.5F, -3.0F, (int) 4.0, (int) 30.0, (int) 6.0, 0.0F);
        this.arm1 = (new ModelPart(this)).setTextureSize(128, 128);
        this.arm1.setPivot(0.0F, -7.0F, 0.0F);
        this.arm1.setTextureOffset(60, 58).addCuboid(9.0F, -2.5F, -3.0F, (int) 4.0, (int) 30.0, (int) 6.0, 0.0F);
        this.leg0 = (new ModelPart(this, 0, 22)).setTextureSize(128, 128);
        this.leg0.setPivot(-4.0F, 11.0F, 0.0F);
        this.leg0.setTextureOffset(37, 0).addCuboid(-3.5F, -3.0F, -3.0F, (int) 6.0, (int) 16.0, (int) 5.0, 0.0F);
        this.leg1 = (new ModelPart(this, 0, 22)).setTextureSize(128, 128);
        this.leg1.mirror = true;
        this.leg1.setTextureOffset(60, 0).setPivot(5.0F, 11.0F, 0.0F);
        this.leg1.addCuboid(-3.5F, -3.0F, -3.0F, (int) 6.0, (int) 16.0, (int) 5.0, 0.0F);
    }

    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.head, this.body, this.leg0, this.leg1, this.arm0, this.arm1);
    }

    @Override
    public void setAngles(float f, float g, float h, float i, float j, float l, Entity ironGolem) {
        this.head.posY = i * ((float) Math.PI / 180F);
        this.head.posX = j * ((float) Math.PI / 180F);
        this.leg0.posX = -1.5F * Utils.triangleWave(f, 13.0F) * g;
        this.leg1.posX = 1.5F * Utils.triangleWave(f, 13.0F) * g;
        this.leg0.posY = 0.0F;
        this.leg1.posY = 0.0F;
    }

    @Override
    public void animateModel(LivingEntity ironGolem, float f, float g, float h) {
        int i = 0;
        if (i > 0) {
            this.arm0.posX = -2.0F + 1.5F * Utils.triangleWave((float) i - h, 10.0F);
            this.arm1.posX = -2.0F + 1.5F * Utils.triangleWave((float) i - h, 10.0F);
        } else {
            int j = 0;
            if (j > 0) {
                this.arm0.posX = -0.8F + 0.025F * Utils.triangleWave((float) j, 70.0F);
                this.arm1.posX = 0.0F;
            } else {
                this.arm0.posX = (-0.2F + 1.5F * Utils.triangleWave(f, 13.0F)) * g;
                this.arm1.posX = (-0.2F - 1.5F * Utils.triangleWave(f, 13.0F)) * g;
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
