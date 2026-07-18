package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.AnimationUtils;
import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedModel extends BipedEntityModel<ClientDrowned> {

    public ClientDrownedModel(float f, float g, int i, int j) {
        super(f, g, i, j);
        this.rightArm = new Cuboid(this, 32, 48);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, f);
        this.rightArm.setRotationPoint(-5.0F, 2.0F + g, 0.0F);
        this.rightLeg = new Cuboid(this, 16, 48);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
        this.rightLeg.setRotationPoint(-1.9F, 12.0F + g, 0.0F);
    }

    @Override
    public void setAngles(@NotNull ClientDrowned state, float f, float g, float h, float i, float k, float s) {
        super.setAngles(state, f, g, h, i, k, s);
        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, true, this.handSwingProgress, h);
    }

    public void animateModel(ClientDrowned zombie, float f, float g, float h) {
        this.rightArmPose = ArmPose.EMPTY;
        this.leftArmPose = ArmPose.EMPTY;

        super.animateModel(zombie, f, g, h);

        if (this.rightArmPose == ArmPose.THROW_SPEAR) {
            this.rightArm.pitch = this.rightArm.pitch * 0.5F - (float) Math.PI;
            this.rightArm.yaw = 0.0F;
        }

        if (this.field_3396 > 0.0F) {
            this.rightArm.pitch = this.method_2804(this.field_3396, this.rightArm.pitch, -2.5132742F) + this.field_3396 * 0.35F * MathHelper.sin(0.1F * h);
            this.leftArm.pitch = this.method_2804(this.field_3396, this.leftArm.pitch, -2.5132742F) - this.field_3396 * 0.35F * MathHelper.sin(0.1F * h);
            this.rightArm.roll = this.method_2804(this.field_3396, this.rightArm.roll, -0.15F);
            this.leftArm.roll = this.method_2804(this.field_3396, this.leftArm.roll, 0.15F);
            Cuboid var10000 = this.leftLeg;
            var10000.pitch -= this.field_3396 * 0.55F * MathHelper.sin(0.1F * h);
            var10000 = this.rightLeg;
            var10000.pitch += this.field_3396 * 0.55F * MathHelper.sin(0.1F * h);
            this.head.pitch = 0.0F;
        }
    }

    @Override
    public void render(ClientDrowned drowned, float f, float j, float h, float i, float k, float l) {
        super.render(drowned, f, j, h, i, k, l);
        com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(1.5f, 1.5f, 1.5f);
        } else {
            com.mojang.blaze3d.platform.GlStateManager.scalef(1, 1, 1);
        }
        com.mojang.blaze3d.platform.GlStateManager.popMatrix();
    }
}
