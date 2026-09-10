package com.jeff.pets.client.rendering.vanilla.sheep;

import net.minecraft.client.render.entity.model.QuadruPedEntityModel;
import net.minecraft.client.render.model.ModelPart;

public class ClientSheepModel extends QuadruPedEntityModel {
    private float headXRot;

    public ClientSheepModel() {
        super(12, 0.0F);
        this.head = new ModelPart(this, 0, 0);
        this.head.addCuboid(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
        this.head.setPivot(0.0F, 6.0F, -8.0F);
        this.torso = new ModelPart(this, 28, 8);
        this.torso.addCuboid(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
        this.torso.setPivot(0.0F, 5.0F, 2.0F);
    }
}
