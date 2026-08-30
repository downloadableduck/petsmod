package com.jeff.pets.client.rendering.vanilla.vex;

import com.jeff.pets.mob.vanilla.hostile.ClientVex;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.model.entity.HumanoidModel;
import net.minecraft.util.math.MathHelper;

public class ClientVexModel extends HumanoidModel {
    private final ModelPart field_3601;
    private final ModelPart field_3602;

    public ClientVexModel() {
        this(0.0F);
    }

    public ClientVexModel(float f) {
        super(f, 0.0F, 64, 64);
        this.leftLeg.visible = false;
        this.hat.visible = false;
        this.rightLeg = new ModelPart(this, 32, 0);
        this.rightLeg.addBox(-1.0F, -1.0F, -2.0F, 6, 10, 4, 0.0F);
        this.rightLeg.setPos(-1.9F, 12.0F, 0.0F);
        this.field_3602 = new ModelPart(this, 0, 32);
        this.field_3602.addBox(-20.0F, 0.0F, 0.0F, 20, 12, 1);
        this.field_3601 = new ModelPart(this, 0, 32);
        this.field_3601.flipped = true;
        this.field_3601.addBox(0.0F, 0.0F, 0.0F, 20, 12, 1);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float f, float g, float h, float i, float j, float k) {
        super.render(entity, f, g, h, i, j, k);
        this.field_3602.render(k);
        this.field_3601.render(k);
    }

    @Override
    public void setupAnimation(float f, float g, float h, float i, float j, float k, net.minecraft.entity.Entity entity) {
        super.setupAnimation(f, g, h, i, j, k, entity);

        ModelPart var10000 = this.rightLeg;
        var10000.rotationX += ((float)Math.PI / 5F);
        this.field_3602.z = 2.0F;
        this.field_3601.z = 2.0F;
        this.field_3602.y = 1.0F;
        this.field_3601.y = 1.0F;
        this.field_3602.rotationY = 0.47123894F + MathHelper.cos(h * 0.8F) * (float)Math.PI * 0.05F;
        this.field_3601.rotationY = -this.field_3602.rotationY;
        this.field_3601.rotationZ = -0.47123894F;
        this.field_3601.rotationX = 0.47123894F;
        this.field_3602.rotationX = 0.47123894F;
        this.field_3602.rotationZ = 0.47123894F;
    }
}
