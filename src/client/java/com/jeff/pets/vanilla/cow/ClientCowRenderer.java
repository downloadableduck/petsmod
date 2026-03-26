package com.jeff.pets.vanilla.cow;

import com.jeff.pets.vanilla.passive.ClientCow;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CowRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Pet.CONFIG;

public class ClientCowRenderer extends MobRenderer<@NotNull ClientCow, @NotNull CowRenderState, @NotNull CowModel> {
    public static ModelLayerLocation COW_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientcow"), "main");
    String cowTexturePath;

    public ClientCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel(context.bakeLayer(ModelLayers.COW)), 0.7F);
    }

    public @NotNull Identifier getTextureLocation(CowRenderState cowRenderState) {
        switch (CONFIG.cowSkin) {
            case "temperate" -> cowTexturePath = "textures/entity/cow/temperate_cow.png";
            case "warm" -> cowTexturePath = "textures/entity/cow/warm_cow.png";
            case "cold" -> cowTexturePath = "textures/entity/cow/cold_cow.png";
            case null, default -> {
                cowTexturePath = "textures/entity/cow/temperate_cow.png";
            }
        }
        return Identifier.withDefaultNamespace(cowTexturePath);
    }

    public CowRenderState createRenderState() {
        return new CowRenderState();
    }

}
