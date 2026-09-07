package com.jeff.pets.client.rendering.custom.first.penguin;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Penguin;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PenguinModel extends PetModel {

    private final ModelRenderer root;
    private final ModelRenderer body;
    private final ModelRenderer left_wing;
    private final ModelRenderer right_wing;
    private final ModelRenderer left_foot;
    private final ModelRenderer left_foot_r1;
    private final ModelRenderer right_foot;
    private final ModelRenderer right_foot_r1;
    private final ModelRenderer tail;
    private final ModelRenderer head;
    private final ModelRenderer beak;

    public PenguinModel() {
        textureWidth = 64;
        textureHeight = 64;

        root = new ModelRenderer(this);
        root.setRotationPoint(0.0F, 28.0F, 0.0F);
        setRotationAngle(root, 0.0F, 1.5708F, 0.0F);


        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 4.0F, 0.0F);
        root.addChild(body);
        body.setTextureOffset(0, 0).addBox(-8.0F, -20.0F, 0.0F, (int) 8.0F, (int) 12.0F, (int) 8.0F, 0.0F);

        left_wing = new ModelRenderer(this);
        left_wing.setRotationPoint(-4.0F, -15.0F, 8.5F);
        root.addChild(left_wing);
        setRotationAngle(left_wing, 0.0F, 0.0F, -1.5708F);
        left_wing.setTextureOffset(24, 25).addBox(-10.0F, -3.0F, -0.5F, (int) 10.0F, (int) 6.0F, (int) 1.0F, 0.0F);

        right_wing = new ModelRenderer(this);
        right_wing.setRotationPoint(-4.0F, -15.0F, 0.0F);
        root.addChild(right_wing);
        setRotationAngle(right_wing, 0.0F, 0.0F, -1.5708F);
        right_wing.setTextureOffset(24, 25).addBox(-10.0F, -3.0F, -1.0F, (int) 10.0F, (int) 6.0F, (int) 1.0F, 0.0F);

        left_foot = new ModelRenderer(this);
        left_foot.setRotationPoint(-4.0F, -4.0F, 2.0F);
        root.addChild(left_foot);


        left_foot_r1 = new ModelRenderer(this);
        left_foot_r1.setRotationPoint(5.0F, 1.0F, 0.0F);
        left_foot.addChild(left_foot_r1);
        setRotationAngle(left_foot_r1, 0.0F, 0.0F, -1.5708F);
        left_foot_r1.setTextureOffset(0, 32).addBox(-1.0F, -6.0F, 2.0F, (int) 2.0F, (int) 6.0F, (int) 4.0F, 0.0F);

        right_foot = new ModelRenderer(this);
        right_foot.setRotationPoint(-4.0F, -4.0F, 7.0F);
        root.addChild(right_foot);


        right_foot_r1 = new ModelRenderer(this);
        right_foot_r1.setRotationPoint(5.0F, 1.0F, -9.0F);
        right_foot.addChild(right_foot_r1);
        setRotationAngle(right_foot_r1, 0.0F, 0.0F, -1.5708F);
        right_foot_r1.setTextureOffset(32, 0).addBox(-1.0F, -6.0F, 2.0F, (int) 2.0F, (int) 6.0F, (int) 4.0F, 0.0F);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(-3.0F, -3.0F, 2.0F);
        root.addChild(tail);
        tail.setTextureOffset(-8, -8).addBox(3.0F, -4.0F, 0.0F, (int) 2.0F, (int) 2.0F, (int) 4.0F, 0.0F);

        head = new ModelRenderer(this);
        head.setRotationPoint(1.0F, -5.0F, 3.0F);
        root.addChild(head);
        head.setTextureOffset(0, 20).addBox(-8.0F, -17.0F, -2.0F, (int) 6.0F, (int) 6.0F, (int) 6.0F, 0.0F);

        beak = new ModelRenderer(this);
        beak.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(beak);
        beak.setTextureOffset(12, 32).addBox(-2.0F, -14.0F, 0.0F, (int) 4.0F, (int) 2.0F, (int) 2.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        root.render(alpha);
    }

    public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float g, float h, float i, float j, float p, Entity entity) {
        Penguin state = (Penguin) entity;
        float flapAngle = (net.minecraft.util.math.MathHelper.sin(state.flap) + 1.0F) * state.flapSpeed;
        this.head.rotateAngleX = state.rotationPitch * ((float) Math.PI / 180F);
        float limbSwingAmount = state.limbSwingAmount;
        float animationPos = state.limbSwing;
        this.right_foot.rotateAngleZ = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_foot.rotateAngleZ = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.right_wing.rotateAngleY = -flapAngle * 0.75F;
        this.left_wing.rotateAngleY = flapAngle * 0.75F;
        this.body.rotateAngleZ = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F) * 0.1F * limbSwingAmount;
        this.head.rotateAngleZ = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F) * 0.1F * limbSwingAmount;
    }
}
