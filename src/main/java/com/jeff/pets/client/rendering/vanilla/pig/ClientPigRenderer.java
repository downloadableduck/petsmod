package com.jeff.pets.client.rendering.vanilla.pig;

import com.jeff.pets.client.rendering.PetRenderer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigRenderer extends PetRenderer {
    public String pigTexturePath;

    public ClientPigRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientPigModel(), 0.7F);
    }

    @Override
    protected void applyScale(MobEntity livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    public @NotNull String getTexture(Entity pigRenderState) {
        return ("textures/mob/pig/pig.png");
    }
}
