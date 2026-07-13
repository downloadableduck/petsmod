package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.client.renderer.model.ModelRenderer;

public class DuckModel extends PetModel<Duck> {

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
        texWidth = 64;
        texHeight = 32;

        root = new ModelRenderer(this);
        root.setPos(0.0F, 15.0F, -4.0F);


        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        root.addChild(head);
        head.texOffs(0, 0).addBox(-1.5F, -5.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);

        bill_r1 = new ModelRenderer(this);
        bill_r1.setPos(1.0F, -15.9F, -9.1F);
        head.addChild(bill_r1);
        setRotationAngle(bill_r1, 3.1176F, 0.0244F, -0.0049F);
        bill_r1.texOffs(14, 0).addBox(-2.2F, -13.0F, -8.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 1.0F, 4.0F);
        root.addChild(body);
        setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
        body.texOffs(1, 10).addBox(-2.5F, -4.0F, -4.0F, 5.0F, 8.0F, 5.0F, 0.0F, false);

        left_wing = new ModelRenderer(this);
        left_wing.setPos(3.0F, 0.0F, 4.0F);
        root.addChild(left_wing);
        left_wing.texOffs(24, 13).addBox(-0.5F, 0.0F, -3.0F, 1.0F, 3.0F, 6.0F, 0.0F, false);

        right_wing = new ModelRenderer(this);
        right_wing.setPos(-3.0F, 0.0F, 4.0F);
        root.addChild(right_wing);
        right_wing.texOffs(24, 13).addBox(-0.5F, 0.0F, -3.0F, 1.0F, 3.0F, 6.0F, 0.0F, false);

        left_leg = new ModelRenderer(this);
        left_leg.setPos(1.0F, 4.0F, 5.0F);
        root.addChild(left_leg);
        left_leg.texOffs(26, 0).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        right_leg = new ModelRenderer(this);
        right_leg.setPos(-2.0F, 4.0F, 5.0F);
        root.addChild(right_leg);
        right_leg.texOffs(26, 0).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 4.0F, 9.0F);
        root.addChild(tail);
        tail.texOffs(0, 23).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(com.mojang.blaze3d.matrix.MatrixStack matrixStack, com.mojang.blaze3d.vertex.IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.xRot = x;
        ModelRenderer.yRot = y;
        ModelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(final Duck state, float f, float g, float h, float i, float j) {
        System.out.println("Y:" + this.root.y);
        System.out.println("X:" + this.root.x);
        System.out.println("Z: " + this.root.z);
        float flapAngle = state.isOnGround() ? 0 : (net.minecraft.util.math.MathHelper.sin(h) + 1.0F) * state.flapSpeed;
        this.head.xRot = j * ((float) Math.PI / 180F);
        this.head.yRot = i * ((float) Math.PI / 180F);
        float animationSpeed = state.animationSpeed;
        float animationPos = state.animationPosition;
        this.right_leg.xRot = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F) * 1.4F * animationSpeed;
        this.left_leg.xRot = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F + (float) Math.PI) * 1.4F * animationSpeed;
        this.right_wing.zRot = flapAngle;
        this.left_wing.zRot = -flapAngle;
        //thing is weird af in 1.16.5 and below, base y is 15, base x is 0, base z is -4
        if (state.isPassenger()) {
            this.root.x = 0.4F;
            this.root.y = 17.5F;
            this.root.z = -4.0F;
            this.right_leg.visible = false;
            this.left_leg.visible = false;
        } else {
            this.root.x = 0;
            this.root.y = 15;
            this.root.z = -4;
            this.right_leg.visible = true;
            this.left_leg.visible = true;
        }
    }
}