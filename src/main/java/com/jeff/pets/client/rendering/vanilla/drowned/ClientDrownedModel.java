package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.AnimationUtils;
import net.minecraft.client.render.entity.model.BiPedModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedModel extends BiPedModel {

    private static final int ARM_POSE_EMPTY = 0;
    private static final int ARM_POSE_BOW_AND_ARROW = 3;
    private int rightArmPose;
    private int leftArmPose;
    private float field_20533;

    public ClientDrownedModel(float f, float g, int i, int j) {
        super(f, g, i, j);
        this.field_1476 = new ModelPart(this, 32, 48);
        this.field_1476.addCuboid(-3.0F, -2.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f);
        this.field_1476.setPivot(-5.0F, 2.0F + g, 0.0F);
        this.field_1478 = new ModelPart(this, 16, 48);
        this.field_1478.addCuboid(-2.0F, 0.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f);
        this.field_1478.setPivot(-1.9F, 12.0F + g, 0.0F);
    }

    public void setAngles(float f, float g, float h, float i, float k, float j, Entity state) {
        super.setAngles(f, g, h, i, k, j, state);
        AnimationUtils.animateZombieArms(this.field_1477, this.field_1476, true, this.field_20533, h);
    }

    private float method_18915(float f, float g, float h) {
        return g + net.minecraft.util.math.MathHelper.wrapDegrees(h - g) * f;
    }

    public void setLivingAnimations(MobEntity zombie, float f, float g, float h) {
        this.rightArmPose = ARM_POSE_EMPTY;
        this.leftArmPose = ARM_POSE_EMPTY;

        super.animateModel(zombie, f, g, h);

        if (this.rightArmPose == ARM_POSE_BOW_AND_ARROW) {
            this.field_1476.posX = this.field_1476.posX * 0.5F - (float) Math.PI;
            this.field_1476.posY = 0.0F;
        }

        if (this.field_20533 > 0.0F) {
            this.field_1476.posX = this.method_18915(this.field_20533, this.field_1476.posX, -2.5132742F) + this.field_20533 * 0.35F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            this.field_1477.posX = this.method_18915(this.field_20533, this.field_1477.posX, -2.5132742F) - this.field_20533 * 0.35F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            this.field_1476.posZ = this.method_18915(this.field_20533, this.field_1476.posZ, -0.15F);
            this.field_1477.posZ = this.method_18915(this.field_20533, this.field_1477.posZ, 0.15F);
            ModelPart var10000 = this.field_1479;
            var10000.posX -= this.field_20533 * 0.55F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            var10000 = this.field_1478;
            var10000.posX += this.field_20533 * 0.55F * net.minecraft.util.math.MathHelper.sin(0.1F * h);
            this.head.posX = 0.0F;
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
