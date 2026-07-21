package com.jeff.pets.client.rendering.vanilla.squid;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSquid;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.model.entity.SquidModel;
import net.minecraft.resource.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSquidRenderer extends PetRenderer<@NotNull ClientSquid, @NotNull SquidModel<ClientSquid>> {
    String squidTexturePath;

    public ClientSquidRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SquidModel<>(), 0.7F);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientSquid squidRenderState) {
        squidTexturePath = "textures/entity/squid.png";
        return new Identifier("minecraft", squidTexturePath);
    }

    @Override
    protected void applyScale(@NotNull ClientSquid livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    protected void applyRotation(ClientSquid squidEntity, float f, float g, float h) {
        super.applyRotation(squidEntity, f, g, h);
        float i = (float) MathHelper.m_4848186 /*lerp*/(h, squidEntity.xBodyRotO, squidEntity.xBodyRot);
        float j = (float) MathHelper.m_4848186 /*lerp*/(h, squidEntity.zBodyRotO, squidEntity.zBodyRot);
        GlStateManager.translatef(0.0F, 0.5F, 0.0F);
        GlStateManager.rotatef(180.0F - g, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(i, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotatef(j, 0.0F, 1.0F, 0.0F);
        GlStateManager.translatef(0.0F, -1.2F, 0.0F);
    }
}
