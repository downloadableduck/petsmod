package com.jeff.pets.client.rendering.vanilla.squid;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSquid;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.model.SquidEntityModel;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSquidRenderer extends PetRenderer<@NotNull ClientSquid> {
    String squidTexturePath;

    public ClientSquidRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SquidEntityModel(), 0.7F);
    }

    @Override
    public @NotNull Identifier getTexture(ClientSquid squidRenderState) {
        squidTexturePath = "textures/entity/squid.png";
        return new Identifier("minecraft", squidTexturePath);
    }

    @Override
    protected void scale(@NotNull ClientSquid livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }

    protected void method_5777(ClientSquid squidEntity, float f, float g, float h) {
        super.method_5777(squidEntity, f, g, h);
        float i = squidEntity.xBodyRotO + (squidEntity.xBodyRot - squidEntity.xBodyRotO) * h;
        float j = squidEntity.zBodyRotO + (squidEntity.zBodyRot - squidEntity.zBodyRotO) * h;
        GlStateManager.translate(0.0F, 0.5F, 0.0F);
        GlStateManager.rotate(180.0F - g, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(i, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(j, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0.0F, -1.2F, 0.0F);
    }
}