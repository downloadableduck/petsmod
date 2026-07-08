package com.jeff.pets.client.rendering.vanilla.allay;

import com.jeff.pets.mob.vanilla.passive.ClientAllay;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class ClientAllayModel extends HierarchicalModel<ClientAllay> implements ArmedModel {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart right_wing;
    private final ModelPart left_wing;

    public ClientAllayModel(ModelPart modelPart) {
        super(RenderType::entityTranslucent);
        this.root = modelPart.getChild("root");
        this.head = this.root.getChild("head");
        this.body = this.root.getChild("body");
        this.right_arm = this.body.getChild("right_arm");
        this.left_arm = this.body.getChild("left_arm");
        this.right_wing = this.body.getChild("right_wing");
        this.left_wing = this.body.getChild("left_wing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition partDefinition2 = partDefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 23.5F, 0.0F));
        partDefinition2.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.99F, 0.0F));
        PartDefinition partDefinition3 = partDefinition2.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 10).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 16).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, -4.0F, 0.0F));
        partDefinition3.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(23, 0).addBox(-0.75F, -0.5F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offset(-1.75F, 0.5F, 0.0F));
        partDefinition3.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(23, 6).addBox(-0.25F, -0.5F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offset(1.75F, 0.5F, 0.0F));
        partDefinition3.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(16, 14).addBox(0.0F, 1.0F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 0.6F));
        partDefinition3.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(16, 14).addBox(0.0F, 1.0F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 0.6F));
        return LayerDefinition.create(meshDefinition, 32, 32);
    }

    @Override
    public void setupAnim(ClientAllay allay, float f, float g, float h, float i, float j) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        float k = h * 20.0F * ((float) Math.PI / 180F) + f;
        float l = Mth.cos(k) * (float) Math.PI * 0.15F + g;
        float m = h - (float) allay.tickCount;
        float n = h * 9.0F * ((float) Math.PI / 180F);
        float o = Math.min(g / 0.3F, 1.0F);
        float p = 1.0F - o;
        this.head.xRot = j * ((float) Math.PI / 180F);
        this.head.yRot = i * ((float) Math.PI / 180F);

        this.right_wing.xRot = 0.43633232F * (1.0F - o);
        this.right_wing.yRot = (-(float) Math.PI / 4F) + l;
        this.left_wing.xRot = 0.43633232F * (1.0F - o);
        this.left_wing.yRot = ((float) Math.PI / 4F) - l;
        this.body.xRot = o * ((float) Math.PI / 4F);
        float q = 1;
        float r = q * Mth.lerp(o, (-(float) Math.PI / 3F), -1.134464F);
        ModelPart var10000 = this.root;
        var10000.y += (float) Math.cos((double) n) * 0.25F * p;
        this.right_arm.xRot = r;
        this.left_arm.xRot = r;
        float s = p * (1.0F - q);
        float t = 0.43633232F - Mth.cos(n + ((float) Math.PI * 1.5F)) * (float) Math.PI * 0.075F * s;
        this.left_arm.zRot = -t;
        this.right_arm.zRot = t;
        this.right_arm.yRot = 0.27925268F * q;
        this.left_arm.yRot = -0.27925268F * q;
    }

    public void translateToHand(HumanoidArm humanoidArm, PoseStack poseStack) {
        float f = 1.0F;
        float g = 3.0F;
        this.root.translateAndRotate(poseStack);
        this.body.translateAndRotate(poseStack);
        poseStack.translate(0.0F, 0.0625F, 0.1875F);
        poseStack.mulPose(Vector3f.XP.rotation(this.right_arm.xRot));
        poseStack.scale(0.7F, 0.7F, 0.7F);
        poseStack.translate(0.0625F, 0.0F, 0.0F);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
