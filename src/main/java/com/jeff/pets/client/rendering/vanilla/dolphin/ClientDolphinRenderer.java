package com.jeff.pets.client.rendering.vanilla.dolphin;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientDolphin;
import net.minecraft.client.render.entity.model.DolphinEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDolphinRenderer extends PetRenderer<@NotNull ClientDolphin, @NotNull DolphinEntityModel<ClientDolphin>> {

    public ClientDolphinRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new DolphinEntityModel(), 0.7f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientDolphin dolphinRenderState) {
        return new Identifier("minecraft", "textures/entity/dolphin.png");
    }

    @Override
    protected void scale(ClientDolphin state, float i) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }
}
