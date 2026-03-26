package com.jeff.pets.custom.duck;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.custom.Duck;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class DuckRenderer extends MobRenderer<@NotNull Duck, @NotNull DuckRenderState, @NotNull DuckModel> {
    public String duckTexturePath;

    public DuckRenderer(final EntityRendererProvider.Context context) {
        super(context, new DuckModel(context.bakeLayer(DuckModel.LAYER_LOCATION)), 0.3F);
    }

    public DuckRenderState createRenderState() {
        return new DuckRenderState();
    }

    @Override
    public void extractRenderState(final Duck entity, final DuckRenderState state, final float partialTicks) {
        state.isServerEntity = entity.isServerEntity();
        state.duckSpecies = entity.getEntityData().get(Duck.DUCK_SKIN);
        state.flap = Mth.lerp(partialTicks, entity.oFlap, entity.flap);
        state.flapSpeed = Mth.lerp(partialTicks, entity.oFlapSpeed, entity.flapSpeed);
        super.extractRenderState(entity, state, partialTicks);
    }

    @Override
    public @NotNull Identifier getTextureLocation(final DuckRenderState state) {
        if (!state.isServerEntity) {
            if (Objects.equals(CONFIG.duckSkin, "pekin")) {
                duckTexturePath = "textures/entity/duck/pekin.png";
            } else if (Objects.equals(CONFIG.duckSkin, "mallard")) {
                duckTexturePath = "textures/entity/duck/mallard_male.png";
            } else if (Objects.equals(CONFIG.duckSkin, "rubber")) {
                duckTexturePath = "textures/entity/duck/rubber.png";
            }
            return Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, duckTexturePath);
        } else {
            if (state.duckSpecies == 0) {
                return Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, "textures/entity/duck/mallard_male.png");
            } else if (state.duckSpecies == 1) {
                return Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, "textures/entity/duck/pekin.png");
            } else if (state.duckSpecies == 2) {
                return Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, "textures/entity/duck/rubber.png");
            } else {
                return Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, "textures/entity/duck/yeahitdidntwork");
            }
        }
        //we use textures/entity/mallard_male.png because nothing else worked because this code fucking sucks
    }
}