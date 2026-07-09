package com.jeff.pets.client.rendering.vanilla.hoglin;

import com.jeff.pets.mob.vanilla.hostile.ClientHoglin;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HoglinModel;
import net.minecraft.client.model.geom.ModelPart;


import static com.jeff.pets.client.Central.CONFIG;

public class ClientHoglinModel extends HoglinModel<ClientHoglin> {

    private final ModelPart head;

    public ClientHoglinModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim( ClientHoglin state, float f, float g, float h, float i, float j) {
        super.setupAnim(state, f, g, h, i, j);
        if (CONFIG.isBaby) {
            this.head.y -= 5;
        }
    }
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        super.renderToBuffer(poseStack, vertexConsumer, i, j, f, g, h, k);
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
