package com.jeff.pets.client.rendering.vanilla.squid;

import com.jeff.pets.client.Math2;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSquid;
import com.jeff.pets.compat.GlStateManager;
import net.minecraft.client.render.model.entity.SquidModel;
import net.minecraft.client.resource.Identifier;
import net.minecraft.util.math.MathHelper;

import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSquidRenderer extends PetRenderer<ClientSquid> {
    String squidTexturePath;

    public ClientSquidRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SquidModel(), 0.7F);
    }

    @Override
    public @NotNull Identifier getTextureLocation(net.minecraft.entity.Entity entity) {
        ClientSquid squidRenderState = (ClientSquid) entity;
        squidTexturePath = "textures/entity/squid.png";
        return new Identifier("minecraft", squidTexturePath);
    }

    @Override
    protected void applyScale(net.minecraft.entity.living.LivingEntity entity, float f) {
        ClientSquid livingEntityRenderState = (ClientSquid) entity;
        if (CONFIG.isBaby) {
            GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    protected void applyRotation(net.minecraft.entity.living.LivingEntity entity, float f, float g, float h) {
        ClientSquid squidEntity = (ClientSquid) entity;
        super.applyRotation(squidEntity, f, g, h);
        float i = (float) Math2.lerp(h, squidEntity.xBodyRotO, squidEntity.xBodyRot);
        float j = (float) Math2.lerp(h, squidEntity.zBodyRotO, squidEntity.zBodyRot);
        GlStateManager.translatef(0.0F, 0.5F, 0.0F);
        GlStateManager.rotatef(180.0F - g, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(i, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotatef(j, 0.0F, 1.0F, 0.0F);
        GlStateManager.translatef(0.0F, -1.2F, 0.0F);
    }
}