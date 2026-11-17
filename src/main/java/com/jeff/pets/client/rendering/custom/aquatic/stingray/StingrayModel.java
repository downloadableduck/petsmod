//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import com.jeff.pets.client.Math2;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;
import com.jeff.pets.mob.custom.aquatic.Stingray;

public class StingrayModel extends PetModel {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart right_fin;
    private final ModelPart left_fin;

    public StingrayModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.root = new ModelPart(this);
        this.root.setPos(0.0F, 24.0F, 0.0F);
        this.body = new ModelPart(this);
        this.body.setPos(0.0F, -2.0F, 0.0F);
        this.root.addChild(this.body);
        this.body.setTextureCoords(0, 0).addBox(-6.0F, -2.0F, -6.0F, 12, 2, 12, 0.0F);
        this.tail = new ModelPart(this);
        this.tail.setPos(0.0F, -2.0F, 0.0F);
        this.root.addChild(this.tail);
        this.tail.setTextureCoords(0, 14).addBox(-1.0F, -2.0F, 6.0F, 2, 2, 10, 0.0F);
        this.right_fin = new ModelPart(this);
        this.right_fin.setPos(6.0F, -4.0F, -1.0F);
        this.root.addChild(this.right_fin);
        this.right_fin.setTextureCoords(24, 14).addBox(0.0F, 0.0F, -3.0F, 4, 2, 6, 0.0F);
        this.left_fin = new ModelPart(this);
        this.left_fin.setPos(-6.0F, -4.0F, -1.0F);
        this.root.addChild(this.left_fin);
        this.left_fin.setTextureCoords(24, 22).addBox(-4.0F, 0.0F, -3.0F, 4, 2, 6, 0.0F);
    }

    public void render(Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        this.root.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.rotationX = x;
        ModelPart.rotationY = y;
        ModelPart.rotationZ = z;
    }

    public void setupAnimation(float f, float g, float m, float k, float p, float s, Entity entity) {
        Stingray state = (Stingray)entity;
        float partialTick = m;
        float flapTime = Math2.lerp(partialTick, state.oFlap, state.flap);
        if (state.walkAnimationSpeed > 0.0F) {
            float anim = flapTime * 7.448451F * 0.017453292F;
            this.left_fin.rotationZ = MathHelper.cos(anim) * 16.0F * 0.017453292F;
            this.right_fin.rotationZ = -this.left_fin.rotationZ;
            this.tail.rotationY = this.left_fin.rotationZ;
        }

    }
}
