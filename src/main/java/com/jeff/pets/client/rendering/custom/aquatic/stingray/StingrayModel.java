package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;

public class StingrayModel extends PetModel {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart right_fin;
    private final ModelPart left_fin;

    public StingrayModel() {
        textureWidth = 64;
        textureHeight = 64;

        root = new ModelPart(this);
        root.setPivot(0.0F, 24.0F, 0.0F);


        body = new ModelPart(this);
        body.setPivot(0.0F, -2.0F, 0.0F);
        root.add(body);
        body.setTextureOffset(0, 0).addCuboid(-6.0F, -2.0F, -6.0F, (int) 12.0F, (int) 2.0F, (int) 12.0F, 0.0F);

        tail = new ModelPart(this);
        tail.setPivot(0.0F, -2.0F, 0.0F);
        root.add(tail);
        tail.setTextureOffset(0, 14).addCuboid(-1.0F, -2.0F, 6.0F, (int) 2.0F, (int) 2.0F, (int) 10.0F, 0.0F);

        right_fin = new ModelPart(this);
        right_fin.setPivot(6.0F, -4.0F, -1.0F);
        root.add(right_fin);
        right_fin.setTextureOffset(24, 14).addCuboid(0.0F, 0.0F, -3.0F, (int) 4.0F, (int) 2.0F, (int) 6.0F, 0.0F);

        left_fin = new ModelPart(this);
        left_fin.setPivot(-6.0F, -4.0F, -1.0F);
        root.add(left_fin);
        left_fin.setTextureOffset(24, 22).addCuboid(-4.0F, 0.0F, -3.0F, (int) 4.0F, (int) 2.0F, (int) 6.0F, 0.0F);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        root.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.posX = x;
        ModelPart.posY = y;
        ModelPart.posZ = z;
    }

    @Override
    public void setAngles(float f, float g, float m, float k, float p, float o, Entity entity) {
        Stingray state = (Stingray) entity;
        float partialTick = m;
        float flapTime = (float) net.minecraft.util.math.MathHelper.clampedLerp(partialTick, state.oFlap, state.flap);
        if (state.field_6749 > 0) {
            float anim = flapTime * 7.448451F * ((float) Math.PI / 180F);
            this.left_fin.posZ = net.minecraft.util.math.MathHelper.cos(anim) * 16.0F * ((float) Math.PI / 180F);
            this.right_fin.posZ = -this.left_fin.posZ;
            this.tail.posY = this.left_fin.posZ;
        }
    }
}
