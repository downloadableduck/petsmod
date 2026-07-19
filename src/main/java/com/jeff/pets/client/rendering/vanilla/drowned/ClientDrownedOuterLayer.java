package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.entity.layer.EntityRenderLayerParent;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientDrownedOuterLayer extends EntityRenderLayer<ClientDrowned, ClientDrownedModel> {

    private final ClientDrownedModel drownedModel;

    public ClientDrownedOuterLayer(EntityRenderLayerParent<@NotNull ClientDrowned, ClientDrownedModel> renderLayerParent, net.minecraft.client.render.entity.EntityRenderDispatcher context) {
        super(renderLayerParent);
        this.drownedModel = new ClientDrownedModel(0.25F, 0.0F, 64, 64);
    }

    @Override
    public void render(ClientDrowned zombieEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (!zombieEntity.isInvisible()) {
            (this.getModel()).m_64619982(this.drownedModel);
            this.drownedModel.prepare(zombieEntity, f, g, h);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.bindTexture(new Identifier("textures/entity/zombie/drowned_outer_layer.png"));
            this.drownedModel.render(zombieEntity, f, g, i, j, k, l);
        }
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
