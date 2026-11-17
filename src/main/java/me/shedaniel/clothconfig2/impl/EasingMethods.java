//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl;

import me.shedaniel.clothconfig2.impl.EasingMethod.EasingMethodImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EasingMethods {
    private static final List<EasingMethod> METHODS = new ArrayList();

    public EasingMethods() {
    }

    public static void register(EasingMethod easingMethod) {
        METHODS.add(easingMethod);
    }

    public static List<EasingMethod> getMethods() {
        return Collections.unmodifiableList(METHODS);
    }

    static {
        METHODS.addAll(Arrays.asList(EasingMethodImpl.values()));
    }
}
