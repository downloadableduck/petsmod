package com.jeff.pets.client.rendering.custom.first.penguin;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Penguin;
import net.minecraft.client.model.Cuboid;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

public class PenguinModel extends PetModel<@NotNull Penguin> {

    private final Cuboid root;
    private final Cuboid body;
    private final Cuboid left_wing;
    private final Cuboid right_wing;
    private final Cuboid left_foot;
    private final Cuboid left_foot_r1;
    private final Cuboid right_foot;
    private final Cuboid right_foot_r1;
    private final Cuboid tail;
    private final Cuboid head;
    private final Cuboid beak;

    public PenguinModel() {
        textureWidth = 64;
        textureHeight = 64;

        root = new Cuboid(this);
        root.setRotationPoint(0.0F, 28.0F, 0.0F);
        setRotationAngle(root, 0.0F, 1.5708F, 0.0F);


        body = new Cuboid(this);
        body.setRotationPoint(0.0F, 4.0F, 0.0F);
        root.addChild(body);
        body.setTextureOffset(0, 0).addBox(-8.0F, -20.0F, 0.0F, 8, 12, 8, 0.0F, false);

        left_wing = new Cuboid(this);
        left_wing.setRotationPoint(-4.0F, -15.0F, 8.5F);
        root.addChild(left_wing);
        setRotationAngle(left_wing, 0.0F, 0.0F, -1.5708F);
        left_wing.setTextureOffset(24, 25).addBox(-10.0F, -3.0F, -0.5F, 10, 6, 1, 0.0F, false);

        right_wing = new Cuboid(this);
        right_wing.setRotationPoint(-4.0F, -15.0F, 0.0F);
        root.addChild(right_wing);
        setRotationAngle(right_wing, 0.0F, 0.0F, -1.5708F);
        right_wing.setTextureOffset(24, 25).addBox(-10.0F, -3.0F, -1.0F, 10, 6, 1, 0.0F, false);

        left_foot = new Cuboid(this);
        left_foot.setRotationPoint(-4.0F, -4.0F, 2.0F);
        root.addChild(left_foot);


        left_foot_r1 = new Cuboid(this);
        left_foot_r1.setRotationPoint(5.0F, 1.0F, 0.0F);
        left_foot.addChild(left_foot_r1);
        setRotationAngle(left_foot_r1, 0.0F, 0.0F, -1.5708F);
        left_foot_r1.setTextureOffset(0, 32).addBox(-1.0F, -6.0F, 2.0F, 2, 6, 4, 0.0F, false);

        right_foot = new Cuboid(this);
        right_foot.setRotationPoint(-4.0F, -4.0F, 7.0F);
        root.addChild(right_foot);


        right_foot_r1 = new Cuboid(this);
        right_foot_r1.setRotationPoint(5.0F, 1.0F, -9.0F);
        right_foot.addChild(right_foot_r1);
        setRotationAngle(right_foot_r1, 0.0F, 0.0F, -1.5708F);
        right_foot_r1.setTextureOffset(32, 0).addBox(-1.0F, -6.0F, 2.0F, 2, 6, 4, 0.0F, false);

        tail = new Cuboid(this);
        tail.setRotationPoint(-3.0F, -3.0F, 2.0F);
        root.addChild(tail);
        tail.setTextureOffset(-8, -8).addBox(3.0F, -4.0F, 0.0F, 2, 2, 4, 0.0F, false);

        head = new Cuboid(this);
        head.setRotationPoint(1.0F, -5.0F, 3.0F);
        root.addChild(head);
        head.setTextureOffset(0, 20).addBox(-8.0F, -17.0F, -2.0F, 6, 6, 6, 0.0F, false);

        beak = new Cuboid(this);
        beak.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(beak);
        beak.setTextureOffset(12, 32).addBox(-2.0F, -14.0F, 0.0F, 4, 2, 2, 0.0F, false);
    }

    @Override
    public void render(Penguin penguin, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        root.render(alpha);
    }

    public void setRotationAngle(Cuboid Cuboid, float x, float y, float z) {
        Cuboid.pitch = x;
        Cuboid.yaw = y;
        Cuboid.roll = z;
    }

    @Override
    public void setAngles(Penguin state, float f, float g, float h, float i, float k, float s) {
        float flapAngle = (MathHelper.sin(state.flap) + 1.0F) * state.flapSpeed;
        this.head.pitch = state.pitch * ((float) Math.PI / 180F);
        float animationSpeed = state.limbDistance;
        float animationPos = state.limbAngle;
        this.right_foot.roll = MathHelper.cos(animationPos * 0.6662F) * 1.4F * animationSpeed;
        this.left_foot.roll = MathHelper.cos(animationPos * 0.6662F + (float) Math.PI) * 1.4F * animationSpeed;
        this.right_wing.yaw = -flapAngle * 0.75F;
        this.left_wing.yaw = flapAngle * 0.75F;
        this.body.roll = MathHelper.cos(animationPos * 0.6662F) * 0.1F * animationSpeed;
        this.head.roll = MathHelper.cos(animationPos * 0.6662F) * 0.1F * animationSpeed;
    }
}
