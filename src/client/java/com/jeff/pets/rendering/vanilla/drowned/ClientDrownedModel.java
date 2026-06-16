package com.jeff.pets.rendering.vanilla.drowned;

import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

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
        if (CONFIG.isBaby) {
            headScale = 1.5f;
            this.head.yScale = headScale;
            this.head.zScale = headScale;
            this.head.xScale = headScale;
        }
    }
}
