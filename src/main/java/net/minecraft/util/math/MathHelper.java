package net.minecraft.util.math;

/**
 * Modern math helper names backed by BTA's MathHelper.
 */
public final class MathHelper {
    private MathHelper() {
    }

    public static int clamp(int value, int min, int max) {
        return net.minecraft.core.util.helper.MathHelper.clamp(value, min, max);
    }

    public static float clamp(float value, float min, float max) {
        return net.minecraft.core.util.helper.MathHelper.clamp(value, min, max);
    }

    public static double clamp(double value, double min, double max) {
        return net.minecraft.core.util.helper.MathHelper.clamp(value, min, max);
    }

    public static int floor(double value) {
        return net.minecraft.core.util.helper.MathHelper.floor(value);
    }

    public static int ceil(double value) {
        return net.minecraft.core.util.helper.MathHelper.ceil(value);
    }

    public static float lerp(float start, float end, float delta) {
        return net.minecraft.core.util.helper.MathHelper.lerp(start, end, delta);
    }

    public static double lerp(double start, double end, double delta) {
        return net.minecraft.core.util.helper.MathHelper.lerp(start, end, delta);
    }

    public static float abs(float value) {
        return net.minecraft.core.util.helper.MathHelper.abs(value);
    }

    public static double abs(double value) {
        return Math.abs(value);
    }
}
