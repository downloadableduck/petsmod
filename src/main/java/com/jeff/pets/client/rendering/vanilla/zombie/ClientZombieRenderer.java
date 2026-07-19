package com.jeff.pets.client.rendering.vanilla.zombie;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombie;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieRenderer extends PetRenderer<@NotNull ClientZombie, @NotNull ClientZombieModel<ClientZombie>> {

    public ClientZombieRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context) {
        super(context, new ClientZombieModel<>(), 0.75f);
    }

    @Override
    protected void applyScale(@NotNull ClientZombie livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientZombie livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/zombie/zombie.png");
    }
}
