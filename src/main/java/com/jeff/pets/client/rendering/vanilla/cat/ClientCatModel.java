package com.jeff.pets.client.rendering.vanilla.cat;

import com.jeff.pets.mob.vanilla.passive.ClientCat;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ClientCatModel extends EntityModel {
    /** The back left leg model for the Ocelot. */
    private final ModelPart ocelotBackLeftLeg;
    /** The back right leg model for the Ocelot. */
    private final ModelPart ocelotBackRightLeg;
    /** The front left leg model for the Ocelot. */
    private final ModelPart ocelotFrontLeftLeg;
    /** The front right leg model for the Ocelot. */
    private final ModelPart ocelotFrontRightLeg;
    /** The tail model for the Ocelot. */
    private final ModelPart ocelotTail;
    /** The second part of tail model for the Ocelot. */
    private final ModelPart ocelotTail2;
    /** The head model for the Ocelot. */
    private final ModelPart ocelotHead;
    /** The body model for the Ocelot. */
    private final ModelPart ocelotBody;
    private int state = 1;

    public ClientCatModel() {
        this.putTexture("head.main", 0, 0);
        this.putTexture("head.nose", 0, 24);
        this.putTexture("head.ear1", 0, 10);
        this.putTexture("head.ear2", 6, 10);
        this.ocelotHead = new ModelPart(this, "head");
        this.ocelotHead.addCuboid("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
        this.ocelotHead.addCuboid("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
        this.ocelotHead.addCuboid("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
        this.ocelotHead.addCuboid("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
        this.ocelotHead.setPivot(0.0F, 15.0F, -9.0F);
        this.ocelotBody = new ModelPart(this, 20, 0);
        this.ocelotBody.addCuboid(-2.0F, 3.0F, -8.0F, 4, 16, 6, 0.0F);
        this.ocelotBody.setPivot(0.0F, 12.0F, -10.0F);
        this.ocelotTail = new ModelPart(this, 0, 15);
        this.ocelotTail.addCuboid(-0.5F, 0.0F, 0.0F, 1, 8, 1);
        this.ocelotTail.posX = 0.9F;
        this.ocelotTail.setPivot(0.0F, 15.0F, 8.0F);
        this.ocelotTail2 = new ModelPart(this, 4, 15);
        this.ocelotTail2.addCuboid(-0.5F, 0.0F, 0.0F, 1, 8, 1);
        this.ocelotTail2.setPivot(0.0F, 20.0F, 14.0F);
        this.ocelotBackLeftLeg = new ModelPart(this, 8, 13);
        this.ocelotBackLeftLeg.addCuboid(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        this.ocelotBackLeftLeg.setPivot(1.1F, 18.0F, 5.0F);
        this.ocelotBackRightLeg = new ModelPart(this, 8, 13);
        this.ocelotBackRightLeg.addCuboid(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        this.ocelotBackRightLeg.setPivot(-1.1F, 18.0F, 5.0F);
        this.ocelotFrontLeftLeg = new ModelPart(this, 40, 0);
        this.ocelotFrontLeftLeg.addCuboid(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        this.ocelotFrontLeftLeg.setPivot(1.2F, 13.8F, -5.0F);
        this.ocelotFrontRightLeg = new ModelPart(this, 40, 0);
        this.ocelotFrontRightLeg.addCuboid(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        this.ocelotFrontRightLeg.setPivot(-1.2F, 13.8F, -5.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        if (this.child) {
            float f = 2.0F;
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
            GlStateManager.translate(0.0F, 10.0F * scale, 4.0F * scale);
            this.ocelotHead.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.ocelotBody.render(scale);
            this.ocelotBackLeftLeg.render(scale);
            this.ocelotBackRightLeg.render(scale);
            this.ocelotFrontLeftLeg.render(scale);
            this.ocelotFrontRightLeg.render(scale);
            this.ocelotTail.render(scale);
            this.ocelotTail2.render(scale);
            GlStateManager.popMatrix();
        } else {
            this.ocelotHead.render(scale);
            this.ocelotBody.render(scale);
            this.ocelotTail.render(scale);
            this.ocelotTail2.render(scale);
            this.ocelotBackLeftLeg.render(scale);
            this.ocelotBackRightLeg.render(scale);
            this.ocelotFrontLeftLeg.render(scale);
            this.ocelotFrontRightLeg.render(scale);
        }

    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how "far"
     * arms and legs can swing at most.
     */
    public void setAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.ocelotHead.posX = headPitch * ((float)Math.PI / 180F);
        this.ocelotHead.posY = netHeadYaw * ((float)Math.PI / 180F);
        if (this.state != 3) {
            this.ocelotBody.posX = ((float)Math.PI / 2F);
            if (this.state == 2) {
                this.ocelotBackLeftLeg.posX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
                this.ocelotBackRightLeg.posX = MathHelper.cos(limbSwing * 0.6662F + 0.3F) * limbSwingAmount;
                this.ocelotFrontLeftLeg.posX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI + 0.3F) * limbSwingAmount;
                this.ocelotFrontRightLeg.posX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;
                this.ocelotTail2.posX = 1.7278761F + ((float)Math.PI / 10F) * MathHelper.cos(limbSwing) * limbSwingAmount;
            } else {
                this.ocelotBackLeftLeg.posX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
                this.ocelotBackRightLeg.posX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;
                this.ocelotFrontLeftLeg.posX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;
                this.ocelotFrontRightLeg.posX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
                if (this.state == 1) {
                    this.ocelotTail2.posX = 1.7278761F + ((float)Math.PI / 4F) * MathHelper.cos(limbSwing) * limbSwingAmount;
                } else {
                    this.ocelotTail2.posX = 1.7278761F + 0.47123894F * MathHelper.cos(limbSwing) * limbSwingAmount;
                }
            }
        }

    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second and
     * third as in the setAngles method.
     */
    public void animateModel(LivingEntity LivingEntity, float limbSwing, float limbSwingAmount, float partialTickTime) {
        ClientCat LivingEntityIn = (ClientCat) LivingEntity;
        this.ocelotBody.pivotY = 12.0F;
        this.ocelotBody.pivotZ = -10.0F;
        this.ocelotHead.pivotY = 15.0F;
        this.ocelotHead.pivotZ = -9.0F;
        this.ocelotTail.pivotY = 15.0F;
        this.ocelotTail.pivotZ = 8.0F;
        this.ocelotTail2.pivotY = 20.0F;
        this.ocelotTail2.pivotZ = 14.0F;
        this.ocelotFrontLeftLeg.pivotY = 13.8F;
        this.ocelotFrontLeftLeg.pivotZ = -5.0F;
        this.ocelotFrontRightLeg.pivotY = 13.8F;
        this.ocelotFrontRightLeg.pivotZ = -5.0F;
        this.ocelotBackLeftLeg.pivotY = 18.0F;
        this.ocelotBackLeftLeg.pivotZ = 5.0F;
        this.ocelotBackRightLeg.pivotY = 18.0F;
        this.ocelotBackRightLeg.pivotZ = 5.0F;
        this.ocelotTail.posX = 0.9F;
        if (LivingEntity.isSneaking()) {
            ++this.ocelotBody.pivotY;
            this.ocelotHead.pivotY += 2.0F;
            ++this.ocelotTail.pivotY;
            this.ocelotTail2.pivotY += -4.0F;
            this.ocelotTail2.pivotZ += 2.0F;
            this.ocelotTail.posX = ((float)Math.PI / 2F);
            this.ocelotTail2.posX = ((float)Math.PI / 2F);
            this.state = 0;
        } else if (LivingEntityIn.isSprinting()) {
            this.ocelotTail2.pivotY = this.ocelotTail.pivotY;
            this.ocelotTail2.pivotZ += 2.0F;
            this.ocelotTail.posX = ((float)Math.PI / 2F);
            this.ocelotTail2.posX = ((float)Math.PI / 2F);
            this.state = 2;
        } else if (LivingEntityIn.getOwner() != null && LivingEntityIn.hasPassengerDeep(LivingEntityIn.getOwner())) {
            this.ocelotBody.posX = ((float)Math.PI / 4F);
            this.ocelotBody.pivotY += -4.0F;
            this.ocelotBody.pivotZ += 5.0F;
            this.ocelotHead.pivotY += -3.3F;
            ++this.ocelotHead.pivotZ;
            this.ocelotTail.pivotY += 8.0F;
            this.ocelotTail.pivotZ += -2.0F;
            this.ocelotTail2.pivotY += 2.0F;
            this.ocelotTail2.pivotZ += -0.8F;
            this.ocelotTail.posX = 1.7278761F;
            this.ocelotTail2.posX = 2.670354F;
            this.ocelotFrontLeftLeg.posX = -0.15707964F;
            this.ocelotFrontLeftLeg.pivotY = 15.8F;
            this.ocelotFrontLeftLeg.pivotZ = -7.0F;
            this.ocelotFrontRightLeg.posX = -0.15707964F;
            this.ocelotFrontRightLeg.pivotY = 15.8F;
            this.ocelotFrontRightLeg.pivotZ = -7.0F;
            this.ocelotBackLeftLeg.posX = (-(float)Math.PI / 2F);
            this.ocelotBackLeftLeg.pivotY = 21.0F;
            this.ocelotBackLeftLeg.pivotZ = 1.0F;
            this.ocelotBackRightLeg.posX = (-(float)Math.PI / 2F);
            this.ocelotBackRightLeg.pivotY = 21.0F;
            this.ocelotBackRightLeg.pivotZ = 1.0F;
            this.state = 3;
        } else {
            this.state = 1;
        }
    }
}
