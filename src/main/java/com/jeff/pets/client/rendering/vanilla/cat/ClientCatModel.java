//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.cat;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.util.helper.MathHelper;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.compat.render.model.ModelPart;
import com.jeff.pets.mob.vanilla.passive.ClientCat;

public class ClientCatModel extends PetModel {
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

    public void render(Entity entity, float walkAnimPos, float walkAnimSpeed, float bob, float yRot, float xRot, float scale) {
        this.setupAnimation(walkAnimPos, walkAnimSpeed, bob, yRot, xRot, scale, entity);
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

    public void setupAnimation(float walkAnimPos, float walkAnimSpeed, float bob, float yRot, float xRot, float scale, Entity entity) {
        this.head.rotationX = xRot * 0.017453292F;
        this.head.rotationY = yRot * 0.017453292F;
        if (this.state != 3) {
            this.body.rotationX = 1.5707964F;
            if (this.state == 2) {
                this.backLeftLeg.rotationX = MathHelper.cos(walkAnimPos * 0.6662F) * walkAnimSpeed;
                this.backRightLeg.rotationX = MathHelper.cos(walkAnimPos * 0.6662F + 0.3F) * walkAnimSpeed;
                this.frontLeftLeg.rotationX = MathHelper.cos(walkAnimPos * 0.6662F + 3.1415927F + 0.3F) * walkAnimSpeed;
                this.frontrightLeg.rotationX = MathHelper.cos(walkAnimPos * 0.6662F + 3.1415927F) * walkAnimSpeed;
                this.lowerTail.rotationX = 1.7278761F + 0.31415927F * MathHelper.cos(walkAnimPos) * walkAnimSpeed;
            } else {
                this.backLeftLeg.rotationX = MathHelper.cos(walkAnimPos * 0.6662F) * walkAnimSpeed;
                this.backRightLeg.rotationX = MathHelper.cos(walkAnimPos * 0.6662F + 3.1415927F) * walkAnimSpeed;
                this.frontLeftLeg.rotationX = MathHelper.cos(walkAnimPos * 0.6662F + 3.1415927F) * walkAnimSpeed;
                this.frontrightLeg.rotationX = MathHelper.cos(walkAnimPos * 0.6662F) * walkAnimSpeed;
                if (this.state == 1) {
                    this.lowerTail.rotationX = 1.7278761F + 0.7853982F * MathHelper.cos(walkAnimPos) * walkAnimSpeed;
                } else {
                    this.lowerTail.rotationX = 1.7278761F + 0.47123894F * MathHelper.cos(walkAnimPos) * walkAnimSpeed;
                }
            }
        }

    }

    public void animateModel(Mob mob, float walkAnimPos, float walkAnimSpeed, float tickDelta) {
        ClientCat ocelotEntity = (ClientCat)mob;
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
        ModelPart var9;
        if (ocelotEntity.isSneaking()) {
            ++this.body.y;
            var9 = this.head;
            var9.y += 2.0F;
            ++this.upperTail.y;
            var9 = this.lowerTail;
            var9.y += -4.0F;
            var9 = this.lowerTail;
            var9.z += 2.0F;
            this.upperTail.rotationX = 1.5707964F;
            this.lowerTail.rotationX = 1.5707964F;
            this.state = 0;
        } else if (ocelotEntity.isSitting()) {
            this.body.rotationX = 0.7853982F;
            var9 = this.body;
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
            this.backLeftLeg.rotationX = -1.5707964F;
            this.backLeftLeg.y = 21.0F;
            this.backLeftLeg.z = 1.0F;
            this.backRightLeg.rotationX = -1.5707964F;
            this.backRightLeg.y = 21.0F;
            this.backRightLeg.z = 1.0F;
            this.state = 3;
        } else {
            this.state = 1;
        }

    }
}
