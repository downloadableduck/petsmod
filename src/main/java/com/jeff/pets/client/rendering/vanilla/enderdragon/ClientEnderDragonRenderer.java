//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.enderdragon;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;

public class ClientEnderDragonRenderer extends PetRenderer<ClientEnderDragon> {
    public ClientEnderDragonRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientEnderDragonModel(0.0F), 0.75F);
    }

    protected void applyScale(LivingEntity entity, float f) {
        ClientEnderDragon livingEntityRenderState = (ClientEnderDragon)entity;
        super.applyScale(livingEntityRenderState, f);
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.25F, 0.25F, 0.25F);
        }

    }

    public @NotNull String getTexture(Entity entity) {
        ClientEnderDragon livingEntityRenderState = (ClientEnderDragon)entity;
        return "textures/mob/enderdragon/dragon.png";
    }
}
