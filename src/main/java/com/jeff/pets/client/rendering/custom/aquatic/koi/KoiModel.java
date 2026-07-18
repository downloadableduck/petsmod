package com.jeff.pets.client.rendering.custom.aquatic.koi;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.Koi;
import net.minecraft.client.model.Cuboid;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

public class KoiModel extends PetModel<@NotNull Koi> {
    private final Cuboid body;
    private final Cuboid top_fin;
    private final Cuboid left_hind_fin;
    private final Cuboid left_hind_fin_r1;
    private final Cuboid right_hind_fin;
    private final Cuboid right_hind_fin_r1;
    private final Cuboid left_fin;
    private final Cuboid left_fin_r1;
    private final Cuboid right_fin;
    private final Cuboid right_fin_r1;
    private final Cuboid tail_fin;

    public KoiModel() {
        textureWidth = 64;
        textureHeight = 64;

        body = new Cuboid(this);
        body.setRotationPoint(0.0F, 20.0F, -7.0F);
        body.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -2.0F, 4, 4, 14, 0.0F, false);

        top_fin = new Cuboid(this);
        top_fin.setRotationPoint(-4.0F, 0.0F, 8.0F);
        body.addChild(top_fin);
        top_fin.setTextureOffset(16, 26).addBox(4.0F, -6.0F, -6.0F, 0, 2, 4, 0.0F, false);

        left_hind_fin = new Cuboid(this);
        left_hind_fin.setRotationPoint(4.0F, 0.0F, 2.0F);
        body.addChild(left_hind_fin);


        left_hind_fin_r1 = new Cuboid(this);
        left_hind_fin_r1.setRotationPoint(-8.0F, 0.0F, 6.0F);
        left_hind_fin.addChild(left_hind_fin_r1);
        setRotationAngle(left_hind_fin_r1, 0.0F, 0.0F, -0.6981F);
        left_hind_fin_r1.setTextureOffset(24, 26).addBox(0.6F, 0.0F, 0.0F, 2, 0, 2, 0.0F, false);

        right_hind_fin = new Cuboid(this);
        right_hind_fin.setRotationPoint(4.0F, 0.0F, 2.0F);
        body.addChild(right_hind_fin);


        right_hind_fin_r1 = new Cuboid(this);
        right_hind_fin_r1.setRotationPoint(-2.0F, -2.0F, 6.0F);
        right_hind_fin.addChild(right_hind_fin_r1);
        setRotationAngle(right_hind_fin_r1, 0.0F, 0.0F, 0.829F);
        right_hind_fin_r1.setTextureOffset(24, 28).addBox(0.0F, 0.0F, 0.0F, 2, 0, 2, 0.0F, false);

        left_fin = new Cuboid(this);
        left_fin.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(left_fin);


        left_fin_r1 = new Cuboid(this);
        left_fin_r1.setRotationPoint(-4.0F, 0.0F, 2.0F);
        left_fin.addChild(left_fin_r1);
        setRotationAngle(left_fin_r1, 0.0F, 0.0F, -0.6981F);
        left_fin_r1.setTextureOffset(16, 22).addBox(-1.4F, 0.0F, -2.0F, 4, 0, 4, 0.0F, false);

        right_fin = new Cuboid(this);
        right_fin.setRotationPoint(-4.0F, 0.0F, 2.0F);
        body.addChild(right_fin);


        right_fin_r1 = new Cuboid(this);
        right_fin_r1.setRotationPoint(8.0F, 0.0F, 0.0F);
        right_fin.addChild(right_fin_r1);
        setRotationAngle(right_fin_r1, 0.0F, 0.0F, 0.6109F);
        right_fin_r1.setTextureOffset(16, 18).addBox(-2.6F, 0.0F, -2.0F, 4, 0, 4, 0.0F, false);

        tail_fin = new Cuboid(this);
        tail_fin.setRotationPoint(0.0F, 0.0F, 14.0F);
        body.addChild(tail_fin);
        tail_fin.setTextureOffset(0, 18).addBox(0.0F, -8.0F, -2.0F, 0, 12, 8, 0.0F, false);
    }

    @Override
    public void render(Koi koi, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        super.render(koi, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(alpha);
    }

    public void setRotationAngle(Cuboid Cuboid, float x, float y, float z) {
        Cuboid.pitch = x;
        Cuboid.yaw = y;
        Cuboid.roll = z;
    }

    @Override
    public void setAngles(Koi state, float f, float g, float ageInTicks, float m, float k, float s) {
        this.body.yaw = -1.0f * 0.25F * MathHelper.sin(1.0f * 0.6F * ageInTicks);
        this.tail_fin.yaw = -this.body.yaw * 1.75f;
    }
}
