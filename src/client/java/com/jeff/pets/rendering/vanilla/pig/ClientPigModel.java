package com.jeff.pets.rendering.vanilla.pig;

import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

import static com.jeff.pets.Central.CONFIG;

public class ClientPigModel extends PigModel {

    private final ModelPart head;

    public ClientPigModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        if (CONFIG.isBaby) {
            head.xScale = 1.5f;
            head.yScale = 1.5f;
            head.zScale = 1.5f;
        }
    }
}
