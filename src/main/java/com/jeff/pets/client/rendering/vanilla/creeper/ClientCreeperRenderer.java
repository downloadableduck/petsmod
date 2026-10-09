package com.jeff.pets.client.rendering.vanilla.creeper;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.util.Identifier;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperRenderer extends PetRenderer {

    public ClientCreeperRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new CreeperEntityModel(), 0.75f);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientCreeper livingEntityRenderState = (ClientCreeper) __e;
        return new Identifier("minecraft", "textures/entity/creeper/creeper.png");
    }

    protected void renderModel(ClientCreeper creeper, float f, float g, float h, float i, float j, float k) {
        super.renderModel(creeper, f, g, h, i, j, k);
        creeper.isPowered = Objects.equals(CONFIG.creeperSkin, "charged");
    }
}