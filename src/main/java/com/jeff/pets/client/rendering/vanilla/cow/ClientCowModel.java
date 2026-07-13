package com.jeff.pets.client.rendering.vanilla.cow;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.entity.Entity;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowModel<T extends Entity> extends CowModel<T> {

    public ClientCowModel() {
        super();
    }

    @Override
    public void renderToBuffer(MatrixStack poseStack, IVertexBuilder vertexConsumer, int i, int j, float f, float g, float h, float k) {
        super.renderToBuffer(poseStack, vertexConsumer, i, j, f, g, h, k);
        poseStack.pushPose();
        if (CONFIG.isBaby) {
            poseStack.scale(2, 2, 2);
        } else {
            poseStack.scale(1, 1, 1);
        }
        this.head.translateAndRotate(poseStack);
        poseStack.popPose();
    }
}
