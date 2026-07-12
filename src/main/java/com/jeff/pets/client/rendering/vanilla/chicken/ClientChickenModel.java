package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenModel<T extends ClientChicken> extends ChickenModel<T> {

    public ClientChickenModel() {
        super();
    }

    @Override
    public void setupAnim(@NotNull T state, float f, float g, float h, float i, float j) {
        h = getBob(state, f);
        ModelPart head = this.headParts().iterator().next();
        super.setupAnim(state, f, g, h, i, j);
    }

    protected float getBob(ClientChicken chicken, float f) {
        float g = Mth.lerp(f, chicken.oFlap, chicken.flap);
        float h = Mth.lerp(f, chicken.oFlapSpeed, chicken.flapSpeed);
        return (Mth.sin(g) + 1.0F) * h;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        super.renderToBuffer(poseStack, vertexConsumer, i, j, f, g, h, k);
        poseStack.pushPose();
        if (CONFIG.isBaby) {
            poseStack.scale(2, 2, 2);
        } else {
            poseStack.scale(1, 1, 1);
        }
        this.headParts().iterator().next().translateAndRotate(poseStack);
        poseStack.popPose();
    }
}
