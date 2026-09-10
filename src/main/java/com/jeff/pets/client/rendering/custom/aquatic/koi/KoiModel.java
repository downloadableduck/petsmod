package com.jeff.pets.client.rendering.custom.aquatic.koi;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.Koi;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;

public class KoiModel extends PetModel {
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
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelPart(this);
        body.setPivot(0.0F, 20.0F, -7.0F);
        body.setTextureOffset(0, 0).method_18947(-2.0F, -4.0F, -2.0F, (int) 4.0F, (int) 4.0F, (int) 14.0F, 0.0F, false);

        top_fin = new ModelPart(this);
        top_fin.setPivot(-4.0F, 0.0F, 8.0F);
        body.add(top_fin);
        top_fin.setTextureOffset(16, 26).method_18947(4.0F, -6.0F, -6.0F, (int) 0.0F, (int) 2.0F, (int) 4.0F, 0.0F, false);

        left_hind_fin = new ModelPart(this);
        left_hind_fin.setPivot(4.0F, 0.0F, 2.0F);
        body.add(left_hind_fin);


        left_hind_fin_r1 = new ModelPart(this);
        left_hind_fin_r1.setPivot(-8.0F, 0.0F, 6.0F);
        left_hind_fin.add(left_hind_fin_r1);
        setRotationAngle(left_hind_fin_r1, 0.0F, 0.0F, -0.6981F);
        left_hind_fin_r1.setTextureOffset(24, 26).method_18947(0.6F, 0.0F, 0.0F, (int) 2.0F, (int) 0.0F, (int) 2.0F, 0.0F, false);

        right_hind_fin = new ModelPart(this);
        right_hind_fin.setPivot(4.0F, 0.0F, 2.0F);
        body.add(right_hind_fin);


        right_hind_fin_r1 = new ModelPart(this);
        right_hind_fin_r1.setPivot(-2.0F, -2.0F, 6.0F);
        right_hind_fin.add(right_hind_fin_r1);
        setRotationAngle(right_hind_fin_r1, 0.0F, 0.0F, 0.829F);
        right_hind_fin_r1.setTextureOffset(24, 28).method_18947(0.0F, 0.0F, 0.0F, (int) 2.0F, (int) 0.0F, (int) 2.0F, 0.0F, false);

        left_fin = new ModelPart(this);
        left_fin.setPivot(0.0F, 0.0F, 0.0F);
        body.add(left_fin);


        left_fin_r1 = new ModelPart(this);
        left_fin_r1.setPivot(-4.0F, 0.0F, 2.0F);
        left_fin.add(left_fin_r1);
        setRotationAngle(left_fin_r1, 0.0F, 0.0F, -0.6981F);
        left_fin_r1.setTextureOffset(16, 22).method_18947(-1.4F, 0.0F, -2.0F, (int) 4.0F, (int) 0.0F, (int) 4.0F, 0.0F, false);

        right_fin = new ModelPart(this);
        right_fin.setPivot(-4.0F, 0.0F, 2.0F);
        body.add(right_fin);


        right_fin_r1 = new ModelPart(this);
        right_fin_r1.setPivot(8.0F, 0.0F, 0.0F);
        right_fin.add(right_fin_r1);
        setRotationAngle(right_fin_r1, 0.0F, 0.0F, 0.6109F);
        right_fin_r1.setTextureOffset(16, 18).method_18947(-2.6F, 0.0F, -2.0F, (int) 4.0F, (int) 0.0F, (int) 4.0F, 0.0F, false);

        tail_fin = new ModelPart(this);
        tail_fin.setPivot(0.0F, 0.0F, 14.0F);
        body.add(tail_fin);
        tail_fin.setTextureOffset(0, 18).method_18947(0.0F, -8.0F, -2.0F, (int) 0.0F, (int) 12.0F, (int) 8.0F, 0.0F, false);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        body.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.posX = x;
        ModelPart.posY = y;
        ModelPart.posZ = z;
    }

    @Override
    public void setAngles(float f, float g, float ageInTicks, float m, float k, float u, Entity entity) {
        Koi state = (Koi) entity;
        this.body.posY = -1.0f * 0.25F * net.minecraft.util.math.MathHelper.sin(1.0f * 0.6F * ageInTicks);
        this.tail_fin.posY = -this.body.posY * 1.75f;
    }
}
