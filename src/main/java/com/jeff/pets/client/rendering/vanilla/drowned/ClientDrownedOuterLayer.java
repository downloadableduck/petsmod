package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import net.minecraft.client.render.entity.DrownedRenderer;
import net.minecraft.client.render.entity.layer.DrownedOuterLayer;
import net.minecraft.client.render.model.entity.DrownedModel;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.living.mob.monster.DrownedEntity;
import net.minecraft.resource.Identifier;

public class ClientDrownedOuterLayer implements EntityRenderLayer<ClientDrowned> {

    private static final Identifier TEXTURE_LOCATION = new Identifier("textures/entity/zombie/drowned_outer_layer.png");
    private final ClientDrownedRenderer renderer;
    private final ClientDrownedModel model = new ClientDrownedModel(0.25F, 0.0F, 64, 64);

    public ClientDrownedOuterLayer(ClientDrownedRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void render(ClientDrowned drownedEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (!drownedEntity.isInvisible()) {
            this.model.copyPropertiesFrom(this.renderer.getModel());
            this.model.prepare(drownedEntity, f, g, h);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.renderer.bindTexture(TEXTURE_LOCATION);
            this.model.render(drownedEntity, f, g, i, j, k, l);
        }
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
