package com.jeff.pets.client.rendering.custom.first.penguin;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Penguin;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class PenguinModel extends PetModel<Penguin> {

    private final RendererModel root;
    private final RendererModel body;
    private final RendererModel left_wing;
    private final RendererModel right_wing;
    private final RendererModel left_foot;
    private final RendererModel left_foot_r1;
    private final RendererModel right_foot;
    private final RendererModel right_foot_r1;
    private final RendererModel tail;
    private final RendererModel head;
    private final RendererModel beak;

    public PenguinModel() {
        texWidth = 64;
        texHeight = 64;

        root = new RendererModel(this);
        root.setPos(0.0F, 28.0F, 0.0F);
        setRotationAngle(root, 0.0F, 1.5708F, 0.0F);


        body = new RendererModel(this);
        body.setPos(0.0F, 4.0F, 0.0F);
        root.addChild(body);
        body.texOffs(0, 0).addBox(-8.0F, -20.0F, 0.0F, (int) 8.0F, (int) 12.0F, (int) 8.0F, 0.0F, false);

        left_wing = new RendererModel(this);
        left_wing.setPos(-4.0F, -15.0F, 8.5F);
        root.addChild(left_wing);
        setRotationAngle(left_wing, 0.0F, 0.0F, -1.5708F);
        left_wing.texOffs(24, 25).addBox(-10.0F, -3.0F, -0.5F, (int) 10.0F, (int) 6.0F, (int) 1.0F, 0.0F, false);

        right_wing = new RendererModel(this);
        right_wing.setPos(-4.0F, -15.0F, 0.0F);
        root.addChild(right_wing);
        setRotationAngle(right_wing, 0.0F, 0.0F, -1.5708F);
        right_wing.texOffs(24, 25).addBox(-10.0F, -3.0F, -1.0F, (int) 10.0F, (int) 6.0F, (int) 1.0F, 0.0F, false);

        left_foot = new RendererModel(this);
        left_foot.setPos(-4.0F, -4.0F, 2.0F);
        root.addChild(left_foot);


        left_foot_r1 = new RendererModel(this);
        left_foot_r1.setPos(5.0F, 1.0F, 0.0F);
        left_foot.addChild(left_foot_r1);
        setRotationAngle(left_foot_r1, 0.0F, 0.0F, -1.5708F);
        left_foot_r1.texOffs(0, 32).addBox(-1.0F, -6.0F, 2.0F, (int) 2.0F, (int) 6.0F, (int) 4.0F, 0.0F, false);

        right_foot = new RendererModel(this);
        right_foot.setPos(-4.0F, -4.0F, 7.0F);
        root.addChild(right_foot);


        right_foot_r1 = new RendererModel(this);
        right_foot_r1.setPos(5.0F, 1.0F, -9.0F);
        right_foot.addChild(right_foot_r1);
        setRotationAngle(right_foot_r1, 0.0F, 0.0F, -1.5708F);
        right_foot_r1.texOffs(32, 0).addBox(-1.0F, -6.0F, 2.0F, (int) 2.0F, (int) 6.0F, (int) 4.0F, 0.0F, false);

        tail = new RendererModel(this);
        tail.setPos(-3.0F, -3.0F, 2.0F);
        root.addChild(tail);
        tail.texOffs(-8, -8).addBox(3.0F, -4.0F, 0.0F, (int) 2.0F, (int) 2.0F, (int) 4.0F, 0.0F, false);

        head = new RendererModel(this);
        head.setPos(1.0F, -5.0F, 3.0F);
        root.addChild(head);
        head.texOffs(0, 20).addBox(-8.0F, -17.0F, -2.0F, (int) 6.0F, (int) 6.0F, (int) 6.0F, 0.0F, false);

        beak = new RendererModel(this);
        beak.setPos(0.0F, 0.0F, 0.0F);
        head.addChild(beak);
        beak.texOffs(12, 32).addBox(-2.0F, -14.0F, 0.0F, (int) 4.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);
    }

    @Override
    public void render(Penguin penguin, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        root.render(alpha);
    }

    public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.xRot = x;
        RendererModel.yRot = y;
        RendererModel.zRot = z;
    }

    @Override
    public void setupAnim(Penguin state, float f, float g, float h, float i, float k, float j) {
        float flapAngle = (net.minecraft.util.math.MathHelper.sin(state.flap) + 1.0F) * state.flapSpeed;
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);
        float animationSpeed = state.animationSpeed;
        float animationPos = state.animationPosition;
        this.right_foot.zRot = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F) * 1.4F * animationSpeed;
        this.left_foot.zRot = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F + (float) Math.PI) * 1.4F * animationSpeed;
        this.right_wing.yRot = -flapAngle * 0.75F;
        this.left_wing.yRot = flapAngle * 0.75F;
        this.body.zRot = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F) * 0.1F * animationSpeed;
        this.head.zRot = net.minecraft.util.math.MathHelper.cos(animationPos * 0.6662F) * 0.1F * animationSpeed;
    }
}
