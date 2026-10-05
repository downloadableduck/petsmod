package com.jeff.pets.client.rendering.vanilla.endermite;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.util.math.MathHelper;

/**
 * Endermite does not exist in 1.7.10, so there is no vanilla model to inherit from.
 * This reproduces it from the 1.9+ shape: a head followed by a chain of eight body
 * segments, with a pair of legs on every other segment.
 */
public class EndermiteModel extends Model {
    private static final int SEGMENTS = 8;
    private static final float SEGMENT_SPACING = 5.0F;

    private final ModelPart head;
    private final ModelPart[] segments = new ModelPart[SEGMENTS];
    private final ModelPart[] legs = new ModelPart[SEGMENTS];

    public EndermiteModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.head = new ModelPart(this, 0, 0);
        this.head.addBox(-3.0F, -3.0F, -6.0F, 6, 6, 6);
        this.head.setPos(0.0F, 3.0F, 0.0F);

        for (int i = 0; i < SEGMENTS; i++) {
            ModelPart segment = new ModelPart(this, 18 + i * 4, 12);
            segment.addBox(-2.0F, 0.0F, -3.0F, 4, 4, 6);
            segment.setPos(0.0F, 0.0F, i == 0 ? -SEGMENT_SPACING : SEGMENT_SPACING);

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
        return ImmutableList.of(this.head, this.segments[0], this.segments[1], this.segments[2],
                this.segments[3], this.segments[4], this.segments[5], this.segments[6], this.segments[7]);
    }

    @Override
    public void setupAnimation(float f, float g, float h, float i, float j, float k, net.minecraft.entity.Entity entity) {
        this.head.rotationY = i * ((float) Math.PI / 180F);
        this.head.rotationX = j * ((float) Math.PI / 180F);

        float wiggle = MathHelper.cos(f * 0.6666666F) * (1.0F - k) * 0.7F;
        for (int i2 = 0; i2 < SEGMENTS; i2++) {
            float phase = (float) i2 * 0.5F;
            this.segments[i2].rotationY = MathHelper.cos(phase) * 0.15F * (1.0F - k);
            this.segments[i2].rotationX = wiggle * 0.15F * (float) i2 * 0.35F;
        }
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float f, float g, float h, float i, float k, float m) {
        this.setupAnimation(f, g, h, i, k, m, entity);
        this.head.render(m);
        for (ModelPart segment : this.segments) {
            segment.render(m);
        }
    }
}