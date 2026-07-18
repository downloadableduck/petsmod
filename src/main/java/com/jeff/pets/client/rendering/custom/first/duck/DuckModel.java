package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.client.model.Cuboid;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

public class DuckModel extends PetModel<@NotNull Duck> {

    private final Cuboid root;
    private final Cuboid head;
    private final Cuboid bill_r1;
    private final Cuboid body;
    private final Cuboid left_wing;
    private final Cuboid right_wing;
    private final Cuboid left_leg;
    private final Cuboid right_leg;
    private final Cuboid tail;

    public DuckModel() {
        textureWidth = 64;
        textureHeight = 32;

        root = new Cuboid(this);
        root.setRotationPoint(0.0F, 15.0F, -4.0F);


        head = new Cuboid(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        root.addChild(head);
        head.setTextureOffset(0, 0).addBox(-1.5F, -5.0F, -1.5F, 3, 6, 3, 0.0F, false);

        bill_r1 = new Cuboid(this);
        bill_r1.setRotationPoint(1.0F, -15.9F, -9.1F);
        head.addChild(bill_r1);
        setRotationAngle(bill_r1, 3.1176F, 0.0244F, -0.0049F);
        bill_r1.setTextureOffset(14, 0).addBox(-2.2F, -13.0F, -8.0F, 2, 1, 2, 0.0F, false);

        body = new Cuboid(this);
        body.setRotationPoint(0.0F, 1.0F, 4.0F);
        root.addChild(body);
        setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
        body.setTextureOffset(1, 10).addBox(-2.5F, -4.0F, -4.0F, 5, 8, 5, 0.0F, false);

        left_wing = new Cuboid(this);
        left_wing.setRotationPoint(3.0F, 0.0F, 4.0F);
        root.addChild(left_wing);
        left_wing.setTextureOffset(24, 13).addBox(-0.5F, 0.0F, -3.0F, 1, 3, 6, 0.0F, false);

        right_wing = new Cuboid(this);
        right_wing.setRotationPoint(-3.0F, 0.0F, 4.0F);
        root.addChild(right_wing);
        right_wing.setTextureOffset(24, 13).addBox(-0.5F, 0.0F, -3.0F, 1, 3, 6, 0.0F, false);

        left_leg = new Cuboid(this);
        left_leg.setRotationPoint(1.0F, 4.0F, 5.0F);
        root.addChild(left_leg);
        left_leg.setTextureOffset(26, 0).addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3, 0.0F, false);

        right_leg = new Cuboid(this);
        right_leg.setRotationPoint(-2.0F, 4.0F, 5.0F);
        root.addChild(right_leg);
        right_leg.setTextureOffset(26, 0).addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3, 0.0F, false);

        tail = new Cuboid(this);
        tail.setRotationPoint(0.0F, 4.0F, 9.0F);
        root.addChild(tail);
        tail.setTextureOffset(0, 23).addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F, false);
    }

    @Override
    public void render(Duck duck, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        root.render(alpha);
    }

    public void setRotationAngle(Cuboid Cuboid, float x, float y, float z) {
        Cuboid.pitch = x;
        Cuboid.yaw = y;
        Cuboid.roll = z;
    }

    @Override
    public void setAngles(final Duck state, float f, float g, float h, float i, float j, float s) {
        float flapAngle = state.onGround ? 0 : (MathHelper.sin(h) + 1.0F) * state.flapSpeed;
        this.head.pitch = j * ((float) Math.PI / 180F);
        this.head.yaw = i * ((float) Math.PI / 180F);
        float animationSpeed = state.limbDistance;
        float animationPos = state.limbAngle;
        this.right_leg.pitch = MathHelper.cos(animationPos * 0.6662F) * 1.4F * animationSpeed;
        this.left_leg.pitch = MathHelper.cos(animationPos * 0.6662F + (float) Math.PI) * 1.4F * animationSpeed;
        this.right_wing.roll = flapAngle;
        this.left_wing.roll = -flapAngle;
        //thing is weird af in 1.16.5 and below, base y is 15, base x is 0, base z is -4
        if (state.hasVehicle()) {
            this.root.rotationPointX = 0.4F;
            this.root.rotationPointY = 17.5F;
            this.root.rotationPointZ = -4.0F;
            this.right_leg.visible = false;
            this.left_leg.visible = false;
        } else {
            this.root.rotationPointX = 0;
            this.root.rotationPointY = 15;
            this.root.rotationPointZ = -4;
            this.right_leg.visible = true;
            this.left_leg.visible = true;
        }
    }
}