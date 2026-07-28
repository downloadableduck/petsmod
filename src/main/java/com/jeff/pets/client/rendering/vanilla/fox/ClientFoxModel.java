package com.jeff.pets.client.rendering.vanilla.fox;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.neutral.ClientFox;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.FoxModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientFoxModel extends EntityModel<ClientFox> {
    public final RendererModel head;
    private final RendererModel earL;
    private final RendererModel earR;
    private final RendererModel nose;
    private final RendererModel body;
    private final RendererModel leg0;
    private final RendererModel leg1;
    private final RendererModel leg2;
    private final RendererModel leg3;
    private final RendererModel tail;
    private float legMotionPos;

    public ClientFoxModel() {
        super();
        this.texWidth = 48;
        this.texHeight = 32;
        this.head = new RendererModel(this, 1, 5);
        this.head.addBox(-3.0F, -2.0F, -5.0F, (int) 8.0, (int) 6.0, (int) 6.0);
        this.head.setPos(-1.0F, 16.5F, -3.0F);
        this.earL = new RendererModel(this, 8, 1);
        this.earL.addBox(-3.0F, -4.0F, -4.0F, (int) 2.0, (int) 2.0, (int) 1.0);
        this.earR = new RendererModel(this, 15, 1);
        this.earR.addBox(3.0F, -4.0F, -4.0F, (int) 2.0, (int) 2.0, (int) 1.0);
        this.nose = new RendererModel(this, 6, 18);
        this.nose.addBox(-1.0F, 2.01F, -8.0F, (int) 4.0, (int) 2.0, (int) 3.0);
        this.head.addChild(this.earL);
        this.head.addChild(this.earR);
        this.head.addChild(this.nose);
        this.body = new RendererModel(this, 24, 15);
        this.body.addBox(-3.0F, 3.999F, -3.5F, (int) 6.0, (int) 11.0, (int) 6.0);
        this.body.setPos(0.0F, 16.0F, -6.0F);
        float f = 0.001F;
        this.leg0 = new RendererModel(this, 13, 24);
        this.leg0.addBox(2.0F, 0.5F, -1.0F, (int) 2.0, (int) 6.0, (int) 2.0, 0.001F);
        this.leg0.setPos(-5.0F, 17.5F, 7.0F);
        this.leg1 = new RendererModel(this, 4, 24);
        this.leg1.addBox(2.0F, 0.5F, -1.0F, (int) 2.0, (int) 6.0, (int) 2.0, 0.001F);
        this.leg1.setPos(-1.0F, 17.5F, 7.0F);
        this.leg2 = new RendererModel(this, 13, 24);
        this.leg2.addBox(2.0F, 0.5F, -1.0F, (int) 2.0, (int) 6.0, (int) 2.0, 0.001F);
        this.leg2.setPos(-5.0F, 17.5F, 0.0F);
        this.leg3 = new RendererModel(this, 4, 24);
        this.leg3.addBox(2.0F, 0.5F, -1.0F, (int) 2.0, (int) 6.0, (int) 2.0, 0.001F);
        this.leg3.setPos(-1.0F, 17.5F, 0.0F);
        this.tail = new RendererModel(this, 30, 0);
        this.tail.addBox(2.0F, 0.0F, -1.0F, (int) 4.0, (int) 9.0, (int) 5.0);
        this.tail.setPos(-4.0F, 15.0F, -1.0F);
        this.body.addChild(this.tail);
    }

    @Override
    public void prepareMobModel(ClientFox fox, float f, float g, float h) {
        this.body.xRot = ((float) Math.PI / 2F);
        this.tail.xRot = -0.05235988F;
        this.leg0.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.leg1.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.leg2.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.leg3.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.head.setPos(-1.0F, 16.5F, -3.0F);
        this.head.yRot = 0.0F;
        this.head.zRot = 0;
        this.leg0.visible = true;
        this.leg1.visible = true;
        this.leg2.visible = true;
        this.leg3.visible = true;
        this.body.setPos(0.0F, 16.0F, -6.0F);
        this.body.zRot = 0.0F;
        this.leg0.setPos(-5.0F, 17.5F, 7.0F);
        this.leg1.setPos(-1.0F, 17.5F, 7.0F);
        if (fox.isPassenger()) {
            this.body.xRot = ((float) Math.PI / 6F);
            this.body.setPos(0.0F, 9.0F, -3.0F);
            this.tail.xRot = ((float) Math.PI / 4F);
            this.tail.setPos(-4.0F, 15.0F, -2.0F);
            this.head.setPos(-1.0F, 10.0F, -0.25F);
            this.head.xRot = 0.0F;
            this.head.yRot = 0.0F;
            if (this.young) {
                this.head.setPos(-1.0F, 13.0F, -3.75F);
            }

            this.leg0.xRot = -1.3089969F;
            this.leg0.setPos(-5.0F, 21.5F, 6.75F);
            this.leg1.xRot = -1.3089969F;
            this.leg1.setPos(-1.0F, 21.5F, 6.75F);
            this.leg2.xRot = -0.2617994F;
            this.leg3.xRot = -0.2617994F;
        }

    }

    protected Iterable<RendererModel> headParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<RendererModel> bodyParts() {
        return ImmutableList.of(this.body, this.leg0, this.leg1, this.leg2, this.leg3);
    }

    @Override
    public void setupAnim(ClientFox fox, float f, float g, float h, float i, float j, float u) {
        this.head.xRot = j * ((float) Math.PI / 180F);
        this.head.yRot = i * ((float) Math.PI / 180F);
    }

    @Override
    public void render(ClientFox p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
        this.setupAnim(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
        if (this.young) {
            GlStateManager.pushMatrix();
            float f = 0.75F;
            GlStateManager.scalef(0.75F, 0.75F, 0.75F);
            GlStateManager.translatef(0.0F, 8.0F * p_78088_7_, 3.35F * p_78088_7_);
            this.head.render(p_78088_7_);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            float f1 = 0.5F;
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translatef(0.0F, 24.0F * p_78088_7_, 0.0F);
            this.body.render(p_78088_7_);
            this.leg0.render(p_78088_7_);
            this.leg1.render(p_78088_7_);
            this.leg2.render(p_78088_7_);
            this.leg3.render(p_78088_7_);
            GlStateManager.popMatrix();
        } else {
            GlStateManager.pushMatrix();
            this.head.render(p_78088_7_);
            this.body.render(p_78088_7_);
            this.leg0.render(p_78088_7_);
            this.leg1.render(p_78088_7_);
            this.leg2.render(p_78088_7_);
            this.leg3.render(p_78088_7_);
            GlStateManager.popMatrix();
        }

    }
}
