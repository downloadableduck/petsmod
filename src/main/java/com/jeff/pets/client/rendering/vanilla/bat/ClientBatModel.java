package com.jeff.pets.client.rendering.vanilla.bat;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ClientBatModel extends EntityModel {
    private final ModelPart batHead;
    /** The body box of the bat model. */
    private final ModelPart batBody;
    /** The inner right wing box of the bat model. */
    private final ModelPart batRightWing;
    /** The inner left wing box of the bat model. */
    private final ModelPart batLeftWing;
    /** The outer right wing box of the bat model. */
    private final ModelPart batOuterRightWing;
    /** The outer left wing box of the bat model. */
    private final ModelPart batOuterLeftWing;

    public ClientBatModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.batHead = new ModelPart(this, 0, 0);
        this.batHead.addCuboid(-3.0F, -3.0F, -3.0F, 6, 6, 6);
        ModelPart ModelPart = new ModelPart(this, 24, 0);
        ModelPart.addCuboid(-4.0F, -6.0F, -2.0F, 3, 4, 1);
        this.batHead.add(ModelPart);
        ModelPart ModelPart1 = new ModelPart(this, 24, 0);
        ModelPart1.mirror = true;
        ModelPart1.addCuboid(1.0F, -6.0F, -2.0F, 3, 4, 1);
        this.batHead.add(ModelPart1);
        this.batBody = new ModelPart(this, 0, 16);
        this.batBody.addCuboid(-3.0F, 4.0F, -3.0F, 6, 12, 6);
        this.batBody.setTextureOffset(0, 34).addCuboid(-5.0F, 16.0F, 0.0F, 10, 6, 1);
        this.batRightWing = new ModelPart(this, 42, 0);
        this.batRightWing.addCuboid(-12.0F, 1.0F, 1.5F, 10, 16, 1);
        this.batOuterRightWing = new ModelPart(this, 24, 16);
        this.batOuterRightWing.setPivot(-12.0F, 1.0F, 1.5F);
        this.batOuterRightWing.addCuboid(-8.0F, 1.0F, 0.0F, 8, 12, 1);
        this.batLeftWing = new ModelPart(this, 42, 0);
        this.batLeftWing.mirror = true;
        this.batLeftWing.addCuboid(2.0F, 1.0F, 1.5F, 10, 16, 1);
        this.batOuterLeftWing = new ModelPart(this, 24, 16);
        this.batOuterLeftWing.mirror = true;
        this.batOuterLeftWing.setPivot(12.0F, 1.0F, 1.5F);
        this.batOuterLeftWing.addCuboid(0.0F, 1.0F, 0.0F, 8, 12, 1);
        this.batBody.add(this.batRightWing);
        this.batBody.add(this.batLeftWing);
        this.batRightWing.add(this.batOuterRightWing);
        this.batLeftWing.add(this.batOuterLeftWing);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.batHead.render(scale);
        this.batBody.render(scale);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how "far"
     * arms and legs can swing at most.
     */
    @Override
    public void setAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.batHead.posX = headPitch * ((float)Math.PI / 180F);
        this.batHead.posY = netHeadYaw * ((float)Math.PI / 180F);
        this.batHead.posZ = 0.0F;
        this.batHead.setPivot(0.0F, 0.0F, 0.0F);
        this.batRightWing.setPivot(0.0F, 0.0F, 0.0F);
        this.batLeftWing.setPivot(0.0F, 0.0F, 0.0F);
        this.batBody.posX = ((float)Math.PI / 4F) + MathHelper.cos(ageInTicks * 0.1F) * 0.15F;
        this.batBody.posY = 0.0F;
        this.batRightWing.posY = MathHelper.cos(ageInTicks * 1.3F) * (float)Math.PI * 0.25F;
        this.batLeftWing.posY = -this.batRightWing.posY;
        this.batOuterRightWing.posY = this.batRightWing.posY * 0.5F;
        this.batOuterLeftWing.posY = -this.batRightWing.posY * 0.5F;
    }
}
