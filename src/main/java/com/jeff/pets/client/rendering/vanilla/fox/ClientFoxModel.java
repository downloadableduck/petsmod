package com.jeff.pets.client.rendering.vanilla.fox;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.neutral.ClientFox;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientFoxModel extends AgeableListModel<ClientFox> {
    private static final int LEG_SIZE = 6;
    private static final float HEAD_HEIGHT = 16.5F;
    private static final float LEG_POS = 17.5F;
    public final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart tail;
    private float legMotionPos;

    public ClientFoxModel(ModelPart modelPart) {
        super(true, 8.0F, 3.35F);
        this.head = modelPart.getChild("head");
        this.body = modelPart.getChild("body");
        this.rightHindLeg = modelPart.getChild("right_hind_leg");
        this.leftHindLeg = modelPart.getChild("left_hind_leg");
        this.rightFrontLeg = modelPart.getChild("right_front_leg");
        this.leftFrontLeg = modelPart.getChild("left_front_leg");
        this.tail = this.body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition partDefinition2 = partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(1, 5).addBox(-3.0F, -2.0F, -5.0F, 8.0F, 6.0F, 6.0F), PartPose.offset(-1.0F, 16.5F, -3.0F));
        partDefinition2.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(8, 1).addBox(-3.0F, -4.0F, -4.0F, 2.0F, 2.0F, 1.0F), PartPose.ZERO);
        partDefinition2.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(15, 1).addBox(3.0F, -4.0F, -4.0F, 2.0F, 2.0F, 1.0F), PartPose.ZERO);
        partDefinition2.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(6, 18).addBox(-1.0F, 2.01F, -8.0F, 4.0F, 2.0F, 3.0F), PartPose.ZERO);
        PartDefinition partDefinition3 = partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(24, 15).addBox(-3.0F, 3.999F, -3.5F, 6.0F, 11.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 16.0F, -6.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));
        CubeDeformation cubeDeformation = new CubeDeformation(0.001F);
        CubeListBuilder cubeListBuilder = CubeListBuilder.create().texOffs(4, 24).addBox(2.0F, 0.5F, -1.0F, 2.0F, 6.0F, 2.0F, cubeDeformation);
        CubeListBuilder cubeListBuilder2 = CubeListBuilder.create().texOffs(13, 24).addBox(2.0F, 0.5F, -1.0F, 2.0F, 6.0F, 2.0F, cubeDeformation);
        partDefinition.addOrReplaceChild("right_hind_leg", cubeListBuilder2, PartPose.offset(-5.0F, 17.5F, 7.0F));
        partDefinition.addOrReplaceChild("left_hind_leg", cubeListBuilder, PartPose.offset(-1.0F, 17.5F, 7.0F));
        partDefinition.addOrReplaceChild("right_front_leg", cubeListBuilder2, PartPose.offset(-5.0F, 17.5F, 0.0F));
        partDefinition.addOrReplaceChild("left_front_leg", cubeListBuilder, PartPose.offset(-1.0F, 17.5F, 0.0F));
        partDefinition3.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(30, 0).addBox(2.0F, 0.0F, -1.0F, 4.0F, 9.0F, 5.0F), PartPose.offsetAndRotation(-4.0F, 15.0F, -1.0F, -0.05235988F, 0.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 48, 32);
    }

    public void prepareMobModel(ClientFox fox, float f, float g, float h) {
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
        if (fox.isPassenger()) {
            this.body.xRot = ((float) Math.PI / 6F);
            this.body.setPos(0.0F, 9.0F, -3.0F);
            this.tail.xRot = ((float) Math.PI / 4F);
            this.tail.setPos(-4.0F, 15.0F, -2.0F);
            this.head.setPos(-1.0F, 10.0F, -0.25F);
            this.head.xRot = 0.0F;
            this.head.yRot = 0.0F;
            if (this.young) {
                this.head.setPos(-1.0F, 13.0F, -3.75F);
            }

            this.rightHindLeg.xRot = -1.3089969F;
            this.rightHindLeg.setPos(-5.0F, 21.5F, 6.75F);
            this.leftHindLeg.xRot = -1.3089969F;
            this.leftHindLeg.setPos(-1.0F, 21.5F, 6.75F);
            this.rightFrontLeg.xRot = -0.2617994F;
            this.leftFrontLeg.xRot = -0.2617994F;
        }

    }

    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.rightHindLeg, this.leftHindLeg, this.rightFrontLeg, this.leftFrontLeg);
    }

    public void setupAnim(ClientFox fox, float f, float g, float h, float i, float j) {
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
