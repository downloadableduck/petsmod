package com.jeff.pets.client.rendering.vanilla.enderdragon;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;


public class ClientEnderDragonRenderer extends PetRenderer<@NotNull ClientEnderDragon, ClientEnderDragonModel> {

    public ClientEnderDragonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context) {
        super(context, new ClientEnderDragonModel(0), 0.75f);
    }

    @Override
    protected void applyScale(@NotNull ClientEnderDragon livingEntityRenderState, float f) {
        super.applyScale(livingEntityRenderState, f);
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(0.25f, 0.25f, 0.25f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientEnderDragon livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/enderdragon/dragon.png");
    }
}
