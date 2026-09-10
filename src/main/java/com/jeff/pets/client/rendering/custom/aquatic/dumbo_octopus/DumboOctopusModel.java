package com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.DumboOctopus;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;

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
        textureWidth = 32;
        textureHeight = 32;

        body = new ModelPart(this);
        body.setPivot(0.0F, 22.0F, 0.0F);
        setRotationAngle(body, 0.0F, -1.5708F, 0.0F);
        body.setTextureOffset(0, 0).method_18947(-4.0F, -5.0F, -3.0F, (int) 6.0F, (int) 6.0F, (int) 6.0F, 0.0F, false);

        left_ear = new ModelPart(this);
        left_ear.setPivot(-1.0F, 2.0F, 2.0F);
        body.add(left_ear);


        left_ear_r1 = new ModelPart(this);
        left_ear_r1.setPivot(-1.0F, -7.0F, 1.0F);
        left_ear.add(left_ear_r1);
        setRotationAngle(left_ear_r1, -0.5236F, 0.0F, 0.0F);
        left_ear_r1.setTextureOffset(8, 12).method_18947(-1.0F, -2.0F, -0.5F, (int) 2.0F, (int) 2.0F, (int) 1.0F, 0.0F, false);

        right_ear = new ModelPart(this);
        right_ear.setPivot(-2.0F, -5.0F, 3.0F);
        body.add(right_ear);


        right_ear_r1 = new ModelPart(this);
        right_ear_r1.setPivot(0.0F, 0.0F, -6.0F);
        right_ear.add(right_ear_r1);
        setRotationAngle(right_ear_r1, 0.5236F, 0.0F, 0.0F);
        right_ear_r1.setTextureOffset(8, 12).method_18947(-1.0F, -2.0F, -0.5F, (int) 2.0F, (int) 2.0F, (int) 1.0F, 0.0F, false);

        leg1 = new ModelPart(this);
        leg1.setPivot(-4.0F, 1.0F, 2.0F);
        body.add(leg1);
        leg1.setTextureOffset(0, 12).method_18947(-2.0F, -1.0F, -1.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);

        leg2 = new ModelPart(this);
        leg2.setPivot(-4.0F, 1.0F, -1.0F);
        body.add(leg2);
        leg2.setTextureOffset(0, 12).method_18947(-2.0F, -1.0F, -1.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);

        leg3 = new ModelPart(this);
        leg3.setPivot(-3.0F, 1.0F, -3.0F);
        body.add(leg3);
        leg3.setTextureOffset(0, 12).method_18947(-1.0F, -1.0F, -2.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);

        leg4 = new ModelPart(this);
        leg4.setPivot(0.0F, 1.0F, -3.0F);
        body.add(leg4);
        leg4.setTextureOffset(0, 12).method_18947(-1.0F, -1.0F, -2.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);

        leg5 = new ModelPart(this);
        leg5.setPivot(2.0F, 1.0F, -2.0F);
        body.add(leg5);
        leg5.setTextureOffset(0, 12).method_18947(0.0F, -1.0F, -1.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);

        leg6 = new ModelPart(this);
        leg6.setPivot(2.0F, 1.0F, 1.0F);
        body.add(leg6);
        leg6.setTextureOffset(0, 12).method_18947(0.0F, -1.0F, -1.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);

        leg7 = new ModelPart(this);
        leg7.setPivot(1.0F, 1.0F, 3.0F);
        body.add(leg7);
        leg7.setTextureOffset(0, 12).method_18947(-1.0F, -1.0F, 0.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);

        leg8 = new ModelPart(this);
        leg8.setPivot(-2.0F, 1.0F, 3.0F);
        body.add(leg8);
        leg8.setTextureOffset(0, 12).method_18947(-1.0F, -1.0F, 0.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float f, float g, float red, float green, float blue, float alpha) {
        body.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.posX = x;
        ModelPart.posY = y;
        ModelPart.posZ = z;
    }


    @Override
    public void setAngles(float f, float g, float h, float i, float k, float u, Entity entity) {
        super.setAngles(f, g, h, i, k, u, entity);
        DumboOctopus state = (DumboOctopus) entity;
        if (state.field_6749 > 0) {
            leg1.posZ = -state.tentacleAngle / 10;
            float rot = leg1.posZ;
            leg2.posZ = rot;
            leg3.posX = -rot;
            leg4.posX = -rot;
            leg5.posZ = -rot;
            leg6.posZ = -rot;
            leg7.posX = rot;
            leg8.posX = rot;
        }
    }
}
