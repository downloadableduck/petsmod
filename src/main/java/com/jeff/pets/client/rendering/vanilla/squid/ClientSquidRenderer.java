package com.jeff.pets.client.rendering.vanilla.squid;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSquid;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.model.SquidEntityModel;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSquidRenderer extends PetRenderer<@NotNull ClientSquid, @NotNull SquidEntityModel<ClientSquid>> {
    String squidTexturePath;

    public ClientSquidRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new SquidEntityModel<>(), 0.7F);
    }

    @Override
    public @NotNull Identifier getTexture(ClientSquid squidRenderState) {
        squidTexturePath = "textures/entity/squid.png";
        return new Identifier("minecraft", squidTexturePath);
    }

    @Override
    protected void scale(@NotNull ClientSquid livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    protected void setupTransforms(ClientSquid squidEntity, float f, float g, float h) {
        super.setupTransforms(squidEntity, f, g, h);
        float i = MathHelper.lerp(h, squidEntity.xBodyRotO, squidEntity.xBodyRot);
        float j = MathHelper.lerp(h, squidEntity.zBodyRotO, squidEntity.zBodyRot);
        GlStateManager.translatef(0.0F, 0.5F, 0.0F);
        GlStateManager.rotatef(180.0F - g, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(i, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotatef(j, 0.0F, 1.0F, 0.0F);
        GlStateManager.translatef(0.0F, -1.2F, 0.0F);
    }

    protected float getAnimationProgress(SquidEntity squidEntity, float f) {
        return MathHelper.lerp(f, squidEntity.field_6900, squidEntity.field_6904);
    }
}
