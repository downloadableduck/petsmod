package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.client.rendering.ModelUtils;
import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.IHeadToggle;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class ClientZombieVillagerModel extends BipedModel<ClientZombieVillager> implements IHeadToggle {
    private RendererModel hatRim;

    public ClientZombieVillagerModel(float f, boolean bl) {
        super(f, 0.0F, 64, bl ? 32 : 64);
        if (bl) {
            this.head = new RendererModel(this, 0, 0);
            this.head.addBox(-4.0F, -10.0F, -4.0F, (int) 8.0, (int) 8.0, (int) 8.0, f);
            this.body = new RendererModel(this, 16, 16);
            this.body.addBox(-4.0F, 0.0F, -2.0F, (int) 8.0, (int) 12.0, (int) 4.0, f + 0.1F);
            this.rightLeg = new RendererModel(this, 0, 16);
            this.rightLeg.setPos(-2.0F, 12.0F, 0.0F);
            this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f + 0.1F);
            this.leftLeg = new RendererModel(this, 0, 16);
            this.leftLeg.mirror = true;
            this.leftLeg.setPos(2.0F, 12.0F, 0.0F);
            this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f + 0.1F);
        } else {
            this.head = new RendererModel(this, 0, 0);
            this.head.texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, (int) 8.0, (int) 10.0, (int) 8.0, f);
            this.head.texOffs(24, 0).addBox(-1.0F, -3.0F, -6.0F, (int) 2.0, (int) 4.0, (int) 2.0, f);
            this.hat = new RendererModel(this, 32, 0);
            this.hat.addBox(-4.0F, -10.0F, -4.0F, (int) 8.0, (int) 10.0, (int) 8.0, f + 0.5F);
            this.hatRim = new RendererModel(this);
            this.hatRim.texOffs(30, 47).addBox(-8.0F, -8.0F, -6.0F, (int) 16.0, (int) 16.0, (int) 1.0, f);
            this.hatRim.xRot = (-(float) Math.PI / 2F);
            this.hat.addChild(this.hatRim);
            this.body = new RendererModel(this, 16, 20);
            this.body.addBox(-4.0F, 0.0F, -3.0F, (int) 8.0, (int) 12.0, (int) 6.0, f);
            this.body.texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, (int) 8.0, (int) 18.0, (int) 6.0, f + 0.05F);
            this.rightArm = new RendererModel(this, 44, 22);
            this.rightArm.addBox(-3.0F, -2.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f);
            this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
            this.leftArm = new RendererModel(this, 44, 22);
            this.leftArm.mirror = true;
            this.leftArm.addBox(-1.0F, -2.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f);
            this.leftArm.setPos(5.0F, 2.0F, 0.0F);
            this.rightLeg = new RendererModel(this, 0, 22);
            this.rightLeg.setPos(-2.0F, 12.0F, 0.0F);
            this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f);
            this.leftLeg = new RendererModel(this, 0, 22);
            this.leftLeg.mirror = true;
            this.leftLeg.setPos(2.0F, 12.0F, 0.0F);
            this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, (int) 4.0, (int) 12.0, (int) 4.0, f);
        }

    }

    @Override
    public void setupAnim(ClientZombieVillager zombie, float f, float g, float h, float i, float j, float r) {
        super.setupAnim(zombie, f, g, h, i, j, r);
        ModelUtils.animateZombieArms(this.leftArm, this.rightArm, zombie.isAggressive(), this.attackTime, h);
    }

    public void hatVisible(boolean bl) {
        this.head.visible = bl;
        this.hat.visible = bl;
        this.hatRim.visible = bl;
    }
}
