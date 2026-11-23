//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.zombie;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.hostile.ClientZombie;

public class ClientZombieRenderer extends PetRenderer<ClientZombie> {
    public ClientZombieRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientZombieModel(), 0.75F);
    }

    protected void applyScale(Mob entity, float f) {
        ClientZombie livingEntityRenderState = (ClientZombie)entity;
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
        }

    }

    public @NotNull String getTexture(Entity entity) {
        ClientZombie livingEntityRenderState = (ClientZombie)entity;
        return "/mob/zombie/zombie.png";
    }
}
