package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.AnimationUtils;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedModel extends ModelBiped {

    private static final int ARM_POSE_EMPTY = 0;
    private static final int ARM_POSE_BOW_AND_ARROW = 3;
    private int rightArmPose;
    private int leftArmPose;
    private float field_20533;

    public ClientDrownedModel(float f, float g, int i, int j) {
        super(f, g, i, j);
        this.bipedRightArm = new ModelRenderer(this, 32, 48);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + g, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 16, 48);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + g, 0.0F);
    }

    public void setRotationAngles(float f, float g, float h, float i, float k, float j, Entity state) {
        super.setRotationAngles(f, g, h, i, k, j, state);
        AnimationUtils.animateZombieArms(this.bipedLeftArm, this.bipedRightArm, true, this.field_20533, h);
    }

    private float method_18915(float f, float g, float h) {
        return g + net.minecraft.util.MathHelper.wrapAngleTo180_float(h - g) * f;
    }

    public void setLivingAnimations(EntityLiving zombie, float f, float g, float h) {
        this.rightArmPose = ARM_POSE_EMPTY;
        this.leftArmPose = ARM_POSE_EMPTY;

        super.setLivingAnimations(zombie, f, g, h);

        if (this.rightArmPose == ARM_POSE_BOW_AND_ARROW) {
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - (float) Math.PI;
            this.bipedRightArm.rotateAngleY = 0.0F;
        }

        if (this.field_20533 > 0.0F) {
            this.bipedRightArm.rotateAngleX = this.method_18915(this.field_20533, this.bipedRightArm.rotateAngleX, -2.5132742F) + this.field_20533 * 0.35F * net.minecraft.util.MathHelper.sin(0.1F * h);
            this.bipedLeftArm.rotateAngleX = this.method_18915(this.field_20533, this.bipedLeftArm.rotateAngleX, -2.5132742F) - this.field_20533 * 0.35F * net.minecraft.util.MathHelper.sin(0.1F * h);
            this.bipedRightArm.rotateAngleZ = this.method_18915(this.field_20533, this.bipedRightArm.rotateAngleZ, -0.15F);
            this.bipedLeftArm.rotateAngleZ = this.method_18915(this.field_20533, this.bipedLeftArm.rotateAngleZ, 0.15F);
            ModelRenderer var10000 = this.bipedLeftLeg;
            var10000.rotateAngleX -= this.field_20533 * 0.55F * net.minecraft.util.MathHelper.sin(0.1F * h);
            var10000 = this.bipedRightLeg;
            var10000.rotateAngleX += this.field_20533 * 0.55F * net.minecraft.util.MathHelper.sin(0.1F * h);
            this.bipedHead.rotateAngleX = 0.0F;
        }
    }

    public void render(Entity drowned, float i, float j, float f, float g, float h, float k) {
        super.render(drowned, i, j, f, g, h, k);
        GL11.glPushMatrix();
        if (CONFIG.isBaby) {
            GL11.glScalef(1.5f, 1.5f, 1.5f);
        } else {
            GL11.glScalef(1, 1, 1);
        }
        GL11.glPopMatrix();
    }
}
