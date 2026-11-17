//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.silverfish;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSilverfish;

public class ClientSilverfishRenderer extends PetRenderer<ClientSilverfish> {
    public ClientSilverfishRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new BipedEntityModel(), 0.75F);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientSilverfish livingEntityRenderState = (ClientSilverfish)entity;
        return "textures/mob/silverfish.png";
    }
}
