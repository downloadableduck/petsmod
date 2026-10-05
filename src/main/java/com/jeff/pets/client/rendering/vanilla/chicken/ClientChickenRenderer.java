package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import net.minecraft.client.resource.Identifier;

import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenRenderer extends PetRenderer<ClientChicken> {

    public ClientChickenRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientChickenModel(), 0.3F);
    }

    @Override
    public @NotNull Identifier getTextureLocation(net.minecraft.entity.Entity entity) {
        ClientChicken livingEntityRenderState = (ClientChicken) entity;
        return new Identifier("minecraft", "textures/entity/chicken.png");
    }

    @Override
    protected void applyScale(net.minecraft.entity.living.LivingEntity entity, float f) {
        ClientChicken state = (ClientChicken) entity;
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }
}
