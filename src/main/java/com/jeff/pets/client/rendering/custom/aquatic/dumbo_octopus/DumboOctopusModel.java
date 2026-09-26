package com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import org.jetbrains.annotations.NotNull;

public class DumboOctopusModel extends EntityModel<@NotNull DumboOctopusRenderState> {
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart leg5;
    private final ModelPart leg6;
    private final ModelPart leg7;
    private final ModelPart leg8;

    public DumboOctopusModel(ModelPart root) {
        super(root);
        ModelPart body = root.getChild("body");
        this.leg1 = body.getChild("leg1");
        this.leg2 = body.getChild("leg2");
        this.leg3 = body.getChild("leg3");
        this.leg4 = body.getChild("leg4");
        this.leg5 = body.getChild("leg5");
        this.leg6 = body.getChild("leg6");
        this.leg7 = body.getChild("leg7");
        this.leg8 = body.getChild("leg8");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition left_ear = body.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(-1.0F, 2.0F, 2.0F));

        left_ear.addOrReplaceChild("left_ear_r1", CubeListBuilder.create().texOffs(8, 12).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -7.0F, 1.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition right_ear = body.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(-2.0F, -5.0F, 3.0F));

        right_ear.addOrReplaceChild("right_ear_r1", CubeListBuilder.create().texOffs(8, 12).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.5236F, 0.0F, 0.0F));

        body.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 12).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 1.0F, 2.0F));

        body.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 12).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 1.0F, -1.0F));

        body.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.0F, -3.0F));

        body.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -3.0F));

        body.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 1.0F, -2.0F));

        body.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 1.0F, 1.0F));

        body.addOrReplaceChild("leg7", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.0F, 3.0F));

        body.addOrReplaceChild("leg8", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 1.0F, 3.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }


    @Override
    public void setupAnim(DumboOctopusRenderState state) {
        super.setupAnim(state);
        if (state.walkAnimationSpeed > 0) {
            leg1.zRot = -state.tentacleAngle / 10;
            float rot = leg1.zRot;
            leg2.zRot = rot;
            leg3.xRot = -rot;
            leg4.xRot = -rot;
            leg5.zRot = -rot;
            leg6.zRot = -rot;
            leg7.xRot = rot;
            leg8.xRot = rot;
        }
    }
}
