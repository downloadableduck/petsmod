//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.donkey;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseModel;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientDonkey;

public class ClientDonkeyRenderer extends PetRenderer<ClientDonkey> {
    public ClientDonkeyRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientHorseModel(0.0F), 0.5F);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientDonkey donkeyRenderState = (ClientDonkey)entity;
        return "/mob/horse/donkey.png";
    }

    protected void applyScale(Mob entity, float f) {
        ClientDonkey state = (ClientDonkey)entity;
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
        }

    }
}
