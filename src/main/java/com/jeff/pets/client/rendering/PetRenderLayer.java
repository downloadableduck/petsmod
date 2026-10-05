package com.jeff.pets.client.rendering;

import net.minecraft.entity.living.LivingEntity;

/**
 * Stand-in for Minecraft 1.8's {@code net.minecraft.client.render.entity.layer.EntityRenderLayer},
 * which does not exist in 1.7.10.
 *
 * <p>Render layers were introduced in 1.8. On 1.7.10 each of this mod's renderers keeps its
 * own list of {@code PetRenderLayer}s (see {@link PetRenderer#addLayer}) and invokes them
 * after the mob model has been drawn.
 *
 * <p>The float parameters mirror 1.7.10's
 * {@code LivingEntityRenderer.renderModel(LivingEntity, float, float, float, float, float, float)},
 * so a layer can forward them straight to
 * {@code Model.render(Entity, float, float, float, float, float, float)}.
 */
public interface PetRenderLayer {

	/**
	 * @param entity          the mob being rendered
	 * @param limbSwing       walking animation amplitude
	 * @param limbSwingAmount walking animation speed
	 * @param ageInTicks      interpolated age of the mob, drives idle animations
	 * @param netHeadYaw      interpolated head yaw
	 * @param headPitch       interpolated head pitch
	 * @param scale           model scale
	 */
	void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
	            float netHeadYaw, float headPitch, float scale);

	/**
	 * @return whether the mob's texture is tinted with the hurt colour underneath this layer
	 */
	boolean colorsWhenDamaged();
}