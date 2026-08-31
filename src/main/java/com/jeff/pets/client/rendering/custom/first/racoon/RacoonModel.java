package com.jeff.pets.client.rendering.custom.first.racoon;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Racoon;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class RacoonModel extends PetModel {
    private final ModelRenderer root;
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer left_hind_leg;
    private final ModelRenderer right_hind_leg;
    private final ModelRenderer left_front_leg;
    private final ModelRenderer right_front_leg;
    private final ModelRenderer tail;

    public RacoonModel() {
        textureWidth = 64;
        textureHeight = 64;

        root = new ModelRenderer(this);
        root.setRotationPoint(-1.0F, 16.5F, -3.0F);


        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        root.addChild(head);
        head.setTextureOffset(0, 15).func_205345_a(-2.5F, -7.0F, -2.0F, (int) 7.0F, (int) 5.0F, (int) 5.0F, 0.0F, false);
        head.setTextureOffset(28, 28).func_205345_a(1.5F, -9.0F, 1.0F, (int) 2.0F, (int) 2.0F, (int) 1.0F, 0.0F, false);
        head.setTextureOffset(0, 30).func_205345_a(-1.5F, -9.0F, 1.0F, (int) 2.0F, (int) 2.0F, (int) 1.0F, 0.0F, false);
        head.setTextureOffset(1, 26).func_205345_a(-0.5F, -4.0F, -4.0F, (int) 3.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);
        head.setTextureOffset(24, 12).func_205345_a(-3.5F, -5.0F, -2.0F, (int) 1.0F, (int) 3.0F, (int) 5.0F, 0.0F, false);
        head.setTextureOffset(24, 20).func_205345_a(4.5F, -5.0F, -2.0F, (int) 1.0F, (int) 3.0F, (int) 5.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(1.0F, -0.5F, -3.0F);
        head.addChild(body);
        setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
        body.setTextureOffset(0, 0).func_205345_a(-3.0F, 4.0F, -3.5F, (int) 6.0F, (int) 9.0F, (int) 6.0F, 0.0F, false);

        left_hind_leg = new ModelRenderer(this);
        left_hind_leg.setRotationPoint(-4.0F, 1.0F, 10.0F);
        root.addChild(left_hind_leg);
        left_hind_leg.setTextureOffset(12, 25).func_205345_a(2.5F, 2.0F, -2.0F, (int) 2.0F, (int) 4.0F, (int) 2.0F, 0.0F, false);

        right_hind_leg = new ModelRenderer(this);
        right_hind_leg.setRotationPoint(0.0F, 1.0F, 10.0F);
        root.addChild(right_hind_leg);
        right_hind_leg.setTextureOffset(20, 28).func_205345_a(1.5F, 2.0F, -2.0F, (int) 2.0F, (int) 4.0F, (int) 2.0F, 0.0F, false);

        left_front_leg = new ModelRenderer(this);
        left_front_leg.setRotationPoint(-4.0F, 1.0F, 3.0F);
        root.addChild(left_front_leg);
        left_front_leg.setTextureOffset(12, 25).func_205345_a(2.5F, 2.0F, -1.0F, (int) 2.0F, (int) 4.0F, (int) 2.0F, 0.0F, false);

        right_front_leg = new ModelRenderer(this);
        right_front_leg.setRotationPoint(0.0F, 1.0F, 3.0F);
        root.addChild(right_front_leg);
        right_front_leg.setTextureOffset(20, 28).func_205345_a(1.5F, 2.0F, -1.0F, (int) 2.0F, (int) 4.0F, (int) 2.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(-3.0F, 0.5F, 12.0F);
        root.addChild(tail);
        setRotationAngle(tail, 1.5708F, 0.0F, 0.0F);
        tail.setTextureOffset(24, 0).func_205345_a(2.0F, -2.0F, -1.0F, (int) 4.0F, (int) 8.0F, (int) 4.0F, 0.0F, false);
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
    public void setLivingAnimations(EntityLivingBase entity, float f, float g, float h) {
        Racoon fox = (Racoon) entity;
        //this.body.rotateAngleX = ((float) Math2.PI / 2F);
        this.tail.rotateAngleX = -0.05235988F;
        this.right_hind_leg.rotateAngleX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.left_hind_leg.rotateAngleX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.right_front_leg.rotateAngleX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.left_front_leg.rotateAngleX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
        //this.head.setRotationPoint(-1.0F, 16.5F, -3.0F);
        //this.head.rotateAngleY = 0.0F;
        //this.head.rotateAngleZ = 0;
        this.right_hind_leg.showModel = true;
        this.left_hind_leg.showModel = true;
        this.right_front_leg.showModel = true;
        this.left_front_leg.showModel = true;
        //this.body.setRotationPoint(0.0F, 16.0F, -6.0F);
        //this.body.rotateAngleZ = 0.0F;
        this.right_hind_leg.setRotationPoint(-5.0F, 17.5F, 7.0F);
        this.left_hind_leg.setRotationPoint(-1.0F, 17.5F, 7.0F);
        this.tail.rotateAngleX = 2f;

        if (fox.isRiding()) {
            //this.body.rotateAngleX = 1.35f;
            //this.tail.z = 7;
        }
    }

    @Override
    public void setRotationAngles(float f, float g, float h, float i, float j, float p, Entity entity) {
        Racoon fox = (Racoon) entity;
        this.head.rotateAngleX = j * ((float) Math.PI / 180F);
        this.head.rotateAngleY = i * ((float) Math.PI / 180F);
    }
}
