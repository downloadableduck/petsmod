package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;


import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedModel extends HumanoidModel<ClientDrowned> {

    public static float headScale;

    final ModelPart head;
    private HumanoidModel.ArmPose rightArmPose;
    private HumanoidModel.ArmPose leftArmPose;

    public ClientDrownedModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    public static LayerDefinition createBodyLayer(CubeDeformation cubeDeformation) {
        MeshDefinition meshDefinition = HumanoidModel.createMesh(cubeDeformation, 0.0F);
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(5.0F, 2.0F, 0.0F));
        partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(1.9F, 12.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    @Override
    public void setupAnim( ClientDrowned state, float f, float g, float h, float i, float k) {
        super.setupAnim(state, f, g, h, i, k);
        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, true, this.attackTime, h);
    }

    public void prepareMobModel(ClientDrowned zombie, float f, float g, float h) {
        this.rightArmPose = HumanoidModel.ArmPose.EMPTY;
        this.leftArmPose = HumanoidModel.ArmPose.EMPTY;

        super.prepareMobModel(zombie, f, g, h);

        if (this.rightArmPose == ArmPose.THROW_SPEAR) {
            this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float) Math.PI;
            this.rightArm.yRot = 0.0F;
        }

        if (this.swimAmount > 0.0F) {
            this.rightArm.xRot = this.rotlerpRad(this.swimAmount, this.rightArm.xRot, -2.5132742F) + this.swimAmount * 0.35F * Mth.sin(0.1F * h);
            this.leftArm.xRot = this.rotlerpRad(this.swimAmount, this.leftArm.xRot, -2.5132742F) - this.swimAmount * 0.35F * Mth.sin(0.1F * h);
            this.rightArm.zRot = this.rotlerpRad(this.swimAmount, this.rightArm.zRot, -0.15F);
            this.leftArm.zRot = this.rotlerpRad(this.swimAmount, this.leftArm.zRot, 0.15F);
            ModelPart var10000 = this.leftLeg;
            var10000.xRot -= this.swimAmount * 0.55F * Mth.sin(0.1F * h);
            var10000 = this.rightLeg;
            var10000.xRot += this.swimAmount * 0.55F * Mth.sin(0.1F * h);
            this.head.xRot = 0.0F;
        }
    }
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        super.renderToBuffer(poseStack, vertexConsumer, i, j, f, g, h, k);
        poseStack.pushPose();
        if (CONFIG.isBaby) {
            poseStack.scale(1.5f, 1.5f, 1.5f);
        } else {
            poseStack.scale(1, 1, 1);
        }
        this.head.translateAndRotate(poseStack);
        poseStack.popPose();
    }
}
