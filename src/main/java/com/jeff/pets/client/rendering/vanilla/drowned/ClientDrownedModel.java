package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.ModelUtils;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedModel extends ModelBiped {

    public ClientDrownedModel(float f, float g, int i, int j) {
        super(f, g, i, j);
        this.bipedRightArm = new ModelRenderer(this, 32, 48);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + g, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 16, 48);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + g, 0.0F);
    }

    @Override
    public void setRotationAngles(float f, float g, float h, float i, float k, float j, Entity state) {
        super.setRotationAngles(f, g, h, i, k, j, state);
        ModelUtils.animateZombieArms(this.bipedLeftArm, this.bipedRightArm, true, this.field_205061_a, h);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase zombie, float f, float g, float h) {
        this.rightArmPose = ArmPose.EMPTY;
        this.leftArmPose = ArmPose.EMPTY;

        super.setLivingAnimations(zombie, f, g, h);

        if (this.rightArmPose == ArmPose.THROW_SPEAR) {
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - (float) Math.PI;
            this.bipedRightArm.rotateAngleY = 0.0F;
        }

        if (this.field_205061_a > 0.0F) {
            this.bipedRightArm.rotateAngleX = this.func_205060_a(this.field_205061_a, this.bipedRightArm.rotateAngleX, -2.5132742F) + this.field_205061_a * 0.35F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            this.bipedLeftArm.rotateAngleX = this.func_205060_a(this.field_205061_a, this.bipedLeftArm.rotateAngleX, -2.5132742F) - this.field_205061_a * 0.35F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            this.bipedRightArm.rotateAngleZ = this.func_205060_a(this.field_205061_a, this.bipedRightArm.rotateAngleZ, -0.15F);
            this.bipedLeftArm.rotateAngleZ = this.func_205060_a(this.field_205061_a, this.bipedLeftArm.rotateAngleZ, 0.15F);
            ModelRenderer var10000 = this.bipedLeftLeg;
            var10000.rotateAngleX -= this.field_205061_a * 0.55F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            var10000 = this.bipedRightLeg;
            var10000.rotateAngleX += this.field_205061_a * 0.55F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            this.bipedHead.rotateAngleX = 0.0F;
        }
    }

    @Override
    public void render(Entity drowned, float i, float j, float f, float g, float h, float k) {
        super.render(drowned, i, j, f, g, h, k);
        net.minecraft.client.renderer.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(1.5f, 1.5f, 1.5f);
        } else {
            net.minecraft.client.renderer.GlStateManager.scalef(1, 1, 1);
        }
        net.minecraft.client.renderer.GlStateManager.popMatrix();
    }
}
