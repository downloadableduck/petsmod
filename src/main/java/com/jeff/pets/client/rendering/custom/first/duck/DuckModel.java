package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;

import static com.jeff.pets.PetsInitializer.LOGGER;

public class DuckModel extends PetModel {

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart bill_r1;
    private final ModelPart body;
    private final ModelPart left_wing;
    private final ModelPart right_wing;
    private final ModelPart left_leg;
    private final ModelPart right_leg;
    private final ModelPart tail;

    public DuckModel() {
        textureWidth = 64;
        textureHeight = 32;

        root = new ModelPart(this);
        root.setPivot(0.0F, 15.0F, -4.0F);


        head = new ModelPart(this);
        head.setPivot(0.0F, 0.0F, 0.0F);
        root.add(head);
        head.setTextureOffset(0, 0).addCuboid(-1.5F, -5.0F, -1.5F, (int) 3.0F, (int) 6.0F, (int) 3.0F, 0.0F);

        bill_r1 = new ModelPart(this);
        bill_r1.setPivot(1.0F, -15.9F, -9.1F);
        head.add(bill_r1);
        setRotationAngle(bill_r1, 3.1176F, 0.0244F, -0.0049F);
        bill_r1.setTextureOffset(14, 0).addCuboid(-2.2F, -13.0F, -8.0F, (int) 2.0F, (int) 1.0F, (int) 2.0F, 0.0F);

        body = new ModelPart(this);
        body.setPivot(0.0F, 1.0F, 4.0F);
        root.add(body);
        setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
        body.setTextureOffset(1, 10).addCuboid(-2.5F, -4.0F, -4.0F, (int) 5.0F, (int) 8.0F, (int) 5.0F, 0.0F);

        left_wing = new ModelPart(this);
        left_wing.setPivot(3.0F, 0.0F, 4.0F);
        root.add(left_wing);
        left_wing.setTextureOffset(24, 13).addCuboid(-0.5F, 0.0F, -3.0F, (int) 1.0F, (int) 3.0F, (int) 6.0F, 0.0F);

        right_wing = new ModelPart(this);
        right_wing.setPivot(-3.0F, 0.0F, 4.0F);
        root.add(right_wing);
        right_wing.setTextureOffset(24, 13).addCuboid(-0.5F, 0.0F, -3.0F, (int) 1.0F, (int) 3.0F, (int) 6.0F, 0.0F);

        left_leg = new ModelPart(this);
        left_leg.setPivot(1.0F, 4.0F, 5.0F);
        root.add(left_leg);
        left_leg.setTextureOffset(26, 0).addCuboid(-1.0F, 0.0F, -3.0F, (int) 3.0F, (int) 5.0F, (int) 3.0F, 0.0F);

        right_leg = new ModelPart(this);
        right_leg.setPivot(-2.0F, 4.0F, 5.0F);
        root.add(right_leg);
        right_leg.setTextureOffset(26, 0).addCuboid(-1.0F, 0.0F, -3.0F, (int) 3.0F, (int) 5.0F, (int) 3.0F, 0.0F);

        tail = new ModelPart(this);
        tail.setPivot(0.0F, 4.0F, 9.0F);
        root.add(tail);
        tail.setTextureOffset(0, 23).addCuboid(-1.0F, -2.0F, -1.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F);
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
        final Duck state = (Duck) entity;
        float flapAngle = state.onGround ? 0 : (net.minecraft.util.math.MathHelper.sin(h) + 1.0F) * state.flapSpeed;
        this.head.posX = j * ((float) Math.PI / 180F);
        this.head.posY = i * ((float) Math.PI / 180F);
        float animationPos = f;
        float limbSwingAmount = g;
        this.right_leg.posX = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.posX = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.right_wing.posZ = flapAngle;
        this.left_wing.posZ = -flapAngle;
        //thing is weird af in 1.16.5 and below, base y is 15, base x is 0, base z is -4
        if (state.isPassenger()) {
            this.root.setPivot(0.4F, 17.5F, -4.0F);
            this.right_leg.visible = false;
            this.left_leg.visible = false;
        } else {
            this.root.setPivot(0, 15, -4);
            this.right_leg.visible = true;
            this.left_leg.visible = true;
        }
    }
}