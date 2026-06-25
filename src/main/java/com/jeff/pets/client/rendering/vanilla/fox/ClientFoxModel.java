package com.jeff.pets.client.rendering.vanilla.fox;

import net.minecraft.client.model.FoxModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.FoxRenderState;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientFoxModel extends FoxModel {

    private final ModelPart head;

    public ClientFoxModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(@NotNull FoxRenderState state) {
        super.setupAnim(state);
        if (CONFIG.isBaby) {
            head.xScale = 1.5f;
            head.yScale = 1.5f;
            head.zScale = 1.5f;
        }
    }
}
