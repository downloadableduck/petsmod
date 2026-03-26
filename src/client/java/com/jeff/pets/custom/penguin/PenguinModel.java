package com.jeff.pets.custom.penguin;

import com.jeff.pets.PetsInitializer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.animal.fox.FoxModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class PenguinModel extends EntityModel<@NotNull PenguinRenderState> {

    public static final ModelLayerLocation PENGUIN_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, "penguin"), "main");

    private final ModelPart body;
    private final ModelPart right_foot;
    private final ModelPart left_foot;
    private final ModelPart right_wing;
    private final ModelPart head;
    private final ModelPart left_wing;
    private final ModelPart tail;

    public PenguinModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.right_foot = root.getChild("right_foot");
        this.left_foot = root.getChild("left_foot");
        this.right_wing = root.getChild("right_wing");
        this.head = root.getChild("head");
        this.left_wing = root.getChild("left_wing");
        this.tail = root.getChild("tail");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition right_foot = partdefinition.addOrReplaceChild("right_foot", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 21.0F, -2.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition right_foot_r1 = right_foot.addOrReplaceChild("right_foot_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -3.0F, -2.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -1.5708F));

        PartDefinition left_foot = partdefinition.addOrReplaceChild("left_foot", CubeListBuilder.create(), PartPose.offset(-2.0F, 21.0F, -2.0F));

        PartDefinition left_foot_r1 = left_foot.addOrReplaceChild("left_foot_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, -3.0F, -2.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -1.5708F));

        PartDefinition right_wing = partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create(), PartPose.offsetAndRotation(4.5F, 8.0F, 0.5F, 0.0F, 1.5708F, 0.0F));

        PartDefinition right_wing_r1 = right_wing.addOrReplaceChild("right_wing_r1", CubeListBuilder.create().texOffs(24, 20).addBox(-10.0F, -2.5F, -0.5F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 20).addBox(-5.5F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(12, 32).addBox(0.5F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -2.5F, 0.0F, 1.5708F, 0.0F));

        PartDefinition left_wing = partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.5F, 8.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition left_wing_r1 = left_wing.addOrReplaceChild("left_wing_r1", CubeListBuilder.create().texOffs(24, 27).addBox(-10.0F, -3.5F, -0.5F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(32, 10).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, 4.0F, 0.0F, 1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void setupAnim(PenguinRenderState state) {
        super.setupAnim(state);
        float flapAngle = (Mth.sin(state.flap) + 1.0F) * state.flapSpeed;
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);
        float animationSpeed = state.walkAnimationSpeed;
        float animationPos = state.walkAnimationPos;
        this.right_foot.xRot = Mth.cos(animationPos * 0.6662F) * 1.4F * animationSpeed;
        this.left_foot.xRot = Mth.cos(animationPos * 0.6662F + (float) Math.PI) * 1.4F * animationSpeed;
        this.right_wing.xRot = flapAngle * 0.75f;
        this.left_wing.xRot = -flapAngle * 0.75f;
        this.body.zRot = Mth.cos(animationPos * 0.6662F) * 0.1F * animationSpeed;
        this.head.zRot = Mth.cos(animationPos * 0.6662F) * 0.1F * animationSpeed;
    }
}
