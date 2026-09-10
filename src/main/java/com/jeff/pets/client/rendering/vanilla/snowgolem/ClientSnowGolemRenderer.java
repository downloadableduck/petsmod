package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import net.minecraft.client.render.entity.model.SnowmanEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSnowGolemRenderer extends PetRenderer<@NotNull ClientSnowGolem> {

    public ClientSnowGolemRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SnowmanEntityModel(), 0.5F);
        this.addFeature(new ClientSnowGolemHeadLayer(this));
    }

    @Override
    protected void scale(ClientSnowGolem snowGolem, float f) {
        super.scale(snowGolem, f);
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientSnowGolem livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/snow_golem.png");
    }
}