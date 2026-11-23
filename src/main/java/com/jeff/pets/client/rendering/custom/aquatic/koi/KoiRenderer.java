//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.custom.aquatic.koi;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Koi;

public class KoiRenderer extends PetRenderer<Koi> {
    public KoiRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new KoiModel(), 0.5F);
    }

    public @NotNull String getTexture(Entity entity) {
        Koi state = (Koi)entity;
        return "/mob/koi/koi.png";
    }
}
