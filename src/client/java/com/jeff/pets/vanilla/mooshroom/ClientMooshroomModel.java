package com.jeff.pets.vanilla.mooshroom;

import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

/**
 * DO NOT DELETE, the mooshroom requires a custom model to work properly
 */
public class ClientMooshroomModel extends QuadrupedModel<@NotNull LivingEntityRenderState> {
    public ClientMooshroomModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshDefinition = createBaseMooshroomModel();
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    static MeshDefinition createBaseMooshroomModel() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F).texOffs(1, 33).addBox(-3.0F, 1.0F, -7.0F, 6.0F, 3.0F, 1.0F).texOffs(22, 0).addBox("right_horn", -5.0F, -5.0F, -5.0F, 1.0F, 3.0F, 1.0F).texOffs(22, 0).addBox("left_horn", 4.0F, -5.0F, -5.0F, 1.0F, 3.0F, 1.0F), PartPose.offset(0.0F, 4.0F, -8.0F));
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(18, 4).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F).texOffs(52, 0).addBox(-2.0F, 2.0F, -8.0F, 4.0F, 6.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));
        CubeListBuilder cubeListBuilder = CubeListBuilder.create().mirror().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
        CubeListBuilder cubeListBuilder2 = CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
        partDefinition.addOrReplaceChild("right_hind_leg", cubeListBuilder2, PartPose.offset(-4.0F, 12.0F, 7.0F));
        partDefinition.addOrReplaceChild("left_hind_leg", cubeListBuilder, PartPose.offset(4.0F, 12.0F, 7.0F));
        partDefinition.addOrReplaceChild("right_front_leg", cubeListBuilder2, PartPose.offset(-4.0F, 12.0F, -5.0F));
        partDefinition.addOrReplaceChild("left_front_leg", cubeListBuilder, PartPose.offset(4.0F, 12.0F, -5.0F));
        return meshDefinition;
    }

    public void setupAnim(LivingEntityRenderState livingEntityRenderState) {
        super.setupAnim(livingEntityRenderState);
        this.head.xRot = livingEntityRenderState.xRot * ((float) Math.PI / 180F);
        this.head.yRot = livingEntityRenderState.yRot * ((float) Math.PI / 180F);
        float f = livingEntityRenderState.walkAnimationPos;
        float g = livingEntityRenderState.walkAnimationSpeed;
        this.rightHindLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
        this.leftHindLeg.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.rightFrontLeg.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.leftFrontLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
    }
}
