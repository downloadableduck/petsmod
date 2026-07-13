package com.jeff.pets.client.rendering.vanilla.guardian;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ClientGuardianModel<T extends AbstractPet> extends SegmentedModel<T> {
    private static final float[] SPIKE_X_ROT = new float[]{1.75F, 0.25F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, 0.5F, 1.25F, 0.75F, 0.0F, 0.0F};
    private static final float[] SPIKE_Y_ROT = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.25F, 1.75F, 1.25F, 0.75F, 0.0F, 0.0F, 0.0F, 0.0F};
    private static final float[] SPIKE_Z_ROT = new float[]{0.0F, 0.0F, 0.25F, 1.75F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.75F, 1.25F};
    private static final float[] SPIKE_X = new float[]{0.0F, 0.0F, 8.0F, -8.0F, -8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F, 8.0F, -8.0F};
    private static final float[] SPIKE_Y = new float[]{-8.0F, -8.0F, -8.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F};
    private static final float[] SPIKE_Z = new float[]{8.0F, -8.0F, 0.0F, 0.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F};
    private final ModelRenderer head;
    private final ModelRenderer eye;
    private final ModelRenderer[] spikeParts;
    private final ModelRenderer[] tailParts;

    public ClientGuardianModel() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.spikeParts = new ModelRenderer[12];
        this.head = new ModelRenderer(this);
        this.head.texOffs(0, 0).addBox(-6.0F, 10.0F, -8.0F, 12.0F, 12.0F, 16.0F);
        this.head.texOffs(0, 28).addBox(-8.0F, 10.0F, -6.0F, 2.0F, 12.0F, 12.0F);
        this.head.texOffs(0, 28).addBox(6.0F, 10.0F, -6.0F, 2.0F, 12.0F, 12.0F, true);
        this.head.texOffs(16, 40).addBox(-6.0F, 8.0F, -6.0F, 12.0F, 2.0F, 12.0F);
        this.head.texOffs(16, 40).addBox(-6.0F, 22.0F, -6.0F, 12.0F, 2.0F, 12.0F);

        for (int i = 0; i < this.spikeParts.length; ++i) {
            this.spikeParts[i] = new ModelRenderer(this, 0, 0);
            this.spikeParts[i].addBox(-1.0F, -4.5F, -1.0F, 2.0F, 9.0F, 2.0F);
            this.head.addChild(this.spikeParts[i]);
        }

        this.eye = new ModelRenderer(this, 8, 0);
        this.eye.addBox(-1.0F, 15.0F, 0.0F, 2.0F, 2.0F, 1.0F);
        this.head.addChild(this.eye);
        this.tailParts = new ModelRenderer[3];
        this.tailParts[0] = new ModelRenderer(this, 40, 0);
        this.tailParts[0].addBox(-2.0F, 14.0F, 7.0F, 4.0F, 4.0F, 8.0F);
        this.tailParts[1] = new ModelRenderer(this, 0, 54);
        this.tailParts[1].addBox(0.0F, 14.0F, 0.0F, 3.0F, 3.0F, 7.0F);
        this.tailParts[2] = new ModelRenderer(this);
        this.tailParts[2].texOffs(41, 32).addBox(0.0F, 14.0F, 0.0F, 2.0F, 2.0F, 6.0F);
        this.tailParts[2].texOffs(25, 19).addBox(1.0F, 10.5F, 3.0F, 1.0F, 9.0F, 9.0F);
        this.head.addChild(this.tailParts[0]);
        this.tailParts[0].addChild(this.tailParts[1]);
        this.tailParts[1].addChild(this.tailParts[2]);
        this.setupSpikes(0.0F, 0.0F);
    }

    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.head);
    }

    public void setupAnim(T guardian, float f, float g, float h, float i, float j) {
        float k = h - (float) guardian.tickCount;
        this.head.yRot = i * ((float) Math.PI / 180F);
        this.head.xRot = j * ((float) Math.PI / 180F);
        float l = 0.55F;
        this.setupSpikes(h, l);
        this.eye.z = -8.25F;
        Entity entity = Minecraft.getInstance().getCameraEntity();

        if (entity != null) {
            net.minecraft.util.math.vector.Vector3d vec3 = entity.getEyePosition(0.0F);
            net.minecraft.util.math.vector.Vector3d vec32 = guardian.getEyePosition(0.0F);
            double d = vec3.y - vec32.y;
            if (d > (double) 0.0F) {
                this.eye.y = 0.0F;
            } else {
                this.eye.y = 1.0F;
            }

            net.minecraft.util.math.vector.Vector3d vec33 = guardian.getViewVector(0.0F);
            vec33 = new net.minecraft.util.math.vector.Vector3d(vec33.x, 0.0F, vec33.z);
            net.minecraft.util.math.vector.Vector3d vec34 = (new net.minecraft.util.math.vector.Vector3d(vec32.x - vec3.x, 0.0F, vec32.z - vec3.z)).normalize().yRot(((float) Math.PI / 2F));
            double e = vec33.dot(vec34);
            this.eye.x = net.minecraft.util.math.MathHelper.sqrt((float) Math.abs(e)) * 2.0F * (float) Math.signum(e);
        }

        this.eye.visible = true;
        float m = 0;
        this.tailParts[0].yRot = net.minecraft.util.math.MathHelper.sin(m) * (float) Math.PI * 0.05F;
        this.tailParts[1].yRot = net.minecraft.util.math.MathHelper.sin(m) * (float) Math.PI * 0.1F;
        this.tailParts[1].x = -1.5F;
        this.tailParts[1].y = 0.5F;
        this.tailParts[1].z = 14.0F;
        this.tailParts[2].yRot = net.minecraft.util.math.MathHelper.sin(m) * (float) Math.PI * 0.15F;
        this.tailParts[2].x = 0.5F;
        this.tailParts[2].y = 0.5F;
        this.tailParts[2].z = 6.0F;
    }

    private void setupSpikes(float f, float g) {
        for (int i = 0; i < 12; ++i) {
            this.spikeParts[i].xRot = (float) Math.PI * SPIKE_X_ROT[i];
            this.spikeParts[i].yRot = (float) Math.PI * SPIKE_Y_ROT[i];
            this.spikeParts[i].zRot = (float) Math.PI * SPIKE_Z_ROT[i];
            this.spikeParts[i].x = SPIKE_X[i] * (1.0F + net.minecraft.util.math.MathHelper.cos(f * 1.5F + (float) i) * 0.01F - g);
            this.spikeParts[i].y = 16.0F + SPIKE_Y[i] * (1.0F + net.minecraft.util.math.MathHelper.cos(f * 1.5F + (float) i) * 0.01F - g);
            this.spikeParts[i].z = SPIKE_Z[i] * (1.0F + net.minecraft.util.math.MathHelper.cos(f * 1.5F + (float) i) * 0.01F - g);
        }

    }
}
