package com.jeff.pets.client.rendering.vanilla.vex;

import com.jeff.pets.mob.vanilla.hostile.ClientVex;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.VexEntityModel;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.util.AbsoluteHand;
import net.minecraft.util.math.MathHelper;

public class ClientVexModel extends BipedEntityModel<ClientVex> {
    private final Cuboid field_3601;
    private final Cuboid field_3602;

    public ClientVexModel() {
        this(0.0F);
    }

    public ClientVexModel(float f) {
        super(f, 0.0F, 64, 64);
        this.leftLeg.visible = false;
        this.headwear.visible = false;
        this.rightLeg = new Cuboid(this, 32, 0);
        this.rightLeg.addBox(-1.0F, -1.0F, -2.0F, 6, 10, 4, 0.0F);
        this.rightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.field_3602 = new Cuboid(this, 0, 32);
        this.field_3602.addBox(-20.0F, 0.0F, 0.0F, 20, 12, 1);
        this.field_3601 = new Cuboid(this, 0, 32);
        this.field_3601.mirror = true;
        this.field_3601.addBox(0.0F, 0.0F, 0.0F, 20, 12, 1);
    }

    @Override
    public void render(ClientVex vexEntity, float f, float g, float h, float i, float j, float k) {
        super.render(vexEntity, f, g, h, i, j, k);
        this.field_3602.render(k);
        this.field_3601.render(k);
    }

    @Override
    public void setAngles(ClientVex vexEntity, float f, float g, float h, float i, float j, float k) {
        super.method_17087(vexEntity, f, g, h, i, j, k);

        Cuboid var10000 = this.rightLeg;
        var10000.pitch += ((float)Math.PI / 5F);
        this.field_3602.rotationPointZ = 2.0F;
        this.field_3601.rotationPointZ = 2.0F;
        this.field_3602.rotationPointY = 1.0F;
        this.field_3601.rotationPointY = 1.0F;
        this.field_3602.yaw = 0.47123894F + MathHelper.cos(h * 0.8F) * (float)Math.PI * 0.05F;
        this.field_3601.yaw = -this.field_3602.yaw;
        this.field_3601.roll = -0.47123894F;
        this.field_3601.pitch = 0.47123894F;
        this.field_3602.pitch = 0.47123894F;
        this.field_3602.roll = 0.47123894F;
    }
}
