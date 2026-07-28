package com.jeff.pets.client.rendering.vanilla.guardian;

import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.GuardianModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class ClientGuardianModel<T extends AbstractPet> extends EntityModel<T> {
    private static final float[] field_17131 = new float[]{1.75F, 0.25F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, 0.5F, 1.25F, 0.75F, 0.0F, 0.0F};
    private static final float[] field_17132 = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.25F, 1.75F, 1.25F, 0.75F, 0.0F, 0.0F, 0.0F, 0.0F};
    private static final float[] field_17133 = new float[]{0.0F, 0.0F, 0.25F, 1.75F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.75F, 1.25F};
    private static final float[] field_17134 = new float[]{0.0F, 0.0F, 8.0F, -8.0F, -8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F, 8.0F, -8.0F};
    private static final float[] field_17135 = new float[]{-8.0F, -8.0F, -8.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F};
    private static final float[] field_17136 = new float[]{8.0F, -8.0F, 0.0F, 0.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F};
    private final RendererModel field_3379;
    private final RendererModel field_3381;
    private final RendererModel[] field_3380;
    private final RendererModel[] field_3378;

    public ClientGuardianModel() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.field_3380 = new RendererModel[12];
        this.field_3379 = new RendererModel(this);
        this.field_3379.texOffs(0, 0).addBox(-6.0F, 10.0F, -8.0F, 12, 12, 16);
        this.field_3379.texOffs(0, 28).addBox(-8.0F, 10.0F, -6.0F, 2, 12, 12);
        this.field_3379.texOffs(0, 28).addBox(6.0F, 10.0F, -6.0F, 2, 12, 12, true);
        this.field_3379.texOffs(16, 40).addBox(-6.0F, 8.0F, -6.0F, 12, 2, 12);
        this.field_3379.texOffs(16, 40).addBox(-6.0F, 22.0F, -6.0F, 12, 2, 12);

        for (int i = 0; i < this.field_3380.length; ++i) {
            this.field_3380[i] = new RendererModel(this, 0, 0);
            this.field_3380[i].addBox(-1.0F, -4.5F, -1.0F, 2, 9, 2);
            this.field_3379.addChild(this.field_3380[i]);
        }

        this.field_3381 = new RendererModel(this, 8, 0);
        this.field_3381.addBox(-1.0F, 15.0F, 0.0F, 2, 2, 1);
        this.field_3379.addChild(this.field_3381);
        this.field_3378 = new RendererModel[3];
        this.field_3378[0] = new RendererModel(this, 40, 0);
        this.field_3378[0].addBox(-2.0F, 14.0F, 7.0F, 4, 4, 8);
        this.field_3378[1] = new RendererModel(this, 0, 54);
        this.field_3378[1].addBox(0.0F, 14.0F, 0.0F, 3, 3, 7);
        this.field_3378[2] = new RendererModel(this);
        this.field_3378[2].texOffs(41, 32).addBox(0.0F, 14.0F, 0.0F, 2, 2, 6);
        this.field_3378[2].texOffs(25, 19).addBox(1.0F, 10.5F, 3.0F, 1, 9, 9);
        this.field_3379.addChild(this.field_3378[0]);
        this.field_3378[0].addChild(this.field_3378[1]);
        this.field_3378[1].addChild(this.field_3378[2]);
    }

    @Override
    public void render(T guardianEntity, float f, float g, float h, float i, float j, float k) {
        this.setupAnim(guardianEntity, f, g, h, i, j, k);
        this.field_3379.render(k);
    }

    @Override
    public void setupAnim(T guardianEntity, float f, float g, float h, float i, float j, float k) {
        float l = h - (float) guardianEntity.tickCount;
        this.field_3379.yRot = i * ((float) Math.PI / 180F);
        this.field_3379.xRot = j * ((float) Math.PI / 180F);
        float m = (1.0F - 2 * 0.55F);

        for (int n = 0; n < 12; ++n) {
            this.field_3380[n].xRot = (float) Math.PI * field_17131[n];
            this.field_3380[n].yRot = (float) Math.PI * field_17132[n];
            this.field_3380[n].zRot = (float) Math.PI * field_17133[n];
            this.field_3380[n].x = field_17134[n] * (1.0F + MathHelper.cos(h * 1.5F + (float) n) * 0.01F - m);
            this.field_3380[n].y = 16.0F + field_17135[n] * (1.0F + MathHelper.cos(h * 1.5F + (float) n) * 0.01F - m);
            this.field_3380[n].z = field_17136[n] * (1.0F + MathHelper.cos(h * 1.5F + (float) n) * 0.01F - m);
        }

        this.field_3381.z = -8.25F;
        Entity entity = Minecraft.getInstance().getCameraEntity();

        if (entity != null) {
            Vec3d vec3d = entity.getEyePosition(0.0F);
            Vec3d vec3d2 = guardianEntity.getEyePosition(0.0F);
            double d = vec3d.y - vec3d2.y;
            if (d > (double) 0.0F) {
                this.field_3381.y = 0.0F;
            } else {
                this.field_3381.y = 1.0F;
            }

            Vec3d vec3d3 = guardianEntity.getViewVector(0.0F);
            vec3d3 = new Vec3d(vec3d3.x, 0.0F, vec3d3.z);
            Vec3d vec3d4 = (new Vec3d(vec3d2.x - vec3d.x, 0.0F, vec3d2.z - vec3d.z)).normalize().yRot(((float) Math.PI / 2F));
            double e = vec3d3.dot(vec3d4);
            this.field_3381.x = MathHelper.sqrt((float) Math.abs(e)) * 2.0F * (float) Math.signum(e);
        }

        this.field_3381.visible = true;
        float o = 10;
        this.field_3378[0].yRot = MathHelper.sin(o) * (float) Math.PI * 0.05F;
        this.field_3378[1].yRot = MathHelper.sin(o) * (float) Math.PI * 0.1F;
        this.field_3378[1].x = -1.5F;
        this.field_3378[1].y = 0.5F;
        this.field_3378[1].z = 14.0F;
        this.field_3378[2].yRot = MathHelper.sin(o) * (float) Math.PI * 0.15F;
        this.field_3378[2].x = 0.5F;
        this.field_3378[2].y = 0.5F;
        this.field_3378[2].z = 6.0F;
    }
}
