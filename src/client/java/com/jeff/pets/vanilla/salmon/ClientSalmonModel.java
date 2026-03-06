package com.jeff.pets.vanilla.salmon;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.SalmonRenderState;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

/**
 * DO NOT DELETE, the cat requires a custom model to work properly
 */
public class ClientSalmonModel extends EntityModel<@NotNull LivingEntityRenderState> {
    private final ModelPart bodyBack;

    public ClientSalmonModel(ModelPart modelPart) {
        super(modelPart);
        this.bodyBack = modelPart.getChild("body_back");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        int i = 20;
        PartDefinition body_front = partDefinition.addOrReplaceChild("body_front", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 8.0F), PartPose.offset(0.0F, 20.0F, -7.2F));
        PartDefinition body_back = partDefinition.addOrReplaceChild("body_back", CubeListBuilder.create().texOffs(0, 13).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 8.0F), PartPose.offset(0.0F, 20.0F, 0.8000002F));
        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 0).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F), PartPose.offset(0.0F, 20.0F, -7.2F));
        body_back.addOrReplaceChild("back_fin", CubeListBuilder.create().texOffs(20, 10).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 6.0F), PartPose.offset(0.0F, 0.0F, 8.0F));
        body_front.addOrReplaceChild("top_front_fin", CubeListBuilder.create().texOffs(2, 1).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 3.0F), PartPose.offset(0.0F, -4.5F, 5.0F));
        body_back.addOrReplaceChild("top_back_fin", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 4.0F), PartPose.offset(0.0F, -4.5F, -1.0F));
        partDefinition.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(-4, 0).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F), PartPose.offsetAndRotation(-1.5F, 21.5F, -7.2F, 0.0F, 0.0F, (-(float) Math.PI / 4F)));
        partDefinition.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F), PartPose.offsetAndRotation(1.5F, 21.5F, -7.2F, 0.0F, 0.0F, ((float) Math.PI / 4F)));
        return LayerDefinition.create(meshDefinition, 32, 32);
    }

    public void setupAnim(SalmonRenderState salmonRenderState) {
        super.setupAnim(salmonRenderState);
        float f = 1.0F;
        float g = 1.0F;
        if (!salmonRenderState.isInWater) {
            f = 1.3F;
            g = 1.7F;
        }

        this.bodyBack.yRot = -f * 0.25F * Mth.sin(g * 0.6F * salmonRenderState.ageInTicks);
    }
}
