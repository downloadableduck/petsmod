package com.jeff.pets.client.rendering.vanilla.zombie;

import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieModel<T extends AbstractPet> extends HumanoidModel<@NotNull T> {

    private final ModelPart head;

    public ClientZombieModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(T state, float f, float g, float h, float i, float j) {
        super.setupAnim(state, f, g, h, i, j);
        if (CONFIG.isBaby) {
            this.head.zScale = 1.5f;
            this.head.xScale = 1.5f;
            this.head.yScale = 1.5f;
        }
        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, true, this.attackTime, h);
    }
}
