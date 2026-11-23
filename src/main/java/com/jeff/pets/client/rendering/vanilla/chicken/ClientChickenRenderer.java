//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.chicken;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientChicken;

public class ClientChickenRenderer extends PetRenderer<ClientChicken> {
    public ClientChickenRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientChickenModel(), 0.3F);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientChicken livingEntityRenderState = (ClientChicken)entity;
        return "/mob/chicken.png";
    }

    protected void applyScale(Mob entity, float f) {
        ClientChicken state = (ClientChicken)entity;
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
        }

    }
}
