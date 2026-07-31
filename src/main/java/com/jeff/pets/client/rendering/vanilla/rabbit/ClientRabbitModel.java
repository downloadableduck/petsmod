package com.jeff.pets.client.rendering.vanilla.rabbit;

import net.minecraft.client.model.animal.rabbit.AdultRabbitModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.RabbitRenderState;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientRabbitModel extends AdultRabbitModel {

    private final ModelPart head;

    public ClientRabbitModel(ModelPart modelPart) {
        super(modelPart);
        this.head = this.root().getChild("head");
    }

    @Override
    public void setupAnim(@NotNull RabbitRenderState state) {
        super.setupAnim(state);
        if (CONFIG.isBaby) {
            this.head.xScale = 1.5f;
            this.head.yScale = 1.5f;
            this.head.zScale = 1.5f;
        }
    }
}
