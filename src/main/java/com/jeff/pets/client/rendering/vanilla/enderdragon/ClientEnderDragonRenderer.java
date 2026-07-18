package com.jeff.pets.client.rendering.vanilla.enderdragon;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;


public class ClientEnderDragonRenderer extends PetRenderer<@NotNull ClientEnderDragon, ClientEnderDragonModel> {

    public ClientEnderDragonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientEnderDragonModel(0), 0.75f);
    }

    @Override
    protected void scale(@NotNull ClientEnderDragon livingEntityRenderState, float f) {
        super.scale(livingEntityRenderState, f);
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.25f, 0.25f, 0.25f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientEnderDragon livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/enderdragon/dragon.png");
    }
}
