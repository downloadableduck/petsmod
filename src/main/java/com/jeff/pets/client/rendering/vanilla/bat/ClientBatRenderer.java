//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.bat;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;

public class ClientBatRenderer extends PetRenderer {
    public ClientBatRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientBatModel(), 0.25F);
    }

    protected void applyScale(LivingEntity bat, float f) {
        super.applyScale(bat, f);
        GlStateManager.scalef(0.35F, 0.35F, 0.35F);
    }

    public @NotNull String getTexture(Entity batRenderState) {
        return "textures/mob/bat.png";
    }
}
