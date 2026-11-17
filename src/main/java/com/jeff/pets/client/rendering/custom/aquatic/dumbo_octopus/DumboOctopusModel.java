//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus;

import net.minecraft.entity.Entity;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;
import com.jeff.pets.mob.custom.aquatic.DumboOctopus;

public class DumboOctopusModel extends PetModel {
    private final ModelPart body;
    private final ModelPart left_ear;
    private final ModelPart left_ear_r1;
    private final ModelPart right_ear;
    private final ModelPart right_ear_r1;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart leg5;
    private final ModelPart leg6;
    private final ModelPart leg7;
    private final ModelPart leg8;

    public DumboOctopusModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.body = new ModelPart(this);
        this.body.setPos(0.0F, 22.0F, 0.0F);
        this.setRotationAngle(this.body, 0.0F, -1.5708F, 0.0F);
        this.body.setTextureCoords(0, 0).addBox(-4.0F, -5.0F, -3.0F, 6, 6, 6, 0.0F);
        this.left_ear = new ModelPart(this);
        this.left_ear.setPos(-1.0F, 2.0F, 2.0F);
        this.body.addChild(this.left_ear);
        this.left_ear_r1 = new ModelPart(this);
        this.left_ear_r1.setPos(-1.0F, -7.0F, 1.0F);
        this.left_ear.addChild(this.left_ear_r1);
        this.setRotationAngle(this.left_ear_r1, -0.5236F, 0.0F, 0.0F);
        this.left_ear_r1.setTextureCoords(8, 12).addBox(-1.0F, -2.0F, -0.5F, 2, 2, 1, 0.0F);
        this.right_ear = new ModelPart(this);
        this.right_ear.setPos(-2.0F, -5.0F, 3.0F);
        this.body.addChild(this.right_ear);
        this.right_ear_r1 = new ModelPart(this);
        this.right_ear_r1.setPos(0.0F, 0.0F, -6.0F);
        this.right_ear.addChild(this.right_ear_r1);
        this.setRotationAngle(this.right_ear_r1, 0.5236F, 0.0F, 0.0F);
        this.right_ear_r1.setTextureCoords(8, 12).addBox(-1.0F, -2.0F, -0.5F, 2, 2, 1, 0.0F);
        this.leg1 = new ModelPart(this);
        this.leg1.setPos(-4.0F, 1.0F, 2.0F);
        this.body.addChild(this.leg1);
        this.leg1.setTextureCoords(0, 12).addBox(-2.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.leg2 = new ModelPart(this);
        this.leg2.setPos(-4.0F, 1.0F, -1.0F);
        this.body.addChild(this.leg2);
        this.leg2.setTextureCoords(0, 12).addBox(-2.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.leg3 = new ModelPart(this);
        this.leg3.setPos(-3.0F, 1.0F, -3.0F);
        this.body.addChild(this.leg3);
        this.leg3.setTextureCoords(0, 12).addBox(-1.0F, -1.0F, -2.0F, 2, 2, 2, 0.0F);
        this.leg4 = new ModelPart(this);
        this.leg4.setPos(0.0F, 1.0F, -3.0F);
        this.body.addChild(this.leg4);
        this.leg4.setTextureCoords(0, 12).addBox(-1.0F, -1.0F, -2.0F, 2, 2, 2, 0.0F);
        this.leg5 = new ModelPart(this);
        this.leg5.setPos(2.0F, 1.0F, -2.0F);
        this.body.addChild(this.leg5);
        this.leg5.setTextureCoords(0, 12).addBox(0.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.leg6 = new ModelPart(this);
        this.leg6.setPos(2.0F, 1.0F, 1.0F);
        this.body.addChild(this.leg6);
        this.leg6.setTextureCoords(0, 12).addBox(0.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.leg7 = new ModelPart(this);
        this.leg7.setPos(1.0F, 1.0F, 3.0F);
        this.body.addChild(this.leg7);
        this.leg7.setTextureCoords(0, 12).addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.leg8 = new ModelPart(this);
        this.leg8.setPos(-2.0F, 1.0F, 3.0F);
        this.body.addChild(this.leg8);
        this.leg8.setTextureCoords(0, 12).addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
    }

    public void render(Entity entity, float f, float packedLight, float packedOverlay, float red, float green, float alpha) {
        this.body.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.rotationX = x;
        ModelPart.rotationY = y;
        ModelPart.rotationZ = z;
    }

    public void setupAnimation(float f, float g, float h, float i, float k, float s, Entity entity) {
        DumboOctopus state = (DumboOctopus)entity;
        super.setupAnimation(f, g, h, i, k, s, entity);
        if (state.walkAnimationSpeed > 0.0F) {
            this.leg1.rotationZ = -state.tentacleAngle / 10.0F;
            float rot = this.leg1.rotationZ;
            this.leg2.rotationZ = rot;
            this.leg3.rotationX = -rot;
            this.leg4.rotationX = -rot;
            this.leg5.rotationZ = -rot;
            this.leg6.rotationZ = -rot;
            this.leg7.rotationX = rot;
            this.leg8.rotationX = rot;
        }

    }
}
