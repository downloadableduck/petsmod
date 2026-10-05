package me.shedaniel.clothconfig2.impl;

/**
 * Local generic two-tuple used instead of {@code net.minecraft.util.Pair},
 * which is not generic on Minecraft 1.8.
 */
public class ConfigPair<L, R> {

    private final L left;
    private final R right;

    public ConfigPair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> ConfigPair<L, R> of(L left, R right) {
        return new ConfigPair<>(left, right);
    }

    public L getLeft() {
        return this.left;
    }

    public R getRight() {
        return this.right;
    }
}
