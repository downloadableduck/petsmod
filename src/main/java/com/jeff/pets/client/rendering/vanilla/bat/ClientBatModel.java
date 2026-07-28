package com.jeff.pets.client.rendering.vanilla.bat;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.passive.ClientBat;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class ClientBatModel extends EntityModel<ClientBat> {
    private final RendererModel head;
    private final RendererModel body;
    private final RendererModel rightWing;
    private final RendererModel leftWing;
    private final RendererModel rightWingTip;
    private final RendererModel leftWingTip;

    public ClientBatModel() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.head = new RendererModel(this, 0, 0);
        this.head.addBox(-3.0F, -3.0F, -3.0F, (int) 6.0, (int) 6.0, (int) 6.0);
        RendererModel modelPart = new RendererModel(this, 24, 0);
        modelPart.addBox(-4.0F, -6.0F, -2.0F, (int) 3.0, (int) 4.0, (int) 1.0);
        this.head.addChild(modelPart);
        RendererModel modelPart2 = new RendererModel(this, 24, 0);
        modelPart2.mirror = true;
        modelPart2.addBox(1.0F, -6.0F, -2.0F, (int) 3.0, (int) 4.0, (int) 1.0);
        this.head.addChild(modelPart2);
        this.body = new RendererModel(this, 0, 16);
        this.body.addBox(-3.0F, 4.0F, -3.0F, (int) 6.0, (int) 12.0, (int) 6.0);
        this.body.texOffs(0, 34).addBox(-5.0F, 16.0F, 0.0F, (int) 10.0, (int) 6.0, (int) 1.0);
        this.rightWing = new RendererModel(this, 42, 0);
        this.rightWing.addBox(-12.0F, 1.0F, 1.5F, (int) 10.0, (int) 16.0, (int) 1.0);
        this.rightWingTip = new RendererModel(this, 24, 16);
        this.rightWingTip.setPos(-12.0F, 1.0F, 1.5F);
        this.rightWingTip.addBox(-8.0F, 1.0F, 0.0F, (int) 8.0, (int) 12.0, (int) 1.0);
        this.leftWing = new RendererModel(this, 42, 0);
        this.leftWing.mirror = true;
        this.leftWing.addBox(2.0F, 1.0F, 1.5F, (int) 10.0, (int) 16.0, (int) 1.0);
        this.leftWingTip = new RendererModel(this, 24, 16);
        this.leftWingTip.mirror = true;
        this.leftWingTip.setPos(12.0F, 1.0F, 1.5F);
        this.leftWingTip.addBox(0.0F, 1.0F, 0.0F, (int) 8.0, (int) 12.0, (int) 1.0);
        this.body.addChild(this.rightWing);
        this.body.addChild(this.leftWing);
        this.rightWing.addChild(this.rightWingTip);
        this.leftWing.addChild(this.leftWingTip);
    }

    public Iterable<RendererModel> parts() {
        return ImmutableList.of(this.head, this.body);
    }

    @Override
    public void setupAnim(ClientBat bat, float f, float g, float h, float i, float j, float k) {
        this.head.xRot = j * ((float) Math.PI / 180F);
        this.head.yRot = i * ((float) Math.PI / 180F);
        this.head.zRot = 0.0F;
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.rightWing.setPos(0.0F, 0.0F, 0.0F);
        this.leftWing.setPos(0.0F, 0.0F, 0.0F);
        this.body.xRot = ((float) Math.PI / 4F) + net.minecraft.util.math.MathHelper.cos(h * 0.1F) * 0.15F;
        this.body.yRot = 0.0F;
        this.rightWing.yRot = net.minecraft.util.math.MathHelper.cos(h * 74.48451F * ((float) Math.PI / 180F)) * (float) Math.PI * 0.25F;
        this.leftWing.yRot = -this.rightWing.yRot;
        this.rightWingTip.yRot = this.rightWing.yRot * 0.5F;
        this.leftWingTip.yRot = -this.rightWing.yRot * 0.5F;
    }

    @Override
    public void render(ClientBat bat, float f, float g, float h, float i, float k, float m) {
        this.setupAnim(bat, f, g, h, i, k, m);
        this.head.render(m);
        this.body.render(m);
    }
}
