package com.jeff.pets.client.rendering.vanilla.bat;

import com.jeff.pets.client.rendering.PetRenderer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.NotNull;

public class ClientBatRenderer extends PetRenderer {

    public ClientBatRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientBatModel(), 0.25F);
    }

    @Override
    protected void applyScale(MobEntity bat, float f) {
        super.applyScale(bat, f);
        com.jeff.pets.compat.GlStateManager.scalef(0.35F, 0.35F, 0.35F);
    }

    @Override
    public @NotNull String getTexture(Entity batRenderState) {
        return ("textures/mob/bat.png");
    }
}
