package com.jeff.pets.client.rendering.vanilla.cat;

import com.jeff.pets.mob.vanilla.passive.ClientCat;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.model.entity.OcelotModel;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.mob.passive.animal.tameable.OcelotEntity;
import net.minecraft.util.math.MathHelper;

import java.lang.reflect.Field;

//if the tail isn't animating correctly, swap lowerTail and upperTail
public class ClientCatModel extends OcelotModel {
    private final ModelPart backLeftLeg;
    private final ModelPart backRightLeg;
    private final ModelPart frontLeftLeg;
    private final ModelPart frontrightLeg;
    private final ModelPart upperTail;
    private final ModelPart lowerTail;
    private final ModelPart head;
    private final ModelPart body;
    private int state = 1;

    public ClientCatModel() {
        this.setTexturePos("head.main", 0, 0);
        this.setTexturePos("head.nose", 0, 24);
        this.setTexturePos("head.ear1", 0, 10);
        this.setTexturePos("head.ear2", 6, 10);
        this.head = new ModelPart(this, "head");
        this.head.addBox("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
        this.head.addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
        this.head.addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
        this.head.addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
        this.head.setPos(0.0F, 15.0F, -9.0F);
        this.body = new ModelPart(this, 20, 0);
        this.body.addBox(-2.0F, 3.0F, -8.0F, 4, 16, 6, 0.0F);
        this.body.setPos(0.0F, 12.0F, -10.0F);
        this.upperTail = new ModelPart(this, 0, 15);
        this.upperTail.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
        this.upperTail.rotationX = 0.9F;
        this.upperTail.setPos(0.0F, 15.0F, 8.0F);
        this.lowerTail = new ModelPart(this, 4, 15);
        this.lowerTail.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
        this.lowerTail.setPos(0.0F, 20.0F, 14.0F);
        this.backLeftLeg = new ModelPart(this, 8, 13);
        this.backLeftLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        this.backLeftLeg.setPos(1.1F, 18.0F, 5.0F);
        this.backRightLeg = new ModelPart(this, 8, 13);
        this.backRightLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        this.backRightLeg.setPos(-1.1F, 18.0F, 5.0F);
        this.frontLeftLeg = new ModelPart(this, 40, 0);
        this.frontLeftLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        this.frontLeftLeg.setPos(1.2F, 13.8F, -5.0F);
        this.frontrightLeg = new ModelPart(this, 40, 0);
        this.frontrightLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        this.frontrightLeg.setPos(-1.2F, 13.8F, -5.0F);
    }

    public void render(Entity entity, float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        this.setupAnimation(walkAnimationProgress, walkAnimationSpeed, bob, yaw, pitch, scale, entity);
        if (this.isBaby) {
            float f = 2.0F;
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.75F, 0.75F, 0.75F);
            GlStateManager.translatef(0.0F, 10.0F * scale, 4.0F * scale);
            this.head.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translatef(0.0F, 24.0F * scale, 0.0F);
            this.body.render(scale);
            this.backLeftLeg.render(scale);
            this.backRightLeg.render(scale);
            this.frontLeftLeg.render(scale);
            this.frontrightLeg.render(scale);
            this.upperTail.render(scale);
            this.lowerTail.render(scale);
            GlStateManager.popMatrix();
        } else {
            this.head.render(scale);
            this.body.render(scale);
            this.upperTail.render(scale);
            this.lowerTail.render(scale);
            this.backLeftLeg.render(scale);
            this.backRightLeg.render(scale);
            this.frontLeftLeg.render(scale);
            this.frontrightLeg.render(scale);
        }

    }

    public void setupAnimation(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale, Entity entity) {
        this.head.rotationX = pitch * ((float)Math.PI / 180F);
        this.head.rotationY = yaw * ((float)Math.PI / 180F);
        if (this.state != 3) {
            this.body.rotationX = ((float)Math.PI / 2F);
            if (this.state == 2) {
                this.backLeftLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F) * walkAnimationSpeed;
                this.backRightLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F + 0.3F) * walkAnimationSpeed;
                this.frontLeftLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F + (float)Math.PI + 0.3F) * walkAnimationSpeed;
                this.frontrightLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F + (float)Math.PI) * walkAnimationSpeed;
                this.lowerTail.rotationX = 1.7278761F + ((float)Math.PI / 10F) * MathHelper.cos(walkAnimationProgress) * walkAnimationSpeed;
            } else {
                this.backLeftLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F) * walkAnimationSpeed;
                this.backRightLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F + (float)Math.PI) * walkAnimationSpeed;
                this.frontLeftLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F + (float)Math.PI) * walkAnimationSpeed;
                this.frontrightLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F) * walkAnimationSpeed;
                if (this.state == 1) {
                    this.lowerTail.rotationX = 1.7278761F + ((float)Math.PI / 4F) * MathHelper.cos(walkAnimationProgress) * walkAnimationSpeed;
                } else {
                    this.lowerTail.rotationX = 1.7278761F + 0.47123894F * MathHelper.cos(walkAnimationProgress) * walkAnimationSpeed;
                }
            }
        }

    }

    public void prepare(LivingEntity mob, float walkAnimationProgress, float walkAnimationSpeed, float tickDelta) {
        ClientCat ocelotEntity = (ClientCat) mob;
        this.body.y = 12.0F;
        this.body.z = -10.0F;
        this.head.y = 15.0F;
        this.head.z = -9.0F;
        this.upperTail.y = 15.0F;
        this.upperTail.z = 8.0F;
        this.lowerTail.y = 20.0F;
        this.lowerTail.z = 14.0F;
        this.frontLeftLeg.y = 13.8F;
        this.frontLeftLeg.z = -5.0F;
        this.frontrightLeg.y = 13.8F;
        this.frontrightLeg.z = -5.0F;
        this.backLeftLeg.y = 18.0F;
        this.backLeftLeg.z = 5.0F;
        this.backRightLeg.y = 18.0F;
        this.backRightLeg.z = 5.0F;
        this.upperTail.rotationX = 0.9F;
        if (ocelotEntity.isSneaking()) {
            ++this.body.y;
            ModelPart var10000 = this.head;
            var10000.y += 2.0F;
            ++this.upperTail.y;
            var10000 = this.lowerTail;
            var10000.y += -4.0F;
            var10000 = this.lowerTail;
            var10000.z += 2.0F;
            this.upperTail.rotationX = ((float)Math.PI / 2F);
            this.lowerTail.rotationX = ((float)Math.PI / 2F);
            this.state = 0;
        } else if (ocelotEntity.isSprinting()) {
            this.lowerTail.y = this.upperTail.y;
            ModelPart var8 = this.lowerTail;
            var8.z += 2.0F;
            this.upperTail.rotationX = ((float)Math.PI / 2F);
            this.lowerTail.rotationX = ((float)Math.PI / 2F);
            this.state = 2;
        } else if (ocelotEntity.isSitting()) {
            this.body.rotationX = ((float)Math.PI / 4F);
            ModelPart var9 = this.body;
            var9.y += -4.0F;
            var9 = this.body;
            var9.z += 5.0F;
            var9 = this.head;
            var9.y += -3.3F;
            ++this.head.z;
            var9 = this.upperTail;
            var9.y += 8.0F;
            var9 = this.upperTail;
            var9.z += -2.0F;
            var9 = this.lowerTail;
            var9.y += 2.0F;
            var9 = this.lowerTail;
            var9.z += -0.8F;
            this.upperTail.rotationX = 1.7278761F;
            this.lowerTail.rotationX = 2.670354F;
            this.frontLeftLeg.rotationX = -0.15707964F;
            this.frontLeftLeg.y = 15.8F;
            this.frontLeftLeg.z = -7.0F;
            this.frontrightLeg.rotationX = -0.15707964F;
            this.frontrightLeg.y = 15.8F;
            this.frontrightLeg.z = -7.0F;
            this.backLeftLeg.rotationX = (-(float)Math.PI / 2F);
            this.backLeftLeg.y = 21.0F;
            this.backLeftLeg.z = 1.0F;
            this.backRightLeg.rotationX = (-(float)Math.PI / 2F);
            this.backRightLeg.y = 21.0F;
            this.backRightLeg.z = 1.0F;
            this.state = 3;
        } else {
            this.state = 1;
        }

    }
}
