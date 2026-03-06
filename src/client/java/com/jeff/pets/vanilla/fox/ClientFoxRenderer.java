package com.jeff.pets.vanilla.fox;

import com.jeff.pets.vanilla.neutral.ClientFox;
import net.minecraft.client.model.animal.fox.FoxModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.FoxRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class ClientFoxRenderer extends MobRenderer<@NotNull ClientFox, @NotNull FoxRenderState, @NotNull FoxModel> {
    public static final ModelLayerLocation FOX_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientfox"), "main");
    public String foxTexturePath;

    public ClientFoxRenderer(EntityRendererProvider.Context context) {
        super(context, new FoxModel(context.bakeLayer(ModelLayers.FOX)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(FoxRenderState livingEntityRenderState) {
        if (Objects.equals(CONFIG.foxSkin, "red")) {
            foxTexturePath = "textures/entity/fox/fox.png";
        } else if (Objects.equals(CONFIG.foxSkin, "snow")) {
            foxTexturePath = "textures/entity/fox/snow_fox.png";
        } else {
            foxTexturePath = "textures/entity/fox/fox.png";
        }
        return Identifier.withDefaultNamespace(foxTexturePath);
    }

    @Override
    public FoxRenderState createRenderState() {
        return new FoxRenderState();
    }
}
