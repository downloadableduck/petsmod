package com.jeff.pets.client.rendering.vanilla.strider;

import com.jeff.pets.mob.vanilla.passive.ClientStrider;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class ClientStriderMOdel extends HierarchicalModel<ClientStrider> {
    private static final String RIGHT_BOTTOM_BRISTLE = "right_bottom_bristle";
    private static final String RIGHT_MIDDLE_BRISTLE = "right_middle_bristle";
    private static final String RIGHT_TOP_BRISTLE = "right_top_bristle";
    private static final String LEFT_TOP_BRISTLE = "left_top_bristle";
    private static final String LEFT_MIDDLE_BRISTLE = "left_middle_bristle";
    private static final String LEFT_BOTTOM_BRISTLE = "left_bottom_bristle";
    private final ModelPart root;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart body;
    private final ModelPart rightBottomBristle;
    private final ModelPart rightMiddleBristle;
    private final ModelPart rightTopBristle;
    private final ModelPart leftTopBristle;
    private final ModelPart leftMiddleBristle;
    private final ModelPart leftBottomBristle;

    public ClientStriderMOdel(ModelPart modelPart) {
        this.root = modelPart;
        this.rightLeg = modelPart.getChild("right_leg");
        this.leftLeg = modelPart.getChild("left_leg");
        this.body = modelPart.getChild("body");
        this.rightBottomBristle = this.body.getChild("right_bottom_bristle");
        this.rightMiddleBristle = this.body.getChild("right_middle_bristle");
        this.rightTopBristle = this.body.getChild("right_top_bristle");
        this.leftTopBristle = this.body.getChild("left_top_bristle");
        this.leftMiddleBristle = this.body.getChild("left_middle_bristle");
        this.leftBottomBristle = this.body.getChild("left_bottom_bristle");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F), PartPose.offset(-4.0F, 8.0F, 0.0F));
        partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 55).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F), PartPose.offset(4.0F, 8.0F, 0.0F));
        PartDefinition partDefinition2 = partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -6.0F, -8.0F, 16.0F, 14.0F, 16.0F), PartPose.offset(0.0F, 1.0F, 0.0F));
        partDefinition2.addOrReplaceChild("right_bottom_bristle", CubeListBuilder.create().texOffs(16, 65).addBox(-12.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, true), PartPose.offsetAndRotation(-8.0F, 4.0F, -8.0F, 0.0F, 0.0F, -1.2217305F));
        partDefinition2.addOrReplaceChild("right_middle_bristle", CubeListBuilder.create().texOffs(16, 49).addBox(-12.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, true), PartPose.offsetAndRotation(-8.0F, -1.0F, -8.0F, 0.0F, 0.0F, -1.134464F));
        partDefinition2.addOrReplaceChild("right_top_bristle", CubeListBuilder.create().texOffs(16, 33).addBox(-12.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, true), PartPose.offsetAndRotation(-8.0F, -5.0F, -8.0F, 0.0F, 0.0F, -0.87266463F));
        partDefinition2.addOrReplaceChild("left_top_bristle", CubeListBuilder.create().texOffs(16, 33).addBox(0.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F), PartPose.offsetAndRotation(8.0F, -6.0F, -8.0F, 0.0F, 0.0F, 0.87266463F));
        partDefinition2.addOrReplaceChild("left_middle_bristle", CubeListBuilder.create().texOffs(16, 49).addBox(0.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F), PartPose.offsetAndRotation(8.0F, -2.0F, -8.0F, 0.0F, 0.0F, 1.134464F));
        partDefinition2.addOrReplaceChild("left_bottom_bristle", CubeListBuilder.create().texOffs(16, 65).addBox(0.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F), PartPose.offsetAndRotation(8.0F, 3.0F, -8.0F, 0.0F, 0.0F, 1.2217305F));
        return LayerDefinition.create(meshDefinition, 64, 128);
    }

    public void setupAnim(ClientStrider strider, float f, float g, float h, float i, float j) {
        g = Math.min(0.25F, g);
        if (!strider.isVehicle()) {
            this.body.xRot = j * ((float) Math.PI / 180F);
            this.body.yRot = i * ((float) Math.PI / 180F);
        } else {
            this.body.xRot = 0.0F;
            this.body.yRot = 0.0F;
        }

        float k = 1.5F;
        this.body.zRot = 0.1F * Mth.sin(f * 1.5F) * 4.0F * g;
        this.body.y = 2.0F;
        ModelPart var10000 = this.body;
        var10000.y -= 2.0F * Mth.cos(f * 1.5F) * 2.0F * g;
        this.leftLeg.xRot = Mth.sin(f * 1.5F * 0.5F) * 2.0F * g;
        this.rightLeg.xRot = Mth.sin(f * 1.5F * 0.5F + (float) Math.PI) * 2.0F * g;
        this.leftLeg.zRot = 0.17453292F * Mth.cos(f * 1.5F * 0.5F) * g;
        this.rightLeg.zRot = 0.17453292F * Mth.cos(f * 1.5F * 0.5F + (float) Math.PI) * g;
        this.leftLeg.y = 8.0F + 2.0F * Mth.sin(f * 1.5F * 0.5F + (float) Math.PI) * 2.0F * g;
        this.rightLeg.y = 8.0F + 2.0F * Mth.sin(f * 1.5F * 0.5F) * 2.0F * g;
        this.rightBottomBristle.zRot = -1.2217305F;
        this.rightMiddleBristle.zRot = -1.134464F;
        this.rightTopBristle.zRot = -0.87266463F;
        this.leftTopBristle.zRot = 0.87266463F;
        this.leftMiddleBristle.zRot = 1.134464F;
        this.leftBottomBristle.zRot = 1.2217305F;
        float l = Mth.cos(f * 1.5F + (float) Math.PI) * g;
        var10000 = this.rightBottomBristle;
        var10000.zRot += l * 1.3F;
        var10000 = this.rightMiddleBristle;
        var10000.zRot += l * 1.2F;
        var10000 = this.rightTopBristle;
        var10000.zRot += l * 0.6F;
        var10000 = this.leftTopBristle;
        var10000.zRot += l * 0.6F;
        var10000 = this.leftMiddleBristle;
        var10000.zRot += l * 1.2F;
        var10000 = this.leftBottomBristle;
        var10000.zRot += l * 1.3F;
        float m = 1.0F;
        float n = 1.0F;
        var10000 = this.rightBottomBristle;
        var10000.zRot += 0.05F * Mth.sin(h * 1.0F * -0.4F);
        var10000 = this.rightMiddleBristle;
        var10000.zRot += 0.1F * Mth.sin(h * 1.0F * 0.2F);
        var10000 = this.rightTopBristle;
        var10000.zRot += 0.1F * Mth.sin(h * 1.0F * 0.4F);
        var10000 = this.leftTopBristle;
        var10000.zRot += 0.1F * Mth.sin(h * 1.0F * 0.4F);
        var10000 = this.leftMiddleBristle;
        var10000.zRot += 0.1F * Mth.sin(h * 1.0F * 0.2F);
        var10000 = this.leftBottomBristle;
        var10000.zRot += 0.05F * Mth.sin(h * 1.0F * -0.4F);
    }

    public ModelPart root() {
        return this.root;
    }
}
