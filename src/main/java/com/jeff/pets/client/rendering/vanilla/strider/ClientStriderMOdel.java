package com.jeff.pets.client.rendering.vanilla.strider;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.passive.ClientStrider;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ClientStriderMOdel extends SegmentedModel<ClientStrider> {
    private final ModelRenderer rightLeg;
    private final ModelRenderer leftLeg;
    private final ModelRenderer body;
    private final ModelRenderer bristle0;
    private final ModelRenderer bristle1;
    private final ModelRenderer bristle2;
    private final ModelRenderer bristle3;
    private final ModelRenderer bristle4;
    private final ModelRenderer bristle5;

    public ClientStriderMOdel() {
        this.texWidth = 64;
        this.texHeight = 128;
        this.rightLeg = new ModelRenderer(this, 0, 32);
        this.rightLeg.setPos(-4.0F, 8.0F, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F, 0.0F);
        this.leftLeg = new ModelRenderer(this, 0, 55);
        this.leftLeg.setPos(4.0F, 8.0F, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 1.0F, 0.0F);
        this.body.addBox(-8.0F, -6.0F, -8.0F, 16.0F, 14.0F, 16.0F, 0.0F);
        this.bristle0 = new ModelRenderer(this, 16, 65);
        this.bristle0.setPos(-8.0F, 4.0F, -8.0F);
        this.bristle0.addBox(-12.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, 0.0F, true);
        this.setRotationAngle(this.bristle0, 0.0F, 0.0F, -1.2217305F);
        this.bristle1 = new ModelRenderer(this, 16, 49);
        this.bristle1.setPos(-8.0F, -1.0F, -8.0F);
        this.bristle1.addBox(-12.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, 0.0F, true);
        this.setRotationAngle(this.bristle1, 0.0F, 0.0F, -1.134464F);
        this.bristle2 = new ModelRenderer(this, 16, 33);
        this.bristle2.setPos(-8.0F, -5.0F, -8.0F);
        this.bristle2.addBox(-12.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, 0.0F, true);
        this.setRotationAngle(this.bristle2, 0.0F, 0.0F, -0.87266463F);
        this.bristle3 = new ModelRenderer(this, 16, 33);
        this.bristle3.setPos(8.0F, -6.0F, -8.0F);
        this.bristle3.addBox(0.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, 0.0F);
        this.setRotationAngle(this.bristle3, 0.0F, 0.0F, 0.87266463F);
        this.bristle4 = new ModelRenderer(this, 16, 49);
        this.bristle4.setPos(8.0F, -2.0F, -8.0F);
        this.bristle4.addBox(0.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, 0.0F);
        this.setRotationAngle(this.bristle4, 0.0F, 0.0F, 1.134464F);
        this.bristle5 = new ModelRenderer(this, 16, 65);
        this.bristle5.setPos(8.0F, 3.0F, -8.0F);
        this.bristle5.addBox(0.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, 0.0F);
        this.setRotationAngle(this.bristle5, 0.0F, 0.0F, 1.2217305F);
        this.body.addChild(this.bristle0);
        this.body.addChild(this.bristle1);
        this.body.addChild(this.bristle2);
        this.body.addChild(this.bristle3);
        this.body.addChild(this.bristle4);
        this.body.addChild(this.bristle5);
    }

    public void setupAnim(ClientStrider strider, float f, float g, float h, float i, float j) {
        g = Math.min(0.25F, g);
        if (strider.getPassengers().size() <= 0) {
            this.body.xRot = j * ((float) Math.PI / 180F);
            this.body.yRot = i * ((float) Math.PI / 180F);
        } else {
            this.body.xRot = 0.0F;
            this.body.yRot = 0.0F;
        }

        float k = 1.5F;
        this.body.zRot = 0.1F * net.minecraft.util.math.MathHelper.sin(f * 1.5F) * 4.0F * g;
        this.body.y = 2.0F;
        ModelRenderer var10000 = this.body;
        var10000.y -= 2.0F * net.minecraft.util.math.MathHelper.cos(f * 1.5F) * 2.0F * g;
        this.leftLeg.xRot = net.minecraft.util.math.MathHelper.sin(f * 1.5F * 0.5F) * 2.0F * g;
        this.rightLeg.xRot = net.minecraft.util.math.MathHelper.sin(f * 1.5F * 0.5F + (float) Math.PI) * 2.0F * g;
        this.leftLeg.zRot = 0.17453292F * net.minecraft.util.math.MathHelper.cos(f * 1.5F * 0.5F) * g;
        this.rightLeg.zRot = 0.17453292F * net.minecraft.util.math.MathHelper.cos(f * 1.5F * 0.5F + (float) Math.PI) * g;
        this.leftLeg.y = 8.0F + 2.0F * net.minecraft.util.math.MathHelper.sin(f * 1.5F * 0.5F + (float) Math.PI) * 2.0F * g;
        this.rightLeg.y = 8.0F + 2.0F * net.minecraft.util.math.MathHelper.sin(f * 1.5F * 0.5F) * 2.0F * g;
        this.bristle0.zRot = -1.2217305F;
        this.bristle1.zRot = -1.134464F;
        this.bristle2.zRot = -0.87266463F;
        this.bristle3.zRot = 0.87266463F;
        this.bristle4.zRot = 1.134464F;
        this.bristle5.zRot = 1.2217305F;
        float l = net.minecraft.util.math.MathHelper.cos(f * 1.5F + (float) Math.PI) * g;
        var10000 = this.bristle0;
        var10000.zRot += l * 1.3F;
        var10000 = this.bristle1;
        var10000.zRot += l * 1.2F;
        var10000 = this.bristle2;
        var10000.zRot += l * 0.6F;
        var10000 = this.bristle3;
        var10000.zRot += l * 0.6F;
        var10000 = this.bristle4;
        var10000.zRot += l * 1.2F;
        var10000 = this.bristle5;
        var10000.zRot += l * 1.3F;
        float m = 1.0F;
        float n = 1.0F;
        var10000 = this.bristle0;
        var10000.zRot += 0.05F * net.minecraft.util.math.MathHelper.sin(h * 1.0F * -0.4F);
        var10000 = this.bristle1;
        var10000.zRot += 0.1F * net.minecraft.util.math.MathHelper.sin(h * 1.0F * 0.2F);
        var10000 = this.bristle2;
        var10000.zRot += 0.1F * net.minecraft.util.math.MathHelper.sin(h * 1.0F * 0.4F);
        var10000 = this.bristle3;
        var10000.zRot += 0.1F * net.minecraft.util.math.MathHelper.sin(h * 1.0F * 0.4F);
        var10000 = this.bristle4;
        var10000.zRot += 0.1F * net.minecraft.util.math.MathHelper.sin(h * 1.0F * 0.2F);
        var10000 = this.bristle5;
        var10000.zRot += 0.05F * net.minecraft.util.math.MathHelper.sin(h * 1.0F * -0.4F);
    }

    public void setRotationAngle(ModelRenderer modelPart, float f, float g, float h) {
        modelPart.xRot = f;
        modelPart.yRot = g;
        modelPart.zRot = h;
    }

    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.body, this.leftLeg, this.rightLeg);
    }
}
