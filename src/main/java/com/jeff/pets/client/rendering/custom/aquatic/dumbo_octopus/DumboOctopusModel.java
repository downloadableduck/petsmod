package com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.DumboOctopus;
import net.minecraft.client.model.Cuboid;
import org.jetbrains.annotations.NotNull;

public class DumboOctopusModel extends PetModel<@NotNull DumboOctopus> {
    private final Cuboid body;
    private final Cuboid left_ear;
    private final Cuboid left_ear_r1;
    private final Cuboid right_ear;
    private final Cuboid right_ear_r1;
    private final Cuboid leg1;
    private final Cuboid leg2;
    private final Cuboid leg3;
    private final Cuboid leg4;
    private final Cuboid leg5;
    private final Cuboid leg6;
    private final Cuboid leg7;
    private final Cuboid leg8;

    public DumboOctopusModel() {
        textureWidth = 32;
        textureHeight = 32;

        body = new Cuboid(this);
        body.setRotationPoint(0.0F, 22.0F, 0.0F);
        setRotationAngle(body, 0.0F, -1.5708F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-4.0F, -5.0F, -3.0F, 6, 6, 6, 0.0F, false);

        left_ear = new Cuboid(this);
        left_ear.setRotationPoint(-1.0F, 2.0F, 2.0F);
        body.addChild(left_ear);


        left_ear_r1 = new Cuboid(this);
        left_ear_r1.setRotationPoint(-1.0F, -7.0F, 1.0F);
        left_ear.addChild(left_ear_r1);
        setRotationAngle(left_ear_r1, -0.5236F, 0.0F, 0.0F);
        left_ear_r1.setTextureOffset(8, 12).addBox(-1.0F, -2.0F, -0.5F, 2, 2, 1, 0.0F, false);

        right_ear = new Cuboid(this);
        right_ear.setRotationPoint(-2.0F, -5.0F, 3.0F);
        body.addChild(right_ear);


        right_ear_r1 = new Cuboid(this);
        right_ear_r1.setRotationPoint(0.0F, 0.0F, -6.0F);
        right_ear.addChild(right_ear_r1);
        setRotationAngle(right_ear_r1, 0.5236F, 0.0F, 0.0F);
        right_ear_r1.setTextureOffset(8, 12).addBox(-1.0F, -2.0F, -0.5F, 2, 2, 1, 0.0F, false);

        leg1 = new Cuboid(this);
        leg1.setRotationPoint(-4.0F, 1.0F, 2.0F);
        body.addChild(leg1);
        leg1.setTextureOffset(0, 12).addBox(-2.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false);

        leg2 = new Cuboid(this);
        leg2.setRotationPoint(-4.0F, 1.0F, -1.0F);
        body.addChild(leg2);
        leg2.setTextureOffset(0, 12).addBox(-2.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false);

        leg3 = new Cuboid(this);
        leg3.setRotationPoint(-3.0F, 1.0F, -3.0F);
        body.addChild(leg3);
        leg3.setTextureOffset(0, 12).addBox(-1.0F, -1.0F, -2.0F, 2, 2, 2, 0.0F, false);

        leg4 = new Cuboid(this);
        leg4.setRotationPoint(0.0F, 1.0F, -3.0F);
        body.addChild(leg4);
        leg4.setTextureOffset(0, 12).addBox(-1.0F, -1.0F, -2.0F, 2, 2, 2, 0.0F, false);

        leg5 = new Cuboid(this);
        leg5.setRotationPoint(2.0F, 1.0F, -2.0F);
        body.addChild(leg5);
        leg5.setTextureOffset(0, 12).addBox(0.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false);

        leg6 = new Cuboid(this);
        leg6.setRotationPoint(2.0F, 1.0F, 1.0F);
        body.addChild(leg6);
        leg6.setTextureOffset(0, 12).addBox(0.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false);

        leg7 = new Cuboid(this);
        leg7.setRotationPoint(1.0F, 1.0F, 3.0F);
        body.addChild(leg7);
        leg7.setTextureOffset(0, 12).addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F, false);

        leg8 = new Cuboid(this);
        leg8.setRotationPoint(-2.0F, 1.0F, 3.0F);
        body.addChild(leg8);
        leg8.setTextureOffset(0, 12).addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F, false);
    }

    @Override
    public void render(DumboOctopus octopus, float f, float packedLight, float packedOverlay, float red, float green, float alpha) {
        body.render(alpha);
    }

    public void setRotationAngle(Cuboid Cuboid, float x, float y, float z) {
        Cuboid.pitch = x;
        Cuboid.yaw = y;
        Cuboid.roll = z;
    }


    @Override
    public void setAngles(DumboOctopus state, float f, float g, float h, float i, float k, float s) {
        super.setAngles(state, f, g, h, i, k, s);
        if (state.limbDistance > 0) {
            leg1.roll = -state.tentacleAngle / 10;
            float rot = leg1.roll;
            leg2.roll = rot;
            leg3.pitch = -rot;
            leg4.pitch = -rot;
            leg5.roll = -rot;
            leg6.roll = -rot;
            leg7.pitch = rot;
            leg8.pitch = rot;
        }
    }
}
