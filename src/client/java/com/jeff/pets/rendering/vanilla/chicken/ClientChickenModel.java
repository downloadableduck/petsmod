package com.jeff.pets.rendering.vanilla.chicken;

import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientChickenModel<T extends ClientChicken> extends ChickenModel<T> {

    public ClientChickenModel(ModelPart modelPart) {
        super(modelPart);
    }

    @Override
    public void setupAnim(@NotNull T state, float f, float g, float h, float i, float j) {
        h = getBob(state, f);
        ModelPart head = this.headParts().iterator().next();
        super.setupAnim(state, f, g, h, i, j);
        if (CONFIG.isBaby) {
            head.zScale = 2;
            head.xScale = 2;
            head.yScale = 2;
        } else {
            head.zScale = 1;
            head.xScale = 1;
            head.yScale = 1;
        }
    }

    protected float getBob(ClientChicken chicken, float f) {
        float g = Mth.lerp(f, chicken.oFlap, chicken.flap);
        float h = Mth.lerp(f, chicken.oFlapSpeed, chicken.flapSpeed);
        return (Mth.sin(g) + 1.0F) * h;
    }
}
