package com.jeff.pets.client.rendering.vanilla.zombie;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombie;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieRenderer extends PetRenderer<@NotNull ClientZombie, @NotNull ClientZombieModel<ClientZombie>> {

    public ClientZombieRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientZombieModel<>(), 0.75f);
    }

    @Override
    protected void scale(@NotNull ClientZombie livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientZombie livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/zombie/zombie.png");
    }
}
