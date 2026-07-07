package com.jeff.pets.rendering;

import com.jeff.pets.compat.ViaFabricPlusCompat;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;

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

    @Override
    public void render(D entity, float f, float g, PoseStack poseStack, MultiBufferSource source, int i) {
        super.render(entity, f, g, poseStack, source, i);
        if (entity.isPassenger()) {
            entity.setPos(entity.getX(), (entity.getY() + 0.35), entity.getZ());
        }
    }
}
