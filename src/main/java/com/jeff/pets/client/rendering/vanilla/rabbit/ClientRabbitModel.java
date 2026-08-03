package com.jeff.pets.client.rendering.vanilla.rabbit;

import net.minecraft.client.model.animal.rabbit.AdultRabbitModel;
import net.minecraft.client.model.animal.rabbit.RabbitModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.RabbitRenderState;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientRabbitModel extends AdultRabbitModel {

    public ClientRabbitModel(ModelPart modelPart) {
        super(modelPart);
    }

    @Override
    public void setupAnim(@NotNull RabbitRenderState state) {
        super.setupAnim(state);
        try {
            Field field = RabbitModel.class.getDeclaredField("head");
            field.setAccessible(true);
            ModelPart head = (ModelPart) field.get(this);
            if (CONFIG.isBaby) {
                head.xScale = 1.5f;
                head.yScale = 1.5f;
                head.zScale = 1.5f;
            }
        } catch (Exception e) {

        }
    }
}
