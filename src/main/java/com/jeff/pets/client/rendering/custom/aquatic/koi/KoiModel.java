package com.jeff.pets.client.rendering.custom.aquatic.koi;

import com.jeff.pets.mob.custom.aquatic.Koi;
import com.jeff.pets.client.rendering.PetModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;


public class KoiModel extends PetModel< Koi> {
    private final ModelPart body;
    private final ModelPart tail_fin;
    private final ModelPart left_fin;
    private final ModelPart right_fin;
    private final ModelPart right_hind_fin;
    private final ModelPart left_hind_fin;
    private final ModelPart top_fin;

    public KoiModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.top_fin = this.body.getChild("top_fin");
        this.left_hind_fin = this.body.getChild("left_hind_fin");
        this.right_hind_fin = this.body.getChild("right_hind_fin");
        this.left_fin = this.body.getChild("left_fin");
        this.right_fin = this.body.getChild("right_fin");
        this.tail_fin = this.body.getChild("tail_fin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, -7.0F));

        PartDefinition top_fin = body.addOrReplaceChild("top_fin", CubeListBuilder.create().texOffs(16, 26).addBox(4.0F, -6.0F, -6.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, 8.0F));

        PartDefinition left_hind_fin = body.addOrReplaceChild("left_hind_fin", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, 2.0F));

        PartDefinition left_hind_fin_r1 = left_hind_fin.addOrReplaceChild("left_hind_fin_r1", CubeListBuilder.create().texOffs(24, 26).addBox(0.6F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 6.0F, 0.0F, 0.0F, -0.6981F));

        PartDefinition right_hind_fin = body.addOrReplaceChild("right_hind_fin", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, 2.0F));

        PartDefinition right_hind_fin_r1 = right_hind_fin.addOrReplaceChild("right_hind_fin_r1", CubeListBuilder.create().texOffs(24, 28).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -2.0F, 6.0F, 0.0F, 0.0F, 0.829F));

        PartDefinition left_fin = body.addOrReplaceChild("left_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_fin_r1 = left_fin.addOrReplaceChild("left_fin_r1", CubeListBuilder.create().texOffs(16, 22).addBox(-1.4F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.6981F));

        PartDefinition right_fin = body.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, 2.0F));

        PartDefinition right_fin_r1 = right_fin.addOrReplaceChild("right_fin_r1", CubeListBuilder.create().texOffs(16, 18).addBox(-2.6F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

        PartDefinition tail_fin = body.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -8.0F, -2.0F, 0.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 14.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Koi state, float f, float g, float ageInTicks, float m, float k) {
        this.body.yRot = -1.0f * 0.25F * Mth.sin(1.0f * 0.6F * ageInTicks);
        this.tail_fin.yRot = -this.body.yRot * 1.75f;
    }
}
