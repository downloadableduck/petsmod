//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.custom.first.duck;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.util.helper.MathHelper;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;
import com.jeff.pets.mob.custom.first.Duck;

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
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.root = new ModelPart(this);
        this.root.setPos(0.0F, 15.0F, -4.0F);
        this.head = new ModelPart(this);
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.root.addChild(this.head);
        this.head.setTextureCoords(0, 0).addBox(-1.5F, -5.0F, -1.5F, 3, 6, 3, 0.0F);
        this.bill_r1 = new ModelPart(this);
        this.bill_r1.setPos(1.0F, -15.9F, -9.1F);
        this.head.addChild(this.bill_r1);
        this.setRotationAngle(this.bill_r1, 3.1176F, 0.0244F, -0.0049F);
        this.bill_r1.setTextureCoords(14, 0).addBox(-2.2F, -13.0F, -8.0F, 2, 1, 2, 0.0F);
        this.body = new ModelPart(this);
        this.body.setPos(0.0F, 1.0F, 4.0F);
        this.root.addChild(this.body);
        this.setRotationAngle(this.body, 1.5708F, 0.0F, 0.0F);
        this.body.setTextureCoords(1, 10).addBox(-2.5F, -4.0F, -4.0F, 5, 8, 5, 0.0F);
        this.left_wing = new ModelPart(this);
        this.left_wing.setPos(3.0F, 0.0F, 4.0F);
        this.root.addChild(this.left_wing);
        this.left_wing.setTextureCoords(24, 13).addBox(-0.5F, 0.0F, -3.0F, 1, 3, 6, 0.0F);
        this.right_wing = new ModelPart(this);
        this.right_wing.setPos(-3.0F, 0.0F, 4.0F);
        this.root.addChild(this.right_wing);
        this.right_wing.setTextureCoords(24, 13).addBox(-0.5F, 0.0F, -3.0F, 1, 3, 6, 0.0F);
        this.left_leg = new ModelPart(this);
        this.left_leg.setPos(1.0F, 4.0F, 5.0F);
        this.root.addChild(this.left_leg);
        this.left_leg.setTextureCoords(26, 0).addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3, 0.0F);
        this.right_leg = new ModelPart(this);
        this.right_leg.setPos(-2.0F, 4.0F, 5.0F);
        this.root.addChild(this.right_leg);
        this.right_leg.setTextureCoords(26, 0).addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3, 0.0F);
        this.tail = new ModelPart(this);
        this.tail.setPos(0.0F, 4.0F, 9.0F);
        this.root.addChild(this.tail);
        this.tail.setTextureCoords(0, 23).addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F);
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
        Duck state = entity instanceof Duck ? (Duck)entity : null;
        float flapSpeed = state != null ? state.flapSpeed : 0.0F;
        boolean onGround = state != null ? state.onGround : true;
        boolean riding = state != null && (state.vehicle != null);
        float flapAngle = onGround ? 0.0F : (MathHelper.sin(ageInTicks) + 1.0F) * flapSpeed;
        this.head.rotationX = headPitch * 0.017453292F;
        this.head.rotationY = netyaw * 0.017453292F;
        this.right_leg.rotationX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotationX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
        this.right_wing.rotationZ = flapAngle;
        this.left_wing.rotationZ = -flapAngle;
        if (riding) {
            this.root.x = 0.4F;
            this.root.y = 17.5F;
            this.root.z = -4.0F;
            this.right_leg.visible = false;
            this.left_leg.visible = false;
        } else {
            this.root.x = 0.0F;
            this.root.y = 15.0F;
            this.root.z = -4.0F;
            this.right_leg.visible = true;
            this.left_leg.visible = true;
        }

    }

    public void animateModel(Mob entity, float f, float g, float h) {
        this.right_leg.rotationX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
        this.left_leg.rotationX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
    }
}
