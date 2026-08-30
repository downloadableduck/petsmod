package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.client.Math2;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

public class StingrayModel extends PetModel {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart right_fin;
    private final ModelPart left_fin;

    public StingrayModel() {
        textureWidth /*textureWidth*/ = 64;
        textureHeight /*textureHeight*/ = 64;

        root = new ModelPart(this);
        root.setPos(0.0F, 24.0F, 0.0F);


        body = new ModelPart(this);
        body.setPos(0.0F, -2.0F, 0.0F);
        root.addChild(body);
        body.setTextureCoords(0, 0).addBox(-6.0F, -2.0F, -6.0F, 12, 2, 12, 0.0F, false);

        tail = new ModelPart(this);
        tail.setPos(0.0F, -2.0F, 0.0F);
        root.addChild(tail);
        tail.setTextureCoords(0, 14).addBox(-1.0F, -2.0F, 6.0F, 2, 2, 10, 0.0F, false);

        right_fin = new ModelPart(this);
        right_fin.setPos(6.0F, -4.0F, -1.0F);
        root.addChild(right_fin);
        right_fin.setTextureCoords(24, 14).addBox(0.0F, 0.0F, -3.0F, 4, 2, 6, 0.0F, false);

        left_fin = new ModelPart(this);
        left_fin.setPos(-6.0F, -4.0F, -1.0F);
        root.addChild(left_fin);
        left_fin.setTextureCoords(24, 22).addBox(-4.0F, 0.0F, -3.0F, 4, 2, 6, 0.0F, false);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        root.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.rotationX = x;
        ModelPart.rotationY = y;
        ModelPart.rotationZ = z;
    }

    @Override
    public void setupAnimation(float f, float g, float m, float k, float p, float s, net.minecraft.entity.Entity entity) {
        Stingray state = (Stingray) entity;
        float partialTick = m;
        float flapTime = (float) Math2.lerp(partialTick, state.oFlap, state.flap);
        if (state.walkAnimationSpeed > 0) {
            float anim = flapTime * 7.448451F * ((float) Math.PI / 180F);
            this.left_fin.rotationZ = MathHelper.cos(anim) * 16.0F * ((float) Math.PI / 180F);
            this.right_fin.rotationZ = -this.left_fin.rotationZ;
            this.tail.rotationY = this.left_fin.rotationZ;
        }
    }
}