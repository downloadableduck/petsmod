//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.compat;

import net.minecraft.client.render.renderer.BlendFactor;
import net.minecraft.client.render.renderer.CullFace;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.render.renderer.State;
import org.joml.Matrix4f;

/**
 * GL11-shaped shim used by the ported model/render code, implemented on top of
 * BTA 8's {@link GLRenderer} so every mob uses the same GLRenderer-backed API.
 *
 * <p>Matrix methods honour the selected matrix mode (model-view or texture),
 * which is how the creeper charge layer scrolls its armour texture. Angles are
 * in degrees (as with {@code GL11.glRotatef}) and converted for JOML.
 */
public final class GlStateManager {
    private static final int GL_MODELVIEW = 5888;
    private static final int GL_TEXTURE = 5890;

    private static int currentMatrixMode = GL_MODELVIEW;

    private GlStateManager() {
    }

    private static Matrix4f activeMatrix() {
        return currentMatrixMode == GL_TEXTURE ? GLRenderer.textureM4f() : GLRenderer.modelM4f();
    }

    public static void pushMatrix() {
        GLRenderer.pushFrame();
    }

    public static void popMatrix() {
        GLRenderer.popFrame();
    }

    public static void loadIdentity() {
        activeMatrix().identity();
    }

    public static void matrixMode(int mode) {
        currentMatrixMode = mode;
    }

    public static void translatef(float x, float y, float z) {
        activeMatrix().translate(x, y, z);
    }

    public static void scalef(float x, float y, float z) {
        activeMatrix().scale(x, y, z);
    }

    /**
     * GL11-compatible rotation helper: {@code angle} is in degrees, like
     * {@code GL11.glRotatef}. BTA's matrices are JOML matrices, whose
     * {@code rotate()} takes radians, so convert before applying.
     */
    public static void rotatef(float angle, float x, float y, float z) {
        activeMatrix().rotate((float)Math.toRadians(angle), x, y, z);
    }

    public static void color4f(float red, float green, float blue, float alpha) {
        GLRenderer.setColor4f(red, green, blue, alpha);
    }

    public static void color4f(float red, float green, float blue) {
        GLRenderer.setColor4f(red, green, blue, 1.0F);
    }

    public static void depthMask(boolean enable) {
        GLRenderer.setDepthMask(enable);
    }

    public static void blendFunc(int srcFactor, int dstFactor) {
        GLRenderer.setBlendFunc(toBlendFactor(srcFactor), toBlendFactor(dstFactor));
    }

    public static void cullFace(int mode) {
        GLRenderer.setCullFace(toCullFace(mode));
    }

    /** BTA renders through shaders and has no fixed-function shade model. */
    public static void shadeModel(int mode) {
    }

    public static void enableAlphaFunc() {
        GLRenderer.setAlphaTest(0.1F);
    }

    public static void disableAlphaFunc() {
        GLRenderer.setAlphaTest(0.0F);
    }

    /** Texture units are bound by the shader/tessellator in BTA. */
    public static void enableBoundTexture() {
    }

    /** Texture units are bound by the shader/tessellator in BTA. */
    public static void disableBoundTexture() {
    }

    public static void enableBlend() {
        GLRenderer.enableState(State.BLEND);
    }

    public static void disableBlend() {
        GLRenderer.disableState(State.BLEND);
    }

    public static void enableCull() {
        GLRenderer.enableState(State.CULL_FACE);
    }

    public static void disableCull() {
        GLRenderer.disableState(State.CULL_FACE);
    }

    public static void enableLighting() {
        GLRenderer.globalSetLightEnabled(true);
    }

    public static void disableLighting() {
        GLRenderer.globalSetLightEnabled(false);
    }

    public static void enableAlphaTest() {
        GLRenderer.setAlphaTest(0.1F);
    }

    public static void disableAlphaTest() {
        GLRenderer.setAlphaTest(0.0F);
    }

    public static void enableDepthTest() {
        GLRenderer.enableState(State.DEPTH_TEST);
    }

    public static void disableDepth() {
        GLRenderer.disableState(State.DEPTH_TEST);
    }

    /** BTA shaders normalize normals themselves; GL_NORMALIZE is unavailable. */
    public static void enableNormalize() {
    }

    /** BTA shaders normalize normals themselves; GL_NORMALIZE is unavailable. */
    public static void disableNormalize() {
    }

    /** Fog is controlled by {@link GLRenderer#getFogState()} in BTA. */
    public static void enableFog() {
    }

    /** Fog is controlled by {@link GLRenderer#getFogState()} in BTA. */
    public static void disableFog() {
    }

    private static BlendFactor toBlendFactor(int glConstant) {
        switch (glConstant) {
            case 0:
                return BlendFactor.ZERO;
            case 1:
                return BlendFactor.ONE;
            case 768:
                return BlendFactor.SRC_COLOR;
            case 769:
                return BlendFactor.ONE_MINUS_SRC_COLOR;
            case 770:
                return BlendFactor.SRC_ALPHA;
            case 771:
                return BlendFactor.ONE_MINUS_SRC_ALPHA;
            case 772:
                return BlendFactor.DST_ALPHA;
            case 773:
                return BlendFactor.ONE_MINUS_DST_ALPHA;
            case 774:
                return BlendFactor.DST_COLOR;
            case 775:
                return BlendFactor.ONE_MINUS_DST_COLOR;
            case 776:
                return BlendFactor.SRC_ALPHA_SATURATE;
            case 32769:
                return BlendFactor.CONSTANT_COLOR;
            case 32770:
                return BlendFactor.ONE_MINUS_CONSTANT_COLOR;
            case 32771:
                return BlendFactor.CONSTANT_ALPHA;
            case 32772:
                return BlendFactor.ONE_MINUS_CONSTANT_ALPHA;
            default:
                return BlendFactor.ONE;
        }
    }

    private static CullFace toCullFace(int glConstant) {
        switch (glConstant) {
            case 1028:
                return CullFace.FRONT;
            case 1032:
                return CullFace.FRONT_AND_BACK;
            case 1029:
            default:
                return CullFace.BACK;
        }
    }
}
