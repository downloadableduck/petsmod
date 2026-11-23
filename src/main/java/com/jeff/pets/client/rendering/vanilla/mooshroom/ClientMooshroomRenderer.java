//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.mooshroom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;

public class ClientMooshroomRenderer extends PetRenderer<ClientMooshroom> {
    String mooshroomTexturePath;

    public ClientMooshroomRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientCowModel(), 0.7F);
        this.addLayer(new ClientMushroomCowMushroomLayer(this));
    }

    protected void applyScale(Mob entity, float f) {
        ClientMooshroom state = (ClientMooshroom)entity;
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
        }

    }

    public @NotNull String getTexture(Entity entity) {
        ClientMooshroom cowRenderState = (ClientMooshroom)entity;
        this.mooshroomTexturePath = "/mob/cow/mooshroom.png";
        return this.mooshroomTexturePath;
    }
}
