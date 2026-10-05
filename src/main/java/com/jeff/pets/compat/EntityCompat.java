package com.jeff.pets.compat;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/**
 * Entity helpers for APIs that 1.8 provides but 1.7.10's Feather mappings do not expose.
 */
public final class EntityCompat {

	private EntityCompat() {
	}

	/**
	 * Equivalent of 1.8's {@code Entity.getEyePosition(float)}, which 1.7.10 lacks.
	 *
	 * @param partialTick interpolation factor between {@code prevX}/{@code prevY}/{@code prevZ} and the current position
	 */
	public static Vec3d eyePosition(Entity entity, float partialTick) {
		double x = entity.prevX + (entity.x - entity.prevX) * partialTick;
		double y = entity.prevY + (entity.y - entity.prevY) * partialTick;
		double z = entity.prevZ + (entity.z - entity.prevZ) * partialTick;
		return Vec3d.of(x, y + entity.getEyeHeight(), z);
	}

	/**
	 * Equivalent of 1.8's {@code Entity.getRotationVec(float)}, which 1.7.10 lacks.
	 * Follows 1.7.10's yaw/pitch sign convention rather than 1.8's.
	 *
	 * @param partialTick interpolation factor between {@code lastYaw}/{@code lastPitch} and the current rotation
	 */
	public static Vec3d lookVector(Entity entity, float partialTick) {
		float pitch = entity.lastPitch + (entity.pitch - entity.lastPitch) * partialTick;
		float yaw = entity.lastYaw + (entity.yaw - entity.lastYaw) * partialTick;

		float cosPitch = MathHelper.cos(pitch * 0.017453292F);
		float sinPitch = MathHelper.sin(pitch * 0.017453292F);
		float cosYaw = MathHelper.cos(yaw * 0.017453292F);
		float sinYaw = MathHelper.sin(yaw * 0.017453292F);

		return Vec3d.of(cosYaw * sinPitch, cosPitch, sinYaw * sinPitch);
	}
}