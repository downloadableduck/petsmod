package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;

public class ClientDrownedOuterLayer {

    private final ClientDrownedRenderer renderer;
    private final ClientDrownedModel drownedModel;

    public ClientDrownedOuterLayer(ClientDrownedRenderer renderer) {
        this.renderer = renderer;
        this.drownedModel = new ClientDrownedModel(0.25F, 0.0F, 64, 64);
    }

    public void render(ClientDrowned zombieEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (!zombieEntity.isInvisible()) {
            this.drownedModel.setLivingAnimations(zombieEntity, f, g, h);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier("textures/entity/zombie/drowned_outer_layer.png"));
            this.drownedModel.render(zombieEntity, f, g, i, j, k, l);
        }
    }

    public boolean combineTextures() {
        return false;
    }
}