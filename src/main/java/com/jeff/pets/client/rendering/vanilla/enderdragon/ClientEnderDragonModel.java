//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.enderdragon;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import com.jeff.pets.client.Math2;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.compat.render.model.ModelPart;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;

public class ClientEnderDragonModel extends PetModel {
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
    private float delta;

    public ClientEnderDragonModel(float f) {
        this.textureWidth = 256;
        this.textureHeight = 256;
        float g = -16.0F;
        this.head = new ModelPart(this, "head");
        this.box(this.head, "upperlip", -6.0F, -1.0F, -24.0F, 12, 5, 16, f, 176, 44);
        this.box(this.head, "upperhead", -8.0F, -8.0F, -10.0F, 16, 16, 16, f, 112, 30);
        this.head.flipped = true;
        this.box(this.head, "applyScale", -5.0F, -12.0F, -4.0F, 2, 4, 6, f, 0, 0);
        this.box(this.head, "nostril", -5.0F, -3.0F, -22.0F, 2, 2, 4, f, 112, 0);
        this.head.flipped = false;
        this.box(this.head, "applyScale", 3.0F, -12.0F, -4.0F, 2, 4, 6, f, 0, 0);
        this.box(this.head, "nostril", 3.0F, -3.0F, -22.0F, 2, 2, 4, f, 112, 0);
        this.jaw = new ModelPart(this, "jaw");
        this.jaw.setPos(0.0F, 4.0F, -8.0F);
        this.box(this.jaw, "jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16, f, 176, 65);
        this.head.addChild(this.jaw);
        this.neck = new ModelPart(this, "neck");
        this.box(this.neck, "box", -5.0F, -5.0F, -5.0F, 10, 10, 10, f, 192, 104);
        this.box(this.neck, "applyScale", -1.0F, -9.0F, -3.0F, 2, 4, 6, f, 48, 0);
        this.body = new ModelPart(this, "body");
        this.body.setPos(0.0F, 4.0F, 8.0F);
        this.box(this.body, "body", -12.0F, 0.0F, -16.0F, 24, 24, 64, f, 0, 0);
        this.box(this.body, "applyScale", -1.0F, -6.0F, -10.0F, 2, 6, 12, f, 220, 53);
        this.box(this.body, "applyScale", -1.0F, -6.0F, 10.0F, 2, 6, 12, f, 220, 53);
        this.box(this.body, "applyScale", -1.0F, -6.0F, 30.0F, 2, 6, 12, f, 220, 53);
        this.wing = new ModelPart(this, "wing");
        this.wing.setPos(-12.0F, 5.0F, 2.0F);
        this.box(this.wing, "bone", -56.0F, -4.0F, -4.0F, 56, 8, 8, f, 112, 88);
        this.box(this.wing, "skin", -56.0F, 0.0F, 2.0F, 56, 0, 56, f, -56, 88);
        this.wingTip = new ModelPart(this, "wingtip");
        this.wingTip.setPos(-56.0F, 0.0F, 0.0F);
        this.box(this.wingTip, "bone", -56.0F, -2.0F, -2.0F, 56, 4, 4, f, 112, 136);
        this.box(this.wingTip, "skin", -56.0F, 0.0F, 2.0F, 56, 0, 56, f, -56, 144);
        this.wing.addChild(this.wingTip);
        this.frontLeg = new ModelPart(this, "frontleg");
        this.frontLeg.setPos(-12.0F, 20.0F, 2.0F);
        this.box(this.frontLeg, "main", -4.0F, -4.0F, -4.0F, 8, 24, 8, f, 112, 104);
        this.frontLegTip = new ModelPart(this, "frontlegtip");
        this.frontLegTip.setPos(0.0F, 20.0F, -1.0F);
        this.box(this.frontLegTip, "main", -3.0F, -1.0F, -3.0F, 6, 24, 6, f, 226, 138);
        this.frontLeg.addChild(this.frontLegTip);
        this.frontFoot = new ModelPart(this, "frontfoot");
        this.frontFoot.setPos(0.0F, 23.0F, 0.0F);
        this.box(this.frontFoot, "main", -4.0F, 0.0F, -12.0F, 8, 4, 16, f, 144, 104);
        this.frontLegTip.addChild(this.frontFoot);
        this.rearLeg = new ModelPart(this, "rearleg");
        this.rearLeg.setPos(-16.0F, 16.0F, 42.0F);
        this.box(this.rearLeg, "main", -8.0F, -4.0F, -8.0F, 16, 32, 16, f, 0, 0);
        this.rearLegTip = new ModelPart(this, "rearlegtip");
        this.rearLegTip.setPos(0.0F, 32.0F, -4.0F);
        this.box(this.rearLegTip, "main", -6.0F, -2.0F, 0.0F, 12, 32, 12, f, 196, 0);
        this.rearLeg.addChild(this.rearLegTip);
        this.rearFoot = new ModelPart(this, "rearfoot");
        this.rearFoot.setPos(0.0F, 31.0F, 4.0F);
        this.box(this.rearFoot, "main", -9.0F, 0.0F, -20.0F, 18, 6, 24, f, 112, 0);
        this.rearLegTip.addChild(this.rearFoot);
    }

    private void box(ModelPart part, String name, float x, float y, float z, int w, int h, int d, float scale, int u, int v) {
        part.setTextureCoords(u, v);
        part.addBox(x, y, z, w, h, d, scale);
    }

    public void animateModel(Mob entity, float f, float g, float h) {
        this.delta = h;
    }

    public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
        ClientEnderDragon enderDragonEntity = (ClientEnderDragon)entity;
        GlStateManager.pushMatrix();
        float l = Math2.lerp(this.delta, enderDragonEntity.oFlapTime, enderDragonEntity.flapTime);
        this.jaw.rotationX = (float)(Math.sin((double)(l * 6.2831855F)) + 1.0) * 0.2F;
        float m = (float)(Math.sin((double)(l * 6.2831855F - 1.0F)) + 1.0);
        m = (m * m + m * 2.0F) * 0.05F;
        GlStateManager.translatef(0.0F, m - 2.0F, -3.0F);
        GlStateManager.rotatef(m * 2.0F, 1.0F, 0.0F, 0.0F);
        float n = 0.0F;
        float o = 20.0F;
        float p = -12.0F;
        float q = 1.5F;
        double[] ds = enderDragonEntity.getLatencyPos(6, this.delta);
        float r = this.updateRotations(enderDragonEntity.getLatencyPos(5, this.delta)[0] - enderDragonEntity.getLatencyPos(10, this.delta)[0]);
        float s = this.updateRotations(enderDragonEntity.getLatencyPos(5, this.delta)[0] + (double)(r / 2.0F));
        float t = l * 6.2831855F;

        float v;
        for(int u = 0; u < 5; ++u) {
            double[] es = enderDragonEntity.getLatencyPos(5 - u, this.delta);
            v = (float)Math.cos((double)((float)u * 0.45F + t)) * 0.15F;
            this.neck.rotationY = this.updateRotations(es[0] - ds[0]) * 0.017453292F * 1.5F;
            this.neck.rotationX = v + enderDragonEntity.getHeadPartYOffset(u, ds, es) * 0.017453292F * 1.5F * 5.0F;
            this.neck.rotationZ = -this.updateRotations(es[0] - (double)s) * 0.017453292F * 1.5F;
            this.neck.y = o;
            this.neck.z = p;
            this.neck.x = n;
            o = (float)((double)o + Math.sin((double)this.neck.rotationX) * 10.0);
            p = (float)((double)p - Math.cos((double)this.neck.rotationY) * Math.cos((double)this.neck.rotationX) * 10.0);
            n = (float)((double)n - Math.sin((double)this.neck.rotationY) * Math.cos((double)this.neck.rotationX) * 10.0);
            this.neck.render(k);
        }

        this.head.y = o;
        this.head.z = p;
        this.head.x = n;
        double[] fs = enderDragonEntity.getLatencyPos(0, this.delta);
        this.head.rotationY = this.updateRotations(fs[0] - ds[0]) * 0.017453292F;
        this.head.rotationX = this.updateRotations((double)enderDragonEntity.getHeadPartYOffset(6, ds, fs)) * 0.017453292F * 1.5F * 5.0F;
        this.head.rotationZ = -this.updateRotations(fs[0] - (double)s) * 0.017453292F;
        this.head.render(k);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(-r * 1.5F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translatef(0.0F, -1.0F, 0.0F);
        this.body.rotationZ = 0.0F;
        this.body.render(k);

        for(int w = 0; w < 2; ++w) {
            GlStateManager.enableCull();
            v = l * 6.2831855F;
            this.wing.rotationX = 0.125F - (float)Math.cos((double)v) * 0.2F;
            this.wing.rotationY = 0.25F;
            this.wing.rotationZ = (float)(Math.sin((double)v) + 0.125) * 0.8F;
            this.wingTip.rotationZ = -((float)(Math.sin((double)(v + 2.0F)) + 0.5)) * 0.75F;
            this.rearLeg.rotationX = 1.0F + m * 0.1F;
            this.rearLegTip.rotationX = 0.5F + m * 0.1F;
            this.rearFoot.rotationX = 0.75F + m * 0.1F;
            this.frontLeg.rotationX = 1.3F + m * 0.1F;
            this.frontLegTip.rotationX = -0.5F - m * 0.1F;
            this.frontFoot.rotationX = 0.75F + m * 0.1F;
            this.wing.render(k);
            this.frontLeg.render(k);
            this.rearLeg.render(k);
            GlStateManager.scalef(-1.0F, 1.0F, 1.0F);
            if (w == 0) {
                GlStateManager.cullFace(1029);
            }
        }

        GlStateManager.popMatrix();
        GlStateManager.cullFace(1028);
        GlStateManager.disableCull();
        float x = -((float)Math.sin((double)(l * 6.2831855F))) * 0.0F;
        t = l * 6.2831855F;
        o = 10.0F;
        p = 60.0F;
        n = 0.0F;
        ds = enderDragonEntity.getLatencyPos(11, this.delta);

        for(int y = 0; y < 12; ++y) {
            fs = enderDragonEntity.getLatencyPos(12 + y, this.delta);
            x = (float)((double)x + Math.sin((double)((float)y * 0.45F + t)) * 0.05000000074505806);
            this.neck.rotationY = (this.updateRotations(fs[0] - ds[0]) * 1.5F + 180.0F) * 0.017453292F;
            this.neck.rotationX = x + (float)(fs[1] - ds[1]) * 0.017453292F * 1.5F * 5.0F;
            this.neck.rotationZ = this.updateRotations(fs[0] - (double)s) * 0.017453292F * 1.5F;
            this.neck.y = o;
            this.neck.z = p;
            this.neck.x = n;
            o = (float)((double)o + Math.sin((double)this.neck.rotationX) * 10.0);
            p = (float)((double)p - Math.cos((double)this.neck.rotationY) * Math.cos((double)this.neck.rotationX) * 10.0);
            n = (float)((double)n - Math.sin((double)this.neck.rotationY) * Math.cos((double)this.neck.rotationX) * 10.0);
            this.neck.render(k);
        }

        GlStateManager.popMatrix();
    }

    private float updateRotations(double d) {
        while(d >= 180.0) {
            d -= 360.0;
        }

        while(d < -180.0) {
            d += 360.0;
        }

        return (float)d;
    }
}
