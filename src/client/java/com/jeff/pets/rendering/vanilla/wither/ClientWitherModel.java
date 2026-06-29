package com.jeff.pets.rendering.vanilla.wither;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class ClientWitherModel<T extends LivingEntity> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart centerHead;
    private final ModelPart rightHead;
    private final ModelPart leftHead;
    private final ModelPart ribcage;
    private final ModelPart tail;

    public ClientWitherModel(ModelPart modelPart) {
        this.root = modelPart;
        this.ribcage = modelPart.getChild("ribcage");
        this.tail = modelPart.getChild("tail");
        this.centerHead = modelPart.getChild("center_head");
        this.rightHead = modelPart.getChild("right_head");
        this.leftHead = modelPart.getChild("left_head");
    }

    public static LayerDefinition createBodyLayer(CubeDeformation cubeDeformation) {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("shoulders", CubeListBuilder.create().texOffs(0, 16).addBox(-10.0F, 3.9F, -0.5F, 20.0F, 3.0F, 3.0F, cubeDeformation), PartPose.ZERO);
        float f = 0.20420352F;
        partDefinition.addOrReplaceChild("ribcage", CubeListBuilder.create().texOffs(0, 22).addBox(0.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, cubeDeformation).texOffs(24, 22).addBox(-4.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, cubeDeformation).texOffs(24, 22).addBox(-4.0F, 4.0F, 0.5F, 11.0F, 2.0F, 2.0F, cubeDeformation).texOffs(24, 22).addBox(-4.0F, 6.5F, 0.5F, 11.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(-2.0F, 6.9F, -0.5F, 0.20420352F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(12, 22).addBox(0.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(-2.0F, 6.9F + Mth.cos(0.20420352F) * 10.0F, -0.5F + Mth.sin(0.20420352F) * 10.0F, 0.83252203F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("center_head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, cubeDeformation), PartPose.ZERO);
        CubeListBuilder cubeListBuilder = CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, cubeDeformation);
        partDefinition.addOrReplaceChild("right_head", cubeListBuilder, PartPose.offset(-8.0F, 4.0F, 0.0F));
        partDefinition.addOrReplaceChild("left_head", cubeListBuilder, PartPose.offset(10.0F, 4.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    private static <T extends LivingEntity> void setupHeadRotation(T witherBoss, ModelPart modelPart, int i) {
        modelPart.yRot = (witherBoss.yHeadRot - witherBoss.yBodyRot) * ((float) Math.PI / 180F);
        modelPart.xRot = witherBoss.getXRot() * ((float) Math.PI / 180F);
    }

    public ModelPart root() {
        return this.root;
    }

    public void setupAnim(T witherBoss, float f, float g, float h, float i, float j) {
        float k = Mth.cos(h * 0.1F);
        this.ribcage.xRot = (0.065F + 0.05F * k) * (float) Math.PI;
        this.tail.setPos(-2.0F, 6.9F + Mth.cos(this.ribcage.xRot) * 10.0F, -0.5F + Mth.sin(this.ribcage.xRot) * 10.0F);
        this.tail.xRot = (0.265F + 0.1F * k) * (float) Math.PI;
        this.centerHead.yRot = i * ((float) Math.PI / 180F);
        this.centerHead.xRot = j * ((float) Math.PI / 180F);
    }

    public void prepareMobModel(T witherBoss, float f, float g, float h) {
        setupHeadRotation(witherBoss, this.rightHead, 0);
        setupHeadRotation(witherBoss, this.leftHead, 1);
    }
}
