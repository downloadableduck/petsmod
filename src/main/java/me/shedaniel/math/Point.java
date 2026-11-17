//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.math;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Point implements Cloneable {
    public int x;
    public int y;

    public Point() {
        this(0, 0);
    }

    public Point(Point p) {
        this(p.x, p.y);
    }

    public Point(FloatingPoint p) {
        this(p.x, p.y);
    }

    public Point(double x, double y) {
        this((int)x, (int)y);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public FloatingPoint getFloatingLocation() {
        return new FloatingPoint((double)this.x, (double)this.y);
    }

    public Point getLocation() {
        return new Point(this.x, this.y);
    }

    public Point clone() {
        return this.getLocation();
    }

    public void setLocation(double x, double y) {
        this.x = (int)Math.floor(x + 0.5);
        this.y = (int)Math.floor(y + 0.5);
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return super.equals(obj);
        } else {
            Point pt = (Point)obj;
            return this.x == pt.x && this.y == pt.y;
        }
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + this.x;
        result = 31 * result + this.y;
        return result;
    }

    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }
}
