package com.jeff.pets.client.rendering.custom.aquatic.koi;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.Koi;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class KoiModel extends PetModel<Koi> {
    private final RendererModel body;
    private final RendererModel top_fin;
    private final RendererModel left_hind_fin;
    private final RendererModel left_hind_fin_r1;
    private final RendererModel right_hind_fin;
    private final RendererModel right_hind_fin_r1;
    private final RendererModel left_fin;
    private final RendererModel left_fin_r1;
    private final RendererModel right_fin;
    private final RendererModel right_fin_r1;
    private final RendererModel tail_fin;

    public KoiModel() {
        texWidth = 64;
        texHeight = 64;

        body = new RendererModel(this);
        body.setPos(0.0F, 20.0F, -7.0F);
        body.texOffs(0, 0).addBox(-2.0F, -4.0F, -2.0F, (int) 4.0F, (int) 4.0F, (int) 14.0F, 0.0F, false);

        top_fin = new RendererModel(this);
        top_fin.setPos(-4.0F, 0.0F, 8.0F);
        body.addChild(top_fin);
        top_fin.texOffs(16, 26).addBox(4.0F, -6.0F, -6.0F, (int) 0.0F, (int) 2.0F, (int) 4.0F, 0.0F, false);

        left_hind_fin = new RendererModel(this);
        left_hind_fin.setPos(4.0F, 0.0F, 2.0F);
        body.addChild(left_hind_fin);


        left_hind_fin_r1 = new RendererModel(this);
        left_hind_fin_r1.setPos(-8.0F, 0.0F, 6.0F);
        left_hind_fin.addChild(left_hind_fin_r1);
        setRotationAngle(left_hind_fin_r1, 0.0F, 0.0F, -0.6981F);
        left_hind_fin_r1.texOffs(24, 26).addBox(0.6F, 0.0F, 0.0F, (int) 2.0F, (int) 0.0F, (int) 2.0F, 0.0F, false);

        right_hind_fin = new RendererModel(this);
        right_hind_fin.setPos(4.0F, 0.0F, 2.0F);
        body.addChild(right_hind_fin);


        right_hind_fin_r1 = new RendererModel(this);
        right_hind_fin_r1.setPos(-2.0F, -2.0F, 6.0F);
        right_hind_fin.addChild(right_hind_fin_r1);
        setRotationAngle(right_hind_fin_r1, 0.0F, 0.0F, 0.829F);
        right_hind_fin_r1.texOffs(24, 28).addBox(0.0F, 0.0F, 0.0F, (int) 2.0F, (int) 0.0F, (int) 2.0F, 0.0F, false);

        left_fin = new RendererModel(this);
        left_fin.setPos(0.0F, 0.0F, 0.0F);
        body.addChild(left_fin);


        left_fin_r1 = new RendererModel(this);
        left_fin_r1.setPos(-4.0F, 0.0F, 2.0F);
        left_fin.addChild(left_fin_r1);
        setRotationAngle(left_fin_r1, 0.0F, 0.0F, -0.6981F);
        left_fin_r1.texOffs(16, 22).addBox(-1.4F, 0.0F, -2.0F, (int) 4.0F, (int) 0.0F, (int) 4.0F, 0.0F, false);

        right_fin = new RendererModel(this);
        right_fin.setPos(-4.0F, 0.0F, 2.0F);
        body.addChild(right_fin);


        right_fin_r1 = new RendererModel(this);
        right_fin_r1.setPos(8.0F, 0.0F, 0.0F);
        right_fin.addChild(right_fin_r1);
        setRotationAngle(right_fin_r1, 0.0F, 0.0F, 0.6109F);
        right_fin_r1.texOffs(16, 18).addBox(-2.6F, 0.0F, -2.0F, (int) 4.0F, (int) 0.0F, (int) 4.0F, 0.0F, false);

        tail_fin = new RendererModel(this);
        tail_fin.setPos(0.0F, 0.0F, 14.0F);
        body.addChild(tail_fin);
        tail_fin.texOffs(0, 18).addBox(0.0F, -8.0F, -2.0F, (int) 0.0F, (int) 12.0F, (int) 8.0F, 0.0F, false);
    }

    @Override
    public void render(Koi koi, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        body.render(alpha);
    }

    public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.xRot = x;
        RendererModel.yRot = y;
        RendererModel.zRot = z;
    }

    @Override
    public void setupAnim(Koi state, float f, float g, float ageInTicks, float m, float k, float u) {
        this.body.yRot = -1.0f * 0.25F * net.minecraft.util.math.MathHelper.sin(1.0f * 0.6F * ageInTicks);
        this.tail_fin.yRot = -this.body.yRot * 1.75f;
    }
}
