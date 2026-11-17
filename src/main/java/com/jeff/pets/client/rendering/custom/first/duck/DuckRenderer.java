//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.custom.first.duck;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.custom.first.Duck;

import java.util.Objects;

public class DuckRenderer extends PetRenderer<@NotNull Duck> {
    public DuckRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new DuckModel(), 0.3F);
    }

    protected void applyScale(LivingEntity entity, float f) {
        if (Central.CONFIG != null && Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.6F, 0.6F, 0.6F);
        }

    }

    public @NotNull String getTexture(Entity entity) {
        String skin = Central.CONFIG != null ? Central.CONFIG.duckSkin : "mallard";
        if (Objects.equals(skin, "pekin")) {
            return "/mob/duck/pekin.png";
        } else if (Objects.equals(skin, "mallard")) {
            return "/mob/duck/mallard_male.png";
        } else if (Objects.equals(skin, "rubber")) {
            return "/mob/duck/rubber.png";
        } else {
            return Objects.equals(skin, "bronze") ? "/mob/duck/bronze.png" : "/mob/duck/mallard_male.png";
        }
    }
}
