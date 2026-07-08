package com.jeff.pets.rendering.vanilla.cow;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Chicken;

import static com.jeff.pets.Central.CONFIG;

public class ClientCowModel<T extends Entity> extends CowModel<T> {

    private final ModelPart head;

    public ClientCowModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(T state, float f, float g, float h, float i, float k) {
        super.setupAnim(state, f, g, h, i, k);
        if (CONFIG.isBaby) {
            head.xScale = 2.0f;
            head.yScale = 2.0f;
            head.zScale = 2.0f;
        } else {
            head.xScale = 1.0f;
            head.yScale = 1.0f;
            head.zScale = 1.0f;
        }
    }
}
