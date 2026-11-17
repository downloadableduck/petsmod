//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.math;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class FloatingDimension implements Cloneable {
    public double width;
    public double height;

    public FloatingDimension() {
        this(0.0, 0.0);
    }

    public FloatingDimension(Dimension d) {
        this((double)d.width, (double)d.height);
    }

    public FloatingDimension(FloatingDimension d) {
        this(d.width, d.height);
    }

    public FloatingDimension(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public FloatingDimension getFloatingSize() {
        return new FloatingDimension(this.width, this.height);
    }

    public Dimension getSize() {
        return new Dimension(this.width, this.height);
    }

    public void setSize(FloatingDimension d) {
        this.setSize(d.width, d.height);
    }

    public void setSize(Dimension d) {
        this.setSize((double)d.width, (double)d.height);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FloatingDimension)) {
            return false;
        } else {
            FloatingDimension d = (FloatingDimension)obj;
            return this.width == d.width && this.height == d.height;
        }
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + Double.hashCode(this.width);
        result = 31 * result + Double.hashCode(this.height);
        return result;
    }

    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

    public FloatingDimension clone() {
        return this.getFloatingSize();
    }
}
