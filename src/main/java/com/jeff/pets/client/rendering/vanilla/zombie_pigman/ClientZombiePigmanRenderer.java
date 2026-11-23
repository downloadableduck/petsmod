//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientZombiePigman;

public class ClientZombiePigmanRenderer extends PetRenderer<ClientZombiePigman> {
    public ClientZombiePigmanRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientZombiePigmanModel(), 0.5F);
    }

    public String getTexture(Entity entity) {
        return "/mob/zombie_pigman.png";
    }
}
