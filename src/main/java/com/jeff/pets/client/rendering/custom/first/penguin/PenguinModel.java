package com.jeff.pets.client.rendering.custom.first.penguin;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Penguin;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

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
        textureWidth /*textureWidth*/ = 64;
        textureHeight /*textureHeight*/ = 64;

        root = new ModelPart(this);
        root.setPos(0.0F, 28.0F, 0.0F);
        setRotationAngle(root, 0.0F, 1.5708F, 0.0F);


        body = new ModelPart(this);
        body.setPos(0.0F, 4.0F, 0.0F);
        root.addChild(body);
        body.setTextureCoords(0, 0).addBox(-8.0F, -20.0F, 0.0F, 8, 12, 8, 0.0F, false);

        left_wing = new ModelPart(this);
        left_wing.setPos(-4.0F, -15.0F, 8.5F);
        root.addChild(left_wing);
        setRotationAngle(left_wing, 0.0F, 0.0F, -1.5708F);
        left_wing.setTextureCoords(24, 25).addBox(-10.0F, -3.0F, -0.5F, 10, 6, 1, 0.0F, false);

        right_wing = new ModelPart(this);
        right_wing.setPos(-4.0F, -15.0F, 0.0F);
        root.addChild(right_wing);
        setRotationAngle(right_wing, 0.0F, 0.0F, -1.5708F);
        right_wing.setTextureCoords(24, 25).addBox(-10.0F, -3.0F, -1.0F, 10, 6, 1, 0.0F, false);

        left_foot = new ModelPart(this);
        left_foot.setPos(-4.0F, -4.0F, 2.0F);
        root.addChild(left_foot);


        left_foot_r1 = new ModelPart(this);
        left_foot_r1.setPos(5.0F, 1.0F, 0.0F);
        left_foot.addChild(left_foot_r1);
        setRotationAngle(left_foot_r1, 0.0F, 0.0F, -1.5708F);
        left_foot_r1.setTextureCoords(0, 32).addBox(-1.0F, -6.0F, 2.0F, 2, 6, 4, 0.0F, false);

        right_foot = new ModelPart(this);
        right_foot.setPos(-4.0F, -4.0F, 7.0F);
        root.addChild(right_foot);


        right_foot_r1 = new ModelPart(this);
        right_foot_r1.setPos(5.0F, 1.0F, -9.0F);
        right_foot.addChild(right_foot_r1);
        setRotationAngle(right_foot_r1, 0.0F, 0.0F, -1.5708F);
        right_foot_r1.setTextureCoords(32, 0).addBox(-1.0F, -6.0F, 2.0F, 2, 6, 4, 0.0F, false);

        tail = new ModelPart(this);
        tail.setPos(-3.0F, -3.0F, 2.0F);
        root.addChild(tail);
        tail.setTextureCoords(-8, -8).addBox(3.0F, -4.0F, 0.0F, 2, 2, 4, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(1.0F, -5.0F, 3.0F);
        root.addChild(head);
        head.setTextureCoords(0, 20).addBox(-8.0F, -17.0F, -2.0F, 6, 6, 6, 0.0F, false);

        beak = new ModelPart(this);
        beak.setPos(0.0F, 0.0F, 0.0F);
        head.addChild(beak);
        beak.setTextureCoords(12, 32).addBox(-2.0F, -14.0F, 0.0F, 4, 2, 2, 0.0F, false);
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
    public void setupAnimation(float f, float g, float h, float i, float k, float s, net.minecraft.entity.Entity entity) {
        Penguin state = (Penguin) entity;
        float flapAngle = (MathHelper.sin(state.flap) + 1.0F) * state.flapSpeed;
        this.head.rotationX = state.pitch * ((float) Math.PI / 180F);
        float animationSpeed = state.walkAnimationSpeed;
        float animationPos = state.walkAnimationProgress;
        this.right_foot.rotationZ = MathHelper.cos(animationPos * 0.6662F) * 1.4F * animationSpeed;
        this.left_foot.rotationZ = MathHelper.cos(animationPos * 0.6662F + (float) Math.PI) * 1.4F * animationSpeed;
        this.right_wing.rotationY = -flapAngle * 0.75F;
        this.left_wing.rotationY = flapAngle * 0.75F;
        this.body.rotationZ = MathHelper.cos(animationPos * 0.6662F) * 0.1F * animationSpeed;
        this.head.rotationZ = MathHelper.cos(animationPos * 0.6662F) * 0.1F * animationSpeed;
    }
}
