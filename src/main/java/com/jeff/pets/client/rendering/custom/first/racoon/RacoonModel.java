package com.jeff.pets.client.rendering.custom.first.racoon;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Racoon;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class RacoonModel extends PetModel<Racoon> {
    private final RendererModel root;
    private final RendererModel head;
    private final RendererModel body;
    private final RendererModel left_hind_leg;
    private final RendererModel right_hind_leg;
    private final RendererModel left_front_leg;
    private final RendererModel right_front_leg;
    private final RendererModel tail;

    public RacoonModel() {
        texWidth = 64;
        texHeight = 64;

        root = new RendererModel(this);
        root.setPos(-1.0F, 16.5F, -3.0F);


        head = new RendererModel(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        root.addChild(head);
        head.texOffs(0, 15).addBox(-2.5F, -7.0F, -2.0F, (int) 7.0F, (int) 5.0F, (int) 5.0F, 0.0F, false);
        head.texOffs(28, 28).addBox(1.5F, -9.0F, 1.0F, (int) 2.0F, (int) 2.0F, (int) 1.0F, 0.0F, false);
        head.texOffs(0, 30).addBox(-1.5F, -9.0F, 1.0F, (int) 2.0F, (int) 2.0F, (int) 1.0F, 0.0F, false);
        head.texOffs(1, 26).addBox(-0.5F, -4.0F, -4.0F, (int) 3.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);
        head.texOffs(24, 12).addBox(-3.5F, -5.0F, -2.0F, (int) 1.0F, (int) 3.0F, (int) 5.0F, 0.0F, false);
        head.texOffs(24, 20).addBox(4.5F, -5.0F, -2.0F, (int) 1.0F, (int) 3.0F, (int) 5.0F, 0.0F, false);

        body = new RendererModel(this);
        body.setPos(1.0F, -0.5F, -3.0F);
        head.addChild(body);
        setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
        body.texOffs(0, 0).addBox(-3.0F, 4.0F, -3.5F, (int) 6.0F, (int) 9.0F, (int) 6.0F, 0.0F, false);

        left_hind_leg = new RendererModel(this);
        left_hind_leg.setPos(-4.0F, 1.0F, 10.0F);
        root.addChild(left_hind_leg);
        left_hind_leg.texOffs(12, 25).addBox(2.5F, 2.0F, -2.0F, (int) 2.0F, (int) 4.0F, (int) 2.0F, 0.0F, false);

        right_hind_leg = new RendererModel(this);
        right_hind_leg.setPos(0.0F, 1.0F, 10.0F);
        root.addChild(right_hind_leg);
        right_hind_leg.texOffs(20, 28).addBox(1.5F, 2.0F, -2.0F, (int) 2.0F, (int) 4.0F, (int) 2.0F, 0.0F, false);

        left_front_leg = new RendererModel(this);
        left_front_leg.setPos(-4.0F, 1.0F, 3.0F);
        root.addChild(left_front_leg);
        left_front_leg.texOffs(12, 25).addBox(2.5F, 2.0F, -1.0F, (int) 2.0F, (int) 4.0F, (int) 2.0F, 0.0F, false);

        right_front_leg = new RendererModel(this);
        right_front_leg.setPos(0.0F, 1.0F, 3.0F);
        root.addChild(right_front_leg);
        right_front_leg.texOffs(20, 28).addBox(1.5F, 2.0F, -1.0F, (int) 2.0F, (int) 4.0F, (int) 2.0F, 0.0F, false);

        tail = new RendererModel(this);
        tail.setPos(-3.0F, 0.5F, 12.0F);
        root.addChild(tail);
        setRotationAngle(tail, 1.5708F, 0.0F, 0.0F);
        tail.texOffs(24, 0).addBox(2.0F, -2.0F, -1.0F, (int) 4.0F, (int) 8.0F, (int) 4.0F, 0.0F, false);
    }

    @Override
    public void render(Racoon racoon, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        root.render(alpha);
    }

    public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.xRot = x;
        RendererModel.yRot = y;
        RendererModel.zRot = z;
    }

    @Override
    public void prepareMobModel(Racoon fox, float f, float g, float h) {
        //this.body.xRot = ((float) Math.PI / 2F);
        this.tail.xRot = -0.05235988F;
        this.right_hind_leg.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.left_hind_leg.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.right_front_leg.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.left_front_leg.xRot = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
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
