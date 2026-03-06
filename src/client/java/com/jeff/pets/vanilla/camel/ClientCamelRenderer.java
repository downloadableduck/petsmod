package com.jeff.pets.vanilla.camel;

import com.jeff.pets.vanilla.passive.ClientCamel;
import net.minecraft.client.model.animal.camel.CamelModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CamelRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class ClientCamelRenderer extends MobRenderer<@NotNull ClientCamel, @NotNull CamelRenderState, @NotNull CamelModel> {
    public static final ModelLayerLocation CAMEL_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientcamel"), "main");
    public String camelTexturePath;

    public ClientCamelRenderer(EntityRendererProvider.Context context) {
        super(context, new CamelModel(context.bakeLayer(ModelLayers.CAMEL)), 0.7F);
    }

    public @NotNull Identifier getTextureLocation(CamelRenderState camelRenderState) {
        if (Objects.equals(CONFIG.camelSkin, "camel")) {
            camelTexturePath = "textures/entity/camel/camel.png";
        } else if (Objects.equals(CONFIG.camelSkin, "husk")) {
            camelTexturePath = "textures/entity/camel/camel_husk.png";
        } else {
            camelTexturePath = "textures/entity/camel/camel.png";
        }
        return Identifier.withDefaultNamespace(camelTexturePath);
    }

    public CamelRenderState createRenderState() {
        return new CamelRenderState();
    }

    public void extractRenderState(ClientCamel camel, CamelRenderState camelRenderState, float f) {
        super.extractRenderState(camel, camelRenderState, f);
        camelRenderState.saddle = camel.getItemBySlot(EquipmentSlot.SADDLE).copy();
        camelRenderState.isRidden = camel.isVehicle();
        /*camelRenderState.jumpCooldown = Math.max((float)camel.getJumpCooldown() - f, 0.0F);
        camelRenderState.sitAnimationState.copyFrom(camel.sitAnimationState);
        camelRenderState.sitPoseAnimationState.copyFrom(camel.sitPoseAnimationState);
        camelRenderState.sitUpAnimationState.copyFrom(camel.sitUpAnimationState);
        camelRenderState.idleAnimationState.copyFrom(camel.idleAnimationState);
        camelRenderState.dashAnimationState.copyFrom(camel.dashAnimationState);*/
    }
}
