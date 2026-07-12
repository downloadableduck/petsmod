package com.jeff.pets.client.rendering.custom.aquatic.koi;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.Koi;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class KoiModel extends PetModel<@NotNull Koi> {
    private final ModelPart body;
    private final ModelPart top_fin;
    private final ModelPart left_hind_fin;
    private final ModelPart left_hind_fin_r1;
    private final ModelPart right_hind_fin;
    private final ModelPart right_hind_fin_r1;
    private final ModelPart left_fin;
    private final ModelPart left_fin_r1;
    private final ModelPart right_fin;
    private final ModelPart right_fin_r1;
    private final ModelPart tail_fin;

    public KoiModel() {
        texWidth = 64;
        texHeight = 64;

        body = new ModelPart(this);
        body.setPos(0.0F, 20.0F, -7.0F);
        body.texOffs(0, 0).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 14.0F, 0.0F, false);

        top_fin = new ModelPart(this);
        top_fin.setPos(-4.0F, 0.0F, 8.0F);
        body.addChild(top_fin);
        top_fin.texOffs(16, 26).addBox(4.0F, -6.0F, -6.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);

        left_hind_fin = new ModelPart(this);
        left_hind_fin.setPos(4.0F, 0.0F, 2.0F);
        body.addChild(left_hind_fin);


        left_hind_fin_r1 = new ModelPart(this);
        left_hind_fin_r1.setPos(-8.0F, 0.0F, 6.0F);
        left_hind_fin.addChild(left_hind_fin_r1);
        setRotationAngle(left_hind_fin_r1, 0.0F, 0.0F, -0.6981F);
        left_hind_fin_r1.texOffs(24, 26).addBox(0.6F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);

        right_hind_fin = new ModelPart(this);
        right_hind_fin.setPos(4.0F, 0.0F, 2.0F);
        body.addChild(right_hind_fin);


        right_hind_fin_r1 = new ModelPart(this);
        right_hind_fin_r1.setPos(-2.0F, -2.0F, 6.0F);
        right_hind_fin.addChild(right_hind_fin_r1);
        setRotationAngle(right_hind_fin_r1, 0.0F, 0.0F, 0.829F);
        right_hind_fin_r1.texOffs(24, 28).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);

        left_fin = new ModelPart(this);
        left_fin.setPos(0.0F, 0.0F, 0.0F);
        body.addChild(left_fin);


        left_fin_r1 = new ModelPart(this);
        left_fin_r1.setPos(-4.0F, 0.0F, 2.0F);
        left_fin.addChild(left_fin_r1);
        setRotationAngle(left_fin_r1, 0.0F, 0.0F, -0.6981F);
        left_fin_r1.texOffs(16, 22).addBox(-1.4F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, 0.0F, false);

        right_fin = new ModelPart(this);
        right_fin.setPos(-4.0F, 0.0F, 2.0F);
        body.addChild(right_fin);


        right_fin_r1 = new ModelPart(this);
        right_fin_r1.setPos(8.0F, 0.0F, 0.0F);
        right_fin.addChild(right_fin_r1);
        setRotationAngle(right_fin_r1, 0.0F, 0.0F, 0.6109F);
        right_fin_r1.texOffs(16, 18).addBox(-2.6F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, 0.0F, false);

        tail_fin = new ModelPart(this);
        tail_fin.setPos(0.0F, 0.0F, 14.0F);
        body.addChild(tail_fin);
        tail_fin.texOffs(0, 18).addBox(0.0F, -8.0F, -2.0F, 0.0F, 12.0F, 8.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.xRot = x;
        ModelPart.yRot = y;
        ModelPart.zRot = z;
    }

    @Override
    public void setupAnim(Koi state, float f, float g, float ageInTicks, float m, float k) {
        this.body.yRot = -1.0f * 0.25F * Mth.sin(1.0f * 0.6F * ageInTicks);
        this.tail_fin.yRot = -this.body.yRot * 1.75f;
    }
}
