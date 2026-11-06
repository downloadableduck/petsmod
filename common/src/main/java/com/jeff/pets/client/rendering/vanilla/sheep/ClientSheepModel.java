package com.jeff.pets.client.rendering.vanilla.sheep;

import net.minecraft.client.model.animal.sheep.SheepModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import org.jetbrains.annotations.NotNull;

public class ClientSheepModel extends SheepModel {

    protected final ModelPart head;

    public ClientSheepModel(ModelPart modelPart) {
        super(modelPart);
        head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(@NotNull SheepRenderState state) {
        super.setupAnim(state);
        if (state.isBaby) {
            head.xScale = 2.0f;
            head.yScale = 2.0f;
            head.zScale = 2.0f;
        }
    }
}
