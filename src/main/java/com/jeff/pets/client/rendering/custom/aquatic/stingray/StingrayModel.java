package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class StingrayModel extends PetModel<@NotNull Stingray> {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart right_fin;
    private final ModelPart left_fin;

    public StingrayModel() {
        texWidth = 64;
        texHeight = 64;

        root = new ModelPart(this);
        root.setPos(0.0F, 24.0F, 0.0F);


        body = new ModelPart(this);
        body.setPos(0.0F, -2.0F, 0.0F);
        root.addChild(body);
        body.texOffs(0, 0).addBox(-6.0F, -2.0F, -6.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);

        tail = new ModelPart(this);
        tail.setPos(0.0F, -2.0F, 0.0F);
        root.addChild(tail);
        tail.texOffs(0, 14).addBox(-1.0F, -2.0F, 6.0F, 2.0F, 2.0F, 10.0F, 0.0F, false);

        right_fin = new ModelPart(this);
        right_fin.setPos(6.0F, -4.0F, -1.0F);
        root.addChild(right_fin);
        right_fin.texOffs(24, 14).addBox(0.0F, 0.0F, -3.0F, 4.0F, 2.0F, 6.0F, 0.0F, false);

        left_fin = new ModelPart(this);
        left_fin.setPos(-6.0F, -4.0F, -1.0F);
        root.addChild(left_fin);
        left_fin.texOffs(24, 22).addBox(-4.0F, 0.0F, -3.0F, 4.0F, 2.0F, 6.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.xRot = x;
        ModelPart.yRot = y;
        ModelPart.zRot = z;
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
