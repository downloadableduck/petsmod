package com.jeff.pets.rendering.aprilfools.batato;

import com.jeff.pets.mob.aprilfools.Batato;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.BatModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BatRenderer;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;

import java.util.Set;

public class BatatoModel extends HierarchicalModel<Batato> {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart rightWingTip;
    private final ModelPart leftWingTip;
    private final ModelPart feet;
    private final ModelPart head;
    AnimationState flyAnimationState = new AnimationState();

    public BatatoModel(ModelPart modelPart) {
        super(RenderType::entityCutout);
        this.root = modelPart;
        this.body = modelPart.getChild("body");
        this.head = modelPart.getChild("head");
        this.rightWing = this.body.getChild("right_wing");
        this.rightWingTip = this.rightWing.getChild("right_wing_tip");
        this.leftWing = this.body.getChild("left_wing");
        this.leftWingTip = this.leftWing.getChild("left_wing_tip");
        this.feet = this.body.getChild("feet");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition head = partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(0, 0, 0));
        PartDefinition body = partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(19, 20).addBox(-1.5F, -2.0F, 0.0F, 13.0F, 12.0F, 0.01F, Set.of(Direction.NORTH)).texOffs(6, 20).mirror().addBox(-1.5F, -2.0F, 0.0F, 13.0F, 12.0F, 0.01F, Set.of(Direction.SOUTH)), PartPose.offset(0.0F, 17.0F, 0.0F));
        PartDefinition rightWing = body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(12, 0).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 7.0F, 0.0F), PartPose.offset(-1.5F, 0.0F, 0.0F));
        rightWing.addOrReplaceChild("right_wing_tip", CubeListBuilder.create().texOffs(16, 0).addBox(-6.0F, -2.0F, 0.0F, 6.0F, 8.0F, 0.0F), PartPose.offset(-2.0F, 0.0F, 0.0F));
        PartDefinition leftWing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(12, 7).addBox(0.0F, -2.0F, 0.0F, 2.0F, 7.0F, 0.0F), PartPose.offset(11.5F, 2.0F, 0.0F));
        leftWing.addOrReplaceChild("left_wing_tip", CubeListBuilder.create().texOffs(16, 8).addBox(0.0F, -2.0F, 0.0F, 6.0F, 8.0F, 0.0F), PartPose.offset(2.0F, 0.0F, 0.0F));
        body.addOrReplaceChild("feet", CubeListBuilder.create().texOffs(16, 16).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F), PartPose.offset(3.0F, 10.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 32, 32);
    }

    @Override
    public void setupAnim(Batato bat, float f, float g, float h, float i, float j) {
        this.head.xRot = j * ((float)Math.PI / 180F);
        this.head.yRot = i * ((float)Math.PI / 180F);
        this.head.zRot = 0.0F;
        this.head.setPos(0.0F, 0.0F, 0.0F);
        //this.rightWing.setPos(0.0F, 0.0F, 0.0F);
        //this.leftWing.setPos(0.0F, 0.0F, 0.0F);
        this.body.xRot = ((float)Math.PI / 4F) + Mth.cos(h * 0.1F) * 0.15F;
        this.body.yRot = 0.0F;
        this.rightWing.yRot = Mth.cos(h * 74.48451F * ((float)Math.PI / 180F)) * (float)Math.PI * 0.25F;
        this.leftWing.yRot = -this.rightWing.yRot;
        this.rightWingTip.yRot = this.rightWing.yRot * 0.5F;
        this.leftWingTip.yRot = -this.rightWing.yRot * 0.5F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        this.root.render(poseStack, vertexConsumer, i, j);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
