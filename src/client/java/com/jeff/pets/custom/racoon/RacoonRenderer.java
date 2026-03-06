package com.jeff.pets.custom.racoon;

import com.jeff.pets.custom.Racoon;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;
import static com.jeff.pets.PetsInitializer.MOD_ID;

public class RacoonRenderer extends MobRenderer<@NotNull Racoon, @NotNull LivingEntityRenderState, @NotNull RacoonModel> {
    public static final ModelLayerLocation RACOON_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(MOD_ID, "racoon"), "main");

    public RacoonRenderer(EntityRendererProvider.Context context) {
        super(context, new RacoonModel(context.bakeLayer(RACOON_LOCATION)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        String racoonTexturePath;
        if (Objects.equals(CONFIG.racoonSkin, "normal")) {
            racoonTexturePath = "textures/entity/racoon/racoon.png";
        } else if (Objects.equals(CONFIG.racoonSkin, "albino")) {
            racoonTexturePath = "textures/entity/racoon/albino.png";
        } else {
            racoonTexturePath = "textures/entity/racoon/racoon.png";
        }
        return Identifier.fromNamespaceAndPath(MOD_ID, racoonTexturePath);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
