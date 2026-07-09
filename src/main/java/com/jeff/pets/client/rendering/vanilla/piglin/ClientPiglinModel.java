package com.jeff.pets.client.rendering.vanilla.piglin;

import com.jeff.pets.mob.vanilla.neutral.ClientPiglin;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.geom.ModelPart;


import static com.jeff.pets.client.Central.CONFIG;

public class ClientPiglinModel extends PiglinModel<ClientPiglin> {

    private final ModelPart head;

    public ClientPiglinModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim( ClientPiglin state, float f, float g, float h, float i, float k) {
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
