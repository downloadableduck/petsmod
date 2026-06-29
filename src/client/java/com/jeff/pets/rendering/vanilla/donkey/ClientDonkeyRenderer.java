package com.jeff.pets.rendering.vanilla.donkey;

import com.jeff.pets.mob.vanilla.passive.ClientDonkey;
import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.rendering.vanilla.horse.ClientHorseModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientDonkeyRenderer extends PetRenderer<@NotNull ClientDonkey, @NotNull ClientHorseModel<ClientDonkey>> {
    public static ModelLayerLocation DONKEY_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientdonkey"), "main");

    public ClientDonkeyRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientHorseModel<>(context.bakeLayer(ModelLayers.DONKEY)), 0.5f);
    }

    public static LayerDefinition createBodyLayer() {
        return DonkeyModel.createBodyLayer();
    }

    public @NotNull ResourceLocation getTextureLocation(ClientDonkey donkeyRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/horse/donkey.png");
    }

    @Override
    protected void scale(ClientDonkey state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }
}
