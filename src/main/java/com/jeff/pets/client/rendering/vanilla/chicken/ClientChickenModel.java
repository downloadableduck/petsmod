package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenModel<T extends ClientChicken> extends ChickenModel<T> {

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart beak;
    private final ModelPart redThing;

    public ClientChickenModel(ModelPart modelPart) {
        super(modelPart);
        head = modelPart.getChild("head");
        this.beak = modelPart.getChild("beak");
        this.redThing = modelPart.getChild("red_thing");
        this.body = modelPart.getChild("body");
        this.rightLeg = modelPart.getChild("right_leg");
        this.leftLeg = modelPart.getChild("left_leg");
        this.rightWing = modelPart.getChild("right_wing");
        this.leftWing = modelPart.getChild("left_wing");
    }

    @Override
    public void setupAnim(@NotNull T state, float f, float g, float h, float i, float j) {
        h = getBob(state, f);
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
