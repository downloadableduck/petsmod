package com.jeff.pets.client.rendering.vanilla.cow;

import com.jeff.pets.client.rendering.PetRenderer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowRenderer extends PetRenderer {

    public ClientCowRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientCowModel(), 0.7F);
    }

    public @NotNull String getTexture(Entity LivingEntityRenderState) {
        return ("textures/mob/cow/cow.png");
    }

    @Override
    protected void applyScale(MobEntity state, float f) {
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }
}
