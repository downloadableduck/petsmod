package com.jeff.pets.client.rendering.vanilla.piglin;

import com.jeff.pets.mob.vanilla.neutral.ClientPiglin;
import net.minecraft.client.renderer.entity.model.PiglinModel;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPiglinModel extends PiglinModel<ClientPiglin> {


    public ClientPiglinModel(float f, int i, int j) {
        super(f, i, j);
    }

    @Override
    public void setupAnim(ClientPiglin state, float f, float g, float h, float i, float k) {
        super.setupAnim(state, f, g, h, i, k);
    }

    @Override
    public void renderToBuffer(com.mojang.blaze3d.matrix.MatrixStack poseStack, com.mojang.blaze3d.vertex.IVertexBuilder vertexConsumer, int i, int j, float f, float g, float h, float k) {
        poseStack.pushPose();
        if (CONFIG.isBaby) {
            poseStack.scale(1.5f, 1.5f, 1.5f);
        } else {
            poseStack.scale(1, 1, 1);
        }
        this.head.translateAndRotate(poseStack);
        poseStack.popPose();
    }
}
