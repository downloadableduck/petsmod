//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.wither;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientWither;

import java.util.Objects;

public class ClientWitherRenderer extends PetRenderer<ClientWither> {
    public ClientWitherRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientWitherModel(0.0F), 0.75F);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientWither livingEntityRenderState = (ClientWither)entity;
        String witherTexturePath;
        if (Objects.equals(Central.CONFIG.witherSkin, "normal")) {
            witherTexturePath = "textures/mob/wither/wither.png";
        } else if (Objects.equals(Central.CONFIG.witherSkin, "invulnerable")) {
            witherTexturePath = "textures/mob/wither/wither_invulnerable.png";
        } else {
            witherTexturePath = "textures/mob/wither/wither.png";
        }

        return witherTexturePath;
    }
}
