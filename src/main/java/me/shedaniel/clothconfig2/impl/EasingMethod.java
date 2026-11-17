package me.shedaniel.clothconfig2.impl;

public interface EasingMethod {
    double apply(double var1);

    enum EasingMethodImpl implements EasingMethod {
        NONE(v -> 1.0),
        LINEAR(v -> v),
        EXPO(v -> v == 0.0 ? 0.0 : (v == 1.0 ? 1.0 : 1.0 - Math.pow(2.0, -10.0 * v))),
        QUAD(v -> 1.0 - (1.0 - v) * (1.0 - v)),
        QUART(v -> 1.0 - Math.pow(1.0 - v, 4.0)),
        SINE(v -> Math.sin(v * (Math.PI / 2.0))),
        CUBIC(v -> 1.0 - Math.pow(1.0 - v, 3.0)),
        QUINTIC(v -> 1.0 - Math.pow(1.0 - v, 5.0)),
        CIRC(v -> Math.sqrt(1.0 - Math.pow(v - 1.0, 2.0)));

        private final java.util.function.DoubleUnaryOperator function;

        EasingMethodImpl(java.util.function.DoubleUnaryOperator function) {
            this.function = function;
        }

        @Override
        public double apply(double v) {
            return this.function.applyAsDouble(v);
        }

        @Override
        public String toString() {
            return this.name();
        }
    }
}
