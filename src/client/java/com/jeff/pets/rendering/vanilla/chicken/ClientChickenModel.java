package com.jeff.pets.rendering.vanilla.chicken;

import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientChickenModel extends ChickenModel {

    private final ModelPart head;

    public ClientChickenModel(ModelPart modelPart) {
        super(modelPart);
        head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(@NotNull ChickenRenderState state) {
        super.setupAnim(state);
        if (CONFIG.isBaby) {
            head.zScale = 2;
            head.xScale = 2;
            head.yScale = 2;
        }
    }
}
