package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.mob.custom.aquatic.Stingray;
import com.jeff.pets.client.rendering.PetModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class StingrayModel extends PetModel<@NotNull Stingray> {
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart right_fin;
    private final ModelPart left_fin;

    public StingrayModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
        this.right_fin = root.getChild("right_fin");
        this.left_fin = root.getChild("left_fin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -2.0F, -6.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -2.0F, 6.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));

        PartDefinition right_fin = partdefinition.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(24, 14).addBox(0.0F, 0.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 20.0F, -1.0F));

        PartDefinition left_fin = partdefinition.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(24, 22).addBox(-4.0F, 0.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 20.0F, -1.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Stingray state, float f, float g, float m, float k, float p) {
        float partialTick = m;
        float flapTime = Mth.lerp(partialTick, state.oFlap, state.flap);
        if (state.animationSpeed > 0) {
            float anim = flapTime * 7.448451F * ((float) Math.PI / 180F);
            this.left_fin.zRot = Mth.cos(anim) * 16.0F * ((float) Math.PI / 180F);
            this.right_fin.zRot = -this.left_fin.zRot;
            this.tail.yRot = this.left_fin.zRot;
        }
    }
}
