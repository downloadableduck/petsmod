package com.jeff.pets.rendering.vanilla.pig;

import com.jeff.pets.mob.vanilla.passive.ClientPig;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelPart;

import static com.jeff.pets.Central.CONFIG;

public class ClientPigModel extends PigModel<ClientPig> {

    private final ModelPart head;

    public ClientPigModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(ClientPig state, float f, float g, float h, float i, float k) {
        super.setupAnim(state, f, g, h, i, k);
        if (CONFIG.isBaby) {
            head.xScale = 1.5f;
            head.yScale = 1.5f;
            head.zScale = 1.5f;
        }
    }
}
