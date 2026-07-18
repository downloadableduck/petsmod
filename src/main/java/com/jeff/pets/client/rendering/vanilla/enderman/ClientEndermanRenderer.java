package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import net.minecraft.client.render.entity.feature.EndermanEyesFeatureRenderer;
import net.minecraft.client.render.entity.model.EndermanEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientEndermanRenderer extends PetRenderer<@NotNull ClientEnderman, @NotNull EndermanEntityModel<ClientEnderman>> {

    public ClientEndermanRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new EndermanEntityModel<>(0), 0.5f);
        this.addFeature(new EndermanEyesFeatureRenderer<>(this));
    }

    @Override
    public @NotNull Identifier getTexture(ClientEnderman enderman) {
        return new Identifier("minecraft", "textures/entity/enderman/enderman.png");
    }
}
