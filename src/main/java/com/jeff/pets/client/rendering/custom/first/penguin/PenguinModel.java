package com.jeff.pets.client.rendering.custom.first.penguin;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Penguin;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;

public class PenguinModel extends PetModel {

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart left_wing;
    private final ModelPart right_wing;
    private final ModelPart left_foot;
    private final ModelPart left_foot_r1;
    private final ModelPart right_foot;
    private final ModelPart right_foot_r1;
    private final ModelPart tail;
    private final ModelPart head;
    private final ModelPart beak;

    public PenguinModel() {
        textureWidth = 64;
        textureHeight = 64;

        root = new ModelPart(this);
        root.setPivot(0.0F, 28.0F, 0.0F);
        setRotationAngle(root, 0.0F, 1.5708F, 0.0F);


        body = new ModelPart(this);
        body.setPivot(0.0F, 4.0F, 0.0F);
        root.add(body);
        body.setTextureOffset(0, 0).method_18947(-8.0F, -20.0F, 0.0F, (int) 8.0F, (int) 12.0F, (int) 8.0F, 0.0F, false);

        left_wing = new ModelPart(this);
        left_wing.setPivot(-4.0F, -15.0F, 8.5F);
        root.add(left_wing);
        setRotationAngle(left_wing, 0.0F, 0.0F, -1.5708F);
        left_wing.setTextureOffset(24, 25).method_18947(-10.0F, -3.0F, -0.5F, (int) 10.0F, (int) 6.0F, (int) 1.0F, 0.0F, false);

        right_wing = new ModelPart(this);
        right_wing.setPivot(-4.0F, -15.0F, 0.0F);
        root.add(right_wing);
        setRotationAngle(right_wing, 0.0F, 0.0F, -1.5708F);
        right_wing.setTextureOffset(24, 25).method_18947(-10.0F, -3.0F, -1.0F, (int) 10.0F, (int) 6.0F, (int) 1.0F, 0.0F, false);

        left_foot = new ModelPart(this);
        left_foot.setPivot(-4.0F, -4.0F, 2.0F);
        root.add(left_foot);


        left_foot_r1 = new ModelPart(this);
        left_foot_r1.setPivot(5.0F, 1.0F, 0.0F);
        left_foot.add(left_foot_r1);
        setRotationAngle(left_foot_r1, 0.0F, 0.0F, -1.5708F);
        left_foot_r1.setTextureOffset(0, 32).method_18947(-1.0F, -6.0F, 2.0F, (int) 2.0F, (int) 6.0F, (int) 4.0F, 0.0F, false);

        right_foot = new ModelPart(this);
        right_foot.setPivot(-4.0F, -4.0F, 7.0F);
        root.add(right_foot);


        right_foot_r1 = new ModelPart(this);
        right_foot_r1.setPivot(5.0F, 1.0F, -9.0F);
        right_foot.add(right_foot_r1);
        setRotationAngle(right_foot_r1, 0.0F, 0.0F, -1.5708F);
        right_foot_r1.setTextureOffset(32, 0).method_18947(-1.0F, -6.0F, 2.0F, (int) 2.0F, (int) 6.0F, (int) 4.0F, 0.0F, false);

        tail = new ModelPart(this);
        tail.setPivot(-3.0F, -3.0F, 2.0F);
        root.add(tail);
        tail.setTextureOffset(-8, -8).method_18947(3.0F, -4.0F, 0.0F, (int) 2.0F, (int) 2.0F, (int) 4.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPivot(1.0F, -5.0F, 3.0F);
        root.add(head);
        head.setTextureOffset(0, 20).method_18947(-8.0F, -17.0F, -2.0F, (int) 6.0F, (int) 6.0F, (int) 6.0F, 0.0F, false);

        beak = new ModelPart(this);
        beak.setPivot(0.0F, 0.0F, 0.0F);
        head.add(beak);
        beak.setTextureOffset(12, 32).method_18947(-2.0F, -14.0F, 0.0F, (int) 4.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        root.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.posX = x;
        ModelPart.posY = y;
        ModelPart.posZ = z;
    }

    @Override
    public void setAngles(float f, float g, float h, float i, float j, float p, Entity entity) {
        Penguin state = (Penguin) entity;
        float flapAngle = (net.minecraft.util.math.MathHelper.sin(state.flap) + 1.0F) * state.flapSpeed;
        this.head.posX = state.pitch * ((float) Math.PI / 180F);
        float animationPos = f;
        float limbSwingAmount = g;
        this.right_foot.posZ = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_foot.posZ = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.right_wing.posY = -flapAngle * 0.75F;
        this.left_wing.posY = flapAngle * 0.75F;
        this.body.posZ = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F) * 0.1F * limbSwingAmount;
        this.head.posZ = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F) * 0.1F * limbSwingAmount;
    }
}
