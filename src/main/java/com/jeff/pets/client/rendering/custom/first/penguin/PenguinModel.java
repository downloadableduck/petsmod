package com.jeff.pets.client.rendering.custom.first.penguin;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Penguin;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class PenguinModel extends PetModel<@NotNull Penguin> {

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart left_wing;
    private final ModelPart right_wing;
    private final ModelPart left_foot;
    private final ModelPart left_foot_r1;
    private final ModelPart right_foot;
    private final ModelPart right_foot_r1;
    private final ModelPart tail;
    private final ModelPart head;
    private final ModelPart beak;

    public PenguinModel() {
        texWidth = 64;
        texHeight = 64;

        root = new ModelPart(this);
        root.setPos(0.0F, 28.0F, 0.0F);
        setRotationAngle(root, 0.0F, 1.5708F, 0.0F);


        body = new ModelPart(this);
        body.setPos(0.0F, 4.0F, 0.0F);
        root.addChild(body);
        body.texOffs(0, 0).addBox(-8.0F, -20.0F, 0.0F, 8.0F, 12.0F, 8.0F, 0.0F, false);

        left_wing = new ModelPart(this);
        left_wing.setPos(-4.0F, -15.0F, 8.5F);
        root.addChild(left_wing);
        setRotationAngle(left_wing, 0.0F, 0.0F, -1.5708F);
        left_wing.texOffs(24, 25).addBox(-10.0F, -3.0F, -0.5F, 10.0F, 6.0F, 1.0F, 0.0F, false);

        right_wing = new ModelPart(this);
        right_wing.setPos(-4.0F, -15.0F, 0.0F);
        root.addChild(right_wing);
        setRotationAngle(right_wing, 0.0F, 0.0F, -1.5708F);
        right_wing.texOffs(24, 25).addBox(-10.0F, -3.0F, -1.0F, 10.0F, 6.0F, 1.0F, 0.0F, false);

        left_foot = new ModelPart(this);
        left_foot.setPos(-4.0F, -4.0F, 2.0F);
        root.addChild(left_foot);


        left_foot_r1 = new ModelPart(this);
        left_foot_r1.setPos(5.0F, 1.0F, 0.0F);
        left_foot.addChild(left_foot_r1);
        setRotationAngle(left_foot_r1, 0.0F, 0.0F, -1.5708F);
        left_foot_r1.texOffs(0, 32).addBox(-1.0F, -6.0F, 2.0F, 2.0F, 6.0F, 4.0F, 0.0F, false);

        right_foot = new ModelPart(this);
        right_foot.setPos(-4.0F, -4.0F, 7.0F);
        root.addChild(right_foot);


        right_foot_r1 = new ModelPart(this);
        right_foot_r1.setPos(5.0F, 1.0F, -9.0F);
        right_foot.addChild(right_foot_r1);
        setRotationAngle(right_foot_r1, 0.0F, 0.0F, -1.5708F);
        right_foot_r1.texOffs(32, 0).addBox(-1.0F, -6.0F, 2.0F, 2.0F, 6.0F, 4.0F, 0.0F, false);

        tail = new ModelPart(this);
        tail.setPos(-3.0F, -3.0F, 2.0F);
        root.addChild(tail);
        tail.texOffs(-8, -8).addBox(3.0F, -4.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(1.0F, -5.0F, 3.0F);
        root.addChild(head);
        head.texOffs(0, 20).addBox(-8.0F, -17.0F, -2.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

        beak = new ModelPart(this);
        beak.setPos(0.0F, 0.0F, 0.0F);
        head.addChild(beak);
        beak.texOffs(12, 32).addBox(-2.0F, -14.0F, 0.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        root.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.xRot = x;
        ModelPart.yRot = y;
        ModelPart.zRot = z;
    }

    @Override
    public void setupAnim(Penguin state, float f, float g, float h, float i, float k) {
        float flapAngle = (Mth.sin(state.flap) + 1.0F) * state.flapSpeed;
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);
        float animationSpeed = state.animationSpeed;
        float animationPos = state.animationPosition;
        this.right_foot.zRot = Mth.cos(animationPos * 0.6662F) * 1.4F * animationSpeed;
        this.left_foot.zRot = Mth.cos(animationPos * 0.6662F + (float) Math.PI) * 1.4F * animationSpeed;
        this.right_wing.yRot = -flapAngle * 0.75F;
        this.left_wing.yRot = flapAngle * 0.75F;
        this.body.zRot = Mth.cos(animationPos * 0.6662F) * 0.1F * animationSpeed;
        this.head.zRot = Mth.cos(animationPos * 0.6662F) * 0.1F * animationSpeed;
    }
}
