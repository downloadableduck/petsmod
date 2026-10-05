package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resource.Identifier;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMooshroomRenderer extends PetRenderer<ClientMooshroom> {

    String mooshroomTexturePath;

    public ClientMooshroomRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientCowModel(), 0.7F);
        this.addLayer(new ClientMushroomCowMushroomLayer(this, this.getBlockRenderer()));
    }

    @Override
    protected void applyScale(net.minecraft.entity.living.LivingEntity entity, float f) {
        ClientMooshroom state = (ClientMooshroom) entity;
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(net.minecraft.entity.Entity entity) {
        ClientMooshroom cowRenderState = (ClientMooshroom) entity;
            mooshroomTexturePath = "textures/entity/cow/mooshroom.png";
        return new Identifier("minecraft", mooshroomTexturePath);
    }
}
