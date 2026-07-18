package com.jeff.pets.client.rendering.vanilla.cow;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCow;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowRenderer extends PetRenderer<@NotNull ClientCow, @NotNull ClientCowModel<ClientCow>> {

    public ClientCowRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientCowModel<>(), 0.7F);
    }

    public @NotNull Identifier getTexture(ClientCow LivingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/cow/cow.png");
    }

    @Override
    protected void scale(ClientCow state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }
}
