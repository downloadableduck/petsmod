package com.jeff.pets.client.rendering.vanilla.bat;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.passive.ClientBat;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.BatEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;
public class ClientBatModel extends EntityModel<ClientBat> {
    private final Cuboid head;
    private final Cuboid body;
    private final Cuboid rightWing;
    private final Cuboid leftWing;
    private final Cuboid rightWingTip;
    private final Cuboid leftWingTip;

    public ClientBatModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new Cuboid(this, 0, 0);
        this.head.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
        Cuboid Cuboid = new Cuboid(this, 24, 0);
        Cuboid.addBox(-4.0F, -6.0F, -2.0F, 3, 4, 1);
        this.head.addChild(Cuboid);
        Cuboid Cuboid2 = new Cuboid(this, 24, 0);
        Cuboid2.mirror = true;
        Cuboid2.addBox(1.0F, -6.0F, -2.0F, 3, 4, 1);
        this.head.addChild(Cuboid2);
        this.body = new Cuboid(this, 0, 16);
        this.body.addBox(-3.0F, 4.0F, -3.0F, 6, 12, 6);
        this.body.setTextureOffset(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10, 6, 1);
        this.rightWing = new Cuboid(this, 42, 0);
        this.rightWing.addBox(-12.0F, 1.0F, 1.5F, 10, 16, 1);
        this.rightWingTip = new Cuboid(this, 24, 16);
        this.rightWingTip.setRotationPoint(-12.0F, 1.0F, 1.5F);
        this.rightWingTip.addBox(-8.0F, 1.0F, 0.0F, 8, 12, 1);
        this.leftWing = new Cuboid(this, 42, 0);
        this.leftWing.mirror = true;
        this.leftWing.addBox(2.0F, 1.0F, 1.5F, 10, 16, 1);
        this.leftWingTip = new Cuboid(this, 24, 16);
        this.leftWingTip.mirror = true;
        this.leftWingTip.setRotationPoint(12.0F, 1.0F, 1.5F);
        this.leftWingTip.addBox(0.0F, 1.0F, 0.0F, 8, 12, 1);
        this.body.addChild(this.rightWing);
        this.body.addChild(this.leftWing);
        this.rightWing.addChild(this.rightWingTip);
        this.leftWing.addChild(this.leftWingTip);
    }

    public Iterable<Cuboid> getParts() {
        return ImmutableList.of(this.head, this.body);
    }

    @Override
    public void setAngles(ClientBat bat, float f, float g, float h, float i, float j, float k) {
        if (bat.isPassenger()) {
            this.head.pitch = j * ((float)Math.PI / 180F);
            this.head.yaw = (float)Math.PI - i * ((float)Math.PI / 180F);
            this.head.roll = (float)Math.PI;
            this.head.setRotationPoint(0.0F, -2.0F, 0.0F);
            this.rightWing.setRotationPoint(-3.0F, 0.0F, 3.0F);
            this.leftWing.setRotationPoint(3.0F, 0.0F, 3.0F);
            this.body.pitch = (float)Math.PI;
            this.rightWing.pitch = -0.15707964F;
            this.rightWing.yaw = -1.2566371F;
            this.rightWingTip.yaw = -1.7278761F;
            this.leftWing.pitch = this.rightWing.pitch;
            this.leftWing.yaw = -this.rightWing.yaw;
            this.leftWingTip.yaw = -this.rightWingTip.yaw;
        } else {
            this.head.pitch = j * ((float)Math.PI / 180F);
            this.head.yaw = i * ((float)Math.PI / 180F);
            this.head.roll = 0.0F;
            this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
            this.rightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
            this.leftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
            this.body.pitch = ((float)Math.PI / 4F) + MathHelper.cos(h * 0.1F) * 0.15F;
            this.body.yaw = 0.0F;
            this.rightWing.yaw = MathHelper.cos(h * 1.3F) * (float)Math.PI * 0.25F;
            this.leftWing.yaw = -this.rightWing.yaw;
            this.rightWingTip.yaw = this.rightWing.yaw * 0.5F;
            this.leftWingTip.yaw = -this.rightWing.yaw * 0.5F;
        }
    }

    @Override
    public void render(ClientBat bat, float f, float g, float h, float i, float k, float m) {
        this.setAngles(bat, f, g, h, i, k, m);
        this.head.render(m);
        this.body.render(m);
    }
}
