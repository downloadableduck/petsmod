package com.jeff.pets.vanilla.mooshroom;

import com.jeff.pets.vanilla.passive.ClientMooshroom;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class ClientMooshroomRenderer extends MobRenderer<@NotNull ClientMooshroom, @NotNull LivingEntityRenderState, @NotNull ClientMooshroomModel> {
    public static final ModelLayerLocation MOOSHROOM_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientmooshroom"), "main");

    String mooshroomTexturePath;

    public ClientMooshroomRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientMooshroomModel(context.bakeLayer(ModelLayers.COW)), 0.7F);
    }

    public @NotNull Identifier getTextureLocation(LivingEntityRenderState cowRenderState) {
        if (Objects.equals(CONFIG.mooshroomSkin, "red")) {
            mooshroomTexturePath = "textures/entity/cow/red_mooshroom.png";
        } else if (Objects.equals(CONFIG.mooshroomSkin, "brown")) {
            mooshroomTexturePath = "textures/entity/cow/brown_mooshroom.png";
        } else {
            mooshroomTexturePath = "textures/entity/cow/red_mooshroom.png";
        }
        return Identifier.withDefaultNamespace(mooshroomTexturePath);
    }

    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

}
