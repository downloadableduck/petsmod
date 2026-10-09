package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import net.minecraft.client.render.entity.model.SnowmanEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSnowGolemRenderer extends PetRenderer {

    public ClientSnowGolemRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SnowmanEntityModel(), 0.5F);
    }

    protected void preRenderCallback(ClientSnowGolem snowGolem, float f) {
        super.scale(snowGolem, f);
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientSnowGolem livingEntityRenderState = (ClientSnowGolem) __e;
        return new Identifier("minecraft", "textures/entity/snow_golem.png");
    }
}