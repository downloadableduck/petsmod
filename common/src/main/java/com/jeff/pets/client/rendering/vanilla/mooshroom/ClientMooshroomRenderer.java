package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.IPetRenderState;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.MushroomCowMushroomLayer;
import net.minecraft.client.renderer.entity.state.MushroomCowRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ClientMooshroomRenderer extends PetRenderer<@NotNull ClientMooshroom, @NotNull MushroomCowRenderState, @NotNull CowModel> {
    public static final ModelLayerLocation MOOSHROOM_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientmooshroom"), "main");

    String mooshroomTexturePath;

    public ClientMooshroomRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientCowModel(context.bakeLayer(ModelLayers.COW)), 0.7F);
        this.addLayer(new MushroomCowMushroomLayer(this));
    }

    @Override
    protected void scale(MushroomCowRenderState state, @NotNull PoseStack poseStack) {
        if (state.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(MushroomCowRenderState cowRenderState) {
        String skin = ((IPetRenderState) cowRenderState).pets$getPetSkin();
        if (Objects.equals(skin, "red")) {
            mooshroomTexturePath = "textures/entity/cow/mooshroom_red.png";
        } else if (Objects.equals(skin, "brown")) {
            mooshroomTexturePath = "textures/entity/cow/mooshroom_brown.png";
        } else {
            mooshroomTexturePath = "textures/entity/cow/mooshroom_red.png";
        }
        return Identifier.withDefaultNamespace(mooshroomTexturePath);
    }

    @Override
    public MushroomCowRenderState createRenderState() {
        return new MushroomCowRenderState();
    }
}
