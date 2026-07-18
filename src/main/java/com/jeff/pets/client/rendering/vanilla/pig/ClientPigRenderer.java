package com.jeff.pets.client.rendering.vanilla.pig;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientPig;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigRenderer extends PetRenderer<@NotNull ClientPig, @NotNull ClientPigModel> {
    public String pigTexturePath;

    public ClientPigRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientPigModel(), 0.7F);
    }

    @Override
    protected void scale(@NotNull ClientPig livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    public @NotNull Identifier getTexture(ClientPig pigRenderState) {
        return new Identifier("minecraft", "textures/entity/pig/pig.png");
    }
}
