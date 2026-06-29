package com.jeff.pets.client.rendering;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.entity.Mob;

/**
 * Used as a shared piece of code across all of the renderers. The main point of this class
 * is to provide a {@code state.isUpsideDown} check for all mobs.
 */
public abstract class PetRenderer<D extends Mob, K extends EntityModel<D>> extends MobRenderer<D, K> {
    public PetRenderer(EntityRendererProvider.Context context, K model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public boolean isBodyVisible(D entity) {
        return true;
    }
}
