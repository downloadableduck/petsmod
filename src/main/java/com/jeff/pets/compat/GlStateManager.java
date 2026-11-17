//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.compat;

import org.lwjgl.opengl.GL11;

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
        GL11.glAlphaFunc(516, 0.1F);
    }

    public static void disableAlphaFunc() {
        GL11.glAlphaFunc(512, 0.0F);
    }

    public static void enableBoundTexture() {
        GL11.glEnable(3553);
    }

    public static void disableBoundTexture() {
        GL11.glDisable(3553);
    }

    public static void enableBlend() {
        GL11.glEnable(3042);
    }

    public static void disableBlend() {
        GL11.glDisable(3042);
    }

    public static void enableCull() {
        GL11.glEnable(2884);
    }

    public static void disableCull() {
        GL11.glDisable(2884);
    }

    public static void enableLighting() {
        GL11.glEnable(2896);
    }

    public static void disableLighting() {
        GL11.glDisable(2896);
    }

    public static void enableAlphaTest() {
        GL11.glEnable(3008);
    }

    public static void disableAlphaTest() {
        GL11.glDisable(3008);
    }

    public static void enableDepthTest() {
        GL11.glEnable(2929);
    }

    public static void disableDepth() {
        GL11.glDisable(2929);
    }

    public static void enableNormalize() {
        GL11.glEnable(2977);
    }

    public static void disableNormalize() {
        GL11.glDisable(2977);
    }

    public static void enableFog() {
        GL11.glFogi(2917, 9729);
    }

    public static void disableFog() {
        GL11.glFogi(2917, 9729);
    }
}
