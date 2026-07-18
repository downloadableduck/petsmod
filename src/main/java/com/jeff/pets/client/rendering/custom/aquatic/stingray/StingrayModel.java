package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import net.minecraft.client.model.Cuboid;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

public class StingrayModel extends PetModel<@NotNull Stingray> {
    private final Cuboid root;
    private final Cuboid body;
    private final Cuboid tail;
    private final Cuboid right_fin;
    private final Cuboid left_fin;

    public StingrayModel() {
        textureWidth = 64;
        textureHeight = 64;

        root = new Cuboid(this);
        root.setRotationPoint(0.0F, 24.0F, 0.0F);


        body = new Cuboid(this);
        body.setRotationPoint(0.0F, -2.0F, 0.0F);
        root.addChild(body);
        body.setTextureOffset(0, 0).addBox(-6.0F, -2.0F, -6.0F, 12, 2, 12, 0.0F, false);

        tail = new Cuboid(this);
        tail.setRotationPoint(0.0F, -2.0F, 0.0F);
        root.addChild(tail);
        tail.setTextureOffset(0, 14).addBox(-1.0F, -2.0F, 6.0F, 2, 2, 10, 0.0F, false);

        right_fin = new Cuboid(this);
        right_fin.setRotationPoint(6.0F, -4.0F, -1.0F);
        root.addChild(right_fin);
        right_fin.setTextureOffset(24, 14).addBox(0.0F, 0.0F, -3.0F, 4, 2, 6, 0.0F, false);

        left_fin = new Cuboid(this);
        left_fin.setRotationPoint(-6.0F, -4.0F, -1.0F);
        root.addChild(left_fin);
        left_fin.setTextureOffset(24, 22).addBox(-4.0F, 0.0F, -3.0F, 4, 2, 6, 0.0F, false);
    }

    @Override
    public void render(Stingray stingray, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        root.render(alpha);
    }

    public void setRotationAngle(Cuboid Cuboid, float x, float y, float z) {
        Cuboid.pitch = x;
        Cuboid.yaw = y;
        Cuboid.roll = z;
    }

    @Override
    public void setAngles(Stingray state, float f, float g, float m, float k, float p, float s) {
        float partialTick = m;
        float flapTime = MathHelper.lerp(partialTick, state.oFlap, state.flap);
        if (state.limbDistance > 0) {
            float anim = flapTime * 7.448451F * ((float) Math.PI / 180F);
            this.left_fin.roll = MathHelper.cos(anim) * 16.0F * ((float) Math.PI / 180F);
            this.right_fin.roll = -this.left_fin.roll;
            this.tail.yaw = this.left_fin.roll;
        }
    }
}
