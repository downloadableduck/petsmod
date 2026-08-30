package com.jeff.pets.client.rendering.custom.aquatic.koi;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.Koi;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

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
        textureWidth /*textureWidth*/ = 64;
        textureHeight /*textureHeight*/ = 64;

        body = new ModelPart(this);
        body.setPos(0.0F, 20.0F, -7.0F);
        body.setTextureCoords(0, 0).addBox(-2.0F, -4.0F, -2.0F, 4, 4, 14, 0.0F, false);

        top_fin = new ModelPart(this);
        top_fin.setPos(-4.0F, 0.0F, 8.0F);
        body.addChild(top_fin);
        top_fin.setTextureCoords(16, 26).addBox(4.0F, -6.0F, -6.0F, 0, 2, 4, 0.0F, false);

        left_hind_fin = new ModelPart(this);
        left_hind_fin.setPos(4.0F, 0.0F, 2.0F);
        body.addChild(left_hind_fin);


        left_hind_fin_r1 = new ModelPart(this);
        left_hind_fin_r1.setPos(-8.0F, 0.0F, 6.0F);
        left_hind_fin.addChild(left_hind_fin_r1);
        setRotationAngle(left_hind_fin_r1, 0.0F, 0.0F, -0.6981F);
        left_hind_fin_r1.setTextureCoords(24, 26).addBox(0.6F, 0.0F, 0.0F, 2, 0, 2, 0.0F, false);

        right_hind_fin = new ModelPart(this);
        right_hind_fin.setPos(4.0F, 0.0F, 2.0F);
        body.addChild(right_hind_fin);


        right_hind_fin_r1 = new ModelPart(this);
        right_hind_fin_r1.setPos(-2.0F, -2.0F, 6.0F);
        right_hind_fin.addChild(right_hind_fin_r1);
        setRotationAngle(right_hind_fin_r1, 0.0F, 0.0F, 0.829F);
        right_hind_fin_r1.setTextureCoords(24, 28).addBox(0.0F, 0.0F, 0.0F, 2, 0, 2, 0.0F, false);

        left_fin = new ModelPart(this);
        left_fin.setPos(0.0F, 0.0F, 0.0F);
        body.addChild(left_fin);


        left_fin_r1 = new ModelPart(this);
        left_fin_r1.setPos(-4.0F, 0.0F, 2.0F);
        left_fin.addChild(left_fin_r1);
        setRotationAngle(left_fin_r1, 0.0F, 0.0F, -0.6981F);
        left_fin_r1.setTextureCoords(16, 22).addBox(-1.4F, 0.0F, -2.0F, 4, 0, 4, 0.0F, false);

        right_fin = new ModelPart(this);
        right_fin.setPos(-4.0F, 0.0F, 2.0F);
        body.addChild(right_fin);


        right_fin_r1 = new ModelPart(this);
        right_fin_r1.setPos(8.0F, 0.0F, 0.0F);
        right_fin.addChild(right_fin_r1);
        setRotationAngle(right_fin_r1, 0.0F, 0.0F, 0.6109F);
        right_fin_r1.setTextureCoords(16, 18).addBox(-2.6F, 0.0F, -2.0F, 4, 0, 4, 0.0F, false);

        tail_fin = new ModelPart(this);
        tail_fin.setPos(0.0F, 0.0F, 14.0F);
        body.addChild(tail_fin);
        tail_fin.setTextureCoords(0, 18).addBox(0.0F, -8.0F, -2.0F, 0, 12, 8, 0.0F, false);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        super.render(entity, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.rotationX = x;
        ModelPart.rotationY = y;
        ModelPart.rotationZ = z;
    }

    @Override
    public void setupAnimation(float f, float g, float ageInTicks, float m, float k, float s, net.minecraft.entity.Entity entity) {
        this.body.rotationY = -1.0f * 0.25F * MathHelper.sin(1.0f * 0.6F * ageInTicks);
        this.tail_fin.rotationY = -this.body.rotationY * 1.75f;
    }
}
