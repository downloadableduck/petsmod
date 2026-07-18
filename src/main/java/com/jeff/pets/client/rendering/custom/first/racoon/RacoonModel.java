package com.jeff.pets.client.rendering.custom.first.racoon;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Racoon;
import net.minecraft.client.model.Cuboid;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

public class RacoonModel extends PetModel<@NotNull Racoon> {
    private final Cuboid root;
    private final Cuboid head;
    private final Cuboid body;
    private final Cuboid left_hind_leg;
    private final Cuboid right_hind_leg;
    private final Cuboid left_front_leg;
    private final Cuboid right_front_leg;
    private final Cuboid tail;

    public RacoonModel() {
        textureWidth = 64;
        textureHeight = 64;

        root = new Cuboid(this);
        root.setRotationPoint(-1.0F, 16.5F, -3.0F);


        head = new Cuboid(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        root.addChild(head);
        head.setTextureOffset(0, 15).addBox(-2.5F, -7.0F, -2.0F, 7, 5, 5, 0.0F, false);
        head.setTextureOffset(28, 28).addBox(1.5F, -9.0F, 1.0F, 2, 2, 1, 0.0F, false);
        head.setTextureOffset(0, 30).addBox(-1.5F, -9.0F, 1.0F, 2, 2, 1, 0.0F, false);
        head.setTextureOffset(1, 26).addBox(-0.5F, -4.0F, -4.0F, 3, 2, 2, 0.0F, false);
        head.setTextureOffset(24, 12).addBox(-3.5F, -5.0F, -2.0F, 1, 3, 5, 0.0F, false);
        head.setTextureOffset(24, 20).addBox(4.5F, -5.0F, -2.0F, 1, 3, 5, 0.0F, false);

        body = new Cuboid(this);
        body.setRotationPoint(1.0F, -0.5F, -3.0F);
        head.addChild(body);
        setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-3.0F, 4.0F, -3.5F, 6, 9, 6, 0.0F, false);

        left_hind_leg = new Cuboid(this);
        left_hind_leg.setRotationPoint(-4.0F, 1.0F, 10.0F);
        root.addChild(left_hind_leg);
        left_hind_leg.setTextureOffset(12, 25).addBox(2.5F, 2.0F, -2.0F, 2, 4, 2, 0.0F, false);

        right_hind_leg = new Cuboid(this);
        right_hind_leg.setRotationPoint(0.0F, 1.0F, 10.0F);
        root.addChild(right_hind_leg);
        right_hind_leg.setTextureOffset(20, 28).addBox(1.5F, 2.0F, -2.0F, 2, 4, 2, 0.0F, false);

        left_front_leg = new Cuboid(this);
        left_front_leg.setRotationPoint(-4.0F, 1.0F, 3.0F);
        root.addChild(left_front_leg);
        left_front_leg.setTextureOffset(12, 25).addBox(2.5F, 2.0F, -1.0F, 2, 4, 2, 0.0F, false);

        right_front_leg = new Cuboid(this);
        right_front_leg.setRotationPoint(0.0F, 1.0F, 3.0F);
        root.addChild(right_front_leg);
        right_front_leg.setTextureOffset(20, 28).addBox(1.5F, 2.0F, -1.0F, 2, 4, 2, 0.0F, false);

        tail = new Cuboid(this);
        tail.setRotationPoint(-3.0F, 0.5F, 12.0F);
        root.addChild(tail);
        setRotationAngle(tail, 1.5708F, 0.0F, 0.0F);
        tail.setTextureOffset(24, 0).addBox(2.0F, -2.0F, -1.0F, 4, 8, 4, 0.0F, false);
    }

    @Override
    public void render(Racoon racoon, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        root.render(alpha);
    }

    public void setRotationAngle(Cuboid Cuboid, float x, float y, float z) {
        Cuboid.pitch = x;
        Cuboid.yaw = y;
        Cuboid.roll = z;
    }

    @Override
    public void animateModel(Racoon fox, float f, float g, float h) {
        //this.body.xRot = ((float) Math.PI / 2F);
        this.tail.pitch = -0.05235988F;
        this.right_hind_leg.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.left_hind_leg.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.right_front_leg.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.left_front_leg.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        //this.head.setPos(-1.0F, 16.5F, -3.0F);
        //this.head.yRot = 0.0F;
        //this.head.zRot = 0;
        this.right_hind_leg.visible = true;
        this.left_hind_leg.visible = true;
        this.right_front_leg.visible = true;
        this.left_front_leg.visible = true;
        //this.body.setPos(0.0F, 16.0F, -6.0F);
        //this.body.zRot = 0.0F;
        this.right_hind_leg.setRotationPoint(-5.0F, 17.5F, 7.0F);
        this.left_hind_leg.setRotationPoint(-1.0F, 17.5F, 7.0F);
        this.tail.pitch = 2f;

        if (fox.hasVehicle()) {
            //this.body.xRot = 1.35f;
            //this.tail.z = 7;
        }
    }

    public void setAngles(Racoon fox, float f, float g, float h, float i, float j, float s) {
        this.head.pitch = j * ((float) Math.PI / 180F);
        this.head.yaw = i * ((float) Math.PI / 180F);
    }
}
