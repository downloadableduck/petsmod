//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.custom.first.penguin;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.custom.first.Penguin;

public class PenguinRenderer extends PetRenderer<@NotNull Penguin> {
    public PenguinRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new PenguinModel(), 0.5F);
    }

    public @NotNull String getTexture(Entity entity) {
        Penguin livingEntityRenderState = (Penguin)entity;
        return "textures/mob/penguin/penguin.png";
    }

    protected void applyScale(LivingEntity entity, float f) {
        Penguin livingEntityRenderState = (Penguin)entity;
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
        }

    }

    public void renderModel(LivingEntity entity, float f, float partialTicks, float h, float i, float j, float k) {
        Penguin penguin = (Penguin)entity;
    }
}
