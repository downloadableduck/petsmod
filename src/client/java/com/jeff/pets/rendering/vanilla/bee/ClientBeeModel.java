package com.jeff.pets.rendering.vanilla.bee;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.neutral.ClientBee;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class ClientBeeModel extends AgeableListModel<ClientBee> {
    private static final float BEE_Y_BASE = 19.0F;
    private static final String BONE = "bone";
    private static final String STINGER = "stinger";
    private static final String LEFT_ANTENNA = "left_antenna";
    private static final String RIGHT_ANTENNA = "right_antenna";
    private static final String FRONT_LEGS = "front_legs";
    private static final String MIDDLE_LEGS = "middle_legs";
    private static final String BACK_LEGS = "back_legs";
    private final ModelPart bone;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart frontLeg;
    private final ModelPart midLeg;
    private final ModelPart backLeg;
    private final ModelPart stinger;
    private final ModelPart leftAntenna;
    private final ModelPart rightAntenna;
    private final ModelPart root;
    private float rollAmount;

    public ClientBeeModel(ModelPart modelPart) {
        super(false, 24.0F, 0.0F);
        this.root = modelPart;
        this.bone = modelPart.getChild("bone");
        ModelPart modelPart2 = this.bone.getChild("body");
        this.stinger = modelPart2.getChild("stinger");
        this.leftAntenna = modelPart2.getChild("left_antenna");
        this.rightAntenna = modelPart2.getChild("right_antenna");
        this.rightWing = this.bone.getChild("right_wing");
        this.leftWing = this.bone.getChild("left_wing");
        this.frontLeg = this.bone.getChild("front_legs");
        this.midLeg = this.bone.getChild("middle_legs");
        this.backLeg = this.bone.getChild("back_legs");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition partDefinition2 = partDefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, 0.0F));
        PartDefinition partDefinition3 = partDefinition2.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -4.0F, -5.0F, 7.0F, 7.0F, 10.0F), PartPose.ZERO);
        partDefinition3.addOrReplaceChild("stinger", CubeListBuilder.create().texOffs(26, 7).addBox(0.0F, -1.0F, 5.0F, 0.0F, 1.0F, 2.0F), PartPose.ZERO);
        partDefinition3.addOrReplaceChild("left_antenna", CubeListBuilder.create().texOffs(2, 0).addBox(1.5F, -2.0F, -3.0F, 1.0F, 2.0F, 3.0F), PartPose.offset(0.0F, -2.0F, -5.0F));
        partDefinition3.addOrReplaceChild("right_antenna", CubeListBuilder.create().texOffs(2, 3).addBox(-2.5F, -2.0F, -3.0F, 1.0F, 2.0F, 3.0F), PartPose.offset(0.0F, -2.0F, -5.0F));
        CubeDeformation cubeDeformation = new CubeDeformation(0.001F);
        partDefinition2.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(0, 18).addBox(-9.0F, 0.0F, 0.0F, 9.0F, 0.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(-1.5F, -4.0F, -3.0F, 0.0F, -0.2618F, 0.0F));
        partDefinition2.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(0.0F, 0.0F, 0.0F, 9.0F, 0.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(1.5F, -4.0F, -3.0F, 0.0F, 0.2618F, 0.0F));
        partDefinition2.addOrReplaceChild("front_legs", CubeListBuilder.create().addBox("front_legs", -5.0F, 0.0F, 0.0F, 7, 2, 0, 26, 1), PartPose.offset(1.5F, 3.0F, -2.0F));
        partDefinition2.addOrReplaceChild("middle_legs", CubeListBuilder.create().addBox("middle_legs", -5.0F, 0.0F, 0.0F, 7, 2, 0, 26, 3), PartPose.offset(1.5F, 3.0F, 0.0F));
        partDefinition2.addOrReplaceChild("back_legs", CubeListBuilder.create().addBox("back_legs", -5.0F, 0.0F, 0.0F, 7, 2, 0, 26, 5), PartPose.offset(1.5F, 3.0F, 2.0F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public void setupAnim(ClientBee bee, float f, float g, float h, float i, float j) {
        this.root.getAllParts().forEach(ModelPart::resetPose);
        this.rightWing.xRot = 0.0F;
        this.leftAntenna.xRot = 0.0F;
        this.rightAntenna.xRot = 0.0F;
        this.bone.xRot = 0.0F;
        float k = h * 120.32113F * ((float) Math.PI / 180F);
        this.rightWing.yRot = 0.0F;
        this.rightWing.zRot = Mth.cos(k) * (float) Math.PI * 0.15F;
        this.leftWing.xRot = this.rightWing.xRot;
        this.leftWing.yRot = this.rightWing.yRot;
        this.leftWing.zRot = -this.rightWing.zRot;
        this.frontLeg.xRot = ((float) Math.PI / 4F);
        this.midLeg.xRot = ((float) Math.PI / 4F);
        this.backLeg.xRot = ((float) Math.PI / 4F);
        this.bone.xRot = 0.0F;
        this.bone.yRot = 0.0F;
        this.bone.zRot = 0.0F;
    }

    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of();
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.bone);
    }
}
