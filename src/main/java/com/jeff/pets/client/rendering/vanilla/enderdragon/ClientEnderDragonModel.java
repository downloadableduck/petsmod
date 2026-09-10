package com.jeff.pets.client.rendering.vanilla.enderdragon;

import com.jeff.pets.client.Math2;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;

import static com.mojang.blaze3d.platform.GlStateManager.class_2865.FRONT;

public class ClientEnderDragonModel extends EntityModel {
    private final ModelPart head;
    private final ModelPart neck;
    private final ModelPart jaw;
    private final ModelPart body;
    private final ModelPart rearLeg;
    private final ModelPart frontLeg;
    private final ModelPart rearLegTip;
    private final ModelPart frontLegTip;
    private final ModelPart rearFoot;
    private final ModelPart frontFoot;
    private final ModelPart wing;
    private final ModelPart wingTip;
    private float a;

    public ClientEnderDragonModel(float p_i46360_1_) {
        this.textureWidth = 256;
        this.textureHeight = 256;
        float f = -16.0F;
        this.head = new ModelPart(this, "head");
        this.head.setTextureOffset(176, 44).addCuboid(-6.0F, -1.0F, -24.0F, 12, 5, 16, p_i46360_1_);
        this.head.setTextureOffset(112, 30).addCuboid(-8.0F, -8.0F, -10.0F, 16, 16, 16, p_i46360_1_);
        this.head.mirror = true;
        this.head.setTextureOffset(0, 0).addCuboid(-5.0F, -12.0F, -4.0F, 2, 4, 6, p_i46360_1_);
        this.head.setTextureOffset(112, 0).addCuboid(-5.0F, -3.0F, -22.0F, 2, 2, 4, p_i46360_1_);
        this.head.mirror = false;
        this.head.setTextureOffset(0, 0).addCuboid(3.0F, -12.0F, -4.0F, 2, 4, 6, p_i46360_1_);
        this.head.setTextureOffset(112, 0).addCuboid(3.0F, -3.0F, -22.0F, 2, 2, 4, p_i46360_1_);
        this.jaw = new ModelPart(this, "jaw");
        this.jaw.setPivot(0.0F, 4.0F, -8.0F);
        this.jaw.setTextureOffset(176, 65).addCuboid(-6.0F, 0.0F, -16.0F, 12, 4, 16, p_i46360_1_);
        this.head.add(this.jaw);
        this.neck = new ModelPart(this, "neck");
        this.neck.setTextureOffset(192, 104).addCuboid(-5.0F, -5.0F, -5.0F, 10, 10, 10, p_i46360_1_);
        this.neck.setTextureOffset(48, 0).addCuboid(-1.0F, -9.0F, -3.0F, 2, 4, 6, p_i46360_1_);
        this.body = new ModelPart(this, "body");
        this.body.setPivot(0.0F, 4.0F, 8.0F);
        this.body.setTextureOffset(0, 0).addCuboid(-12.0F, 0.0F, -16.0F, 24, 24, 64, p_i46360_1_);
        this.body.setTextureOffset(220, 53).addCuboid(-1.0F, -6.0F, -10.0F, 2, 6, 12, p_i46360_1_);
        this.body.setTextureOffset(220, 53).addCuboid(-1.0F, -6.0F, 10.0F, 2, 6, 12, p_i46360_1_);
        this.body.setTextureOffset(220, 53).addCuboid(-1.0F, -6.0F, 30.0F, 2, 6, 12, p_i46360_1_);
        this.wing = new ModelPart(this, "wing");
        this.wing.setPivot(-12.0F, 5.0F, 2.0F);
        this.wing.setTextureOffset(112, 88).addCuboid(-56.0F, -4.0F, -4.0F, 56, 8, 8, p_i46360_1_);
        this.wing.setTextureOffset(-56, 88).addCuboid(-56.0F, 0.0F, 2.0F, 56, 0, 56, p_i46360_1_);
        this.wingTip = new ModelPart(this, "wingtip");
        this.wingTip.setPivot(-56.0F, 0.0F, 0.0F);
        this.wingTip.setTextureOffset(112, 136).addCuboid(-56.0F, -2.0F, -2.0F, 56, 4, 4, p_i46360_1_);
        this.wingTip.setTextureOffset(-56, 144).addCuboid(-56.0F, 0.0F, 2.0F, 56, 0, 56, p_i46360_1_);
        this.wing.add(this.wingTip);
        this.frontLeg = new ModelPart(this, "frontleg");
        this.frontLeg.setPivot(-12.0F, 20.0F, 2.0F);
        this.frontLeg.setTextureOffset(112, 104).addCuboid(-4.0F, -4.0F, -4.0F, 8, 24, 8, p_i46360_1_);
        this.frontLegTip = new ModelPart(this, "frontlegtip");
        this.frontLegTip.setPivot(0.0F, 20.0F, -1.0F);
        this.frontLegTip.setTextureOffset(226, 138).addCuboid(-3.0F, -1.0F, -3.0F, 6, 24, 6, p_i46360_1_);
        this.frontLeg.add(this.frontLegTip);
        this.frontFoot = new ModelPart(this, "frontfoot");
        this.frontFoot.setPivot(0.0F, 23.0F, 0.0F);
        this.frontFoot.setTextureOffset(144, 104).addCuboid(-4.0F, 0.0F, -12.0F, 8, 4, 16, p_i46360_1_);
        this.frontLegTip.add(this.frontFoot);
        this.rearLeg = new ModelPart(this, "rearleg");
        this.rearLeg.setPivot(-16.0F, 16.0F, 42.0F);
        this.rearLeg.setTextureOffset(0, 0).addCuboid(-8.0F, -4.0F, -8.0F, 16, 32, 16, p_i46360_1_);
        this.rearLegTip = new ModelPart(this, "rearlegtip");
        this.rearLegTip.setPivot(0.0F, 32.0F, -4.0F);
        this.rearLegTip.setTextureOffset(196, 0).addCuboid(-6.0F, -2.0F, 0.0F, 12, 32, 12, p_i46360_1_);
        this.rearLeg.add(this.rearLegTip);
        this.rearFoot = new ModelPart(this, "rearfoot");
        this.rearFoot.setPivot(0.0F, 31.0F, 4.0F);
        this.rearFoot.setTextureOffset(112, 0).addCuboid(-9.0F, 0.0F, -20.0F, 18, 6, 24, p_i46360_1_);
        this.rearLegTip.add(this.rearFoot);
    }

    public void animateModel(ClientEnderDragon p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
        this.a = p_212843_4_;
    }

    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        ClientEnderDragon dragon = (ClientEnderDragon) p_78088_1_;
        GlStateManager.pushMatrix();
        float f = Math2.lerp(this.a, dragon.oFlapTime, dragon.flapTime);
        this.jaw.posX = (float) (Math.sin(f * ((float) Math.PI * 2F)) + 1.0D) * 0.2F;
        float f1 = (float) (Math.sin(f * ((float) Math.PI * 2F) - 1.0F) + 1.0D);
        f1 = (f1 * f1 + f1 * 2.0F) * 0.05F;
        GlStateManager.translate(0.0F, f1 - 2.0F, -3.0F);
        GlStateManager.rotate(f1 * 2.0F, 1.0F, 0.0F, 0.0F);
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
            this.neck.posY = this.rotWrap(adouble1[0] - adouble[0]) * ((float) Math.PI / 180F) * 1.5F;
            this.neck.posX = f9 + dragon.getHeadPartYOffset(i, adouble, adouble1) * ((float) Math.PI / 180F) * 1.5F * 5.0F;
            this.neck.posZ = -this.rotWrap(adouble1[0] - (double) f7) * ((float) Math.PI / 180F) * 1.5F;
            this.neck.pivotY = f3;
            this.neck.pivotZ = f4;
            this.neck.pivotX = f2;
            f3 = (float) ((double) f3 + Math.sin(this.neck.posX) * 10.0D);
            f4 = (float) ((double) f4 - Math.cos(this.neck.posY) * Math.cos(this.neck.posX) * 10.0D);
            f2 = (float) ((double) f2 - Math.sin(this.neck.posY) * Math.cos(this.neck.posX) * 10.0D);
            this.neck.render(p_78088_7_);
        }

        this.head.pivotY = f3;
        this.head.pivotZ = f4;
        this.head.pivotX = f2;
        double[] adouble2 = dragon.getLatencyPos(0, this.a);
        this.head.posY = this.rotWrap(adouble2[0] - adouble[0]) * ((float) Math.PI / 180F);
        this.head.posX = this.rotWrap(dragon.getHeadPartYOffset(6, adouble, adouble2)) * ((float) Math.PI / 180F) * 1.5F * 5.0F;
        this.head.posZ = -this.rotWrap(adouble2[0] - (double) f7) * ((float) Math.PI / 180F);
        this.head.render(p_78088_7_);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-f6 * 1.5F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(0.0F, -1.0F, 0.0F);
        this.body.posZ = 0.0F;
        this.body.render(p_78088_7_);

        for (int j = 0; j < 2; ++j) {
            GlStateManager.enableCull();
            float f11 = f * ((float) Math.PI * 2F);
            this.wing.posX = 0.125F - (float) Math.cos(f11) * 0.2F;
            this.wing.posY = 0.25F;
            this.wing.posZ = (float) (Math.sin(f11) + 0.125D) * 0.8F;
            this.wingTip.posZ = -((float) (Math.sin(f11 + 2.0F) + 0.5D)) * 0.75F;
            this.rearLeg.posX = 1.0F + f1 * 0.1F;
            this.rearLegTip.posX = 0.5F + f1 * 0.1F;
            this.rearFoot.posX = 0.75F + f1 * 0.1F;
            this.frontLeg.posX = 1.3F + f1 * 0.1F;
            this.frontLegTip.posX = -0.5F - f1 * 0.1F;
            this.frontFoot.posX = 0.75F + f1 * 0.1F;
            this.wing.render(p_78088_7_);
            this.frontLeg.render(p_78088_7_);
            this.rearLeg.render(p_78088_7_);
            GlStateManager.scale(-1.0F, 1.0F, 1.0F);
            if (j == 0) {
                GlStateManager.method_12284(FRONT);
            }
        }

        GlStateManager.popMatrix();
        GlStateManager.method_12284(GlStateManager.class_2865.BACK);
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
            this.neck.posY = (this.rotWrap(adouble2[0] - adouble[0]) * 1.5F + 180.0F) * ((float) Math.PI / 180F);
            this.neck.posX = f10 + (float) (adouble2[1] - adouble[1]) * ((float) Math.PI / 180F) * 1.5F * 5.0F;
            this.neck.posZ = this.rotWrap(adouble2[0] - (double) f7) * ((float) Math.PI / 180F) * 1.5F;
            this.neck.pivotY = f3;
            this.neck.pivotZ = f4;
            this.neck.pivotX = f2;
            f3 = (float) ((double) f3 + Math.sin(this.neck.posX) * 10.0D);
            f4 = (float) ((double) f4 - Math.cos(this.neck.posY) * Math.cos(this.neck.posX) * 10.0D);
            f2 = (float) ((double) f2 - Math.sin(this.neck.posY) * Math.cos(this.neck.posX) * 10.0D);
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
