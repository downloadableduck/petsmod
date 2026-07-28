package com.jeff.pets.client.rendering.vanilla.enderdragon;

import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.util.math.MathHelper;

public class ClientEnderDragonModel extends EntityModel<ClientEnderDragon> {
    private final RendererModel head;
    private final RendererModel neck;
    private final RendererModel jaw;
    private final RendererModel body;
    private final RendererModel rearLeg;
    private final RendererModel frontLeg;
    private final RendererModel rearLegTip;
    private final RendererModel frontLegTip;
    private final RendererModel rearFoot;
    private final RendererModel frontFoot;
    private final RendererModel wing;
    private final RendererModel wingTip;
    private float a;

    public ClientEnderDragonModel(float p_i46360_1_) {
        this.texWidth = 256;
        this.texHeight = 256;
        float f = -16.0F;
        this.head = new RendererModel(this, "head");
        this.head.addBox("upperlip", -6.0F, -1.0F, -24.0F, 12, 5, 16, p_i46360_1_, 176, 44);
        this.head.addBox("upperhead", -8.0F, -8.0F, -10.0F, 16, 16, 16, p_i46360_1_, 112, 30);
        this.head.mirror = true;
        this.head.addBox("scale", -5.0F, -12.0F, -4.0F, 2, 4, 6, p_i46360_1_, 0, 0);
        this.head.addBox("nostril", -5.0F, -3.0F, -22.0F, 2, 2, 4, p_i46360_1_, 112, 0);
        this.head.mirror = false;
        this.head.addBox("scale", 3.0F, -12.0F, -4.0F, 2, 4, 6, p_i46360_1_, 0, 0);
        this.head.addBox("nostril", 3.0F, -3.0F, -22.0F, 2, 2, 4, p_i46360_1_, 112, 0);
        this.jaw = new RendererModel(this, "jaw");
        this.jaw.setPos(0.0F, 4.0F, -8.0F);
        this.jaw.addBox("jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16, p_i46360_1_, 176, 65);
        this.head.addChild(this.jaw);
        this.neck = new RendererModel(this, "neck");
        this.neck.addBox("box", -5.0F, -5.0F, -5.0F, 10, 10, 10, p_i46360_1_, 192, 104);
        this.neck.addBox("scale", -1.0F, -9.0F, -3.0F, 2, 4, 6, p_i46360_1_, 48, 0);
        this.body = new RendererModel(this, "body");
        this.body.setPos(0.0F, 4.0F, 8.0F);
        this.body.addBox("body", -12.0F, 0.0F, -16.0F, 24, 24, 64, p_i46360_1_, 0, 0);
        this.body.addBox("scale", -1.0F, -6.0F, -10.0F, 2, 6, 12, p_i46360_1_, 220, 53);
        this.body.addBox("scale", -1.0F, -6.0F, 10.0F, 2, 6, 12, p_i46360_1_, 220, 53);
        this.body.addBox("scale", -1.0F, -6.0F, 30.0F, 2, 6, 12, p_i46360_1_, 220, 53);
        this.wing = new RendererModel(this, "wing");
        this.wing.setPos(-12.0F, 5.0F, 2.0F);
        this.wing.addBox("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8, p_i46360_1_, 112, 88);
        this.wing.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56, p_i46360_1_, -56, 88);
        this.wingTip = new RendererModel(this, "wingtip");
        this.wingTip.setPos(-56.0F, 0.0F, 0.0F);
        this.wingTip.addBox("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4, p_i46360_1_, 112, 136);
        this.wingTip.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56, p_i46360_1_, -56, 144);
        this.wing.addChild(this.wingTip);
        this.frontLeg = new RendererModel(this, "frontleg");
        this.frontLeg.setPos(-12.0F, 20.0F, 2.0F);
        this.frontLeg.addBox("main", -4.0F, -4.0F, -4.0F, 8, 24, 8, p_i46360_1_, 112, 104);
        this.frontLegTip = new RendererModel(this, "frontlegtip");
        this.frontLegTip.setPos(0.0F, 20.0F, -1.0F);
        this.frontLegTip.addBox("main", -3.0F, -1.0F, -3.0F, 6, 24, 6, p_i46360_1_, 226, 138);
        this.frontLeg.addChild(this.frontLegTip);
        this.frontFoot = new RendererModel(this, "frontfoot");
        this.frontFoot.setPos(0.0F, 23.0F, 0.0F);
        this.frontFoot.addBox("main", -4.0F, 0.0F, -12.0F, 8, 4, 16, p_i46360_1_, 144, 104);
        this.frontLegTip.addChild(this.frontFoot);
        this.rearLeg = new RendererModel(this, "rearleg");
        this.rearLeg.setPos(-16.0F, 16.0F, 42.0F);
        this.rearLeg.addBox("main", -8.0F, -4.0F, -8.0F, 16, 32, 16, p_i46360_1_, 0, 0);
        this.rearLegTip = new RendererModel(this, "rearlegtip");
        this.rearLegTip.setPos(0.0F, 32.0F, -4.0F);
        this.rearLegTip.addBox("main", -6.0F, -2.0F, 0.0F, 12, 32, 12, p_i46360_1_, 196, 0);
        this.rearLeg.addChild(this.rearLegTip);
        this.rearFoot = new RendererModel(this, "rearfoot");
        this.rearFoot.setPos(0.0F, 31.0F, 4.0F);
        this.rearFoot.addBox("main", -9.0F, 0.0F, -20.0F, 18, 6, 24, p_i46360_1_, 112, 0);
        this.rearLegTip.addChild(this.rearFoot);
    }

    public void prepareMobModel(EnderDragonEntity p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
        this.a = p_212843_4_;
    }

    public void render(ClientEnderDragon p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        GlStateManager.pushMatrix();
        float f = MathHelper.lerp(this.a, p_78088_1_.oFlapTime, p_78088_1_.flapTime);
        this.jaw.xRot = (float)(Math.sin((double)(f * ((float)Math.PI * 2F))) + 1.0D) * 0.2F;
        float f1 = (float)(Math.sin((double)(f * ((float)Math.PI * 2F) - 1.0F)) + 1.0D);
        f1 = (f1 * f1 + f1 * 2.0F) * 0.05F;
        GlStateManager.translatef(0.0F, f1 - 2.0F, -3.0F);
        GlStateManager.rotatef(f1 * 2.0F, 1.0F, 0.0F, 0.0F);
        float f2 = 0.0F;
        float f3 = 20.0F;
        float f4 = -12.0F;
        float f5 = 1.5F;
        double[] adouble = p_78088_1_.getLatencyPos(6, this.a);
        float f6 = this.rotWrap(p_78088_1_.getLatencyPos(5, this.a)[0] - p_78088_1_.getLatencyPos(10, this.a)[0]);
        float f7 = this.rotWrap(p_78088_1_.getLatencyPos(5, this.a)[0] + (double)(f6 / 2.0F));
        float f8 = f * ((float)Math.PI * 2F);

        for(int i = 0; i < 5; ++i) {
            double[] adouble1 = p_78088_1_.getLatencyPos(5 - i, this.a);
            float f9 = (float)Math.cos((double)((float)i * 0.45F + f8)) * 0.15F;
            this.neck.yRot = this.rotWrap(adouble1[0] - adouble[0]) * ((float)Math.PI / 180F) * 1.5F;
            this.neck.xRot = f9 + p_78088_1_.getHeadPartYOffset(i, adouble, adouble1) * ((float)Math.PI / 180F) * 1.5F * 5.0F;
            this.neck.zRot = -this.rotWrap(adouble1[0] - (double)f7) * ((float)Math.PI / 180F) * 1.5F;
            this.neck.y = f3;
            this.neck.z = f4;
            this.neck.x = f2;
            f3 = (float)((double)f3 + Math.sin((double)this.neck.xRot) * 10.0D);
            f4 = (float)((double)f4 - Math.cos((double)this.neck.yRot) * Math.cos((double)this.neck.xRot) * 10.0D);
            f2 = (float)((double)f2 - Math.sin((double)this.neck.yRot) * Math.cos((double)this.neck.xRot) * 10.0D);
            this.neck.render(p_78088_7_);
        }

        this.head.y = f3;
        this.head.z = f4;
        this.head.x = f2;
        double[] adouble2 = p_78088_1_.getLatencyPos(0, this.a);
        this.head.yRot = this.rotWrap(adouble2[0] - adouble[0]) * ((float)Math.PI / 180F);
        this.head.xRot = this.rotWrap((double)p_78088_1_.getHeadPartYOffset(6, adouble, adouble2)) * ((float)Math.PI / 180F) * 1.5F * 5.0F;
        this.head.zRot = -this.rotWrap(adouble2[0] - (double)f7) * ((float)Math.PI / 180F);
        this.head.render(p_78088_7_);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(-f6 * 1.5F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translatef(0.0F, -1.0F, 0.0F);
        this.body.zRot = 0.0F;
        this.body.render(p_78088_7_);

        for(int j = 0; j < 2; ++j) {
            GlStateManager.enableCull();
            float f11 = f * ((float)Math.PI * 2F);
            this.wing.xRot = 0.125F - (float)Math.cos((double)f11) * 0.2F;
            this.wing.yRot = 0.25F;
            this.wing.zRot = (float)(Math.sin((double)f11) + 0.125D) * 0.8F;
            this.wingTip.zRot = -((float)(Math.sin((double)(f11 + 2.0F)) + 0.5D)) * 0.75F;
            this.rearLeg.xRot = 1.0F + f1 * 0.1F;
            this.rearLegTip.xRot = 0.5F + f1 * 0.1F;
            this.rearFoot.xRot = 0.75F + f1 * 0.1F;
            this.frontLeg.xRot = 1.3F + f1 * 0.1F;
            this.frontLegTip.xRot = -0.5F - f1 * 0.1F;
            this.frontFoot.xRot = 0.75F + f1 * 0.1F;
            this.wing.render(p_78088_7_);
            this.frontLeg.render(p_78088_7_);
            this.rearLeg.render(p_78088_7_);
            GlStateManager.scalef(-1.0F, 1.0F, 1.0F);
            if (j == 0) {
                GlStateManager.cullFace(GlStateManager.CullFace.FRONT);
            }
        }

        GlStateManager.popMatrix();
        GlStateManager.cullFace(GlStateManager.CullFace.BACK);
        GlStateManager.disableCull();
        float f10 = -((float)Math.sin((double)(f * ((float)Math.PI * 2F)))) * 0.0F;
        f8 = f * ((float)Math.PI * 2F);
        f3 = 10.0F;
        f4 = 60.0F;
        f2 = 0.0F;
        adouble = p_78088_1_.getLatencyPos(11, this.a);

        for(int k = 0; k < 12; ++k) {
            adouble2 = p_78088_1_.getLatencyPos(12 + k, this.a);
            f10 = (float)((double)f10 + Math.sin((double)((float)k * 0.45F + f8)) * (double)0.05F);
            this.neck.yRot = (this.rotWrap(adouble2[0] - adouble[0]) * 1.5F + 180.0F) * ((float)Math.PI / 180F);
            this.neck.xRot = f10 + (float)(adouble2[1] - adouble[1]) * ((float)Math.PI / 180F) * 1.5F * 5.0F;
            this.neck.zRot = this.rotWrap(adouble2[0] - (double)f7) * ((float)Math.PI / 180F) * 1.5F;
            this.neck.y = f3;
            this.neck.z = f4;
            this.neck.x = f2;
            f3 = (float)((double)f3 + Math.sin((double)this.neck.xRot) * 10.0D);
            f4 = (float)((double)f4 - Math.cos((double)this.neck.yRot) * Math.cos((double)this.neck.xRot) * 10.0D);
            f2 = (float)((double)f2 - Math.sin((double)this.neck.yRot) * Math.cos((double)this.neck.xRot) * 10.0D);
            this.neck.render(p_78088_7_);
        }

        GlStateManager.popMatrix();
    }

    private float rotWrap(double p_78214_1_) {
        while(p_78214_1_ >= 180.0D) {
            p_78214_1_ -= 360.0D;
        }

        while(p_78214_1_ < -180.0D) {
            p_78214_1_ += 360.0D;
        }

        return (float)p_78214_1_;
    }
}
