//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.squid;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.Math2;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientSquid;

public class ClientSquidRenderer extends PetRenderer<ClientSquid> {
    String squidTexturePath;

    public ClientSquidRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new SquidModel(), 0.7F);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientSquid squidRenderState = (ClientSquid)entity;
        this.squidTexturePath = "/mob/squid.png";
        return this.squidTexturePath;
    }

    protected void applyScale(Mob entity, float f) {
        ClientSquid livingEntityRenderState = (ClientSquid)entity;
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
        }

    }

    protected void applyHandSwingRotation(Mob entity, float f, float g, float h) {
        ClientSquid squidEntity = (ClientSquid)entity;
        super.applyHandSwingRotation(squidEntity, f, g, h);
        float i = Math2.lerp(h, squidEntity.xBodyRotO, squidEntity.xBodyRot);
        float j = Math2.lerp(h, squidEntity.zBodyRotO, squidEntity.zBodyRot);
        GlStateManager.translatef(0.0F, 0.5F, 0.0F);
        GlStateManager.rotatef(180.0F - g, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(i, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotatef(j, 0.0F, 1.0F, 0.0F);
        GlStateManager.translatef(0.0F, -1.2F, 0.0F);
    }
}
