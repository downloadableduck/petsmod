package com.jeff.pets.rendering.vanilla.wolf;

import net.minecraft.client.model.animal.wolf.AdultWolfModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientWolfModel extends AdultWolfModel {

    private final ModelPart head;

    public ClientWolfModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(@NotNull WolfRenderState state) {
        super.setupAnim(state);
        if (CONFIG.isBaby) {
            head.xScale = 2.0f;
            head.yScale = 2.0f;
            head.zScale = 2.0f;
            head.y -= 1;
        }
    }
}
