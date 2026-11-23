//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.endermite;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.util.helper.MathHelper;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;

public class EndermiteModel extends PetModel {
    private static final int SEGMENTS = 8;
    private static final float SEGMENT_SPACING = 5.0F;
    private final ModelPart head;
    private final ModelPart[] segments = new ModelPart[8];
    private final ModelPart[] legs = new ModelPart[8];

    public EndermiteModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelPart(this, 0, 0);
        this.head.addBox(-3.0F, -3.0F, -6.0F, 6, 6, 6);
        this.head.setPos(0.0F, 3.0F, 0.0F);

        for(int i = 0; i < 8; ++i) {
            ModelPart segment = new ModelPart(this, 18 + i * 4, 12);
            segment.addBox(-2.0F, 0.0F, -3.0F, 4, 4, 6);
            segment.setPos(0.0F, 0.0F, i == 0 ? -5.0F : 5.0F);
            ModelPart leftLeg = new ModelPart(this, 0, 22 + i * 2);
            leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2);
            leftLeg.setPos(-2.0F, 3.0F, 0.0F);
            ModelPart rightLeg = new ModelPart(this, 0, 22 + i * 2);
            rightLeg.flipped = true;
            rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2);
            rightLeg.setPos(2.0F, 3.0F, 0.0F);
            segment.addChild(leftLeg);
            segment.addChild(rightLeg);
            this.segments[i] = segment;
            this.legs[i] = segment;
        }

    }

    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.head, this.segments[0], this.segments[1], this.segments[2], this.segments[3], this.segments[4], this.segments[5], this.segments[6], this.segments[7]);
    }

    public void setupAnimation(float f, float g, float h, float i, float j, float k, Entity entity) {
        this.head.rotationY = i * 0.017453292F;
        this.head.rotationX = j * 0.017453292F;
        float wiggle = MathHelper.cos(f * 0.6666666F) * (1.0F - k) * 0.7F;

        for(int i2 = 0; i2 < 8; ++i2) {
            float phase = (float)i2 * 0.5F;
            this.segments[i2].rotationY = MathHelper.cos(phase) * 0.15F * (1.0F - k);
            this.segments[i2].rotationX = wiggle * 0.15F * (float)i2 * 0.35F;
        }

    }

    public void render(Entity entity, float f, float g, float h, float i, float k, float m) {
        this.setupAnimation(f, g, h, i, k, m, entity);
        this.head.render(m);
        ModelPart[] var8 = this.segments;
        int var9 = var8.length;

        for(int var10 = 0; var10 < var9; ++var10) {
            ModelPart segment = var8[var10];
            segment.render(m);
        }

    }
}
