package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class StingrayModel extends PetModel<Stingray> {
    private final RendererModel root;
    private final RendererModel body;
    private final RendererModel tail;
    private final RendererModel right_fin;
    private final RendererModel left_fin;

    public StingrayModel() {
        texWidth = 64;
        texHeight = 64;

        root = new RendererModel(this);
        root.setPos(0.0F, 24.0F, 0.0F);


        body = new RendererModel(this);
        body.setPos(0.0F, -2.0F, 0.0F);
        root.addChild(body);
        body.texOffs(0, 0).addBox(-6.0F, -2.0F, -6.0F, (int) 12.0F, (int) 2.0F, (int) 12.0F, 0.0F, false);

        tail = new RendererModel(this);
        tail.setPos(0.0F, -2.0F, 0.0F);
        root.addChild(tail);
        tail.texOffs(0, 14).addBox(-1.0F, -2.0F, 6.0F, (int) 2.0F, (int) 2.0F, (int) 10.0F, 0.0F, false);

        right_fin = new RendererModel(this);
        right_fin.setPos(6.0F, -4.0F, -1.0F);
        root.addChild(right_fin);
        right_fin.texOffs(24, 14).addBox(0.0F, 0.0F, -3.0F, (int) 4.0F, (int) 2.0F, (int) 6.0F, 0.0F, false);

        left_fin = new RendererModel(this);
        left_fin.setPos(-6.0F, -4.0F, -1.0F);
        root.addChild(left_fin);
        left_fin.texOffs(24, 22).addBox(-4.0F, 0.0F, -3.0F, (int) 4.0F, (int) 2.0F, (int) 6.0F, 0.0F, false);
    }

    @Override
    public void render(Stingray stingray, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        root.render(alpha);
    }

    public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.xRot = x;
        RendererModel.yRot = y;
        RendererModel.zRot = z;
    }

    @Override
    public void setupAnim(Stingray state, float f, float g, float m, float k, float p, float o) {
        float partialTick = m;
        float flapTime = net.minecraft.util.math.MathHelper.lerp(partialTick, state.oFlap, state.flap);
        if (state.animationSpeed > 0) {
            float anim = flapTime * 7.448451F * ((float) Math.PI / 180F);
            this.left_fin.zRot = net.minecraft.util.math.MathHelper.cos(anim) * 16.0F * ((float) Math.PI / 180F);
            this.right_fin.zRot = -this.left_fin.zRot;
            this.tail.yRot = this.left_fin.zRot;
        }
    }
}
