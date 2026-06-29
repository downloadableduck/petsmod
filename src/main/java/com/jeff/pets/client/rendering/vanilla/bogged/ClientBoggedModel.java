package com.jeff.pets.client.rendering.vanilla.bogged;

import com.jeff.pets.mob.vanilla.hostile.ClientBogged;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ClientBoggedModel extends SkeletonModel<ClientBogged> {
    private final ModelPart mushrooms;

    public ClientBoggedModel(ModelPart modelPart) {
        super(modelPart);
        this.mushrooms = modelPart.getChild("head").getChild("mushrooms");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partDefinition = meshDefinition.getRoot();
        SkeletonModel.createDefaultSkeletonMesh(partDefinition);
        PartDefinition partDefinition2 = partDefinition.getChild("head").addOrReplaceChild("mushrooms", CubeListBuilder.create(), PartPose.ZERO);
        partDefinition2.addOrReplaceChild("red_mushroom_1", CubeListBuilder.create().texOffs(50, 16).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 0.0F), PartPose.offsetAndRotation(3.0F, -8.0F, 3.0F, 0.0F, ((float) Math.PI / 4F), 0.0F));
        partDefinition2.addOrReplaceChild("red_mushroom_2", CubeListBuilder.create().texOffs(50, 16).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 0.0F), PartPose.offsetAndRotation(3.0F, -8.0F, 3.0F, 0.0F, 2.3561945F, 0.0F));
        partDefinition2.addOrReplaceChild("brown_mushroom_1", CubeListBuilder.create().texOffs(50, 22).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 0.0F), PartPose.offsetAndRotation(-3.0F, -8.0F, -3.0F, 0.0F, ((float) Math.PI / 4F), 0.0F));
        partDefinition2.addOrReplaceChild("brown_mushroom_2", CubeListBuilder.create().texOffs(50, 22).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 0.0F), PartPose.offsetAndRotation(-3.0F, -8.0F, -3.0F, 0.0F, 2.3561945F, 0.0F));
        partDefinition2.addOrReplaceChild("brown_mushroom_3", CubeListBuilder.create().texOffs(50, 28).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 4.0F, 0.0F), PartPose.offsetAndRotation(-2.0F, -1.0F, 4.0F, (-(float) Math.PI / 2F), 0.0F, ((float) Math.PI / 4F)));
        partDefinition2.addOrReplaceChild("brown_mushroom_4", CubeListBuilder.create().texOffs(50, 28).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 4.0F, 0.0F), PartPose.offsetAndRotation(-2.0F, -1.0F, 4.0F, (-(float) Math.PI / 2F), 0.0F, 2.3561945F));
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public void prepareMobModel(ClientBogged bogged, float f, float g, float h) {
        this.mushrooms.visible = true;
        super.prepareMobModel(bogged, f, g, h);
    }
}
