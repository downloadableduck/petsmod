package com.jeff.pets.client.rendering.vanilla.ravager;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.vanilla.hostile.ClientRavager;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ClientRavagerModel extends EntityModel<ClientRavager> {
    private final Cuboid head;
    private final Cuboid mouth;
    private final Cuboid body;
    private final Cuboid leg0;
    private final Cuboid leg1;
    private final Cuboid leg2;
    private final Cuboid leg3;
    private final Cuboid neck;

    public ClientRavagerModel() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        int i = 16;
        float f = 0.0F;
        this.neck = new Cuboid(this);
        this.neck.setRotationPoint(0.0F, -7.0F, -1.5F);
        this.neck.setTextureOffset(68, 73).addBox(-5.0F, -1.0F, -18.0F, 10, 10, 18, 0.0F);
        this.head = new Cuboid(this);
        this.head.setRotationPoint(0.0F, 16.0F, -17.0F);
        this.head.setTextureOffset(0, 0).addBox(-8.0F, -20.0F, -14.0F, 16, 20, 16, 0.0F);
        this.head.setTextureOffset(0, 0).addBox(-2.0F, -6.0F, -18.0F, 4, 8, 4, 0.0F);
        Cuboid Cuboid = new Cuboid(this);
        Cuboid.setRotationPoint(-10.0F, -14.0F, -8.0F);
        Cuboid.setTextureOffset(74, 55).addBox(0.0F, -14.0F, -2.0F, 2, 14, 4, 0.0F);
        Cuboid.pitch = 1.0995574F;
        this.head.addChild(Cuboid);
        Cuboid Cuboid2 = new Cuboid(this);
        Cuboid2.mirror = true;
        Cuboid2.setRotationPoint(8.0F, -14.0F, -8.0F);
        Cuboid2.setTextureOffset(74, 55).addBox(0.0F, -14.0F, -2.0F, 2, 14, 4, 0.0F);
        Cuboid2.pitch = 1.0995574F;
        this.head.addChild(Cuboid2);
        this.mouth = new Cuboid(this);
        this.mouth.setRotationPoint(0.0F, -2.0F, 2.0F);
        this.mouth.setTextureOffset(0, 36).addBox(-8.0F, 0.0F, -16.0F, 16, 3, 16, 0.0F);
        this.head.addChild(this.mouth);
        this.neck.addChild(this.head);
        this.body = new Cuboid(this);
        this.body.setTextureOffset(0, 55).addBox(-7.0F, -10.0F, -7.0F, 14, 16, 20, 0.0F);
        this.body.setTextureOffset(0, 91).addBox(-6.0F, 6.0F, -7.0F, 12, 13, 18, 0.0F);
        this.body.setRotationPoint(0.0F, 1.0F, 2.0F);
        this.leg0 = new Cuboid(this, 96, 0);
        this.leg0.addBox(-4.0F, 0.0F, -4.0F, 8, 37, 8, 0.0F);
        this.leg0.setRotationPoint(-8.0F, -13.0F, 18.0F);
        this.leg1 = new Cuboid(this, 96, 0);
        this.leg1.mirror = true;
        this.leg1.addBox(-4.0F, 0.0F, -4.0F, 8, 37, 8, 0.0F);
        this.leg1.setRotationPoint(8.0F, -13.0F, 18.0F);
        this.leg2 = new Cuboid(this, 64, 0);
        this.leg2.addBox(-4.0F, 0.0F, -4.0F, 8, 37, 8, 0.0F);
        this.leg2.setRotationPoint(-8.0F, -13.0F, -5.0F);
        this.leg3 = new Cuboid(this, 64, 0);
        this.leg3.mirror = true;
        this.leg3.addBox(-4.0F, 0.0F, -4.0F, 8, 37, 8, 0.0F);
        this.leg3.setRotationPoint(8.0F, -13.0F, -5.0F);
    }

    public void setAngles(ClientRavager ravager, float f, float g, float h, float i, float j, float s) {
        super.setAngles(ravager, f, g, h, i, j, s);
        this.head.pitch = j * ((float) Math.PI / 180F);
        this.head.yaw = i * ((float) Math.PI / 180F);
        this.body.pitch = ((float) Math.PI / 2F);
        float k = 0.4F * g;
        this.leg0.pitch = MathHelper.cos(f * 0.6662F) * k;
        this.leg1.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * k;
        this.leg2.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * k;
        this.leg3.pitch = MathHelper.cos(f * 0.6662F) * k;
    }

    @Override
    public void animateModel(ClientRavager ravager, float f, float g, float h) {
        this.head.y = -0.5f;
        this.head.z = 0.5f;
        int i = 0;
        int j = 0;
        int k = 20;
        int l = 0;
        int m = 10;
        if (l > 0) {
            float n = Utils.triangleWave((float) l - h, 10.0F);
            float o = (1.0F + n) * 0.5F;
            float p = o * o * o * 12.0F;
            float q = p * MathHelper.sin(this.neck.pitch);
            this.neck.rotationPointZ = -6.5F + p;
            this.neck.rotationPointY = -7.0F - q;
            float r = MathHelper.sin(((float) l - h) / 10.0F * (float) Math.PI * 0.25F);
            this.mouth.pitch = ((float) Math.PI / 2F) * r;
            if (l > 5) {
                this.mouth.pitch = MathHelper.sin(((float) (-4 + l) - h) / 4.0F) * (float) Math.PI * 0.4F;
            } else {
                this.mouth.pitch = 0.15707964F * MathHelper.sin((float) Math.PI * ((float) l - h) / 10.0F);
            }
        } else {
            float n = -1.0F;
            float o = -1.0F * MathHelper.sin(this.neck.pitch);
            this.neck.rotationPointX = 0.0F;
            this.neck.rotationPointY = -7.0F - o;
            this.neck.rotationPointZ = 5.5F;
            boolean bl = i > 0;
            this.neck.pitch = bl ? 0.21991149F : 0.0F;
            this.mouth.pitch = (float) Math.PI * (bl ? 0.05F : 0.01F);
            if (bl) {
                double d = (double) i / (double) 40.0F;
                this.neck.rotationPointX = (float) Math.sin(d * (double) 10.0F) * 3.0F;
            } else if (j > 0) {
                float q = MathHelper.sin(((float) (20 - j) - h) / 20.0F * (float) Math.PI * 0.25F);
                this.mouth.pitch = ((float) Math.PI / 2F) * q;
            }
        }

    }

    @Override
    public void render(ClientRavager ravager, float f, float g, float h, float i, float j, float k) {
        super.render(ravager, f, g, h, i, j, k);
        this.body.render(k);
        this.head.render(k);
        this.mouth.render(k);
        this.leg0.render(k);
        this.leg1.render(k);
        this.leg2.render(k);
        this.leg3.render(k);
    }
}
