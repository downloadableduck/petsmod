package com.jeff.pets.rendering.aprilfools.smilingcreeper;

import com.jeff.pets.mob.aprilfools.SmilingCreeper;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Central.CONFIG;

public class SmilingCreeperRenderer extends PetRenderer<@NotNull SmilingCreeper, @NotNull CreeperRenderState, @NotNull CreeperModel> {

    public static final ModelLayerLocation SMILING_CREEPER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("smilingcreeper"), "main");

    public SmilingCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperModel(context.bakeLayer(ModelLayers.CREEPER)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(CreeperRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/creeper/smiling_creeper.png");
    }

    @Override
    public CreeperRenderState createRenderState() {
        return new CreeperRenderState();
    }

    @Override
    public void extractRenderState(SmilingCreeper smilingCreeper, CreeperRenderState state, float f) {
        super.extractRenderState(smilingCreeper, state, f);
        if (Objects.equals(CONFIG.creeperSkin, "charged")) {
            state.isPowered = true;
        }
    }
}
