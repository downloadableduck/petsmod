package com.jeff.pets.client.rendering.vanilla.llama;

import net.minecraft.client.model.animal.llama.LlamaModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.LlamaRenderState;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientLlamaModel extends LlamaModel {

    private final ModelPart head;

    public ClientLlamaModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(@NotNull LlamaRenderState state) {
        super.setupAnim(state);
        if (CONFIG.isBaby) {
            head.yScale = 1.5f;
            head.zScale = 1.5f;
            head.xScale = 1.5f;
        }
    }
}
