//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.guardian;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGuardian;

public class ClientGuardianRenderer extends PetRenderer<ClientGuardian> {
    public ClientGuardianRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientGuardianModel(), 0.75F);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientGuardian livingEntityRenderState = (ClientGuardian)entity;
        return "textures/mob/guardian.png";
    }
}
