package com.jeff.pets.vanilla.donkey;

import net.minecraft.client.model.animal.equine.AbstractEquineModel;
import net.minecraft.client.model.animal.equine.EquineSaddleModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.EquineRenderState;
import org.jetbrains.annotations.NotNull;

/**
 * DO NOT DELETE, the donkey requires a custom model to work properly
 */
public class ClientDonkeyModel extends AbstractEquineModel<@NotNull EquineRenderState> {
    private static final MeshTransformer DONKEY_TRANSFORMER = (meshDefinition) -> meshDefinition;
    private final ModelPart leftChest;
    private final ModelPart rightChest;

    public ClientDonkeyModel(ModelPart modelPart) {
        super(modelPart);
        this.leftChest = this.body.getChild("left_chest");
        this.rightChest = this.body.getChild("right_chest");
    }

    public static LayerDefinition getTexturedModelData() {
        return LayerDefinition.create(AbstractEquineModel.createBodyMesh(CubeDeformation.NONE), 64, 64).apply(DONKEY_TRANSFORMER);
    }

    public void setupAnim(EquineRenderState donkeyRenderState) {
        super.setupAnim(donkeyRenderState);
        this.leftChest.visible = false;
        this.rightChest.visible = false;
    }
}
