package com.jeff.pets.client.rendering.vanilla.drowned;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.zombie.DrownedModel;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import org.jetbrains.annotations.NotNull;

public class ClientDrownedModel extends DrownedModel {

    public static float headScale;

    final ModelPart head;

    public ClientDrownedModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(@NotNull ZombieRenderState state) {
        super.setupAnim(state);
        if (state.isBaby) {
            headScale = 1.5f;
            this.head.yScale = headScale;
            this.head.zScale = headScale;
            this.head.xScale = headScale;
        }
    }
}
