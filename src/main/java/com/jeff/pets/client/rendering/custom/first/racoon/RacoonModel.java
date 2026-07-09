package com.jeff.pets.client.rendering.custom.first.racoon;

import com.jeff.pets.mob.custom.first.Racoon;
import com.jeff.pets.client.rendering.PetModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;


import static com.jeff.pets.client.Central.CONFIG;

public class RacoonModel extends PetModel< Racoon> {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart tail;

    public RacoonModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.tail = root.getChild("tail");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 15).addBox(-2.5F, -7.0F, -2.0F, 7.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(28, 28).addBox(1.5F, -9.0F, 1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 30).addBox(-1.5F, -9.0F, 1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(1, 26).addBox(-0.5F, -4.0F, -4.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(24, 12).addBox(-3.5F, -5.0F, -2.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(24, 20).addBox(4.5F, -5.0F, -2.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 16.5F, -3.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 4.0F, -3.5F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, -6.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition leftHindLeg = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(12, 25).addBox(2.5F, 2.0F, -2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 17.5F, 7.0F));

        PartDefinition rightHindLeg = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(20, 28).addBox(1.5F, 2.0F, -2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 17.5F, 7.0F));

        PartDefinition leftFrontLeg = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(12, 25).addBox(2.5F, 2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 17.5F, 0.0F));

        PartDefinition rightFrontLeg = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(20, 28).addBox(1.5F, 2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 17.5F, 0.0F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(24, 0).addBox(2.0F, -2.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 17.0F, 9.0F, 1.5708F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void prepareMobModel(Racoon fox, float f, float g, float h) {
        this.body.xRot = ((float) Math.PI / 2F);
        this.tail.xRot = -0.05235988F;
        this.rightHindLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
        this.leftHindLeg.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.rightFrontLeg.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.leftFrontLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
        this.head.setPos(-1.0F, 16.5F, -3.0F);
        this.head.yRot = 0.0F;
        this.head.zRot = 0;
        this.rightHindLeg.visible = true;
        this.leftHindLeg.visible = true;
        this.rightFrontLeg.visible = true;
        this.leftFrontLeg.visible = true;
        this.body.setPos(0.0F, 16.0F, -6.0F);
        this.body.zRot = 0.0F;
        this.rightHindLeg.setPos(-5.0F, 17.5F, 7.0F);
        this.leftHindLeg.setPos(-1.0F, 17.5F, 7.0F);
        this.tail.xRot = 2f;

        if (fox.isPassenger()) {
            this.body.xRot = 1.35f;
            this.tail.z = 7;
        }
    }

    public void setupAnim(Racoon fox, float f, float g, float h, float i, float j) {
        this.head.xRot = j * ((float) Math.PI / 180F);
        this.head.yRot = i * ((float) Math.PI / 180F);
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
