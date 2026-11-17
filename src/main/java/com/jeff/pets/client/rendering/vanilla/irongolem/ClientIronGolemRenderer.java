//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.irongolem;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientIronGolem;

public class ClientIronGolemRenderer extends PetRenderer<ClientIronGolem> {
    public ClientIronGolemRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientIronGolemModel(), 0.75F);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientIronGolem livingEntityRenderState = (ClientIronGolem)entity;
        return "textures/mob/iron_golem.png";
    }
}
