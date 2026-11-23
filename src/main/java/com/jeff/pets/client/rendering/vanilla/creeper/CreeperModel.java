//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.creeper;

import com.jeff.pets.compat.render.model.Model;
import com.jeff.pets.compat.render.model.ModelPart;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.util.helper.MathHelper;

@Environment(EnvType.CLIENT)
public class CreeperModel extends Model {
    public ModelPart head;
    public ModelPart hat;
    public ModelPart body;
    public ModelPart rightBackLeg;
    public ModelPart leftBackleg;
    public ModelPart rightFrontLeg;
    public ModelPart leftFrontLeg;

    public CreeperModel() {
        this(0.0F);
    }

    public CreeperModel(float reduction) {
        int i = 4;
        this.head = new ModelPart(this, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, reduction);
        this.head.setPos(0.0F, (float)i, 0.0F);
        this.hat = new ModelPart(this, 32, 0);
        this.hat.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, reduction + 0.5F);
        this.hat.setPos(0.0F, (float)i, 0.0F);
        this.body = new ModelPart(this, 16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, reduction);
        this.body.setPos(0.0F, (float)i, 0.0F);
        this.rightBackLeg = new ModelPart(this, 0, 16);
        this.rightBackLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, reduction);
        this.rightBackLeg.setPos(-2.0F, (float)(12 + i), 4.0F);
        this.leftBackleg = new ModelPart(this, 0, 16);
        this.leftBackleg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, reduction);
        this.leftBackleg.setPos(2.0F, (float)(12 + i), 4.0F);
        this.rightFrontLeg = new ModelPart(this, 0, 16);
        this.rightFrontLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, reduction);
        this.rightFrontLeg.setPos(-2.0F, (float)(12 + i), -4.0F);
        this.leftFrontLeg = new ModelPart(this, 0, 16);
        this.leftFrontLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, reduction);
        this.leftFrontLeg.setPos(2.0F, (float)(12 + i), -4.0F);
    }

    public void render(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        this.setupAnimation(walkAnimationProgress, walkAnimationSpeed, bob, yaw, pitch, scale);
        this.head.render(scale);
        this.body.render(scale);
        this.rightBackLeg.render(scale);
        this.leftBackleg.render(scale);
        this.rightFrontLeg.render(scale);
        this.leftFrontLeg.render(scale);
    }

    public void setupAnimation(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        this.head.rotationY = yaw / 57.295776F;
        this.head.rotationX = pitch / 57.295776F;
        this.rightBackLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F) * 1.4F * walkAnimationSpeed;
        this.leftBackleg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F + 3.1415927F) * 1.4F * walkAnimationSpeed;
        this.rightFrontLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F + 3.1415927F) * 1.4F * walkAnimationSpeed;
        this.leftFrontLeg.rotationX = MathHelper.cos(walkAnimationProgress * 0.6662F) * 1.4F * walkAnimationSpeed;
    }
}
