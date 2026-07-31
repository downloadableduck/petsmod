package com.jeff.pets.client.rendering;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

/**
 * Used as a shared piece of code across all of the renderers. The main point of this class
 * is to provide a {@code state.isUpsideDown} check for all mobs.
 */
public abstract class PetRenderer<D extends Mob, U extends LivingEntityRenderState, K extends EntityModel<? super U>> extends MobRenderer<@NotNull D, @NotNull U, @NotNull K> {
    public PetRenderer(EntityRendererProvider.Context context, K model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public void extractRenderState(D entity, U state, float f) {
        super.extractRenderState(entity, state, f);
        state.isUpsideDown = entity.getPlainTextName().equals("Grumm") || entity.getPlainTextName().equals("Dinnerbone");
        if (state.passengerOffset == null) {
            state.passengerOffset = new Vec3(0, 0, 0);
        }
        //if (ViaFabricPlusCompat.shouldUpdateThingy() && entity.isPassenger()) {
           // state.passengerOffset = new Vec3(state.passengerOffset.x, state.passengerOffset.y + 0.35, state.passengerOffset.z);
        //}
    }
}
