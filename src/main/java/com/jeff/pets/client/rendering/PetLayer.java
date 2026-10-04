package com.jeff.pets.client.rendering;

import net.minecraft.entity.EntityLivingBase;

/**
 * 1.7.10 has no baked-model layer system: there is no {@code LayerRenderer}, no
 * {@code RendererLivingEntity#addLayer}, and layers are never invoked automatically.
 * Vanilla 1.7.10 renderers instead draw their overlays inline from
 * {@code RendererLivingEntity#renderModel}, which is the only hook that still carries the
 * limb-swing / age-in-ticks interpolation values a layer needs.
 *
 * <p>This interface mirrors the 1.8 {@code LayerRenderer#render} parameter list minus the
 * baked-model argument. Implementations are attached to a {@link PetRenderer} with
 * {@link PetRenderer#setPetLayer} and drawn immediately after the main model.
 */
public interface PetLayer {
    void render(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                float netHeadYaw, float headPitch, float scale);
}
