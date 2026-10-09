package com.jeff.pets.client.rendering.vanilla.squid;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSquid;
import net.minecraft.client.render.entity.model.SquidEntityModel;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSquidRenderer extends PetRenderer {
    String squidTexturePath;

    public ClientSquidRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SquidEntityModel(), 0.7F);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientSquid squidRenderState = (ClientSquid) __e;
        squidTexturePath = "textures/entity/squid.png";
        return new Identifier("minecraft", squidTexturePath);
    }

    protected void preRenderCallback(@NotNull ClientSquid livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }

    protected void setLivingAnimations(ClientSquid squidEntity, float f, float g, float h) {
        float i = squidEntity.xBodyRotO + (squidEntity.xBodyRot - squidEntity.xBodyRotO) * h;
        float j = squidEntity.zBodyRotO + (squidEntity.zBodyRot - squidEntity.zBodyRotO) * h;
        GL11.glTranslatef(0.0F, 0.5F, 0.0F);
        GL11.glRotatef(180.0F - g, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(i, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, -1.2F, 0.0F);
    }
}