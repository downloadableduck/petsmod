package com.jeff.duck;

import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import static com.jeff.duck.DuckPet.CONFIG;

@Config(name = "duckrenderer")
public class DuckRenderer extends MobRenderer<@NotNull Duck, @NotNull DuckRenderState, @NotNull DuckModel> {
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
            return Identifier.fromNamespaceAndPath(DuckInitializer.MOD_ID, CONFIG.duckTexturePath);
        } else {
            if (state.duckSpecies == 0) {
                return Identifier.fromNamespaceAndPath(DuckInitializer.MOD_ID, "textures/entity/mallard_male.png");
            } else if (state.duckSpecies == 1) {
                return Identifier.fromNamespaceAndPath(DuckInitializer.MOD_ID, "textures/entity/pekin.png");
            } else {
                return Identifier.fromNamespaceAndPath(DuckInitializer.MOD_ID, "textures/entity/yeahitdidntwork");
            }
        }
        //we use textures/entity/mallard_male.png because nothing else worked because this code fucking sucks
    }
}
