package com.jeff.pets.client.rendering.custom.first.racoon;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Racoon;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class RacoonModel extends PetModel<@NotNull Racoon> {
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
    public void setupAnim(Racoon state, float f, float g, float h, float i, float k) {
        super.setupAnim(state, f, g, h, i, k);
        float animSpeed = state.walkAnimation.speed();
        float animPos = state.walkAnimation.position();
        this.rightFrontLeg.xRot = Mth.cos(animPos * 0.6662F + (float) Math.PI) * 1.4F * animSpeed;
        this.leftFrontLeg.xRot = Mth.cos(animPos * 0.6662F) * 1.4F * animSpeed;
        this.rightHindLeg.xRot = Mth.cos(animPos * 0.6662F + (float) Math.PI) * 1.4F * animSpeed;
        this.leftHindLeg.xRot = Mth.cos(animPos * 0.6662F) * 1.4F * animSpeed;
        this.rightHindLeg.visible = true;
        this.leftHindLeg.visible = true;
        this.rightFrontLeg.visible = true;
        this.leftFrontLeg.visible = true;
        if (state.isPassenger()) {
            this.root.z -= 2f;
            this.body.xRot = ((float) Math.PI / 2.4F);
            ModelPart modelPart = this.body;
            //modelPart.y -= 7.0F * animPos;
            //modelPart.xRot -= 5;
            this.tail.xRot = ((float) Math.PI / 4F);
            modelPart = this.tail;
            modelPart.z -= animPos + 1;
            modelPart.y += 3;
            modelPart.xRot -= -45;
            this.head.xRot = 0.0F;
            this.head.yRot = 0.0F;

            this.rightHindLeg.xRot -= 1.3089969F;
            modelPart = this.rightHindLeg;
            modelPart.y += 4.0F;
            modelPart.z -= 0.25F * animPos;
            this.leftHindLeg.xRot -= 1.3089969F;
            modelPart = this.leftHindLeg;
            modelPart.y += 4.0F;
            modelPart.z -= 0.25F * animPos;
            this.rightFrontLeg.xRot = -0.2617994F;
            this.leftFrontLeg.xRot = -0.2617994F;
        }
        if ((CONFIG.isBaby)) {
            this.head.xScale = 1.5f;
            this.head.yScale = 1.5f;
            this.head.zScale = 1.5f;
        }
    }
}
