package com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.DumboOctopus;
import net.minecraft.client.render.model.ModelPart;
import org.jetbrains.annotations.NotNull;

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
        textureWidth /*textureWidth*/ = 32;
        textureHeight /*textureHeight*/ = 32;

        body = new ModelPart(this);
        body.setPos(0.0F, 22.0F, 0.0F);
        setRotationAngle(body, 0.0F, -1.5708F, 0.0F);
        body.setTextureCoords(0, 0).addBox(-4.0F, -5.0F, -3.0F, 6, 6, 6, 0.0F);

        left_ear = new ModelPart(this);
        left_ear.setPos(-1.0F, 2.0F, 2.0F);
        body.addChild(left_ear);


        left_ear_r1 = new ModelPart(this);
        left_ear_r1.setPos(-1.0F, -7.0F, 1.0F);
        left_ear.addChild(left_ear_r1);
        setRotationAngle(left_ear_r1, -0.5236F, 0.0F, 0.0F);
        left_ear_r1.setTextureCoords(8, 12).addBox(-1.0F, -2.0F, -0.5F, 2, 2, 1, 0.0F);

        right_ear = new ModelPart(this);
        right_ear.setPos(-2.0F, -5.0F, 3.0F);
        body.addChild(right_ear);


        right_ear_r1 = new ModelPart(this);
        right_ear_r1.setPos(0.0F, 0.0F, -6.0F);
        right_ear.addChild(right_ear_r1);
        setRotationAngle(right_ear_r1, 0.5236F, 0.0F, 0.0F);
        right_ear_r1.setTextureCoords(8, 12).addBox(-1.0F, -2.0F, -0.5F, 2, 2, 1, 0.0F);

        leg1 = new ModelPart(this);
        leg1.setPos(-4.0F, 1.0F, 2.0F);
        body.addChild(leg1);
        leg1.setTextureCoords(0, 12).addBox(-2.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);

        leg2 = new ModelPart(this);
        leg2.setPos(-4.0F, 1.0F, -1.0F);
        body.addChild(leg2);
        leg2.setTextureCoords(0, 12).addBox(-2.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);

        leg3 = new ModelPart(this);
        leg3.setPos(-3.0F, 1.0F, -3.0F);
        body.addChild(leg3);
        leg3.setTextureCoords(0, 12).addBox(-1.0F, -1.0F, -2.0F, 2, 2, 2, 0.0F);

        leg4 = new ModelPart(this);
        leg4.setPos(0.0F, 1.0F, -3.0F);
        body.addChild(leg4);
        leg4.setTextureCoords(0, 12).addBox(-1.0F, -1.0F, -2.0F, 2, 2, 2, 0.0F);

        leg5 = new ModelPart(this);
        leg5.setPos(2.0F, 1.0F, -2.0F);
        body.addChild(leg5);
        leg5.setTextureCoords(0, 12).addBox(0.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);

        leg6 = new ModelPart(this);
        leg6.setPos(2.0F, 1.0F, 1.0F);
        body.addChild(leg6);
        leg6.setTextureCoords(0, 12).addBox(0.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);

        leg7 = new ModelPart(this);
        leg7.setPos(1.0F, 1.0F, 3.0F);
        body.addChild(leg7);
        leg7.setTextureCoords(0, 12).addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);

        leg8 = new ModelPart(this);
        leg8.setPos(-2.0F, 1.0F, 3.0F);
        body.addChild(leg8);
        leg8.setTextureCoords(0, 12).addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float f, float packedLight, float packedOverlay, float red, float green, float alpha) {
        body.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.rotationX = x;
        ModelPart.rotationY = y;
        ModelPart.rotationZ = z;
    }


    @Override
    public void setupAnimation(float f, float g, float h, float i, float k, float s, net.minecraft.entity.Entity entity) {
        DumboOctopus state = (DumboOctopus) entity;
        super.setupAnimation(f, g, h, i, k, s, entity);
        if (state.walkAnimationSpeed > 0) {
            leg1.rotationZ = -state.tentacleAngle / 10;
            float rot = leg1.rotationZ;
            leg2.rotationZ = rot;
            leg3.rotationX = -rot;
            leg4.rotationX = -rot;
            leg5.rotationZ = -rot;
            leg6.rotationZ = -rot;
            leg7.rotationX = rot;
            leg8.rotationX = rot;
        }
    }
}
