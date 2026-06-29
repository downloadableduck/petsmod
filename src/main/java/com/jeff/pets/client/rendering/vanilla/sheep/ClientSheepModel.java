package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.mob.vanilla.passive.ClientSheep;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSheepModel extends QuadrupedModel<ClientSheep> {
    private float headXRot;

    public ClientSheepModel(ModelPart modelPart) {
        super(modelPart, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = QuadrupedModel.createBodyMesh(12, CubeDeformation.NONE);
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F), PartPose.offset(0.0F, 6.0F, -8.0F));
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public void prepareMobModel(ClientSheep sheep, float f, float g, float h) {
        super.prepareMobModel(sheep, f, g, h);
    }

    public void setupAnim(ClientSheep sheep, float f, float g, float h, float i, float j) {
        super.setupAnim(sheep, f, g, h, i, j);
        if (CONFIG.isBaby) {
            head.xScale = 2.0f;
            head.yScale = 2.0f;
            head.zScale = 2.0f;
        }
    }
}
