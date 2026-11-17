//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.creeper;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;

import java.util.Objects;

public class ClientCreeperRenderer extends PetRenderer<ClientCreeper> {
    public ClientCreeperRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new CreeperEntityModel(), 0.75F);
        this.addLayer(new ClientCreeperChargeLayer(this));
    }

    public @NotNull String getTexture(Entity entity) {
        ClientCreeper livingEntityRenderState = (ClientCreeper)entity;
        return "textures/mob/creeper/creeper.png";
    }

    public void renderModel(LivingEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientCreeper creeper = (ClientCreeper)entity;
        creeper.isPowered = Objects.equals(Central.CONFIG.creeperSkin, "charged");
    }
}
