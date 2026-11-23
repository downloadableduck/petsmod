//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.cow;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;

public class ClientCowRenderer extends PetRenderer {
    public ClientCowRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientCowModel(), 0.7F);
    }

    public @NotNull String getTexture(Entity LivingEntityRenderState) {
        return "/mob/cow/cow.png";
    }

    protected void applyScale(Mob state, float f) {
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
        }

    }
}
