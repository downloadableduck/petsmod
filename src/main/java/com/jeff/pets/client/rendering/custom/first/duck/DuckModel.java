package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

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
        textureWidth /*textureWidth*/ = 64;
        textureHeight /*textureHeight*/ = 32;

        root = new ModelPart(this);
        root.setPos(0.0F, 15.0F, -4.0F);


        head = new ModelPart(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        root.addChild(head);
        head.setTextureCoords(0, 0).addBox(-1.5F, -5.0F, -1.5F, 3, 6, 3, 0.0F);

        bill_r1 = new ModelPart(this);
        bill_r1.setPos(1.0F, -15.9F, -9.1F);
        head.addChild(bill_r1);
        setRotationAngle(bill_r1, 3.1176F, 0.0244F, -0.0049F);
        bill_r1.setTextureCoords(14, 0).addBox(-2.2F, -13.0F, -8.0F, 2, 1, 2, 0.0F);

        body = new ModelPart(this);
        body.setPos(0.0F, 1.0F, 4.0F);
        root.addChild(body);
        setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
        body.setTextureCoords(1, 10).addBox(-2.5F, -4.0F, -4.0F, 5, 8, 5, 0.0F);

        left_wing = new ModelPart(this);
        left_wing.setPos(3.0F, 0.0F, 4.0F);
        root.addChild(left_wing);
        left_wing.setTextureCoords(24, 13).addBox(-0.5F, 0.0F, -3.0F, 1, 3, 6, 0.0F);

        right_wing = new ModelPart(this);
        right_wing.setPos(-3.0F, 0.0F, 4.0F);
        root.addChild(right_wing);
        right_wing.setTextureCoords(24, 13).addBox(-0.5F, 0.0F, -3.0F, 1, 3, 6, 0.0F);

        left_leg = new ModelPart(this);
        left_leg.setPos(1.0F, 4.0F, 5.0F);
        root.addChild(left_leg);
        left_leg.setTextureCoords(26, 0).addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3, 0.0F);

        right_leg = new ModelPart(this);
        right_leg.setPos(-2.0F, 4.0F, 5.0F);
        root.addChild(right_leg);
        right_leg.setTextureCoords(26, 0).addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3, 0.0F);

        tail = new ModelPart(this);
        tail.setPos(0.0F, 4.0F, 9.0F);
        root.addChild(tail);
        tail.setTextureCoords(0, 23).addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        root.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.rotationX = x;
        ModelPart.rotationY = y;
        ModelPart.rotationZ = z;
    }

    @Override
    public void setupAnimation(float f, float g, float h, float i, float j, float s, net.minecraft.entity.Entity entity) {
        Duck state = (Duck) entity;
        float flapAngle = state.onGround ? 0 : (MathHelper.sin(h) + 1.0F) * state.flapSpeed;
        this.head.rotationX = j * ((float) Math.PI / 180F);
        this.head.rotationY = i * ((float) Math.PI / 180F);
        float animationSpeed = state.walkAnimationSpeed;
        float animationPos = state.walkAnimationProgress;
        this.right_leg.rotationX = MathHelper.cos(animationPos * 0.6662F) * 1.4F * animationSpeed;
        this.left_leg.rotationX = MathHelper.cos(animationPos * 0.6662F + (float) Math.PI) * 1.4F * animationSpeed;
        this.right_wing.rotationZ = flapAngle;
        this.left_wing.rotationZ = -flapAngle;
        //thing is weird af in 1.16.5 and below, base y is 15, base x is 0, base z is -4
        if (state.isRiding()) {
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