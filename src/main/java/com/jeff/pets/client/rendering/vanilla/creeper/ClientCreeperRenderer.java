//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.creeper;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;

import java.util.Objects;

public class ClientCreeperRenderer extends PetRenderer<ClientCreeper> {
    public ClientCreeperRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new CreeperModel(), 0.75F);
        this.addLayer(new ClientCreeperChargeLayer(this));
    }

    public @NotNull String getTexture(Entity entity) {
        ClientCreeper livingEntityRenderState = (ClientCreeper)entity;
        return "/mob/creeper/creeper.png";
    }

    public void renderModel(Mob entity, float f, float g, float h, float i, float j, float k) {
        ClientCreeper creeper = (ClientCreeper)entity;
        creeper.isPowered = Objects.equals(Central.CONFIG.creeperSkin, "charged");
    }
}
