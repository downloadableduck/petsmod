package com.jeff.pets.client.rendering.vanilla.cat;

import net.minecraft.client.model.animal.camel.CamelModel;
import net.minecraft.client.model.animal.feline.CatModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.CatRenderState;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCatModel extends CatModel {
    public ClientCatModel(ModelPart modelPart) {
        super(modelPart);
    }

    @Override
    public void setupAnim(CatRenderState state) {
        super.setupAnim(state);
        if (CONFIG.isBaby) {
            this.head.yScale = 1.5f;
            this.head.xScale = 1.5f;
            this.head.zScale = 1.5f;
            this.head.y -= 1.0f;
        }
    }
}
