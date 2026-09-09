package com.jeff.pets.client.rendering.vanilla.goat;

import net.minecraft.client.model.animal.goat.GoatModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.GoatRenderState;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientGoatModel extends GoatModel {

    private final ModelPart head;
    private final ModelPart leftHorn;
    private final ModelPart rightHorn;

    public ClientGoatModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
        this.leftHorn = head.getChild("left_horn");
        this.rightHorn = head.getChild("right_horn");
    }

    @Override
    public void setupAnim(@NotNull GoatRenderState state) {
        super.setupAnim(state);
        if (state.isBaby) {
            this.leftHorn.visible = false;
            this.rightHorn.visible = false;
        }
    }
}
