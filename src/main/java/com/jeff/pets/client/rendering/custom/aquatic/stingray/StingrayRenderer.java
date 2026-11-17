//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Stingray;

public class StingrayRenderer extends PetRenderer<Stingray> {
    public StingrayRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new StingrayModel(), 0.75F);
    }

    public @NotNull String getTexture(Entity entity) {
        Stingray state = (Stingray)entity;
        return "textures/mob/stingray/stingray.png";
    }

    public void renderModel(LivingEntity entity, float f, float partialTick, float g, float h, float i, float j) {
        Stingray stingray = (Stingray)entity;
    }
}
