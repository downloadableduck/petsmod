//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.bat;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;

public class ClientBatRenderer extends PetRenderer {
    public ClientBatRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientBatModel(), 0.25F);
    }

    protected void applyScale(Mob bat, float f) {
        super.applyScale(bat, f);
        GlStateManager.scalef(0.35F, 0.35F, 0.35F);
    }

    public @NotNull String getTexture(Entity batRenderState) {
        return "/mob/bat.png";
    }
}
