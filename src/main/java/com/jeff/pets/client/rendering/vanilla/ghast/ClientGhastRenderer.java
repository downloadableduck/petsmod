//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.ghast;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.entity.MobRendererGhast;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.models.entity.StaticEntityModel;

public class ClientGhastRenderer extends PetRenderer {
    public ClientGhastRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new GhastModel(), 0.75F);
    }

    public @NotNull String getTexture(Entity livingEntityRenderState) {
        return "/mob/ghast/ghast.png";
    }

    public void applyScale(Mob ghast, float f) {
        GlStateManager.scalef(4.5F, 4.5F, 4.5F);
    }
}
