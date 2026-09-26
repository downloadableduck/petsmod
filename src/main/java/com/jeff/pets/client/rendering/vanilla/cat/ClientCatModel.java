package com.jeff.pets.client.rendering.vanilla.cat;

import net.minecraft.client.model.animal.feline.AdultCatModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.CatRenderState;

public class ClientCatModel extends AdultCatModel {
    public ClientCatModel(ModelPart modelPart) {
        super(modelPart);
    }

    @Override
    public void setupAnim(CatRenderState state) {
        super.setupAnim(state);
        if (state.isBaby) {
            this.head.yScale = 1.5f;
            this.head.xScale = 1.5f;
            this.head.zScale = 1.5f;
            this.head.y -= 1.0f;
        }
    }
}
