package com.jeff.pets.client.rendering.vanilla.parrot;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.passive.ClientParrot;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.ParrotModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class ClientParrotModel extends EntityModel<ClientParrot> {
    private final RendererModel body;
    private final RendererModel tail;
    private final RendererModel wingLeft;
    private final RendererModel wingRight;
    private final RendererModel head;
    private final RendererModel head2;
    private final RendererModel beak1;
    private final RendererModel beak2;
    private final RendererModel feather;
    private final RendererModel legLeft;
    private final RendererModel legRight;

    public ClientParrotModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.body = new RendererModel(this, 2, 8);
        this.body.addBox(-1.5F, 0.0F, -1.5F, (int) 3.0, (int) 6.0, (int) 3.0);
        this.body.setPos(0.0F, 16.5F, -3.0F);
        this.tail = new RendererModel(this, 22, 1);
        this.tail.addBox(-1.5F, -1.0F, -1.0F, (int) 3.0, (int) 4.0, (int) 1.0);
        this.tail.setPos(0.0F, 21.07F, 1.16F);
        this.wingLeft = new RendererModel(this, 19, 8);
        this.wingLeft.addBox(-0.5F, 0.0F, -1.5F, (int) 1.0, (int) 5.0, (int) 3.0);
        this.wingLeft.setPos(1.5F, 16.94F, -2.76F);
        this.wingRight = new RendererModel(this, 19, 8);
        this.wingRight.addBox(-0.5F, 0.0F, -1.5F, (int) 1.0, (int) 5.0, (int) 3.0);
        this.wingRight.setPos(-1.5F, 16.94F, -2.76F);
        this.head = new RendererModel(this, 2, 2);
        this.head.addBox(-1.0F, -1.5F, -1.0F, (int) 2.0, (int) 3.0, (int) 2.0);
        this.head.setPos(0.0F, 15.69F, -2.76F);
        this.head2 = new RendererModel(this, 10, 0);
        this.head2.addBox(-1.0F, -0.5F, -2.0F, (int) 2.0, (int) 1.0, (int) 4.0);
        this.head2.setPos(0.0F, -2.0F, -1.0F);
        this.head.addChild(this.head2);
        this.beak1 = new RendererModel(this, 11, 7);
        this.beak1.addBox(-0.5F, -1.0F, -0.5F, (int) 1.0, (int) 2.0, (int) 1.0);
        this.beak1.setPos(0.0F, -0.5F, -1.5F);
        this.head.addChild(this.beak1);
        this.beak2 = new RendererModel(this, 16, 7);
        this.beak2.addBox(-0.5F, 0.0F, -0.5F, (int) 1.0, (int) 2.0, (int) 1.0);
        this.beak2.setPos(0.0F, -1.75F, -2.45F);
        this.head.addChild(this.beak2);
        this.feather = new RendererModel(this, 2, 18);
        this.feather.addBox(0.0F, -4.0F, -2.0F, (int) 0.0, (int) 5.0, (int) 4.0);
        this.feather.setPos(0.0F, -2.15F, 0.15F);
        this.head.addChild(this.feather);
        this.legLeft = new RendererModel(this, 14, 18);
        this.legLeft.addBox(-0.5F, 0.0F, -0.5F, (int) 1.0, (int) 2.0, (int) 1.0);
        this.legLeft.setPos(1.0F, 22.0F, -1.05F);
        this.legRight = new RendererModel(this, 14, 18);
        this.legRight.addBox(-0.5F, 0.0F, -0.5F, (int) 1.0, (int) 2.0, (int) 1.0);
        this.legRight.setPos(-1.0F, 22.0F, -1.05F);
    }

    public Iterable<RendererModel> parts() {
        return ImmutableList.of(this.body, this.wingLeft, this.wingRight, this.tail, this.head, this.legLeft, this.legRight);
    }

    @Override
    public void setupAnim(ClientParrot parrot, float f, float g, float h, float i, float j, float u) {
        this.setupAnim(ParrotModel.State.FLYING, parrot.tickCount, f, g, h, i, j);
    }

    @Override
    public void prepareMobModel(ClientParrot parrot, float f, float g, float h) {
        this.prepare(ParrotModel.State.FLYING);
    }

    private void setupAnim(ParrotModel.State state, int i, float f, float g, float h, float j, float k) {
        this.head.xRot = k * ((float) Math.PI / 180F);
        this.head.yRot = j * ((float) Math.PI / 180F);
        this.head.zRot = 0.0F;
        this.head.x = 0.0F;
        this.body.x = 0.0F;
        this.tail.x = 0.0F;
        this.wingRight.x = -1.5F;
        this.wingLeft.x = 1.5F;
        switch (state) {
            case SITTING:
                break;
            case PARTY:
                float l = net.minecraft.util.math.MathHelper.cos((float) i);
                float m = net.minecraft.util.math.MathHelper.sin((float) i);
                this.head.x = l;
                this.head.y = 15.69F + m;
                this.head.xRot = 0.0F;
                this.head.yRot = 0.0F;
                this.head.zRot = net.minecraft.util.math.MathHelper.sin((float) i) * 0.4F;
                this.body.x = l;
                this.body.y = 16.5F + m;
                this.wingLeft.zRot = -0.0873F - h;
                this.wingLeft.x = 1.5F + l;
                this.wingLeft.y = 16.94F + m;
                this.wingRight.zRot = 0.0873F + h;
                this.wingRight.x = -1.5F + l;
                this.wingRight.y = 16.94F + m;
                this.tail.x = l;
                this.tail.y = 21.07F + m;
                break;
            case STANDING:
                RendererModel var10000 = this.legLeft;
                var10000.xRot += net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
                var10000 = this.legRight;
                var10000.xRot += net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
            case FLYING:
            case ON_SHOULDER:
            default:
                float n = h * 0.3F;
                this.head.y = 15.69F + n;
                this.tail.xRot = 1.015F + net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 0.3F * g;
                this.tail.y = 21.07F + n;
                this.body.y = 16.5F + n;
                this.wingLeft.zRot = -0.0873F - h;
                this.wingLeft.y = 16.94F + n;
                this.wingRight.zRot = 0.0873F + h;
                this.wingRight.y = 16.94F + n;
                this.legLeft.y = 22.0F + n;
                this.legRight.y = 22.0F + n;
        }

    }

    private void prepare(ParrotModel.State state) {
        this.feather.xRot = -0.2214F;
        this.body.xRot = 0.4937F;
        this.wingLeft.xRot = -0.6981F;
        this.wingLeft.yRot = -(float) Math.PI;
        this.wingRight.xRot = -0.6981F;
        this.wingRight.yRot = -(float) Math.PI;
        this.legLeft.xRot = -0.0299F;
        this.legRight.xRot = -0.0299F;
        this.legLeft.y = 22.0F;
        this.legRight.y = 22.0F;
        this.legLeft.zRot = 0.0F;
        this.legRight.zRot = 0.0F;
        switch (state) {
            case SITTING:
                float f = 1.9F;
                this.head.y = 17.59F;
                this.tail.xRot = 1.5388988F;
                this.tail.y = 22.97F;
                this.body.y = 18.4F;
                this.wingLeft.zRot = -0.0873F;
                this.wingLeft.y = 18.84F;
                this.wingRight.zRot = 0.0873F;
                this.wingRight.y = 18.84F;
                ++this.legLeft.y;
                ++this.legRight.y;
                ++this.legLeft.xRot;
                ++this.legRight.xRot;
                break;
            case PARTY:
                this.legLeft.zRot = -0.34906584F;
                this.legRight.zRot = 0.34906584F;
            case STANDING:
            case ON_SHOULDER:
            default:
                break;
            case FLYING:
                RendererModel var10000 = this.legLeft;
                var10000.xRot += 0.6981317F;
                var10000 = this.legRight;
                var10000.xRot += 0.6981317F;
        }

    }

    @Override
    public void render(ClientParrot parrot, float f, float g, float h, float i, float j, float p_217159_1_) {
        this.body.render(p_217159_1_);
        this.wingLeft.render(p_217159_1_);
        this.wingRight.render(p_217159_1_);
        this.tail.render(p_217159_1_);
        this.head.render(p_217159_1_);
        this.legLeft.render(p_217159_1_);
        this.legRight.render(p_217159_1_);
    }
}
