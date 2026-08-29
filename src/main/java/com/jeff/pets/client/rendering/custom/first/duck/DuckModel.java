package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class DuckModel extends PetModel {

    private final ModelRenderer root;
    private final ModelRenderer head;
    private final ModelRenderer bill_r1;
    private final ModelRenderer body;
    private final ModelRenderer left_wing;
    private final ModelRenderer right_wing;
    private final ModelRenderer left_leg;
    private final ModelRenderer right_leg;
    private final ModelRenderer tail;

    public DuckModel() {
        textureWidth = 64;
        textureHeight = 32;

        root = new ModelRenderer(this);
        root.setRotationPoint(0.0F, 15.0F, -4.0F);


        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        root.addChild(head);
        head.setTextureOffset(0, 0).addBox(-1.5F, -5.0F, -1.5F, (int) 3.0F, (int) 6.0F, (int) 3.0F, 0.0F, false);

        bill_r1 = new ModelRenderer(this);
        bill_r1.setRotationPoint(1.0F, -15.9F, -9.1F);
        head.addChild(bill_r1);
        setRotationAngle(bill_r1, 3.1176F, 0.0244F, -0.0049F);
        bill_r1.setTextureOffset(14, 0).addBox(-2.2F, -13.0F, -8.0F, (int) 2.0F, (int) 1.0F, (int) 2.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 1.0F, 4.0F);
        root.addChild(body);
        setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
        body.setTextureOffset(1, 10).addBox(-2.5F, -4.0F, -4.0F, (int) 5.0F, (int) 8.0F, (int) 5.0F, 0.0F, false);

        left_wing = new ModelRenderer(this);
        left_wing.setRotationPoint(3.0F, 0.0F, 4.0F);
        root.addChild(left_wing);
        left_wing.setTextureOffset(24, 13).addBox(-0.5F, 0.0F, -3.0F, (int) 1.0F, (int) 3.0F, (int) 6.0F, 0.0F, false);

        right_wing = new ModelRenderer(this);
        right_wing.setRotationPoint(-3.0F, 0.0F, 4.0F);
        root.addChild(right_wing);
        right_wing.setTextureOffset(24, 13).addBox(-0.5F, 0.0F, -3.0F, (int) 1.0F, (int) 3.0F, (int) 6.0F, 0.0F, false);

        left_leg = new ModelRenderer(this);
        left_leg.setRotationPoint(1.0F, 4.0F, 5.0F);
        root.addChild(left_leg);
        left_leg.setTextureOffset(26, 0).addBox(-1.0F, 0.0F, -3.0F, (int) 3.0F, (int) 5.0F, (int) 3.0F, 0.0F, false);

        right_leg = new ModelRenderer(this);
        right_leg.setRotationPoint(-2.0F, 4.0F, 5.0F);
        root.addChild(right_leg);
        right_leg.setTextureOffset(26, 0).addBox(-1.0F, 0.0F, -3.0F, (int) 3.0F, (int) 5.0F, (int) 3.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(0.0F, 4.0F, 9.0F);
        root.addChild(tail);
        tail.setTextureOffset(0, 23).addBox(-1.0F, -2.0F, -1.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        root.render(alpha);
    }

    public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float g, float h, float i, float j, float p, Entity entity) {
        final Duck state = (Duck) entity;
        float flapAngle = state.onGround ? 0 : (net.minecraft.util.math.MathHelper.sin(h) + 1.0F) * state.flapSpeed;
        this.head.rotateAngleX = j * ((float) Math.PI / 180F);
        this.head.rotateAngleY = i * ((float) Math.PI / 180F);
        float limbSwingAmount = state.limbSwingAmount;
        float animationPos = state.limbSwing;
        this.right_leg.rotateAngleX = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.right_wing.rotateAngleZ = flapAngle;
        this.left_wing.rotateAngleZ = -flapAngle;
        //thing is weird af in 1.16.5 and below, base y is 15, base x is 0, base z is -4
        if (state.isPassenger()) {
            this.root.setRotationPoint(0.4F, 17.5F, -4.0F);
            this.right_leg.showModel = false;
            this.left_leg.showModel = false;
        } else {
            this.root.setRotationPoint(0, 15, -4);
            this.right_leg.showModel = true;
            this.left_leg.showModel = true;
        }
    }
}