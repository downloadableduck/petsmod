//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.slime;

import com.jeff.pets.client.rendering.vanilla.squid.SquidModel;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.hostile.ClientSlime;

public class ClientSlimeRenderer extends PetRenderer<ClientSlime> {
    public ClientSlimeRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new SlimeModel(16), 0.75F);
        this.addLayer(new SlimeOuterLayer(this));
    }

    protected void applyScale(Mob entity, float f) {
        ClientSlime slimeRenderState = (ClientSlime)entity;
        byte slimeapplyScale;
        switch (Central.CONFIG.slimeSkin) {
            case "small":
                slimeapplyScale = 1;
                break;
            case "medium":
                slimeapplyScale = 2;
                break;
            case "large":
                slimeapplyScale = 4;
                break;
            default:
                slimeapplyScale = 1;
        }

        GlStateManager.scalef((float)slimeapplyScale, (float)slimeapplyScale, (float)slimeapplyScale);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientSlime livingEntityRenderState = (ClientSlime)entity;
        return "/mob/slime/slime.png";
    }
}
