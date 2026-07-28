package com.jeff.pets.client.rendering.vanilla.ravager;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.vanilla.hostile.ClientRavager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RavagerModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.monster.RavagerEntity;

public class ClientRavagerModel extends EntityModel<ClientRavager> {
    private final RendererModel head;
    private final RendererModel mouth;
    private final RendererModel body;
    private final RendererModel leg0;
    private final RendererModel leg1;
    private final RendererModel leg2;
    private final RendererModel leg3;
    private final RendererModel neck;

    public ClientRavagerModel() {
        this.texWidth = 128;
        this.texHeight = 128;
        int i = 16;
        float f = 0.0F;
        this.neck = new RendererModel(this);
        this.neck.setPos(0.0F, -7.0F, -1.5F);
        this.neck.texOffs(68, 73).addBox(-5.0F, -1.0F, -18.0F, (int) 10.0, (int) 10.0, (int) 18.0, 0.0F);
        this.head = new RendererModel(this);
        this.head.setPos(0.0F, 16.0F, -17.0F);
        this.head.texOffs(0, 0).addBox(-8.0F, -20.0F, -14.0F, (int) 16.0, (int) 20.0, (int) 16.0, 0.0F);
        this.head.texOffs(0, 0).addBox(-2.0F, -6.0F, -18.0F, (int) 4.0, (int) 8.0, (int) 4.0, 0.0F);
        RendererModel modelPart = new RendererModel(this);
        modelPart.setPos(-10.0F, -14.0F, -8.0F);
        modelPart.texOffs(74, 55).addBox(0.0F, -14.0F, -2.0F, (int) 2.0, (int) 14.0, (int) 4.0, 0.0F);
        modelPart.xRot = 1.0995574F;
        this.head.addChild(modelPart);
        RendererModel modelPart2 = new RendererModel(this);
        modelPart2.mirror = true;
        modelPart2.setPos(8.0F, -14.0F, -8.0F);
        modelPart2.texOffs(74, 55).addBox(0.0F, -14.0F, -2.0F, (int) 2.0, (int) 14.0, (int) 4.0, 0.0F);
        modelPart2.xRot = 1.0995574F;
        this.head.addChild(modelPart2);
        this.mouth = new RendererModel(this);
        this.mouth.setPos(0.0F, -2.0F, 2.0F);
        this.mouth.texOffs(0, 36).addBox(-8.0F, 0.0F, -16.0F, (int) 16.0, (int) 3.0, (int) 16.0, 0.0F);
        this.head.addChild(this.mouth);
        this.neck.addChild(this.head);
        this.body = new RendererModel(this);
        this.body.texOffs(0, 55).addBox(-7.0F, -10.0F, -7.0F, (int) 14.0, (int) 16.0, (int) 20.0, 0.0F);
        this.body.texOffs(0, 91).addBox(-6.0F, 6.0F, -7.0F, (int) 12.0, (int) 13.0, (int) 18.0, 0.0F);
        this.body.setPos(0.0F, 1.0F, 2.0F);
        this.leg0 = new RendererModel(this, 96, 0);
        this.leg0.addBox(-4.0F, 0.0F, -4.0F, (int) 8.0, (int) 37.0, (int) 8.0, 0.0F);
        this.leg0.setPos(-8.0F, -13.0F, 18.0F);
        this.leg1 = new RendererModel(this, 96, 0);
        this.leg1.mirror = true;
        this.leg1.addBox(-4.0F, 0.0F, -4.0F, (int) 8.0, (int) 37.0, (int) 8.0, 0.0F);
        this.leg1.setPos(8.0F, -13.0F, 18.0F);
        this.leg2 = new RendererModel(this, 64, 0);
        this.leg2.addBox(-4.0F, 0.0F, -4.0F, (int) 8.0, (int) 37.0, (int) 8.0, 0.0F);
        this.leg2.setPos(-8.0F, -13.0F, -5.0F);
        this.leg3 = new RendererModel(this, 64, 0);
        this.leg3.mirror = true;
        this.leg3.addBox(-4.0F, 0.0F, -4.0F, (int) 8.0, (int) 37.0, (int) 8.0, 0.0F);
        this.leg3.setPos(8.0F, -13.0F, -5.0F);
    }

    public Iterable<RendererModel> parts() {
        return ImmutableList.of(this.neck, this.body, this.leg0, this.leg1, this.leg2, this.leg3);
    }

    @Override
    public void setupAnim(ClientRavager ravager, float f, float g, float h, float i, float j, float u) {
        this.head.xRot = j * ((float) Math.PI / 180F);
        this.head.yRot = i * ((float) Math.PI / 180F);
        this.body.xRot = ((float) Math.PI / 2F);
        float k = 0.4F * g;
        this.leg0.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * k;
        this.leg1.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * k;
        this.leg2.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * k;
        this.leg3.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * k;
    }

    public void prepareMobModel(ClientRavager ravager, float f, float g, float h) {
        super.prepareMobModel(ravager, f, g, h);
        int i = 0;
        int j = 0;
        int k = 20;
        int l = 0;
        int m = 10;
        if (l > 0) {
            float n = Utils.triangleWave((float) l - h, 10.0F);
            float o = (1.0F + n) * 0.5F;
            float p = o * o * o * 12.0F;
            float q = p * net.minecraft.util.math.MathHelper.sin(this.neck.xRot);
            this.neck.z = -6.5F + p;
            this.neck.y = -7.0F - q;
            float r = net.minecraft.util.math.MathHelper.sin(((float) l - h) / 10.0F * (float) Math.PI * 0.25F);
            this.mouth.xRot = ((float) Math.PI / 2F) * r;
            if (l > 5) {
                this.mouth.xRot = net.minecraft.util.math.MathHelper.sin(((float) (-4 + l) - h) / 4.0F) * (float) Math.PI * 0.4F;
            } else {
                this.mouth.xRot = 0.15707964F * net.minecraft.util.math.MathHelper.sin((float) Math.PI * ((float) l - h) / 10.0F);
            }
        } else {
            float n = -1.0F;
            float o = -1.0F * net.minecraft.util.math.MathHelper.sin(this.neck.xRot);
            this.neck.x = 0.0F;
            this.neck.y = -7.0F - o;
            this.neck.z = 5.5F;
            boolean bl = i > 0;
            this.neck.xRot = bl ? 0.21991149F : 0.0F;
            this.mouth.xRot = (float) Math.PI * (bl ? 0.05F : 0.01F);
            if (bl) {
                double d = (double) i / (double) 40.0F;
                this.neck.x = (float) Math.sin(d * (double) 10.0F) * 3.0F;
            } else if (j > 0) {
                float q = net.minecraft.util.math.MathHelper.sin(((float) (20 - j) - h) / 20.0F * (float) Math.PI * 0.25F);
                this.mouth.xRot = ((float) Math.PI / 2F) * q;
            }
        }

    }

    public void render(ClientRavager p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.setupAnim(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
        this.neck.render(p_78088_7_);
        this.body.render(p_78088_7_);
        this.leg0.render(p_78088_7_);
        this.leg1.render(p_78088_7_);
        this.leg2.render(p_78088_7_);
        this.leg3.render(p_78088_7_);
    }
}
