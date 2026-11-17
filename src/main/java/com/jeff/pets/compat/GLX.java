//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.compat;

import org.lwjgl.opengl.GL13;

public final class GLX {
    public static final int GL_TEXTURE1 = 33985;

    private GLX() {
    }

    public static void multiTexCoord2f(int target, float s, float t) {
        GL13.glMultiTexCoord2f(target, s, t);
    }
}
