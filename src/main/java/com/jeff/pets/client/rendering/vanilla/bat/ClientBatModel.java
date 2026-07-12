package com.jeff.pets.client.rendering.vanilla.bat;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.passive.ClientBat;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class ClientBatModel extends ListModel<ClientBat> {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart rightWingTip;
    private final ModelPart leftWingTip;

    public ClientBatModel() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.head = new ModelPart(this, 0, 0);
        this.head.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F);
        ModelPart modelPart = new ModelPart(this, 24, 0);
        modelPart.addBox(-4.0F, -6.0F, -2.0F, 3.0F, 4.0F, 1.0F);
        this.head.addChild(modelPart);
        ModelPart modelPart2 = new ModelPart(this, 24, 0);
        modelPart2.mirror = true;
        modelPart2.addBox(1.0F, -6.0F, -2.0F, 3.0F, 4.0F, 1.0F);
        this.head.addChild(modelPart2);
        this.body = new ModelPart(this, 0, 16);
        this.body.addBox(-3.0F, 4.0F, -3.0F, 6.0F, 12.0F, 6.0F);
        this.body.texOffs(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10.0F, 6.0F, 1.0F);
        this.rightWing = new ModelPart(this, 42, 0);
        this.rightWing.addBox(-12.0F, 1.0F, 1.5F, 10.0F, 16.0F, 1.0F);
        this.rightWingTip = new ModelPart(this, 24, 16);
        this.rightWingTip.setPos(-12.0F, 1.0F, 1.5F);
        this.rightWingTip.addBox(-8.0F, 1.0F, 0.0F, 8.0F, 12.0F, 1.0F);
        this.leftWing = new ModelPart(this, 42, 0);
        this.leftWing.mirror = true;
        this.leftWing.addBox(2.0F, 1.0F, 1.5F, 10.0F, 16.0F, 1.0F);
        this.leftWingTip = new ModelPart(this, 24, 16);
        this.leftWingTip.mirror = true;
        this.leftWingTip.setPos(12.0F, 1.0F, 1.5F);
        this.leftWingTip.addBox(0.0F, 1.0F, 0.0F, 8.0F, 12.0F, 1.0F);
        this.body.addChild(this.rightWing);
        this.body.addChild(this.leftWing);
        this.rightWing.addChild(this.rightWingTip);
        this.leftWing.addChild(this.leftWingTip);
    }

    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.head, this.body);
    }

    public void setupAnim(ClientBat bat, float f, float g, float h, float i, float j) {
        this.head.xRot = j * ((float) Math.PI / 180F);
        this.head.yRot = i * ((float) Math.PI / 180F);
        this.head.zRot = 0.0F;
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.rightWing.setPos(0.0F, 0.0F, 0.0F);
        this.leftWing.setPos(0.0F, 0.0F, 0.0F);
        this.body.xRot = ((float) Math.PI / 4F) + Mth.cos(h * 0.1F) * 0.15F;
        this.body.yRot = 0.0F;
        this.rightWing.yRot = Mth.cos(h * 74.48451F * ((float) Math.PI / 180F)) * (float) Math.PI * 0.25F;
        this.leftWing.yRot = -this.rightWing.yRot;
        this.rightWingTip.yRot = this.rightWing.yRot * 0.5F;
        this.leftWingTip.yRot = -this.rightWing.yRot * 0.5F;
    }
}
