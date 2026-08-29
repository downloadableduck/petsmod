package com.jeff.pets.client.rendering.vanilla.wolf;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;

import java.util.List;

public class ClientWolfModel extends ModelBase {
    private final ModelRenderer head;
    private final ModelRenderer realHead;
    private final ModelRenderer body;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer tail;
    private final ModelRenderer realTail;
    private final ModelRenderer upperBody;

    public ClientWolfModel() {
        float f = 0.0F;
        float g = 13.5F;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.realHead = new ModelRenderer(this, 0, 0);
        this.realHead.addBox(-2.0F, -3.0F, -2.0F, (int) 6.0, (int) 6.0, (int) 4.0, 0.0F);
        this.head.addChild(this.realHead);
        this.body = new ModelRenderer(this, 18, 14);
        this.body.addBox(-3.0F, -2.0F, -3.0F, (int) 6.0, (int) 9.0, (int) 6.0, 0.0F);
        this.body.setRotationPoint(0.0F, 14.0F, 2.0F);
        this.upperBody = new ModelRenderer(this, 21, 0);
        this.upperBody.addBox(-3.0F, -3.0F, -3.0F, (int) 8.0, (int) 6.0, (int) 7.0, 0.0F);
        this.upperBody.setRotationPoint(-1.0F, 14.0F, 2.0F);
        this.leg0 = new ModelRenderer(this, 0, 18);
        this.leg0.addBox(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.leg0.setRotationPoint(-2.5F, 16.0F, 7.0F);
        this.leg1 = new ModelRenderer(this, 0, 18);
        this.leg1.addBox(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.leg1.setRotationPoint(0.5F, 16.0F, 7.0F);
        this.leg2 = new ModelRenderer(this, 0, 18);
        this.leg2.addBox(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.leg2.setRotationPoint(-2.5F, 16.0F, -4.0F);
        this.leg3 = new ModelRenderer(this, 0, 18);
        this.leg3.addBox(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.leg3.setRotationPoint(0.5F, 16.0F, -4.0F);
        this.tail = new ModelRenderer(this, 9, 18);
        this.tail.setRotationPoint(-1.0F, 12.0F, 8.0F);
        this.realTail = new ModelRenderer(this, 9, 18);
        this.realTail.addBox(0.0F, 0.0F, -1.0F, (int) 2.0, (int) 8.0, (int) 2.0, 0.0F);
        this.tail.addChild(this.realTail);
        this.realHead.setTextureOffset(16, 14).addBox(-2.0F, -5.0F, 0.0F, (int) 2.0, (int) 2.0, (int) 1.0, 0.0F);
        this.realHead.setTextureOffset(16, 14).addBox(2.0F, -5.0F, 0.0F, (int) 2.0, (int) 2.0, (int) 1.0, 0.0F);
        this.realHead.setTextureOffset(0, 10).addBox(-0.5F, 0.0F, -5.0F, (int) 3.0, (int) 3.0, (int) 4.0, 0.0F);
    }

    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.body, this.leg0, this.leg1, this.leg2, this.leg3, this.tail, this.upperBody);
    }

    public void setLivingAnimations(ClientWolf wolf, float f, float g, float h) {
        //this.tail.rotateAngleY = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;

        if (wolf.isPassenger()) {
            this.upperBody.setRotationPoint(-1.0F, 16.0F, -3.0F);
            this.upperBody.rotateAngleX = 1.2566371F;
            this.upperBody.rotateAngleY = 0.0F;
            this.body.setRotationPoint(0.0F, 18.0F, 0.0F);
            this.body.rotateAngleX = ((float) Math.PI / 4F);
            this.tail.setRotationPoint(-1.0F, 21.0F, 6.0F);
            this.leg0.setRotationPoint(-2.5F, 22.7F, 2.0F);
            this.leg0.rotateAngleX = ((float) Math.PI * 1.5F);
            this.leg1.setRotationPoint(0.5F, 22.7F, 2.0F);
            this.leg1.rotateAngleX = ((float) Math.PI * 1.5F);
            this.leg2.rotateAngleX = 5.811947F;
            this.leg2.setRotationPoint(-2.49F, 17.0F, -4.0F);
            this.leg3.rotateAngleX = 5.811947F;
            this.leg3.setRotationPoint(0.51F, 17.0F, -4.0F);
        } else {
            this.body.setRotationPoint(0.0F, 14.0F, 2.0F);
            this.body.rotateAngleX = ((float) Math.PI / 2F);
            this.upperBody.setRotationPoint(-1.0F, 14.0F, -3.0F);
            this.upperBody.rotateAngleX = this.body.rotateAngleX;
            this.tail.setRotationPoint(-1.0F, 12.0F, 8.0F);
            this.leg0.setRotationPoint(-2.5F, 16.0F, 7.0F);
            this.leg1.setRotationPoint(0.5F, 16.0F, 7.0F);
            this.leg2.setRotationPoint(-2.5F, 16.0F, -4.0F);
            this.leg3.setRotationPoint(0.5F, 16.0F, -4.0F);
            this.leg0.rotateAngleX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
            this.leg1.rotateAngleX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
            this.leg2.rotateAngleX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
            this.leg3.rotateAngleX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
        }

        this.realHead.rotateAngleZ = 0;
        this.upperBody.rotateAngleZ = 0;
        this.body.rotateAngleZ = 0;
        //this.realTail.rotateAngleZ = wolf.getBodyRollAngle(h, -0.2F);
    }

    public void setRotationAngles(ClientWolf wolf, float f, float g, float h, float i, float j, float k) {
        this.head.rotateAngleX = j * ((float) Math.PI / 180F);
        this.head.rotateAngleY = i * ((float) Math.PI / 180F);
        this.tail.rotateAngleX = 45;
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float f, float g, float h, float i, float j, float k) {
        super.render(entity, f, g, h, i, j, k);
        ClientWolf wolf = (ClientWolf) entity;
        this.setRotationAngles(wolf, f, g, h, i, j, k);
        this.setLivingAnimations(wolf, f, g, h);
        for (ModelRenderer cube : this.getParts()) {
            cube.render(k);
        }
    }

    private List<ModelRenderer> getParts() {
        return ImmutableList.of(head, body, leg0, leg1, leg2, leg3, tail, upperBody);
    }
}
