package com.jeff.pets.client.rendering.vanilla.enderdragon;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import net.minecraft.client.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;


public class ClientEnderDragonRenderer extends PetRenderer<ClientEnderDragon> {

    public ClientEnderDragonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientEnderDragonModel(0), 0.75f);
    }

    @Override
    protected void applyScale(net.minecraft.entity.living.LivingEntity entity, float f) {
        ClientEnderDragon livingEntityRenderState = (ClientEnderDragon) entity;
        super.applyScale(livingEntityRenderState, f);
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.25f, 0.25f, 0.25f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(net.minecraft.entity.Entity entity) {
        ClientEnderDragon livingEntityRenderState = (ClientEnderDragon) entity;
        return new Identifier("minecraft", "textures/entity/enderdragon/dragon.png");
    }
}
