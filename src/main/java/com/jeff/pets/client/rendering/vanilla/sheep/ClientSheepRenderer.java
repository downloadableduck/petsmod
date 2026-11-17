//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.sheep;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientSheep;

public class ClientSheepRenderer extends PetRenderer<ClientSheep> {
    public ClientSheepRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientSheepModel(), 0.7F);
        this.addLayer(new ClientSheepWoolLayer(this));
    }

    protected void applyScale(LivingEntity entity, float f) {
        ClientSheep livingEntityRenderState = (ClientSheep)entity;
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
        }

    }

    public @NotNull String getTexture(Entity entity) {
        ClientSheep livingEntityRenderState = (ClientSheep)entity;
        return "textures/mob/sheep/sheep.png";
    }

    public void bindTexture(String string) {
        super.bindTexture(string);
    }
}
