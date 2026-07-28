package com.jeff.pets.client.rendering.vanilla.irongolem;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.client.Utils;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;

public class ClientIronGolemModel<T extends LivingEntity> extends EntityModel<T> {
    private final RendererModel head;
    private final RendererModel body;
    private final RendererModel arm0;
    private final RendererModel arm1;
    private final RendererModel leg0;
    private final RendererModel leg1;

    public ClientIronGolemModel() {
        int i = 128;
        int j = 128;
        this.head = (new RendererModel(this)).setTexSize(128, 128);
        this.head.setPos(0.0F, -7.0F, -2.0F);
        this.head.texOffs(0, 0).addBox(-4.0F, -12.0F, -5.5F, (int) 8.0, (int) 10.0, (int) 8.0, 0.0F);
        this.head.texOffs(24, 0).addBox(-1.0F, -5.0F, -7.5F, (int) 2.0, (int) 4.0, (int) 2.0, 0.0F);
        this.body = (new RendererModel(this)).setTexSize(128, 128);
        this.body.setPos(0.0F, -7.0F, 0.0F);
        this.body.texOffs(0, 40).addBox(-9.0F, -2.0F, -6.0F, (int) 18.0, (int) 12.0, (int) 11.0, 0.0F);
        this.body.texOffs(0, 70).addBox(-4.5F, 10.0F, -3.0F, (int) 9.0, (int) 5.0, (int) 6.0, 0.5F);
        this.arm0 = (new RendererModel(this)).setTexSize(128, 128);
        this.arm0.setPos(0.0F, -7.0F, 0.0F);
        this.arm0.texOffs(60, 21).addBox(-13.0F, -2.5F, -3.0F, (int) 4.0, (int) 30.0, (int) 6.0, 0.0F);
        this.arm1 = (new RendererModel(this)).setTexSize(128, 128);
        this.arm1.setPos(0.0F, -7.0F, 0.0F);
        this.arm1.texOffs(60, 58).addBox(9.0F, -2.5F, -3.0F, (int) 4.0, (int) 30.0, (int) 6.0, 0.0F);
        this.leg0 = (new RendererModel(this, 0, 22)).setTexSize(128, 128);
        this.leg0.setPos(-4.0F, 11.0F, 0.0F);
        this.leg0.texOffs(37, 0).addBox(-3.5F, -3.0F, -3.0F, (int) 6.0, (int) 16.0, (int) 5.0, 0.0F);
        this.leg1 = (new RendererModel(this, 0, 22)).setTexSize(128, 128);
        this.leg1.mirror = true;
        this.leg1.texOffs(60, 0).setPos(5.0F, 11.0F, 0.0F);
        this.leg1.addBox(-3.5F, -3.0F, -3.0F, (int) 6.0, (int) 16.0, (int) 5.0, 0.0F);
    }

    public Iterable<RendererModel> parts() {
        return ImmutableList.of(this.head, this.body, this.leg0, this.leg1, this.arm0, this.arm1);
    }

    @Override
    public void setupAnim(T ironGolem, float f, float g, float h, float i, float j, float l) {
        this.head.yRot = i * ((float) Math.PI / 180F);
        this.head.xRot = j * ((float) Math.PI / 180F);
        this.leg0.xRot = -1.5F * Utils.triangleWave(f, 13.0F) * g;
        this.leg1.xRot = 1.5F * Utils.triangleWave(f, 13.0F) * g;
        this.leg0.yRot = 0.0F;
        this.leg1.yRot = 0.0F;
    }

    @Override
    public void prepareMobModel(T ironGolem, float f, float g, float h) {
        int i = 0;
        if (i > 0) {
            this.arm0.xRot = -2.0F + 1.5F * Utils.triangleWave((float) i - h, 10.0F);
            this.arm1.xRot = -2.0F + 1.5F * Utils.triangleWave((float) i - h, 10.0F);
        } else {
            int j = 0;
            if (j > 0) {
                this.arm0.xRot = -0.8F + 0.025F * Utils.triangleWave((float) j, 70.0F);
                this.arm1.xRot = 0.0F;
            } else {
                this.arm0.xRot = (-0.2F + 1.5F * Utils.triangleWave(f, 13.0F)) * g;
                this.arm1.xRot = (-0.2F - 1.5F * Utils.triangleWave(f, 13.0F)) * g;
            }
        }
    }

    @Override
    public void render(T ironGOlem, float f, float g, float h, float i, float j, float k) {
        super.render(ironGOlem, f, g, h, i, j, k);
        this.body.render(k);
        this.head.render(k);
        this.arm0.render(k);
        this.arm1.render(k);
        this.leg0.render(k);
        this.leg1.render(k);
    }
}
