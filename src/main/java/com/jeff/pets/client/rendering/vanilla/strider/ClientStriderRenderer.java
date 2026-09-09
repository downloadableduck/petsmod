package com.jeff.pets.client.rendering.vanilla.strider;

import com.jeff.pets.client.rendering.IPetRenderState;
import com.jeff.pets.mob.vanilla.passive.ClientStrider;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.strider.AdultStriderModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.StriderRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientStriderRenderer extends PetRenderer<@NotNull ClientStrider, @NotNull StriderRenderState, @NotNull AdultStriderModel> {
    public static ModelLayerLocation STRIDER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientstrider"), "main");

    public String striderTexturePath;

    public ClientStriderRenderer(EntityRendererProvider.Context context) {
        super(context, new AdultStriderModel(context.bakeLayer(ModelLayers.STRIDER)), 0.5F);
    }

    @Override
    protected void scale(@NotNull StriderRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (livingEntityRenderState.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public StriderRenderState createRenderState() {
        return new StriderRenderState();
    }

    public @NotNull Identifier getTextureLocation(StriderRenderState striderRenderState) {
        String skin = ((IPetRenderState) striderRenderState).pets$getPetSkin();
        if (Objects.equals(skin, "warm")) {
            striderTexturePath = "textures/entity/strider/strider.png";
        } else if (Objects.equals(skin, "cold")) {
            striderTexturePath = "textures/entity/strider/strider_cold.png";
        }
        return Identifier.withDefaultNamespace(striderTexturePath);
    }
}
