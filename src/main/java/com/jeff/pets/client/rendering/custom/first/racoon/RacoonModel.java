//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.custom.first.racoon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;
import com.jeff.pets.mob.custom.first.Racoon;

public class RacoonModel extends PetModel {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart left_hind_leg;
    private final ModelPart right_hind_leg;
    private final ModelPart left_front_leg;
    private final ModelPart right_front_leg;
    private final ModelPart tail;

    public RacoonModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.root = new ModelPart(this);
        this.root.setPos(-1.0F, 16.5F, -3.0F);
        this.head = new ModelPart(this);
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.root.addChild(this.head);
        this.head.setTextureCoords(0, 15).addBox(-2.5F, -7.0F, -2.0F, 7, 5, 5, 0.0F);
        this.head.setTextureCoords(28, 28).addBox(1.5F, -9.0F, 1.0F, 2, 2, 1, 0.0F);
        this.head.setTextureCoords(0, 30).addBox(-1.5F, -9.0F, 1.0F, 2, 2, 1, 0.0F);
        this.head.setTextureCoords(1, 26).addBox(-0.5F, -4.0F, -4.0F, 3, 2, 2, 0.0F);
        this.head.setTextureCoords(24, 12).addBox(-3.5F, -5.0F, -2.0F, 1, 3, 5, 0.0F);
        this.head.setTextureCoords(24, 20).addBox(4.5F, -5.0F, -2.0F, 1, 3, 5, 0.0F);
        this.body = new ModelPart(this);
        this.body.setPos(1.0F, -0.5F, -3.0F);
        this.head.addChild(this.body);
        this.setRotationAngle(this.body, 1.5708F, 0.0F, 0.0F);
        this.body.setTextureCoords(0, 0).addBox(-3.0F, 4.0F, -3.5F, 6, 9, 6, 0.0F);
        this.left_hind_leg = new ModelPart(this);
        this.left_hind_leg.setPos(-4.0F, 1.0F, 10.0F);
        this.root.addChild(this.left_hind_leg);
        this.left_hind_leg.setTextureCoords(12, 25).addBox(2.5F, 2.0F, -2.0F, 2, 4, 2, 0.0F);
        this.right_hind_leg = new ModelPart(this);
        this.right_hind_leg.setPos(0.0F, 1.0F, 10.0F);
        this.root.addChild(this.right_hind_leg);
        this.right_hind_leg.setTextureCoords(20, 28).addBox(1.5F, 2.0F, -2.0F, 2, 4, 2, 0.0F);
        this.left_front_leg = new ModelPart(this);
        this.left_front_leg.setPos(-4.0F, 1.0F, 3.0F);
        this.root.addChild(this.left_front_leg);
        this.left_front_leg.setTextureCoords(12, 25).addBox(2.5F, 2.0F, -1.0F, 2, 4, 2, 0.0F);
        this.right_front_leg = new ModelPart(this);
        this.right_front_leg.setPos(0.0F, 1.0F, 3.0F);
        this.root.addChild(this.right_front_leg);
        this.right_front_leg.setTextureCoords(20, 28).addBox(1.5F, 2.0F, -1.0F, 2, 4, 2, 0.0F);
        this.tail = new ModelPart(this);
        this.tail.setPos(-3.0F, 0.5F, 12.0F);
        this.root.addChild(this.tail);
        this.setRotationAngle(this.tail, 1.5708F, 0.0F, 0.0F);
        this.tail.setTextureCoords(24, 0).addBox(2.0F, -2.0F, -1.0F, 4, 8, 4, 0.0F);
    }

    public void render(Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        this.root.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.rotationX = x;
        ModelPart.rotationY = y;
        ModelPart.rotationZ = z;
    }

    public void animateModel(LivingEntity entity, float f, float g, float h) {
        Racoon fox = entity instanceof Racoon ? (Racoon)entity : null;
        this.tail.rotationX = -0.05235988F;
        this.right_hind_leg.rotationX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.left_hind_leg.rotationX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
        this.right_front_leg.rotationX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
        this.left_front_leg.rotationX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.right_hind_leg.visible = true;
        this.left_hind_leg.visible = true;
        this.right_front_leg.visible = true;
        this.left_front_leg.visible = true;
        this.tail.rotationX = 2.0F;
        if (fox.hasVehicle()) {
        }

    }

    public void setupAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netyaw, float headPitch, float scale, Entity entity) {
        this.head.rotationX = headPitch * 0.017453292F;
        this.head.rotationY = netyaw * 0.017453292F;
    }
}
