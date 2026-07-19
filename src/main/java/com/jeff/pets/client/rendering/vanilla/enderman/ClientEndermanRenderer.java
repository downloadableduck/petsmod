package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import net.minecraft.client.render.entity.layer.EndermanEyesLayer;
import net.minecraft.client.render.model.entity.EndermanModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientEndermanRenderer extends PetRenderer<@NotNull ClientEnderman, @NotNull EndermanModel<ClientEnderman>> {

    public ClientEndermanRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context) {
        super(context, new EndermanModel<>(0), 0.5f);
        this.addLayer(new EndermanEyesLayer<>(this));
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientEnderman enderman) {
        return new Identifier("minecraft", "textures/entity/enderman/enderman.png");
    }
}
