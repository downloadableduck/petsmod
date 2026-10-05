package com.jeff.pets.client.rendering.custom.first.racoon;

import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Racoon;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.PetsInitializer.MOD_ID;
import static com.jeff.pets.client.Central.CONFIG;

public class RacoonRenderer extends PetRenderer<@NotNull Racoon> {

    public RacoonRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new RacoonModel(), 0.75f);
    }

    protected void applyScale(net.minecraft.entity.living.LivingEntity entity, float f) {
        Racoon livingEntityRenderState = (Racoon) entity;
        if ((CONFIG.isBaby && !livingEntityRenderState.isServerEntity()) || (livingEntityRenderState.isBaby() && livingEntityRenderState.isServerEntity())) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }


    public @NotNull Identifier getTextureLocation(net.minecraft.entity.Entity entity) {
        Racoon state = (Racoon) entity;
        String racoonTexturePath;
        if (!state.isServerEntity()) {
            if (Objects.equals(CONFIG.racoonSkin, "normal")) {
                racoonTexturePath = "textures/entity/racoon/racoon.png";
            } else if (Objects.equals(CONFIG.racoonSkin, "albino")) {
                racoonTexturePath = "textures/entity/racoon/albino.png";
            } else {
                racoonTexturePath = "textures/entity/racoon/racoon.png";
            }
        } else {
            racoonTexturePath = "textures/entity/racoon/racoon.png";
        }
        return new Identifier(MOD_ID, racoonTexturePath);
    }

    @Override
    public void renderModel(net.minecraft.entity.living.LivingEntity entity, float g, float f, float u, float h, float m, float i) {
        Racoon racoon = (Racoon) entity;
        super.renderModel(racoon, g, f, u, h, m, i);
        racoon.setServerEntity(racoon.getSyncedData().getByte(Racoon.IS_SERVER_ENTITY) == 1);
    }
}
