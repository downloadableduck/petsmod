package com.jeff.pets.rendering.vanilla.hoglin;

import com.jeff.pets.mob.vanilla.hostile.ClientHoglin;
import net.minecraft.client.model.HoglinModel;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientHoglinModel extends HoglinModel<ClientHoglin> {

    private final ModelPart head;

    public ClientHoglinModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(@NotNull ClientHoglin state, float f, float g, float h, float i, float j) {
        super.setupAnim(state, f, g, h, i, j);
        if (CONFIG.isBaby) {
            this.head.zScale = 1.5f;
            this.head.xScale = 1.5f;
            this.head.yScale = 1.5f;
            this.head.y -= 5;
        }
    }
}
