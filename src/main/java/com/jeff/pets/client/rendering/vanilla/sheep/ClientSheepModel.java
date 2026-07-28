package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.mob.vanilla.passive.ClientSheep;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class ClientSheepModel extends QuadrupedModel<ClientSheep> {
    private float headXRot;

    public ClientSheepModel() {
        super(12, 0.0F);
        this.head = new RendererModel(this, 0, 0);
        this.head.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
        this.head.setPos(0.0F, 6.0F, -8.0F);
        this.body = new RendererModel(this, 28, 8);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
        this.body.setPos(0.0F, 5.0F, 2.0F);
    }
}
