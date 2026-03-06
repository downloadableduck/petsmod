package com.jeff.pets.vanilla.strider;

import com.jeff.pets.vanilla.passive.ClientStrider;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.strider.StriderModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.StriderRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class ClientStriderRenderer extends MobRenderer<@NotNull ClientStrider, @NotNull StriderRenderState, @NotNull StriderModel> {
    public static ModelLayerLocation STRIDER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientstrider"), "main");

    public String striderTexturePath;

    public ClientStriderRenderer(EntityRendererProvider.Context context) {
        super(context, new StriderModel(context.bakeLayer(ModelLayers.STRIDER)), 0.5F);
    }

    @Override
    public StriderRenderState createRenderState() {
        return new StriderRenderState();
    }

    public @NotNull Identifier getTextureLocation(StriderRenderState striderRenderState) {
        if (Objects.equals(CONFIG.striderSkin, "warm")) {
            striderTexturePath = "textures/entity/strider/strider.png";
        } else if (Objects.equals(CONFIG.striderSkin, "cold")) {
            striderTexturePath = "textures/entity/strider/strider_cold.png";
        }
        return Identifier.withDefaultNamespace(striderTexturePath);

    }
}
