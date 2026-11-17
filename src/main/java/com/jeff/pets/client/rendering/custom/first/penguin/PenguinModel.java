//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.custom.first.penguin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;
import com.jeff.pets.mob.custom.first.Penguin;

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
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.root = new ModelPart(this);
        this.root.setPos(0.0F, 28.0F, 0.0F);
        this.setRotationAngle(this.root, 0.0F, 1.5708F, 0.0F);
        this.body = new ModelPart(this);
        this.body.setPos(0.0F, 4.0F, 0.0F);
        this.root.addChild(this.body);
        this.body.setTextureCoords(0, 0).addBox(-8.0F, -20.0F, 0.0F, 8, 12, 8, 0.0F);
        this.left_wing = new ModelPart(this);
        this.left_wing.setPos(-4.0F, -15.0F, 8.5F);
        this.root.addChild(this.left_wing);
        this.setRotationAngle(this.left_wing, 0.0F, 0.0F, -1.5708F);
        this.left_wing.setTextureCoords(24, 25).addBox(-10.0F, -3.0F, -0.5F, 10, 6, 1, 0.0F);
        this.right_wing = new ModelPart(this);
        this.right_wing.setPos(-4.0F, -15.0F, 0.0F);
        this.root.addChild(this.right_wing);
        this.setRotationAngle(this.right_wing, 0.0F, 0.0F, -1.5708F);
        this.right_wing.setTextureCoords(24, 25).addBox(-10.0F, -3.0F, -1.0F, 10, 6, 1, 0.0F);
        this.left_foot = new ModelPart(this);
        this.left_foot.setPos(-4.0F, -4.0F, 2.0F);
        this.root.addChild(this.left_foot);
        this.left_foot_r1 = new ModelPart(this);
        this.left_foot_r1.setPos(5.0F, 1.0F, 0.0F);
        this.left_foot.addChild(this.left_foot_r1);
        this.setRotationAngle(this.left_foot_r1, 0.0F, 0.0F, -1.5708F);
        this.left_foot_r1.setTextureCoords(0, 32).addBox(-1.0F, -6.0F, 2.0F, 2, 6, 4, 0.0F);
        this.right_foot = new ModelPart(this);
        this.right_foot.setPos(-4.0F, -4.0F, 7.0F);
        this.root.addChild(this.right_foot);
        this.right_foot_r1 = new ModelPart(this);
        this.right_foot_r1.setPos(5.0F, 1.0F, -9.0F);
        this.right_foot.addChild(this.right_foot_r1);
        this.setRotationAngle(this.right_foot_r1, 0.0F, 0.0F, -1.5708F);
        this.right_foot_r1.setTextureCoords(32, 0).addBox(-1.0F, -6.0F, 2.0F, 2, 6, 4, 0.0F);
        this.tail = new ModelPart(this);
        this.tail.setPos(-3.0F, -3.0F, 2.0F);
        this.root.addChild(this.tail);
        this.tail.setTextureCoords(-8, -8).addBox(3.0F, -4.0F, 0.0F, 2, 2, 4, 0.0F);
        this.head = new ModelPart(this);
        this.head.setPos(1.0F, -5.0F, 3.0F);
        this.root.addChild(this.head);
        this.head.setTextureCoords(0, 20).addBox(-8.0F, -17.0F, -2.0F, 6, 6, 6, 0.0F);
        this.beak = new ModelPart(this);
        this.beak.setPos(0.0F, 0.0F, 0.0F);
        this.head.addChild(this.beak);
        this.beak.setTextureCoords(12, 32).addBox(-2.0F, -14.0F, 0.0F, 4, 2, 2, 0.0F);
    }

    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netyaw, float headPitch, float scale) {
        this.setupAnimation(limbSwing, limbSwingAmount, ageInTicks, netyaw, headPitch, scale, entity);
        this.root.render(scale);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.rotationX = x;
        ModelPart.rotationY = y;
        ModelPart.rotationZ = z;
    }

    public void setupAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netyaw, float headPitch, float scale, Entity entity) {
        Penguin state = entity instanceof Penguin ? (Penguin)entity : null;
        float flap = state != null ? state.flap : 0.0F;
        float flapSpeed = state != null ? state.flapSpeed : 0.0F;
        float flapAngle = (MathHelper.sin(flap) + 1.0F) * flapSpeed;
        this.head.rotationX = headPitch * 0.017453292F;
        this.head.rotationY = netyaw * 0.017453292F;
        this.right_foot.rotationZ = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_foot.rotationZ = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
        this.right_wing.rotationY = -flapAngle * 0.75F;
        this.left_wing.rotationY = flapAngle * 0.75F;
        this.body.rotationZ = MathHelper.cos(limbSwing * 0.6662F) * 0.1F * limbSwingAmount;
        this.head.rotationZ = MathHelper.cos(limbSwing * 0.6662F) * 0.1F * limbSwingAmount;
    }

    public void animateModel(LivingEntity entity, float f, float g, float h) {
        this.right_foot.rotationX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
        this.left_foot.rotationX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
    }
}
