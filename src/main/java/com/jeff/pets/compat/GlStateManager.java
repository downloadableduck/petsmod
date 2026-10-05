package com.jeff.pets.compat;

import org.lwjgl.opengl.GL11;

/**
 * Backport of Minecraft 1.8's {@code GlStateManager} for 1.7.10.
 *
 * <p>{@code GlStateManager} was introduced in Minecraft 1.8 and does not exist in 1.7.10,
 * where the equivalent operations are issued directly against LWJGL's {@link GL11}.
 * This shim provides the same static API surface that this mod relies on so the
 * rendering code can stay unchanged.
 *
 * <p>1.8's implementation caches state to skip redundant driver calls. That is a pure
 * optimization, so forwarding straight to {@code GL11} is behaviourally equivalent.
 */
public final class GlStateManager {

	private GlStateManager() {
	}

	public static void pushMatrix() {
		GL11.glPushMatrix();
	}

	public static void popMatrix() {
		GL11.glPopMatrix();
	}

	public static void loadIdentity() {
		GL11.glLoadIdentity();
	}

	public static void matrixMode(int mode) {
		GL11.glMatrixMode(mode);
	}

	public static void translatef(float x, float y, float z) {
		GL11.glTranslatef(x, y, z);
	}

	public static void scalef(float x, float y, float z) {
		GL11.glScalef(x, y, z);
	}

	public static void rotatef(float angle, float x, float y, float z) {
		GL11.glRotatef(angle, x, y, z);
	}

	public static void color4f(float red, float green, float blue, float alpha) {
		GL11.glColor4f(red, green, blue, alpha);
	}

	public static void color4f(float red, float green, float blue) {
		GL11.glColor4f(red, green, blue, 1.0F);
	}

	public static void depthMask(boolean enable) {
		GL11.glDepthMask(enable);
	}

	public static void blendFunc(int srcFactor, int dstFactor) {
		GL11.glBlendFunc(srcFactor, dstFactor);
	}

	public static void cullFace(int mode) {
		GL11.glCullFace(mode);
	}

	public static void shadeModel(int mode) {
		GL11.glShadeModel(mode);
	}

	public static void enableAlphaFunc() {
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
	}

	public static void disableAlphaFunc() {
		GL11.glAlphaFunc(GL11.GL_NEVER, 0.0F);
	}

	public static void enableBoundTexture() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public static void disableBoundTexture() {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	public static void enableBlend() {
		GL11.glEnable(GL11.GL_BLEND);
	}

	public static void disableBlend() {
		GL11.glDisable(GL11.GL_BLEND);
	}

	public static void enableCull() {
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	public static void disableCull() {
		GL11.glDisable(GL11.GL_CULL_FACE);
	}

	public static void enableLighting() {
		GL11.glEnable(GL11.GL_LIGHTING);
	}

	public static void disableLighting() {
		GL11.glDisable(GL11.GL_LIGHTING);
	}

	public static void enableAlphaTest() {
		GL11.glEnable(GL11.GL_ALPHA_TEST);
	}

	public static void disableAlphaTest() {
		GL11.glDisable(GL11.GL_ALPHA_TEST);
	}

	public static void enableDepthTest() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	public static void disableDepth() {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}

	public static void enableNormalize() {
		GL11.glEnable(GL11.GL_NORMALIZE);
	}

	public static void disableNormalize() {
		GL11.glDisable(GL11.GL_NORMALIZE);
	}

	public static void enableFog() {
		GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
	}

	public static void disableFog() {
		GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
	}
}