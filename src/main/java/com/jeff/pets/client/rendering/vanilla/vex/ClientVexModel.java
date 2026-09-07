package com.jeff.pets.client.rendering.vanilla.vex;

import com.jeff.pets.mob.vanilla.hostile.ClientVex;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class ClientVexModel extends ModelBiped {
    private final ModelRenderer leftWing;
    private final ModelRenderer rightWing;

    public ClientVexModel() {
        super(0.0F, 0.0F, 64, 64);
        this.bipedLeftLeg.showModel = false;
        this.bipedHeadwear.showModel = false;
        this.bipedRightLeg = new ModelRenderer(this, 32, 0);
        this.bipedRightLeg.addBox(-1.0F, -1.0F, -2.0F, (int) 6.0, (int) 10.0, (int) 4.0, 0.0F);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.rightWing = new ModelRenderer(this, 0, 32);
        this.rightWing.addBox(-20.0F, 0.0F, 0.0F, (int) 20.0, (int) 12.0, (int) 1.0);
        this.leftWing = new ModelRenderer(this, 0, 32);
        this.leftWing.mirror = true;
        this.leftWing.addBox(0.0F, 0.0F, 0.0F, (int) 20.0, (int) 12.0, (int) 1.0);
    }

    public void setRotationAngles(net.minecraft.entity.Entity entity, float f, float g, float h, float i, float j, float p) {
        ClientVex vex = (ClientVex) entity;

        this.bipedRightLeg.rotateAngleX += ((float) Math.PI / 5F);
        this.rightWing.rotationPointZ = 2.0F;
        this.leftWing.rotationPointZ = 2.0F;
        this.rightWing.rotationPointY = 1.0F;
        this.leftWing.rotationPointY = 1.0F;
        this.rightWing.rotateAngleY = 0.47123894F + net.minecraft.util.math.MathHelper.cos(h * 0.8F) * (float) Math.PI * 0.05F;
        this.leftWing.rotateAngleY = -this.rightWing.rotateAngleY;
        this.leftWing.rotateAngleZ = -0.47123894F;
        this.leftWing.rotateAngleX = 0.47123894F;
        this.rightWing.rotateAngleX = 0.47123894F;
        this.rightWing.rotateAngleZ = 0.47123894F;
    }
}
