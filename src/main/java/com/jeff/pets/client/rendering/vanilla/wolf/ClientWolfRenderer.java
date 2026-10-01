package com.jeff.pets.client.rendering.vanilla.wolf;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWolfRenderer extends PetRenderer {

    public ClientWolfRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientWolfModel(), 0.75f);
    }

    @Override
    protected void applyScale(LivingEntity livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(Entity livingEntityRenderState) {
        String wolfTexturePath = "textures/entity/wolf/wolf.png";

        return new Identifier("minecraft", wolfTexturePath);
    }

    @Override
    public void renderModel(LivingEntity e, float f, float g, float h, float i, float j, float k) {
        ClientWolf wolf = (ClientWolf) e;
        super.renderModel(e, f, g, h, i, j, k);
        wolf.setSitting(wolf.isRiding());
    }
}
