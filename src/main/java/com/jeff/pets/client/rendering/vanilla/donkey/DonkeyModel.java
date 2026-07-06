package com.jeff.pets.client.rendering.vanilla.donkey;

import com.jeff.pets.mob.vanilla.passive.ClientDonkey;
import com.jeff.pets.client.rendering.PetModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class DonkeyModel extends PetModel<ClientDonkey> {
    private final ModelPart head;
    private final ModelPart mouth;
    private final ModelPart left_ear;
    private final ModelPart right_ear;
    private final ModelPart neck;
    private final ModelPart mane;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart front_left_leg;
    private final ModelPart front_right_leg;
    private final ModelPart back_left_leg;
    private final ModelPart back_right_leg;
    private final ModelPart left_chest;
    private final ModelPart right_chest;

    public DonkeyModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.mouth = root.getChild("mouth");
        this.left_ear = root.getChild("left_ear");
        this.right_ear = root.getChild("right_ear");
        this.neck = root.getChild("neck");
        this.mane = root.getChild("mane");
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
        this.front_left_leg = root.getChild("front_left_leg");
        this.front_right_leg = root.getChild("front_right_leg");
        this.back_left_leg = root.getChild("back_left_leg");
        this.back_right_leg = root.getChild("back_right_leg");
        this.left_chest = root.getChild("left_chest");
        this.right_chest = root.getChild("right_chest");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -11.0F, -2.0F, 6.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -9.0F));

        PartDefinition mouth = partdefinition.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 25).addBox(-2.0F, -11.0F, -7.0F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -9.0F));

        PartDefinition left_ear = partdefinition.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -7.1F, 0.0F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -7.9F, -5.0F));

        PartDefinition right_ear = partdefinition.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -7.1F, 0.0F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -7.9F, -5.0F));

        PartDefinition neck = partdefinition.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 35).addBox(-2.05F, -6.0F, -2.0F, 4.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -9.0F));

        PartDefinition mane = partdefinition.addOrReplaceChild("mane", CubeListBuilder.create().texOffs(56, 36).addBox(-1.0F, -11.0F, 5.01F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -9.01F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-5.0F, -8.0F, -17.0F, 10.0F, 10.0F, 22.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, 11.0F, 6.0F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(42, 36).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 11.0F));

        PartDefinition front_left_leg = partdefinition.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(-3.0F, -1.0F, -1.9F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 14.0F, -9.0F));

        PartDefinition front_right_leg = partdefinition.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(48, 21).addBox(-1.0F, -1.0F, -1.9F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 14.0F, -9.0F));

        PartDefinition back_left_leg = partdefinition.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(-3.0F, -1.0F, -1.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 14.0F, 8.0F));

        PartDefinition back_right_leg = partdefinition.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(48, 21).addBox(-1.0F, -1.0F, -1.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 14.0F, 8.0F));

        PartDefinition left_chest = partdefinition.addOrReplaceChild("left_chest", CubeListBuilder.create().texOffs(26, 21).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 3.0F, 6.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition right_chest = partdefinition.addOrReplaceChild("right_chest", CubeListBuilder.create().texOffs(26, 21).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 3.0F, 6.0F, 0.0F, 1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(ClientDonkey abstractHorse, float f, float g, float h, float a, float b) {
        float i = Mth.rotLerp(h, abstractHorse.yBodyRotO, abstractHorse.yBodyRot);
        float j = Mth.rotLerp(h, abstractHorse.yHeadRotO, abstractHorse.yHeadRot);
        float k = Mth.lerp(h, abstractHorse.xRotO, abstractHorse.getXRot());
        float l = j - i;
        float m = k * ((float) Math.PI / 180F);
        if (l > 20.0F) {
            l = 20.0F;
        }

        if (l < -20.0F) {
            l = -20.0F;
        }

        if (g > 0.2F) {
            m += Mth.cos(f * 0.8F) * 0.15F * g;
        }

        float o = 1;
        float n = 1;
        float q = 1;
        float p = 1.0F;
        float r = (float) abstractHorse.tickCount + h;
        this.head.y = 4.0F;
        this.head.z = -12.0F;
        this.body.xRot = 0.0F;
        this.head.xRot = ((float) Math.PI / 6F) + m;
        this.head.yRot = l * ((float) Math.PI / 180F);
        float s = abstractHorse.isInWater() ? 0.2F : 1.0F;
        float t = Mth.cos(s * f * 0.6662F + (float) Math.PI);
        float u = t * 0.8F * g;
        float v = (1.0F - Math.max(o, n)) * (((float) Math.PI / 6F) + m + q * Mth.sin(r) * 0.05F);
        this.head.xRot = o * (0.2617994F + m) + n * (2.1816616F + Mth.sin(r) * 0.05F) + v;
        this.head.yRot = o * l * ((float) Math.PI / 180F) + (1.0F - Math.max(o, n)) * this.head.yRot;
        this.head.y = o * -4.0F + n * 11.0F + (1.0F - Math.max(o, n)) * this.head.y;
        this.head.z = o * -4.0F + n * -12.0F + (1.0F - Math.max(o, n)) * this.head.z;
        this.body.xRot = o * (-(float) Math.PI / 4F) + p * this.body.xRot;
        float w = 0.2617994F * o;
        float x = Mth.cos(r * 0.6F + (float) Math.PI);
        this.front_left_leg.y = 2.0F * o + 14.0F * p;
        this.front_left_leg.z = -6.0F * o - 10.0F * p;
        this.front_right_leg.y = this.front_left_leg.y;
        this.front_right_leg.z = this.front_left_leg.z;
        float y = ((-(float) Math.PI / 3F) + x) * o + u * p;
        float z = ((-(float) Math.PI / 3F) - x) * o - u * p;
        this.back_left_leg.xRot = w - t * 0.5F * g * p;
        this.back_right_leg.xRot = w + t * 0.5F * g * p;
        this.front_left_leg.xRot = y;
        this.front_right_leg.xRot = z;
        this.tail.xRot = ((float) Math.PI / 6F) + g * 0.75F;
        this.tail.y = -5.0F + g;
        this.tail.z = 2.0F + g * 2.0F;
        this.body.y = 10.8F;
    }
}
