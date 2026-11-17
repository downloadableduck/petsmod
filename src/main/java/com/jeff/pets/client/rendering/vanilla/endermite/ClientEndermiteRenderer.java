//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.endermite;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEndermite;

public class ClientEndermiteRenderer extends PetRenderer<ClientEndermite> {
    public ClientEndermiteRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new EndermiteModel(), 0.75F);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientEndermite livingEntityRenderState = (ClientEndermite)entity;
        return "textures/mob/endermite.png";
    }
}
