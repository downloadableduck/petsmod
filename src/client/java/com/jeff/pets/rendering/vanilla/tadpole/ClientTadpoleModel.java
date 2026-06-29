package com.jeff.pets.rendering.vanilla.tadpole;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.passive.ClientTadpole;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class ClientTadpoleModel extends AgeableListModel<ClientTadpole> {
    private final ModelPart root;
    private final ModelPart tail;

    public ClientTadpoleModel(ModelPart modelPart) {
        super(true, 8.0F, 3.35F);
        this.root = modelPart;
        this.tail = modelPart.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        float f = 0.0F;
        float g = 22.0F;
        float h = -3.0F;
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 3.0F), PartPose.offset(0.0F, 22.0F, -3.0F));
        partDefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 7.0F), PartPose.offset(0.0F, 22.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 16, 16);
    }

    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.root);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.tail);
    }

    public void setupAnim(ClientTadpole tadpole, float f, float g, float h, float i, float j) {
        float k = tadpole.isInWater() ? 1.0F : 1.5F;
        this.tail.yRot = -k * 0.25F * Mth.sin(0.3F * h);
    }
}
