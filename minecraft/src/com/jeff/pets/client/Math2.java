package com.jeff.pets.client;

public class Math2 {
    public static float lerp(float delta, float start, float end) {
        return start + delta * (end - start);
    }

    public static double lerp(double delta, double start, double end) {
        return start + delta * (end - start);
    }

    public static double clampedLerp(double p_clampedLerp_0_, double p_clampedLerp_2_, double p_clampedLerp_4_) {
        if (p_clampedLerp_4_ < (double)0.0F) {
            return p_clampedLerp_0_;
        } else {
            return p_clampedLerp_4_ > (double)1.0F ? p_clampedLerp_2_ : p_clampedLerp_0_ + (p_clampedLerp_2_ - p_clampedLerp_0_) * p_clampedLerp_4_;
        }
    }
}
