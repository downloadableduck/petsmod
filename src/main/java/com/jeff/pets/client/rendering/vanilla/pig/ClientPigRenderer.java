package com.jeff.pets.client.rendering.vanilla.pig;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientPig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigRenderer extends PetRenderer {
    public String pigTexturePath;

    public ClientPigRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientPigModel(), 0.7F);
    }

    @Override
    protected void applyScale(LivingEntity livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    public @NotNull Identifier getTextureLocation(Entity pigRenderState) {
        return new Identifier("minecraft", "textures/entity/pig/pig.png");
    }
}
