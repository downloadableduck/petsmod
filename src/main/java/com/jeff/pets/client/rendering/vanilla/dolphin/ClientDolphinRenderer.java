package com.jeff.pets.client.rendering.vanilla.dolphin;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientDolphin;
import net.minecraft.client.render.model.entity.DolphinModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDolphinRenderer extends PetRenderer<@NotNull ClientDolphin, @NotNull DolphinModel<ClientDolphin>> {

    public ClientDolphinRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new DolphinModel(), 0.7f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientDolphin dolphinRenderState) {
        return new Identifier("minecraft", "textures/entity/dolphin.png");
    }

    @Override
    protected void applyScale(ClientDolphin state, float i) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }
}
