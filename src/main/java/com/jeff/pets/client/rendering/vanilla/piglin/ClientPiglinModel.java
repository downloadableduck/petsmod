package com.jeff.pets.client.rendering.vanilla.piglin;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.piglin.AdultPiglinModel;
import net.minecraft.client.renderer.entity.state.PiglinRenderState;
import org.jetbrains.annotations.NotNull;

public class ClientPiglinModel extends AdultPiglinModel {

    private final ModelPart head;

    public ClientPiglinModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    public void setupAnim(@NotNull PiglinRenderState state) {
        super.setupAnim(state);
        if (state.isBaby) {
            head.xScale = 1.5f;
            head.yScale = 1.5f;
            head.zScale = 1.5f;
        }
    }
}
