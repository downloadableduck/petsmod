//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.ghast;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.model.GhastEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;

public class ClientGhastRenderer extends PetRenderer {
    public ClientGhastRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new GhastEntityModel(), 0.75F);
    }

    public @NotNull String getTexture(Entity livingEntityRenderState) {
        return "textures/mob/ghast/ghast.png";
    }

    public void applyScale(LivingEntity ghast, float f) {
        GlStateManager.scalef(4.5F, 4.5F, 4.5F);
    }
}
