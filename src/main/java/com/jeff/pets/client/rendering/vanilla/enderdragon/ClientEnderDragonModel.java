package com.jeff.pets.client.rendering.vanilla.enderdragon;

import com.jeff.pets.client.Math2;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ClientEnderDragonModel extends ModelBase {
    private final ModelRenderer head;
    private final ModelRenderer neck;
    private final ModelRenderer jaw;
    private final ModelRenderer body;
    private final ModelRenderer rearLeg;
    private final ModelRenderer frontLeg;
    private final ModelRenderer rearLegTip;
    private final ModelRenderer frontLegTip;
    private final ModelRenderer rearFoot;
    private final ModelRenderer frontFoot;
    private final ModelRenderer wing;
    private final ModelRenderer wingTip;
    private float a;

    public ClientEnderDragonModel(float p_i46360_1_) {
        this.textureWidth = 256;
        this.textureHeight = 256;
        float f = -16.0F;
        this.head = new ModelRenderer(this, "head");
        this.head.setTextureOffset(176, 44).addBox(-6.0F, -1.0F, -24.0F, 12, 5, 16, p_i46360_1_);
        this.head.setTextureOffset(112, 30).addBox(-8.0F, -8.0F, -10.0F, 16, 16, 16, p_i46360_1_);
        this.head.mirror = true;
        this.head.setTextureOffset(0, 0).addBox(-5.0F, -12.0F, -4.0F, 2, 4, 6, p_i46360_1_);
        this.head.setTextureOffset(112, 0).addBox(-5.0F, -3.0F, -22.0F, 2, 2, 4, p_i46360_1_);
        this.head.mirror = false;
        this.head.setTextureOffset(0, 0).addBox(3.0F, -12.0F, -4.0F, 2, 4, 6, p_i46360_1_);
        this.head.setTextureOffset(112, 0).addBox(3.0F, -3.0F, -22.0F, 2, 2, 4, p_i46360_1_);
        this.jaw = new ModelRenderer(this, "jaw");
        this.jaw.setRotationPoint(0.0F, 4.0F, -8.0F);
        this.jaw.setTextureOffset(176, 65).addBox(-6.0F, 0.0F, -16.0F, 12, 4, 16, p_i46360_1_);
        this.head.addChild(this.jaw);
        this.neck = new ModelRenderer(this, "neck");
        this.neck.setTextureOffset(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10, 10, 10, p_i46360_1_);
        this.neck.setTextureOffset(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2, 4, 6, p_i46360_1_);
        this.body = new ModelRenderer(this, "body");
        this.body.setRotationPoint(0.0F, 4.0F, 8.0F);
        this.body.setTextureOffset(0, 0).addBox(-12.0F, 0.0F, -16.0F, 24, 24, 64, p_i46360_1_);
        this.body.setTextureOffset(220, 53).addBox(-1.0F, -6.0F, -10.0F, 2, 6, 12, p_i46360_1_);
        this.body.setTextureOffset(220, 53).addBox(-1.0F, -6.0F, 10.0F, 2, 6, 12, p_i46360_1_);
        this.body.setTextureOffset(220, 53).addBox(-1.0F, -6.0F, 30.0F, 2, 6, 12, p_i46360_1_);
        this.wing = new ModelRenderer(this, "wing");
        this.wing.setRotationPoint(-12.0F, 5.0F, 2.0F);
        this.wing.setTextureOffset(112, 88).addBox(-56.0F, -4.0F, -4.0F, 56, 8, 8, p_i46360_1_);
        this.wing.setTextureOffset(-56, 88).addBox(-56.0F, 0.0F, 2.0F, 56, 0, 56, p_i46360_1_);
        this.wingTip = new ModelRenderer(this, "wingtip");
        this.wingTip.setRotationPoint(-56.0F, 0.0F, 0.0F);
        this.wingTip.setTextureOffset(112, 136).addBox(-56.0F, -2.0F, -2.0F, 56, 4, 4, p_i46360_1_);
        this.wingTip.setTextureOffset(-56, 144).addBox(-56.0F, 0.0F, 2.0F, 56, 0, 56, p_i46360_1_);
        this.wing.addChild(this.wingTip);
        this.frontLeg = new ModelRenderer(this, "frontleg");
        this.frontLeg.setRotationPoint(-12.0F, 20.0F, 2.0F);
        this.frontLeg.setTextureOffset(112, 104).addBox(-4.0F, -4.0F, -4.0F, 8, 24, 8, p_i46360_1_);
        this.frontLegTip = new ModelRenderer(this, "frontlegtip");
        this.frontLegTip.setRotationPoint(0.0F, 20.0F, -1.0F);
        this.frontLegTip.setTextureOffset(226, 138).addBox(-3.0F, -1.0F, -3.0F, 6, 24, 6, p_i46360_1_);
        this.frontLeg.addChild(this.frontLegTip);
        this.frontFoot = new ModelRenderer(this, "frontfoot");
        this.frontFoot.setRotationPoint(0.0F, 23.0F, 0.0F);
        this.frontFoot.setTextureOffset(144, 104).addBox(-4.0F, 0.0F, -12.0F, 8, 4, 16, p_i46360_1_);
        this.frontLegTip.addChild(this.frontFoot);
        this.rearLeg = new ModelRenderer(this, "rearleg");
        this.rearLeg.setRotationPoint(-16.0F, 16.0F, 42.0F);
        this.rearLeg.setTextureOffset(0, 0).addBox(-8.0F, -4.0F, -8.0F, 16, 32, 16, p_i46360_1_);
        this.rearLegTip = new ModelRenderer(this, "rearlegtip");
        this.rearLegTip.setRotationPoint(0.0F, 32.0F, -4.0F);
        this.rearLegTip.setTextureOffset(196, 0).addBox(-6.0F, -2.0F, 0.0F, 12, 32, 12, p_i46360_1_);
        this.rearLeg.addChild(this.rearLegTip);
        this.rearFoot = new ModelRenderer(this, "rearfoot");
        this.rearFoot.setRotationPoint(0.0F, 31.0F, 4.0F);
        this.rearFoot.setTextureOffset(112, 0).addBox(-9.0F, 0.0F, -20.0F, 18, 6, 24, p_i46360_1_);
        this.rearLegTip.addChild(this.rearFoot);
    }

    public void setLivingAnimations(ClientEnderDragon p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
        this.a = p_212843_4_;
    }

    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        ClientEnderDragon dragon = (ClientEnderDragon) p_78088_1_;
        GlStateManager.pushMatrix();
        float f = Math2.lerp(this.a, dragon.oFlapTime, dragon.flapTime);
        this.jaw.rotateAngleX = (float) (Math.sin(f * ((float) Math.PI * 2F)) + 1.0D) * 0.2F;
        float f1 = (float) (Math.sin(f * ((float) Math.PI * 2F) - 1.0F) + 1.0D);
        f1 = (f1 * f1 + f1 * 2.0F) * 0.05F;
        GlStateManager.translatef(0.0F, f1 - 2.0F, -3.0F);
        GlStateManager.rotatef(f1 * 2.0F, 1.0F, 0.0F, 0.0F);
        float f2 = 0.0F;
        float f3 = 20.0F;
        float f4 = -12.0F;
        float f5 = 1.5F;
        double[] adouble = dragon.getLatencyPos(6, this.a);
        float f6 = this.rotWrap(dragon.getLatencyPos(5, this.a)[0] - dragon.getLatencyPos(10, this.a)[0]);
        float f7 = this.rotWrap(dragon.getLatencyPos(5, this.a)[0] + (double) (f6 / 2.0F));
        float f8 = f * ((float) Math.PI * 2F);

        for (int i = 0; i < 5; ++i) {
            double[] adouble1 = dragon.getLatencyPos(5 - i, this.a);
            float f9 = (float) Math.cos((float) i * 0.45F + f8) * 0.15F;
            this.neck.rotateAngleY = this.rotWrap(adouble1[0] - adouble[0]) * ((float) Math.PI / 180F) * 1.5F;
            this.neck.rotateAngleX = f9 + dragon.getHeadPartYOffset(i, adouble, adouble1) * ((float) Math.PI / 180F) * 1.5F * 5.0F;
            this.neck.rotateAngleZ = -this.rotWrap(adouble1[0] - (double) f7) * ((float) Math.PI / 180F) * 1.5F;
            this.neck.rotationPointY = f3;
            this.neck.rotationPointZ = f4;
            this.neck.rotationPointX = f2;
            f3 = (float) ((double) f3 + Math.sin(this.neck.rotateAngleX) * 10.0D);
            f4 = (float) ((double) f4 - Math.cos(this.neck.rotateAngleY) * Math.cos(this.neck.rotateAngleX) * 10.0D);
            f2 = (float) ((double) f2 - Math.sin(this.neck.rotateAngleY) * Math.cos(this.neck.rotateAngleX) * 10.0D);
            this.neck.render(p_78088_7_);
        }

        this.head.rotationPointY = f3;
        this.head.rotationPointZ = f4;
        this.head.rotationPointX = f2;
        double[] adouble2 = dragon.getLatencyPos(0, this.a);
        this.head.rotateAngleY = this.rotWrap(adouble2[0] - adouble[0]) * ((float) Math.PI / 180F);
        this.head.rotateAngleX = this.rotWrap(dragon.getHeadPartYOffset(6, adouble, adouble2)) * ((float) Math.PI / 180F) * 1.5F * 5.0F;
        this.head.rotateAngleZ = -this.rotWrap(adouble2[0] - (double) f7) * ((float) Math.PI / 180F);
        this.head.render(p_78088_7_);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(-f6 * 1.5F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translatef(0.0F, -1.0F, 0.0F);
        this.body.rotateAngleZ = 0.0F;
        this.body.render(p_78088_7_);

        for (int j = 0; j < 2; ++j) {
            GlStateManager.enableCull();
            float f11 = f * ((float) Math.PI * 2F);
            this.wing.rotateAngleX = 0.125F - (float) Math.cos(f11) * 0.2F;
            this.wing.rotateAngleY = 0.25F;
            this.wing.rotateAngleZ = (float) (Math.sin(f11) + 0.125D) * 0.8F;
            this.wingTip.rotateAngleZ = -((float) (Math.sin(f11 + 2.0F) + 0.5D)) * 0.75F;
            this.rearLeg.rotateAngleX = 1.0F + f1 * 0.1F;
            this.rearLegTip.rotateAngleX = 0.5F + f1 * 0.1F;
            this.rearFoot.rotateAngleX = 0.75F + f1 * 0.1F;
            this.frontLeg.rotateAngleX = 1.3F + f1 * 0.1F;
            this.frontLegTip.rotateAngleX = -0.5F - f1 * 0.1F;
            this.frontFoot.rotateAngleX = 0.75F + f1 * 0.1F;
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
        float f10 = -((float) Math.sin(f * ((float) Math.PI * 2F))) * 0.0F;
        f8 = f * ((float) Math.PI * 2F);
        f3 = 10.0F;
        f4 = 60.0F;
        f2 = 0.0F;
        adouble = dragon.getLatencyPos(11, this.a);

        for (int k = 0; k < 12; ++k) {
            adouble2 = dragon.getLatencyPos(12 + k, this.a);
            f10 = (float) ((double) f10 + Math.sin((float) k * 0.45F + f8) * (double) 0.05F);
            this.neck.rotateAngleY = (this.rotWrap(adouble2[0] - adouble[0]) * 1.5F + 180.0F) * ((float) Math.PI / 180F);
            this.neck.rotateAngleX = f10 + (float) (adouble2[1] - adouble[1]) * ((float) Math.PI / 180F) * 1.5F * 5.0F;
            this.neck.rotateAngleZ = this.rotWrap(adouble2[0] - (double) f7) * ((float) Math.PI / 180F) * 1.5F;
            this.neck.rotationPointY = f3;
            this.neck.rotationPointZ = f4;
            this.neck.rotationPointX = f2;
            f3 = (float) ((double) f3 + Math.sin(this.neck.rotateAngleX) * 10.0D);
            f4 = (float) ((double) f4 - Math.cos(this.neck.rotateAngleY) * Math.cos(this.neck.rotateAngleX) * 10.0D);
            f2 = (float) ((double) f2 - Math.sin(this.neck.rotateAngleY) * Math.cos(this.neck.rotateAngleX) * 10.0D);
            this.neck.render(p_78088_7_);
        }

        GlStateManager.popMatrix();
    }

    private float rotWrap(double p_78214_1_) {
        while (p_78214_1_ >= 180.0D) {
            p_78214_1_ -= 360.0D;
        }

        while (p_78214_1_ < -180.0D) {
            p_78214_1_ += 360.0D;
        }

        return (float) p_78214_1_;
    }
}
