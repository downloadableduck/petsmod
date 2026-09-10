package com.jeff.pets.client.rendering.aprilfools.nerdcreeper;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.aprilfools.NerdCreeper;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class NerdCreeperRenderer extends PetRenderer<@NotNull NerdCreeper, @NotNull CreeperRenderState, @NotNull CreeperModel> {

    public static final ModelLayerLocation NERD_CREEPER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("nerdcreeper"), "main");

    public NerdCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperModel(context.bakeLayer(ModelLayers.CREEPER)), 0.75f);
        this.addLayer(new NerdCreeperNerdLayer(this, context));
        this.addLayer(new CreeperPowerLayer(this, EntityModelSet.vanilla()));
    }

    @Override
    public @NotNull Identifier getTextureLocation(CreeperRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/creeper/creeper.png");
    }

    @Override
    public CreeperRenderState createRenderState() {
        return new CreeperRenderState();
    }

    @Override
    public void extractRenderState(NerdCreeper nerdCreeper, CreeperRenderState state, float f) {
        super.extractRenderState(nerdCreeper, state, f);
        if (Objects.equals(CONFIG.creeperSkin, "charged")) {
            state.isPowered = true;
        }
    }
}
