package com.jeff.pets.client.rendering.vanilla.vex;

import com.jeff.pets.mob.vanilla.hostile.ClientVex;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;

public class ClientVexModel extends HierarchicalModel<ClientVex> implements ArmedModel {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart head;

    public ClientVexModel(ModelPart modelPart) {
        super(RenderType::entityTranslucent);
        this.root = modelPart.getChild("root");
        this.body = this.root.getChild("body");
        this.rightArm = this.body.getChild("right_arm");
        this.leftArm = this.body.getChild("left_arm");
        this.rightWing = this.body.getChild("right_wing");
        this.leftWing = this.body.getChild("left_wing");
        this.head = this.root.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition partDefinition2 = partDefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, 0.0F));
        partDefinition2.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));
        PartDefinition partDefinition3 = partDefinition2.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 10).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 16).addBox(-1.5F, 1.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, 20.0F, 0.0F));
        partDefinition3.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(23, 0).addBox(-1.25F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(-1.75F, 0.25F, 0.0F));
        partDefinition3.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(23, 6).addBox(-0.75F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.75F, 0.25F, 0.0F));
        partDefinition3.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(16, 14).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.5F, 1.0f, 1.0f));
        partDefinition3.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(16, 14).addBox(0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 1.0f, 1.0f));
        return LayerDefinition.create(meshDefinition, 32, 32);
    }

    public void setupAnim(ClientVex vex, float f, float g, float h, float i, float j) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.head.yRot = i * ((float) Math.PI / 180F);
        this.head.xRot = j * ((float) Math.PI / 180F);
        float k = Mth.cos(h * 5.5F * ((float) Math.PI / 180F)) * 0.1F;
        this.rightArm.zRot = ((float) Math.PI / 5F) + k;
        this.leftArm.zRot = -(((float) Math.PI / 5F) + k);
        this.body.xRot = 0.15707964F;

        this.leftWing.yRot = 1.0995574F + Mth.cos(h * 45.836624F * ((float) Math.PI / 180F)) * ((float) Math.PI / 180F) * 16.2F;
        this.rightWing.yRot = -this.leftWing.yRot;
        this.leftWing.xRot = 0.47123888F;
        this.leftWing.zRot = -0.47123888F;
        this.rightWing.xRot = 0.47123888F;
        this.rightWing.zRot = 0.47123888F;
    }

    private void setArmsCharging(ItemStack itemStack, ItemStack itemStack2, float f) {
        if (itemStack.isEmpty() && itemStack2.isEmpty()) {
            this.rightArm.xRot = -1.2217305F;
            this.rightArm.yRot = 0.2617994F;
            this.rightArm.zRot = -0.47123888F - f;
            this.leftArm.xRot = -1.2217305F;
            this.leftArm.yRot = -0.2617994F;
            this.leftArm.zRot = 0.47123888F + f;
        } else {
            if (!itemStack.isEmpty()) {
                this.rightArm.xRot = 3.6651914F;
                this.rightArm.yRot = 0.2617994F;
                this.rightArm.zRot = -0.47123888F - f;
            }

            if (!itemStack2.isEmpty()) {
                this.leftArm.xRot = 3.6651914F;
                this.leftArm.yRot = -0.2617994F;
                this.leftArm.zRot = 0.47123888F + f;
            }

        }
    }

    public ModelPart root() {
        return this.root;
    }

    public void translateToHand(HumanoidArm humanoidArm, PoseStack poseStack) {
        boolean bl = humanoidArm == HumanoidArm.RIGHT;
        ModelPart modelPart = bl ? this.rightArm : this.leftArm;
        this.root.translateAndRotate(poseStack);
        this.body.translateAndRotate(poseStack);
        modelPart.translateAndRotate(poseStack);
        poseStack.scale(0.55F, 0.55F, 0.55F);
        this.offsetStackPosition(poseStack, bl);
    }

    private void offsetStackPosition(PoseStack poseStack, boolean bl) {
        if (bl) {
            poseStack.translate((double) 0.046875F, (double) -0.15625F, (double) 0.078125F);
        } else {
            poseStack.translate((double) -0.046875F, (double) -0.15625F, (double) 0.078125F);
        }

    }
}
