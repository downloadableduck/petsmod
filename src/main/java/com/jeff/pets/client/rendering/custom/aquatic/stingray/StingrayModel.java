package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class StingrayModel extends PetModel {
    private final ModelRenderer root;
    private final ModelRenderer body;
    private final ModelRenderer tail;
    private final ModelRenderer right_fin;
    private final ModelRenderer left_fin;

    public StingrayModel() {
        textureWidth = 64;
        textureHeight = 64;

        root = new ModelRenderer(this);
        root.setRotationPoint(0.0F, 24.0F, 0.0F);


        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -2.0F, 0.0F);
        root.addChild(body);
        body.setTextureOffset(0, 0).addBox(-6.0F, -2.0F, -6.0F, (int) 12.0F, (int) 2.0F, (int) 12.0F, 0.0F);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(0.0F, -2.0F, 0.0F);
        root.addChild(tail);
        tail.setTextureOffset(0, 14).addBox(-1.0F, -2.0F, 6.0F, (int) 2.0F, (int) 2.0F, (int) 10.0F, 0.0F);

        right_fin = new ModelRenderer(this);
        right_fin.setRotationPoint(6.0F, -4.0F, -1.0F);
        root.addChild(right_fin);
        right_fin.setTextureOffset(24, 14).addBox(0.0F, 0.0F, -3.0F, (int) 4.0F, (int) 2.0F, (int) 6.0F, 0.0F);

        left_fin = new ModelRenderer(this);
        left_fin.setRotationPoint(-6.0F, -4.0F, -1.0F);
        root.addChild(left_fin);
        left_fin.setTextureOffset(24, 22).addBox(-4.0F, 0.0F, -3.0F, (int) 4.0F, (int) 2.0F, (int) 6.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        root.render(alpha);
    }

    public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float g, float m, float k, float p, float o, Entity entity) {
        Stingray state = (Stingray) entity;
        float partialTick = m;
        float flapTime = (float) net.minecraft.util.math.MathHelper.clampedLerp(partialTick, state.oFlap, state.flap);
        if (state.limbSwingAmount > 0) {
            float anim = flapTime * 7.448451F * ((float) Math.PI / 180F);
            this.left_fin.rotateAngleZ = net.minecraft.util.math.MathHelper.cos(anim) * 16.0F * ((float) Math.PI / 180F);
            this.right_fin.rotateAngleZ = -this.left_fin.rotateAngleZ;
            this.tail.rotateAngleY = this.left_fin.rotateAngleZ;
        }
    }
}
