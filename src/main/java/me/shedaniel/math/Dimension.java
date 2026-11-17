//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.math;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Dimension implements Cloneable {
    public int width;
    public int height;

    public Dimension() {
        this(0, 0);
    }

    public Dimension(Dimension d) {
        this(d.width, d.height);
    }

    public Dimension(FloatingDimension d) {
        this(d.width, d.height);
    }

    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Dimension(double width, double height) {
        this.width = (int)Math.ceil(width);
        this.height = (int)Math.ceil(height);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setSize(double width, double height) {
        this.width = (int)Math.ceil(width);
        this.height = (int)Math.ceil(height);
    }

    public FloatingDimension getFloatingSize() {
        return new FloatingDimension((double)this.width, (double)this.height);
    }

    public Dimension getSize() {
        return new Dimension(this.width, this.height);
    }

    public void setSize(Dimension d) {
        this.setSize(d.width, d.height);
    }

    public void setSize(FloatingDimension d) {
        this.setSize(d.width, d.height);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Dimension)) {
            return false;
        } else {
            Dimension d = (Dimension)obj;
            return this.width == d.width && this.height == d.height;
        }
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + this.width;
        result = 31 * result + this.height;
        return result;
    }

    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

    public Dimension clone() {
        return this.getSize();
    }
}
