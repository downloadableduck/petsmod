//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.compat;

/**
 * Legacy multi-texture coordinate helper kept for API compatibility.
 *
 * <p>BTA 8 does not expose fixed-function multi-texture coordinates; texture
 * coordinates are supplied through the tessellator and the shader's texture
 * matrix (see {@code GlStateManager.matrixMode(GL_TEXTURE)}). No ported mob
 * uses this method, so it is intentionally a no-op rather than issuing an
 * unsupported GL13 call.
 */
public final class GLX {
    public static final int GL_TEXTURE1 = 33985;

    private GLX() {
    }

    public static void multiTexCoord2f(int target, float s, float t) {
    }
}
