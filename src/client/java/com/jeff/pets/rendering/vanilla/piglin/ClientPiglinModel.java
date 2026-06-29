package com.jeff.pets.rendering.vanilla.piglin;

import com.jeff.pets.mob.vanilla.neutral.ClientPiglin;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientPiglinModel extends PiglinModel<ClientPiglin> {

    private final ModelPart head;

    public ClientPiglinModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(@NotNull ClientPiglin state, float f, float g, float h, float i, float k) {
        super.setupAnim(state, f, g, h, i, k);
        if (CONFIG.isBaby) {
            head.xScale = 1.5f;
            head.yScale = 1.5f;
            head.zScale = 1.5f;
        }
    }
}
