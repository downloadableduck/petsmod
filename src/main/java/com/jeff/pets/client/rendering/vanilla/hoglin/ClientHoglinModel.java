package com.jeff.pets.client.rendering.vanilla.hoglin;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.hoglin.HoglinModel;
import net.minecraft.client.renderer.entity.state.HoglinRenderState;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHoglinModel extends HoglinModel {

    private final ModelPart head;

    public ClientHoglinModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(@NotNull HoglinRenderState state) {
        super.setupAnim(state);
        if (state.isBaby) {
            this.head.zScale = 1.5f;
            this.head.xScale = 1.5f;
            this.head.yScale = 1.5f;
            this.head.y -= 5;
        }
    }
}
