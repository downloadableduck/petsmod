//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.cow;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;

public class ClientCowRenderer extends PetRenderer {
    public ClientCowRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientCowModel(), 0.7F);
    }

    public @NotNull String getTexture(Entity LivingEntityRenderState) {
        return "textures/mob/cow/cow.png";
    }

    protected void applyScale(LivingEntity state, float f) {
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
        }

    }
}
