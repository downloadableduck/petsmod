//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.enderman;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;

public class ClientEndermanRenderer extends PetRenderer<ClientEnderman> {
    public ClientEndermanRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new BipedEntityModel(), 0.5F);
        this.addLayer(new EndermanEyesLayer(this));
    }

    public @NotNull String getTexture(Entity entity) {
        ClientEnderman enderman = (ClientEnderman)entity;
        return "textures/mob/enderman/enderman.png";
    }
}
