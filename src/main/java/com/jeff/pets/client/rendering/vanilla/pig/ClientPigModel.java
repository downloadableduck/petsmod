package com.jeff.pets.client.rendering.vanilla.pig;

import com.jeff.pets.mob.vanilla.passive.ClientPig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PigModel;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigModel extends PigModel<ClientPig> {

    public ClientPigModel() {
        super();
    }

    @Override
    public void setupAnim(ClientPig state, float f, float g, float h, float i, float k) {
        super.setupAnim(state, f, g, h, i, k);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
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
