package com.jeff.pets.client.rendering.vanilla.guardian;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class ClientGuardianModel extends EntityModel {
    private static final float[] field_17131 = new float[]{1.75F, 0.25F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, 0.5F, 1.25F, 0.75F, 0.0F, 0.0F};
    private static final float[] field_17132 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.25F, 1.75F, 1.25F, 0.75F, 0.0F, 0.0F, 0.0F, 0.0F};
    private static final float[] field_17133 = new float[]{0.0F, 0.0F, 0.25F, 1.75F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.75F, 1.25F};
    private static final float[] field_17134 = new float[]{0.0F, 0.0F, 8.0F, -8.0F, -8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F, 8.0F, -8.0F};
    private static final float[] field_17135 = new float[]{-8.0F, -8.0F, -8.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F};
    private static final float[] field_17136 = new float[]{8.0F, -8.0F, 0.0F, 0.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F};
    private final ModelPart field_3379;
    private final ModelPart field_3381;
    private final ModelPart[] field_3380;
    private final ModelPart[] field_3378;

    public ClientGuardianModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.field_3380 = new ModelPart[12];
        this.field_3379 = new ModelPart(this);
        this.field_3379.setTextureOffset(0, 0).addCuboid(-6.0F, 10.0F, -8.0F, 12, 12, 16);
        this.field_3379.setTextureOffset(0, 28).addCuboid(-8.0F, 10.0F, -6.0F, 2, 12, 12);
        this.field_3379.setTextureOffset(0, 28).addCuboid(6.0F, 10.0F, -6.0F, 2, 12, 12, true);
        this.field_3379.setTextureOffset(16, 40).addCuboid(-6.0F, 8.0F, -6.0F, 12, 2, 12);
        this.field_3379.setTextureOffset(16, 40).addCuboid(-6.0F, 22.0F, -6.0F, 12, 2, 12);

        for (int i = 0; i < this.field_3380.length; ++i) {
            this.field_3380[i] = new ModelPart(this, 0, 0);
            this.field_3380[i].addCuboid(-1.0F, -4.5F, -1.0F, 2, 9, 2);
            this.field_3379.add(this.field_3380[i]);
        }

        this.field_3381 = new ModelPart(this, 8, 0);
        this.field_3381.addCuboid(-1.0F, 15.0F, 0.0F, 2, 2, 1);
        this.field_3379.add(this.field_3381);
        this.field_3378 = new ModelPart[3];
        this.field_3378[0] = new ModelPart(this, 40, 0);
        this.field_3378[0].addCuboid(-2.0F, 14.0F, 7.0F, 4, 4, 8);
        this.field_3378[1] = new ModelPart(this, 0, 54);
        this.field_3378[1].addCuboid(0.0F, 14.0F, 0.0F, 3, 3, 7);
        this.field_3378[2] = new ModelPart(this);
        this.field_3378[2].setTextureOffset(41, 32).addCuboid(0.0F, 14.0F, 0.0F, 2, 2, 6);
        this.field_3378[2].setTextureOffset(25, 19).addCuboid(1.0F, 10.5F, 3.0F, 1, 9, 9);
        this.field_3379.add(this.field_3378[0]);
        this.field_3378[0].add(this.field_3378[1]);
        this.field_3378[1].add(this.field_3378[2]);
    }

    @Override
    public void render(Entity guardianEntity, float f, float g, float h, float i, float j, float k) {
        this.setAngles(f, g, h, i, j, k, guardianEntity);
        this.field_3379.render(k);
    }

    @Override
    public void setAngles(float f, float g, float h, float i, float j, float k, Entity entity2) {
        float l = h - (float) entity2.ticksAlive;
        this.field_3379.posY = i * ((float) Math.PI / 180F);
        this.field_3379.posX = j * ((float) Math.PI / 180F);
        float m = (1.0F - 2 * 0.55F);

        for (int n = 0; n < 12; ++n) {
            this.field_3380[n].posX = (float) Math.PI * field_17131[n];
            this.field_3380[n].posY = (float) Math.PI * field_17132[n];
            this.field_3380[n].posZ = (float) Math.PI * field_17133[n];
            this.field_3380[n].pivotX = field_17134[n] * (1.0F + MathHelper.cos(h * 1.5F + (float) n) * 0.01F - m);
            this.field_3380[n].pivotY = 16.0F + field_17135[n] * (1.0F + MathHelper.cos(h * 1.5F + (float) n) * 0.01F - m);
            this.field_3380[n].pivotZ = field_17136[n] * (1.0F + MathHelper.cos(h * 1.5F + (float) n) * 0.01F - m);
        }

        this.field_3381.pivotZ = -8.25F;
        Entity entity = MinecraftClient.getInstance().targetedEntity;

        if (entity != null) {
            Vec3d vec3d = entity.getCameraPosVec(0.0F);
            Vec3d vec3d2 = entity2.getCameraPosVec(0.0F);
            double d = vec3d.y - vec3d2.y;
            if (d > (double) 0.0F) {
                this.field_3381.pivotY = 0.0F;
            } else {
                this.field_3381.pivotY = 1.0F;
            }

            Vec3d vec3d3 = entity2.getRotationVector(0.0F);
            vec3d3 = new Vec3d(vec3d3.x, 0.0F, vec3d3.z);
            Vec3d vec3d4 = (new Vec3d(vec3d2.x - vec3d.x, 0.0F, vec3d2.z - vec3d.z)).normalize().rotateY(((float) Math.PI / 2F));
            double e = vec3d3.dotProduct(vec3d4);
            this.field_3381.pivotX = MathHelper.sqrt((float) Math.abs(e)) * 2.0F * (float) Math.signum(e);
        }

        this.field_3381.visible = true;
        float o = 10;
        this.field_3378[0].posY = MathHelper.sin(o) * (float) Math.PI * 0.05F;
        this.field_3378[1].posY = MathHelper.sin(o) * (float) Math.PI * 0.1F;
        this.field_3378[1].pivotX = -1.5F;
        this.field_3378[1].pivotY = 0.5F;
        this.field_3378[1].pivotZ = 14.0F;
        this.field_3378[2].posY = MathHelper.sin(o) * (float) Math.PI * 0.15F;
        this.field_3378[2].pivotX = 0.5F;
        this.field_3378[2].pivotY = 0.5F;
        this.field_3378[2].pivotZ = 6.0F;
    }
}
