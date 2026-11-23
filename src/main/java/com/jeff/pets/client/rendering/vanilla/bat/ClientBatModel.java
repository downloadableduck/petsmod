//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.bat;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.util.helper.MathHelper;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;
import com.jeff.pets.mob.vanilla.passive.ClientBat;

public class ClientBatModel extends PetModel {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart rightWingTip;
    private final ModelPart leftWingTip;

    public ClientBatModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelPart(this, 0, 0);
        this.head.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
        ModelPart ModelPart = new ModelPart(this, 24, 0);
        ModelPart.addBox(-4.0F, -6.0F, -2.0F, 3, 4, 1);
        this.head.addChild(ModelPart);
        ModelPart ModelPart2 = new ModelPart(this, 24, 0);
        ModelPart2.flipped = true;
        ModelPart2.addBox(1.0F, -6.0F, -2.0F, 3, 4, 1);
        this.head.addChild(ModelPart2);
        this.body = new ModelPart(this, 0, 16);
        this.body.addBox(-3.0F, 4.0F, -3.0F, 6, 12, 6);
        this.body.setTextureCoords(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10, 6, 1);
        this.rightWing = new ModelPart(this, 42, 0);
        this.rightWing.addBox(-12.0F, 1.0F, 1.5F, 10, 16, 1);
        this.rightWingTip = new ModelPart(this, 24, 16);
        this.rightWingTip.setPos(-12.0F, 1.0F, 1.5F);
        this.rightWingTip.addBox(-8.0F, 1.0F, 0.0F, 8, 12, 1);
        this.leftWing = new ModelPart(this, 42, 0);
        this.leftWing.flipped = true;
        this.leftWing.addBox(2.0F, 1.0F, 1.5F, 10, 16, 1);
        this.leftWingTip = new ModelPart(this, 24, 16);
        this.leftWingTip.flipped = true;
        this.leftWingTip.setPos(12.0F, 1.0F, 1.5F);
        this.leftWingTip.addBox(0.0F, 1.0F, 0.0F, 8, 12, 1);
        this.body.addChild(this.rightWing);
        this.body.addChild(this.leftWing);
        this.rightWing.addChild(this.rightWingTip);
        this.leftWing.addChild(this.leftWingTip);
    }

    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.head, this.body);
    }

    public void setupAnimation(float f, float g, float h, float i, float j, float k, Entity entity) {
        ClientBat bat = (ClientBat)entity;
        if (bat.isPassenger()) {
            this.head.rotationX = j * 0.017453292F;
            this.head.rotationY = 3.1415927F - i * 0.017453292F;
            this.head.rotationZ = 3.1415927F;
            this.head.setPos(0.0F, -2.0F, 0.0F);
            this.rightWing.setPos(-3.0F, 0.0F, 3.0F);
            this.leftWing.setPos(3.0F, 0.0F, 3.0F);
            this.body.rotationX = 3.1415927F;
            this.rightWing.rotationX = -0.15707964F;
            this.rightWing.rotationY = -1.2566371F;
            this.rightWingTip.rotationY = -1.7278761F;
            this.leftWing.rotationX = this.rightWing.rotationX;
            this.leftWing.rotationY = -this.rightWing.rotationY;
            this.leftWingTip.rotationY = -this.rightWingTip.rotationY;
        } else {
            this.head.rotationX = j * 0.017453292F;
            this.head.rotationY = i * 0.017453292F;
            this.head.rotationZ = 0.0F;
            this.head.setPos(0.0F, 0.0F, 0.0F);
            this.rightWing.setPos(0.0F, 0.0F, 0.0F);
            this.leftWing.setPos(0.0F, 0.0F, 0.0F);
            this.body.rotationX = 0.7853982F + MathHelper.cos(h * 0.1F) * 0.15F;
            this.body.rotationY = 0.0F;
            this.rightWing.rotationY = MathHelper.cos(h * 1.3F) * 3.1415927F * 0.25F;
            this.leftWing.rotationY = -this.rightWing.rotationY;
            this.rightWingTip.rotationY = this.rightWing.rotationY * 0.5F;
            this.leftWingTip.rotationY = -this.rightWing.rotationY * 0.5F;
        }

    }

    public void render(Entity entity, float f, float g, float h, float i, float k, float m) {
        this.setupAnimation(f, g, h, i, k, m, entity);
        this.head.render(m);
        this.body.render(m);
    }
}
