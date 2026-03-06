package com.jeff.pets.vanilla.hoglin;

import com.jeff.pets.vanilla.hostile.ClientHoglin;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.hoglin.HoglinModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.HoglinRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class ClientHoglinRenderer extends MobRenderer<@NotNull ClientHoglin, @NotNull HoglinRenderState, @NotNull HoglinModel> {

    public static final ModelLayerLocation HOGLIN_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clienthoglin"), "main");

    public ClientHoglinRenderer(EntityRendererProvider.Context context) {
        super(context, new HoglinModel(context.bakeLayer(ModelLayers.HOGLIN)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(HoglinRenderState livingEntityRenderState) {
        String hoglinTexturePath;
        if (Objects.equals(CONFIG.hoglinSkin, "hoglin")) {
            hoglinTexturePath = "textures/entity/hoglin/hoglin.png";
        } else if (Objects.equals(CONFIG.hoglinSkin, "zoglin")) {
            hoglinTexturePath = "textures/entity/hoglin/zoglin.png";
        } else {
            hoglinTexturePath = "textures/entity/hoglin/hoglin.png";
        }
        return Identifier.withDefaultNamespace(hoglinTexturePath);
    }

    @Override
    public HoglinRenderState createRenderState() {
        return new HoglinRenderState();
    }
}
