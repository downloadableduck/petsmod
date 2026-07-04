package com.jeff.pets.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class PetModel<T extends LivingEntity> extends EntityModel<T> {

    protected final ModelPart root;

    public PetModel(ModelPart root) {
        this.root = root;
    }

    @Override
    public void setupAnim(T entity, float f, float g, float h, float i, float j) {
        this.root.getAllParts().forEach(ModelPart::resetPose);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        this.root.render(poseStack, vertexConsumer, i, j);
    }
}
