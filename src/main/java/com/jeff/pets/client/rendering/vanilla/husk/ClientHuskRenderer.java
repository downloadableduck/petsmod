package com.jeff.pets.client.rendering.vanilla.husk;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.zombie.ClientZombieModel;
import com.jeff.pets.mob.vanilla.hostile.ClientHusk;
import net.minecraft.entity.Entity;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHuskRenderer extends PetRenderer {

    public ClientHuskRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientZombieModel(), 0.75F);
    }

    @Override
    protected void applyScale(LivingEntity state, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(Entity livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/zombie/husk.png");
    }

    @Override
    public void applyRotation(LivingEntity e, float f, float g, float i) {
        ClientHusk husk = (ClientHusk) e;
        super.applyRotation(e, f, g, i);
        if (husk.isRiding()) {
            net.minecraft.client.render.platform.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
