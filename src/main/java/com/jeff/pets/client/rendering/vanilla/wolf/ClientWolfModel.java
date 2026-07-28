package com.jeff.pets.client.rendering.vanilla.wolf;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

import java.util.List;

public class ClientWolfModel extends EntityModel<ClientWolf> {
    private final RendererModel head;
    private final RendererModel realHead;
    private final RendererModel body;
    private final RendererModel leg0;
    private final RendererModel leg1;
    private final RendererModel leg2;
    private final RendererModel leg3;
    private final RendererModel tail;
    private final RendererModel realTail;
    private final RendererModel upperBody;

    public ClientWolfModel() {
        float f = 0.0F;
        float g = 13.5F;
        this.head = new RendererModel(this, 0, 0);
        this.head.setPos(-1.0F, 13.5F, -7.0F);
        this.realHead = new RendererModel(this, 0, 0);
        this.realHead.addBox(-2.0F, -3.0F, -2.0F, (int) 6.0, (int) 6.0, (int) 4.0, 0.0F);
        this.head.addChild(this.realHead);
        this.body = new RendererModel(this, 18, 14);
        this.body.addBox(-3.0F, -2.0F, -3.0F, (int) 6.0, (int) 9.0, (int) 6.0, 0.0F);
        this.body.setPos(0.0F, 14.0F, 2.0F);
        this.upperBody = new RendererModel(this, 21, 0);
        this.upperBody.addBox(-3.0F, -3.0F, -3.0F, (int) 8.0, (int) 6.0, (int) 7.0, 0.0F);
        this.upperBody.setPos(-1.0F, 14.0F, 2.0F);
        this.leg0 = new RendererModel(this, 0, 18);
        this.leg0.addBox(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.leg0.setPos(-2.5F, 16.0F, 7.0F);
        this.leg1 = new RendererModel(this, 0, 18);
        this.leg1.addBox(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.leg1.setPos(0.5F, 16.0F, 7.0F);
        this.leg2 = new RendererModel(this, 0, 18);
        this.leg2.addBox(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.leg2.setPos(-2.5F, 16.0F, -4.0F);
        this.leg3 = new RendererModel(this, 0, 18);
        this.leg3.addBox(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.leg3.setPos(0.5F, 16.0F, -4.0F);
        this.tail = new RendererModel(this, 9, 18);
        this.tail.setPos(-1.0F, 12.0F, 8.0F);
        this.realTail = new RendererModel(this, 9, 18);
        this.realTail.addBox(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.tail.addChild(this.realTail);
        this.realHead.texOffs(16, 14).addBox(-2.0F, -5.0F, 0.0F, (int) 2.0, (int) 2.0, (int) 1.0, 0.0F);
        this.realHead.texOffs(16, 14).addBox(2.0F, -5.0F, 0.0F, (int) 2.0, (int) 2.0, (int) 1.0, 0.0F);
        this.realHead.texOffs(0, 10).addBox(-0.5F, 0.0F, -5.0F, (int) 3.0, (int) 3.0, (int) 4.0, 0.0F);
    }

    protected Iterable<RendererModel> headParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<RendererModel> bodyParts() {
        return ImmutableList.of(this.body, this.leg0, this.leg1, this.leg2, this.leg3, this.tail, this.upperBody);
    }

    public void prepareMobModel(ClientWolf wolf, float f, float g, float h) {
        //this.tail.yRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;

        if (wolf.isPassenger()) {
            this.upperBody.setPos(-1.0F, 16.0F, -3.0F);
            this.upperBody.xRot = 1.2566371F;
            this.upperBody.yRot = 0.0F;
            this.body.setPos(0.0F, 18.0F, 0.0F);
            this.body.xRot = ((float) Math.PI / 4F);
            this.tail.setPos(-1.0F, 21.0F, 6.0F);
            this.leg0.setPos(-2.5F, 22.7F, 2.0F);
            this.leg0.xRot = ((float) Math.PI * 1.5F);
            this.leg1.setPos(0.5F, 22.7F, 2.0F);
            this.leg1.xRot = ((float) Math.PI * 1.5F);
            this.leg2.xRot = 5.811947F;
            this.leg2.setPos(-2.49F, 17.0F, -4.0F);
            this.leg3.xRot = 5.811947F;
            this.leg3.setPos(0.51F, 17.0F, -4.0F);
        } else {
            this.body.setPos(0.0F, 14.0F, 2.0F);
            this.body.xRot = ((float) Math.PI / 2F);
            this.upperBody.setPos(-1.0F, 14.0F, -3.0F);
            this.upperBody.xRot = this.body.xRot;
            this.tail.setPos(-1.0F, 12.0F, 8.0F);
            this.leg0.setPos(-2.5F, 16.0F, 7.0F);
            this.leg1.setPos(0.5F, 16.0F, 7.0F);
            this.leg2.setPos(-2.5F, 16.0F, -4.0F);
            this.leg3.setPos(0.5F, 16.0F, -4.0F);
            this.leg0.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
            this.leg1.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
            this.leg2.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
            this.leg3.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
        }

        this.realHead.zRot = 0;
        this.upperBody.zRot = 0;
        this.body.zRot = 0;
        //this.realTail.zRot = wolf.getBodyRollAngle(h, -0.2F);
    }

    @Override
    public void setupAnim(ClientWolf wolf, float f, float g, float h, float i, float j, float k) {
        this.head.xRot = j * ((float) Math.PI / 180F);
        this.head.yRot = i * ((float) Math.PI / 180F);
        this.tail.xRot = 45;
    }

    @Override
    public void render(ClientWolf wolf, float f,  float g, float h, float i, float j, float k) {
        super.render(wolf, f, g, h, i, j, k);
        for (RendererModel cube : this.getParts()) {
            cube.render(k);
        }
    }

    private List<RendererModel> getParts() {
        return ImmutableList.of(head, body, leg0, leg1, leg2, leg3, tail, upperBody);
    }
}
