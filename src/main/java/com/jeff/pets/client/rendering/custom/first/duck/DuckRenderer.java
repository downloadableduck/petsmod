package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Duck;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class DuckRenderer extends PetRenderer<@NotNull Duck> {

    public DuckRenderer(final net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new DuckModel(), 0.3F);
    }

    @Override
    protected void applyScale(net.minecraft.entity.mob.MobEntity entity, float f) {
        if (CONFIG != null && CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.6f, 0.6f, 0.6f);
        }
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        String skin = CONFIG != null ? CONFIG.duckSkin : "mallard";
        if (Objects.equals(skin, "pekin")) {
            return "/mob/duck/pekin.png";
        } else if (Objects.equals(skin, "mallard")) {
            return "/mob/duck/mallard_male.png";
        } else if (Objects.equals(skin, "rubber")) {
            return "/mob/duck/rubber.png";
        } else if (Objects.equals(skin, "bronze")) {
            return "/mob/duck/bronze.png";
        }
        return "/mob/duck/mallard_male.png";
    }
}
