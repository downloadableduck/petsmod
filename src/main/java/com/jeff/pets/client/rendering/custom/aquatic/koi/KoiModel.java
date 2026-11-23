//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.custom.aquatic.koi;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.util.helper.MathHelper;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;

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
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.body = new ModelPart(this);
        this.body.setPos(0.0F, 20.0F, -7.0F);
        this.body.setTextureCoords(0, 0).addBox(-2.0F, -4.0F, -2.0F, 4, 4, 14, 0.0F);
        this.top_fin = new ModelPart(this);
        this.top_fin.setPos(-4.0F, 0.0F, 8.0F);
        this.body.addChild(this.top_fin);
        this.top_fin.setTextureCoords(16, 26).addBox(4.0F, -6.0F, -6.0F, 0, 2, 4, 0.0F);
        this.left_hind_fin = new ModelPart(this);
        this.left_hind_fin.setPos(4.0F, 0.0F, 2.0F);
        this.body.addChild(this.left_hind_fin);
        this.left_hind_fin_r1 = new ModelPart(this);
        this.left_hind_fin_r1.setPos(-8.0F, 0.0F, 6.0F);
        this.left_hind_fin.addChild(this.left_hind_fin_r1);
        this.setRotationAngle(this.left_hind_fin_r1, 0.0F, 0.0F, -0.6981F);
        this.left_hind_fin_r1.setTextureCoords(24, 26).addBox(0.6F, 0.0F, 0.0F, 2, 0, 2, 0.0F);
        this.right_hind_fin = new ModelPart(this);
        this.right_hind_fin.setPos(4.0F, 0.0F, 2.0F);
        this.body.addChild(this.right_hind_fin);
        this.right_hind_fin_r1 = new ModelPart(this);
        this.right_hind_fin_r1.setPos(-2.0F, -2.0F, 6.0F);
        this.right_hind_fin.addChild(this.right_hind_fin_r1);
        this.setRotationAngle(this.right_hind_fin_r1, 0.0F, 0.0F, 0.829F);
        this.right_hind_fin_r1.setTextureCoords(24, 28).addBox(0.0F, 0.0F, 0.0F, 2, 0, 2, 0.0F);
        this.left_fin = new ModelPart(this);
        this.left_fin.setPos(0.0F, 0.0F, 0.0F);
        this.body.addChild(this.left_fin);
        this.left_fin_r1 = new ModelPart(this);
        this.left_fin_r1.setPos(-4.0F, 0.0F, 2.0F);
        this.left_fin.addChild(this.left_fin_r1);
        this.setRotationAngle(this.left_fin_r1, 0.0F, 0.0F, -0.6981F);
        this.left_fin_r1.setTextureCoords(16, 22).addBox(-1.4F, 0.0F, -2.0F, 4, 0, 4, 0.0F);
        this.right_fin = new ModelPart(this);
        this.right_fin.setPos(-4.0F, 0.0F, 2.0F);
        this.body.addChild(this.right_fin);
        this.right_fin_r1 = new ModelPart(this);
        this.right_fin_r1.setPos(8.0F, 0.0F, 0.0F);
        this.right_fin.addChild(this.right_fin_r1);
        this.setRotationAngle(this.right_fin_r1, 0.0F, 0.0F, 0.6109F);
        this.right_fin_r1.setTextureCoords(16, 18).addBox(-2.6F, 0.0F, -2.0F, 4, 0, 4, 0.0F);
        this.tail_fin = new ModelPart(this);
        this.tail_fin.setPos(0.0F, 0.0F, 14.0F);
        this.body.addChild(this.tail_fin);
        this.tail_fin.setTextureCoords(0, 18).addBox(0.0F, -8.0F, -2.0F, 0, 12, 8, 0.0F);
    }

    public void render(Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        super.render(entity, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.rotationX = x;
        ModelPart.rotationY = y;
        ModelPart.rotationZ = z;
    }

    public void setupAnimation(float f, float g, float ageInTicks, float m, float k, float s, Entity entity) {
        this.body.rotationY = -0.25F * MathHelper.sin(0.6F * ageInTicks);
        this.tail_fin.rotationY = -this.body.rotationY * 1.75F;
    }
}
