package com.jeff.pets.compat;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

/**
 * Backport of Minecraft 1.8's {@code GLX} for 1.7.10.
 *
 * <p>{@code GLX} was introduced in Minecraft 1.8 and does not exist in 1.7.10, where the
 * multi-texture coordinate calls are issued directly against LWJGL's {@link GL13}, since
 * {@code glMultiTexCoord2f} lives in the GL13 binding rather than GL11.
 */
public final class GLX {

	/** Mirrors 1.8's {@code GLX.GL_TEXTURE1}. */
	public static final int GL_TEXTURE1 = GL13.GL_TEXTURE1;

	private GLX() {
	}

	public static void multiTexCoord2f(int target, float s, float t) {
		GL13.glMultiTexCoord2f(target, s, t);
	}
}