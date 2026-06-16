package com.jeff.pets.client.rendering.vanilla.cow;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowModel extends CowModel {

    private final ModelPart head;

    public ClientCowModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        if (CONFIG.isBaby) {
            head.xScale = 2.0f;
            head.yScale = 2.0f;
            head.zScale = 2.0f;
        }
    }
}
