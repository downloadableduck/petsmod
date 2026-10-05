package com.jeff.pets.client.rendering.vanilla.zombie;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombie;
import net.minecraft.client.resource.Identifier;

import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieRenderer extends PetRenderer<ClientZombie> {

    public ClientZombieRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientZombieModel(), 0.75f);
    }

    @Override
    protected void applyScale(net.minecraft.entity.living.LivingEntity entity, float f) {
        ClientZombie livingEntityRenderState = (ClientZombie) entity;
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(net.minecraft.entity.Entity entity) {
        ClientZombie livingEntityRenderState = (ClientZombie) entity;
        return new Identifier("minecraft", "textures/entity/zombie/zombie.png");
    }
}
