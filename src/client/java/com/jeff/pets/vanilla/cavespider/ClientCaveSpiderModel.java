package com.jeff.pets.vanilla.cavespider;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

/**
 * DO NOT DELETE, the cave spider requires a custom model to work properly
 */
public class ClientCaveSpiderModel extends EntityModel<@NotNull LivingEntityRenderState> {
    private final ModelPart head;
    //private final ModelPart neck;
    //private final ModelPart body;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightMiddleHindLeg;
    private final ModelPart leftMiddleHindLeg;
    private final ModelPart rightMiddleFrontLeg;
    private final ModelPart leftMiddleFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;

    public ClientCaveSpiderModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        // this.neck = root.getChild("neck");
        //this.body = root.getChild("body");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightMiddleHindLeg = root.getChild("right_middle_hind_leg");
        this.leftMiddleHindLeg = root.getChild("left_middle_hind_leg");
        this.rightMiddleFrontLeg = root.getChild("right_middle_front_leg");
        this.leftMiddleFrontLeg = root.getChild("left_middle_front_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.leftFrontLeg = root.getChild("left_front_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 4).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -3.0F));

        PartDefinition neck = partdefinition.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 12).addBox(-5.0F, -4.0F, -6.0F, 10.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 9.0F));

        PartDefinition rightHindLeg = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 15.0F, 4.0F));

        PartDefinition leftHindLeg = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 15.0F, 4.0F));

        PartDefinition rightMiddleHindLeg = partdefinition.addOrReplaceChild("right_middle_hind_leg", CubeListBuilder.create().texOffs(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 15.0F, 1.0F));

        PartDefinition leftMiddleHindLeg = partdefinition.addOrReplaceChild("left_middle_hind_leg", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 15.0F, 1.0F));

        PartDefinition rightMiddleFrontLeg = partdefinition.addOrReplaceChild("right_middle_front_leg", CubeListBuilder.create().texOffs(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 15.0F, -2.0F));

        PartDefinition leftMiddleFrontLeg = partdefinition.addOrReplaceChild("left_middle_front_leg", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 15.0F, -2.0F));

        PartDefinition rightFrontLeg = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 15.0F, -5.0F));

        PartDefinition leftFrontLeg = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 15.0F, -5.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(LivingEntityRenderState livingEntityRenderState) {
        super.setupAnim(livingEntityRenderState);
        this.head.yRot = livingEntityRenderState.yRot * ((float) Math.PI / 180);
        this.head.xRot = livingEntityRenderState.xRot * ((float) Math.PI / 180);
        float f = livingEntityRenderState.walkAnimationPos * 0.6662f;
        float g = livingEntityRenderState.walkAnimationSpeed;
        float h = -(Mth.cos(f * 2.0f + 0.0f) * 0.4f) * g;
        float i = -(Mth.cos(f * 2.0f + (float) Math.PI) * 0.4f) * g;
        float j = -(Mth.cos(f * 2.0f + 1.5707964f) * 0.4f) * g;
        float k = -(Mth.cos(f * 2.0f + 4.712389f) * 0.4f) * g;
        float l = Math.abs(Mth.sin(f + 0.0f) * 0.4f) * g;
        float m = Math.abs(Mth.sin(f + (float) Math.PI) * 0.4f) * g;
        float n = Math.abs(Mth.sin(f + 1.5707964f) * 0.4f) * g;
        float o = Math.abs(Mth.sin(f + 4.712389f) * 0.4f) * g;
        this.rightHindLeg.yRot += h;
        this.leftHindLeg.yRot -= h;
        this.rightMiddleHindLeg.yRot += i;
        this.leftMiddleHindLeg.yRot -= i;
        this.rightMiddleFrontLeg.yRot += j;
        this.leftMiddleFrontLeg.yRot -= j;
        this.rightFrontLeg.yRot += k;
        this.leftFrontLeg.yRot -= k;
        this.rightHindLeg.zRot += l;
        this.leftHindLeg.zRot -= l;
        this.rightMiddleHindLeg.zRot += m;
        this.leftMiddleHindLeg.zRot -= m;
        this.rightMiddleFrontLeg.zRot += n;
        this.leftMiddleFrontLeg.zRot -= n;
        this.rightFrontLeg.zRot += o;
        this.leftFrontLeg.zRot -= o;
    }
}
