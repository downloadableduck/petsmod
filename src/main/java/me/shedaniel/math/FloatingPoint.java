//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.math;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class FloatingPoint implements Cloneable {
    public double x;
    public double y;

    public FloatingPoint() {
        this(0.0, 0.0);
    }

    public FloatingPoint(Point p) {
        this((double)p.x, (double)p.y);
    }

    public FloatingPoint(FloatingPoint p) {
        this(p.x, p.y);
    }

    public FloatingPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public FloatingPoint getFloatingLocation() {
        return new FloatingPoint(this.x, this.y);
    }

    public Point getLocation() {
        return new Point(this.x, this.y);
    }

    public FloatingPoint clone() {
        return this.getFloatingLocation();
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FloatingPoint)) {
            return super.equals(obj);
        } else {
            FloatingPoint pt = (FloatingPoint)obj;
            return this.x == pt.x && this.y == pt.y;
        }
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + Double.hashCode(this.x);
        result = 31 * result + Double.hashCode(this.y);
        return result;
    }

    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }
}
