package com.jeff.pets.client.rendering.vanilla.sheep;

import net.minecraft.client.model.SheepFurModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSheepFurModel extends SheepFurModel {

    private final ModelPart head;

    public ClientSheepFurModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(@NotNull SheepRenderState state) {
        super.setupAnim(state);
        if (CONFIG.isBaby) {
            head.xScale = 2.0f;
            head.yScale = 2.0f;
            head.zScale = 2.0f;
        }
    }
}
