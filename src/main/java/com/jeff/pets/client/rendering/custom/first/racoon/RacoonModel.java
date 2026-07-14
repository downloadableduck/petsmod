package com.jeff.pets.client.rendering.custom.first.racoon;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Racoon;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class RacoonModel extends PetModel<@NotNull Racoon> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart left_hind_leg;
    private final ModelPart right_hind_leg;
    private final ModelPart left_front_leg;
    private final ModelPart right_front_leg;
    private final ModelPart tail;

    public RacoonModel() {
        texWidth = 64;
        texHeight = 64;

        root = new ModelPart(this);
        root.setPos(-1.0F, 16.5F, -3.0F);


        head = new ModelPart(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        root.addChild(head);
        head.texOffs(0, 15).addBox(-2.5F, -7.0F, -2.0F, 7.0F, 5.0F, 5.0F, 0.0F, false);
        head.texOffs(28, 28).addBox(1.5F, -9.0F, 1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.texOffs(0, 30).addBox(-1.5F, -9.0F, 1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.texOffs(1, 26).addBox(-0.5F, -4.0F, -4.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
        head.texOffs(24, 12).addBox(-3.5F, -5.0F, -2.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);
        head.texOffs(24, 20).addBox(4.5F, -5.0F, -2.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);

        body = new ModelPart(this);
        body.setPos(1.0F, -0.5F, -3.0F);
        head.addChild(body);
        setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
        body.texOffs(0, 0).addBox(-3.0F, 4.0F, -3.5F, 6.0F, 9.0F, 6.0F, 0.0F, false);

        left_hind_leg = new ModelPart(this);
        left_hind_leg.setPos(-4.0F, 1.0F, 10.0F);
        root.addChild(left_hind_leg);
        left_hind_leg.texOffs(12, 25).addBox(2.5F, 2.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        right_hind_leg = new ModelPart(this);
        right_hind_leg.setPos(0.0F, 1.0F, 10.0F);
        root.addChild(right_hind_leg);
        right_hind_leg.texOffs(20, 28).addBox(1.5F, 2.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        left_front_leg = new ModelPart(this);
        left_front_leg.setPos(-4.0F, 1.0F, 3.0F);
        root.addChild(left_front_leg);
        left_front_leg.texOffs(12, 25).addBox(2.5F, 2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        right_front_leg = new ModelPart(this);
        right_front_leg.setPos(0.0F, 1.0F, 3.0F);
        root.addChild(right_front_leg);
        right_front_leg.texOffs(20, 28).addBox(1.5F, 2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        tail = new ModelPart(this);
        tail.setPos(-3.0F, 0.5F, 12.0F);
        root.addChild(tail);
        setRotationAngle(tail, 1.5708F, 0.0F, 0.0F);
        tail.texOffs(24, 0).addBox(2.0F, -2.0F, -1.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
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
    public void prepareMobModel(Racoon fox, float f, float g, float h) {
        //this.body.xRot = ((float) Math.PI / 2F);
        this.tail.xRot = -0.05235988F;
        this.right_hind_leg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
        this.left_hind_leg.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.right_front_leg.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.left_front_leg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
        //this.head.setPos(-1.0F, 16.5F, -3.0F);
        //this.head.yRot = 0.0F;
        //this.head.zRot = 0;
        this.right_hind_leg.visible = true;
        this.left_hind_leg.visible = true;
        this.right_front_leg.visible = true;
        this.left_front_leg.visible = true;
        //this.body.setPos(0.0F, 16.0F, -6.0F);
        //this.body.zRot = 0.0F;
        this.right_hind_leg.setPos(-5.0F, 17.5F, 7.0F);
        this.left_hind_leg.setPos(-1.0F, 17.5F, 7.0F);
        this.tail.xRot = 2f;

        if (fox.isPassenger()) {
            //this.body.xRot = 1.35f;
            //this.tail.z = 7;
        }
    }

    public void setupAnim(Racoon fox, float f, float g, float h, float i, float j) {
        this.head.xRot = j * ((float) Math.PI / 180F);
        this.head.yRot = i * ((float) Math.PI / 180F);
    }
}
