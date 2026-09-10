package com.jeff.pets.client.rendering.custom.first.racoon;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.first.Racoon;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class RacoonModel extends PetModel {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart left_hind_leg;
    private final ModelPart right_hind_leg;
    private final ModelPart left_front_leg;
    private final ModelPart right_front_leg;
    private final ModelPart tail;

    public RacoonModel() {
        textureWidth = 64;
        textureHeight = 64;

        root = new ModelPart(this);
        root.setPivot(-1.0F, 16.5F, -3.0F);


        head = new ModelPart(this);
        head.setPivot(0.0F, 0.0F, 0.0F);
        root.add(head);
        head.setTextureOffset(0, 15).method_18947(-2.5F, -7.0F, -2.0F, (int) 7.0F, (int) 5.0F, (int) 5.0F, 0.0F, false);
        head.setTextureOffset(28, 28).method_18947(1.5F, -9.0F, 1.0F, (int) 2.0F, (int) 2.0F, (int) 1.0F, 0.0F, false);
        head.setTextureOffset(0, 30).method_18947(-1.5F, -9.0F, 1.0F, (int) 2.0F, (int) 2.0F, (int) 1.0F, 0.0F, false);
        head.setTextureOffset(1, 26).method_18947(-0.5F, -4.0F, -4.0F, (int) 3.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);
        head.setTextureOffset(24, 12).method_18947(-3.5F, -5.0F, -2.0F, (int) 1.0F, (int) 3.0F, (int) 5.0F, 0.0F, false);
        head.setTextureOffset(24, 20).method_18947(4.5F, -5.0F, -2.0F, (int) 1.0F, (int) 3.0F, (int) 5.0F, 0.0F, false);

        body = new ModelPart(this);
        body.setPivot(1.0F, -0.5F, -3.0F);
        head.add(body);
        setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
        body.setTextureOffset(0, 0).method_18947(-3.0F, 4.0F, -3.5F, (int) 6.0F, (int) 9.0F, (int) 6.0F, 0.0F, false);

        left_hind_leg = new ModelPart(this);
        left_hind_leg.setPivot(-4.0F, 1.0F, 10.0F);
        root.add(left_hind_leg);
        left_hind_leg.setTextureOffset(12, 25).method_18947(2.5F, 2.0F, -2.0F, (int) 2.0F, (int) 4.0F, (int) 2.0F, 0.0F, false);

        right_hind_leg = new ModelPart(this);
        right_hind_leg.setPivot(0.0F, 1.0F, 10.0F);
        root.add(right_hind_leg);
        right_hind_leg.setTextureOffset(20, 28).method_18947(1.5F, 2.0F, -2.0F, (int) 2.0F, (int) 4.0F, (int) 2.0F, 0.0F, false);

        left_front_leg = new ModelPart(this);
        left_front_leg.setPivot(-4.0F, 1.0F, 3.0F);
        root.add(left_front_leg);
        left_front_leg.setTextureOffset(12, 25).method_18947(2.5F, 2.0F, -1.0F, (int) 2.0F, (int) 4.0F, (int) 2.0F, 0.0F, false);

        right_front_leg = new ModelPart(this);
        right_front_leg.setPivot(0.0F, 1.0F, 3.0F);
        root.add(right_front_leg);
        right_front_leg.setTextureOffset(20, 28).method_18947(1.5F, 2.0F, -1.0F, (int) 2.0F, (int) 4.0F, (int) 2.0F, 0.0F, false);

        tail = new ModelPart(this);
        tail.setPivot(-3.0F, 0.5F, 12.0F);
        root.add(tail);
        setRotationAngle(tail, 1.5708F, 0.0F, 0.0F);
        tail.setTextureOffset(24, 0).method_18947(2.0F, -2.0F, -1.0F, (int) 4.0F, (int) 8.0F, (int) 4.0F, 0.0F, false);
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
    public void animateModel(LivingEntity entity, float f, float g, float h) {
        Racoon fox = (Racoon) entity;
        //this.body.posX = ((float) Math2.PI / 2F);
        this.tail.posX = -0.05235988F;
        this.right_hind_leg.posX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.left_hind_leg.posX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.right_front_leg.posX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.left_front_leg.posX = net.minecraft.util.math.MathHelper.cos(f * 0.6662F) * 1.4F * g;
        //this.head.setPivot(-1.0F, 16.5F, -3.0F);
        //this.head.posY = 0.0F;
        //this.head.posZ = 0;
        this.right_hind_leg.visible = true;
        this.left_hind_leg.visible = true;
        this.right_front_leg.visible = true;
        this.left_front_leg.visible = true;
        //this.body.setPivot(0.0F, 16.0F, -6.0F);
        //this.body.posZ = 0.0F;
        this.right_hind_leg.setPivot(-5.0F, 17.5F, 7.0F);
        this.left_hind_leg.setPivot(-1.0F, 17.5F, 7.0F);
        this.tail.posX = 2f;

        if (fox.isPassenger()) {
            //this.body.posX = 1.35f;
            //this.tail.z = 7;
        }
    }

    @Override
    public void setAngles(float f, float g, float h, float i, float j, float p, Entity entity) {
        Racoon fox = (Racoon) entity;
        this.head.posX = j * ((float) Math.PI / 180F);
        this.head.posY = i * ((float) Math.PI / 180F);
    }
}
