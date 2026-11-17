//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.magmacube;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.hostile.ClientMagmaCube;

public class ClientMagmaCubeRenderer extends PetRenderer<ClientMagmaCube> {
    public ClientMagmaCubeRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new SlimeEntityModel(0), 0.75F);
    }

    protected void applyScale(LivingEntity entity, float a) {
        ClientMagmaCube slimeRenderState = (ClientMagmaCube)entity;
        byte magmaCubeapplyScale;
        switch (Central.CONFIG.magmaCubeSkin) {
            case "small":
                magmaCubeapplyScale = 1;
                break;
            case "medium":
                magmaCubeapplyScale = 2;
                break;
            case "large":
                magmaCubeapplyScale = 4;
                break;
            default:
                magmaCubeapplyScale = 1;
        }

        GlStateManager.scalef((float)magmaCubeapplyScale, (float)magmaCubeapplyScale, (float)magmaCubeapplyScale);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientMagmaCube livingEntityRenderState = (ClientMagmaCube)entity;
        return "textures/mob/slime/magmacube.png";
    }
}
