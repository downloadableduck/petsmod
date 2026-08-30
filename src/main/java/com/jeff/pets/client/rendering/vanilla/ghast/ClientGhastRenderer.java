package com.jeff.pets.client.rendering.vanilla.ghast;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGhast;
import net.minecraft.client.render.model.entity.GhastModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientGhastRenderer extends PetRenderer {

    public ClientGhastRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new GhastModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(Entity livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/ghast/ghast.png");
    }

    @Override
    public void applyScale(LivingEntity ghast, float f) {
        net.minecraft.client.render.platform.GlStateManager.scalef(4.5F, 4.5F, 4.5F);
    }
}
