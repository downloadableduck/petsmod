package com.jeff.pets.client.rendering.vanilla.zombie;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieModel extends ZombieModel<@NotNull ZombieRenderState> {

    private final ModelPart head;

    public ClientZombieModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(ZombieRenderState state) {
        super.setupAnim(state);
        if (CONFIG.isBaby) {
            this.head.zScale = 1.5f;
            this.head.xScale = 1.5f;
            this.head.yScale = 1.5f;
        }
    }
}
