//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.custom.first.racoon;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.custom.first.Racoon;

import java.util.Objects;

public class RacoonRenderer extends PetRenderer<@NotNull Racoon> {
    public RacoonRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new RacoonModel(), 0.75F);
    }

    protected void applyScale(LivingEntity entity, float f) {
        Racoon livingEntityRenderState = (Racoon)entity;
        if (Central.CONFIG.isBaby && !livingEntityRenderState.isServerEntity()) {
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
        }

    }

    public @NotNull String getTexture(Entity entity) {
        Racoon state = (Racoon)entity;
        String racoonTexturePath;
        if (!state.isServerEntity()) {
            if (Objects.equals(Central.CONFIG.racoonSkin, "normal")) {
                racoonTexturePath = "textures/mob/racoon/racoon.png";
            } else if (Objects.equals(Central.CONFIG.racoonSkin, "albino")) {
                racoonTexturePath = "textures/mob/racoon/albino.png";
            } else {
                racoonTexturePath = "textures/mob/racoon/racoon.png";
            }
        } else {
            racoonTexturePath = "textures/mob/racoon/racoon.png";
        }

        return racoonTexturePath;
    }

    public void renderModel(LivingEntity entity, float g, float f, float u, float h, float m, float i) {
        Racoon racoon = (Racoon)entity;
        racoon.setServerEntity(racoon.getDataTracker().getByte(20) == 1);
    }
}
