//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.math;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class FloatingRectangle implements Cloneable {
    public double x;
    public double y;
    public double width;
    public double height;

    public FloatingRectangle() {
        this(0.0, 0.0, 0.0, 0.0);
    }

    public FloatingRectangle(FloatingRectangle r) {
        this(r.x, r.y, r.width, r.height);
    }

    public FloatingRectangle(Rectangle r) {
        this((double)r.x, (double)r.y, (double)r.width, (double)r.height);
    }

    public FloatingRectangle(int width, int height) {
        this(0.0, 0.0, (double)width, (double)height);
    }

    public FloatingRectangle(Point p, Dimension d) {
        this((double)p.x, (double)p.y, (double)d.width, (double)d.height);
    }

    public FloatingRectangle(Point p, FloatingDimension d) {
        this((double)p.x, (double)p.y, d.width, d.height);
    }

    public FloatingRectangle(FloatingPoint p, Dimension d) {
        this(p.x, p.y, (double)d.width, (double)d.height);
    }

    public FloatingRectangle(FloatingPoint p, FloatingDimension d) {
        this(p.x, p.y, d.width, d.height);
    }

    public FloatingRectangle(Point p) {
        this((double)p.x, (double)p.y, 0.0, 0.0);
    }

    public FloatingRectangle(FloatingPoint p) {
        this(p.x, p.y, 0.0, 0.0);
    }

    public FloatingRectangle(Dimension d) {
        this(0.0, 0.0, (double)d.width, (double)d.height);
    }

    public FloatingRectangle(FloatingDimension d) {
        this(0.0, 0.0, d.width, d.height);
    }

    public FloatingRectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return this.x;
    }

    public double getMinX() {
        return this.x;
    }

    public double getMaxX() {
        return this.x + this.width;
    }

    public double getCenterX() {
        return this.x + this.width / 2.0;
    }

    public double getY() {
        return this.y;
    }

    public double getMinY() {
        return this.y;
    }

    public double getMaxY() {
        return this.y + this.height;
    }

    public double getCenterY() {
        return this.y + this.height / 2.0;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public FloatingRectangle getFloatingBounds() {
        return new FloatingRectangle(this.x, this.y, this.width, this.height);
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public void setBounds(FloatingRectangle r) {
        this.setBounds(r.x, r.y, r.width, r.height);
    }

    public void setBounds(Rectangle r) {
        this.setBounds((double)r.x, (double)r.y, (double)r.width, (double)r.height);
    }

    public void setBounds(double x, double y, double width, double height) {
        this.reshape(x, y, width, height);
    }

    /** @deprecated */
    @Deprecated
    public void reshape(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public FloatingPoint getFloatingLocation() {
        return new FloatingPoint(this.x, this.y);
    }

    public Point getLocation() {
        return new Point(this.x, this.y);
    }

    public void setLocation(Point p) {
        this.setLocation((double)p.x, (double)p.y);
    }

    public void setLocation(FloatingPoint p) {
        this.setLocation(p.x, p.y);
    }

    public void setLocation(double x, double y) {
        this.move(x, y);
    }

    /** @deprecated */
    @Deprecated
    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public FloatingRectangle clone() {
        return this.getFloatingBounds();
    }

    public Dimension getSize() {
        return new Dimension(this.width, this.height);
    }

    public void setSize(Dimension d) {
        this.setSize((double)d.width, (double)d.height);
    }

    public void setSize(FloatingDimension d) {
        this.setSize(d.width, d.height);
    }

    public void setSize(double width, double height) {
        this.resize(width, height);
    }

    /** @deprecated */
    @Deprecated
    public void resize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public boolean contains(Point p) {
        return this.contains(p.x, p.y);
    }

    public boolean contains(FloatingPoint p) {
        return this.contains(p.x, p.y);
    }

    public boolean contains(int x, int y) {
        return this.inside((double)x, (double)y);
    }

    public boolean contains(double x, double y) {
        return this.inside((double)((int)x), (double)((int)y));
    }

    public boolean contains(Rectangle r) {
        return this.contains((double)r.x, (double)r.y, (double)r.width, (double)r.height);
    }

    public boolean contains(FloatingRectangle r) {
        return this.contains(r.x, r.y, r.width, r.height);
    }

    public boolean contains(double X, double Y, double W, double H) {
        return this.contains(X, Y) && this.contains(W, H);
    }

    /** @deprecated */
    @Deprecated
    public boolean inside(double x, double y) {
        double thisX = this.x;
        double thisY = this.y;
        return !this.isEmpty() && x >= thisX && x <= thisX + this.width && y >= thisY && y <= thisY + this.height;
    }

    public boolean intersects(FloatingRectangle r) {
        double tw = this.width;
        double th = this.height;
        double rw = r.width;
        double rh = r.height;
        if (!(rw <= 0.0) && !(rh <= 0.0) && !(tw <= 0.0) && !(th <= 0.0)) {
            double tx = this.x;
            double ty = this.y;
            double rx = r.x;
            double ry = r.y;
            rw += rx;
            rh += ry;
            tw += tx;
            th += ty;
            return (rw < rx || rw > tx) && (rh < ry || rh > ty) && (tw < tx || tw > rx) && (th < ty || th > ry);
        } else {
            return false;
        }
    }

    public FloatingRectangle intersection(FloatingRectangle r) {
        double tx1 = this.x;
        double ty1 = this.y;
        double rx1 = r.x;
        double ry1 = r.y;
        double tx2 = tx1;
        tx2 += this.width;
        double ty2 = ty1;
        ty2 += this.height;
        double rx2 = rx1;
        rx2 += r.width;
        double ry2 = ry1;
        ry2 += r.height;
        if (tx1 < rx1) {
            tx1 = rx1;
        }

        if (ty1 < ry1) {
            ty1 = ry1;
        }

        if (tx2 > rx2) {
            tx2 = rx2;
        }

        if (ty2 > ry2) {
            ty2 = ry2;
        }

        tx2 -= tx1;
        ty2 -= ty1;
        if (tx2 < Double.MIN_VALUE) {
            tx2 = Double.MIN_VALUE;
        }

        if (ty2 < Double.MIN_VALUE) {
            ty2 = Double.MIN_VALUE;
        }

        return new FloatingRectangle(tx1, ty1, (double)((int)tx2), (double)((int)ty2));
    }

    public FloatingRectangle union(FloatingRectangle r) {
        double tx2 = this.width;
        double ty2 = this.height;
        if (!(tx2 < 0.0) && !(ty2 < 0.0)) {
            double rx2 = r.width;
            double ry2 = r.height;
            if (!(rx2 < 0.0) && !(ry2 < 0.0)) {
                double tx1 = this.x;
                double ty1 = this.y;
                tx2 += tx1;
                ty2 += ty1;
                double rx1 = r.x;
                double ry1 = r.y;
                rx2 += rx1;
                ry2 += ry1;
                if (tx1 > rx1) {
                    tx1 = rx1;
                }

                if (ty1 > ry1) {
                    ty1 = ry1;
                }

                if (tx2 < rx2) {
                    tx2 = rx2;
                }

                if (ty2 < ry2) {
                    ty2 = ry2;
                }

                tx2 -= tx1;
                ty2 -= ty1;
                if (tx2 > Double.MAX_VALUE) {
                    tx2 = Double.MAX_VALUE;
                }

                if (ty2 > Double.MAX_VALUE) {
                    ty2 = Double.MAX_VALUE;
                }

                return new FloatingRectangle(tx1, ty1, (double)((int)tx2), (double)((int)ty2));
            } else {
                return new FloatingRectangle(this);
            }
        } else {
            return new FloatingRectangle(r);
        }
    }

    public void add(double newx, double newy) {
        if (!(this.width < 0.0) && !(this.height < 0.0)) {
            double x1 = this.x;
            double y1 = this.y;
            double x2 = this.width;
            double y2 = this.height;
            x2 += x1;
            y2 += y1;
            if (x1 > newx) {
                x1 = newx;
            }

            if (y1 > newy) {
                y1 = newy;
            }

            if (x2 < newx) {
                x2 = newx;
            }

            if (y2 < newy) {
                y2 = newy;
            }

            x2 -= x1;
            y2 -= y1;
            if (x2 > Double.MAX_VALUE) {
                x2 = Double.MAX_VALUE;
            }

            if (y2 > Double.MAX_VALUE) {
                y2 = Double.MAX_VALUE;
            }

            this.reshape(x1, y1, (double)((int)x2), (double)((int)y2));
        } else {
            this.x = newx;
            this.y = newy;
            this.width = this.height = 0.0;
        }
    }

    public void add(FloatingPoint pt) {
        this.add(pt.x, pt.y);
    }

    public void add(Point pt) {
        this.add((double)pt.x, (double)pt.y);
    }

    public void add(FloatingRectangle r) {
        double tx2 = this.width;
        double ty2 = this.height;
        if (tx2 < 0.0 || ty2 < 0.0) {
            this.reshape(r.x, r.y, r.width, r.height);
        }

        double rx2 = r.width;
        double ry2 = r.height;
        if (!(rx2 < 0.0) && !(ry2 < 0.0)) {
            double tx1 = this.x;
            double ty1 = this.y;
            tx2 += tx1;
            ty2 += ty1;
            double rx1 = r.x;
            double ry1 = r.y;
            rx2 += rx1;
            ry2 += ry1;
            if (tx1 > rx1) {
                tx1 = rx1;
            }

            if (ty1 > ry1) {
                ty1 = ry1;
            }

            if (tx2 < rx2) {
                tx2 = rx2;
            }

            if (ty2 < ry2) {
                ty2 = ry2;
            }

            tx2 -= tx1;
            ty2 -= ty1;
            if (tx2 > Double.MAX_VALUE) {
                tx2 = Double.MAX_VALUE;
            }

            if (ty2 > Double.MAX_VALUE) {
                ty2 = Double.MAX_VALUE;
            }

            this.reshape(tx1, ty1, (double)((int)tx2), (double)((int)ty2));
        }
    }

    public void grow(double h, double v) {
        double x0 = this.x;
        double y0 = this.y;
        double x1 = this.width;
        double y1 = this.height;
        x1 += x0;
        y1 += y0;
        x0 -= h;
        y0 -= v;
        x1 += h;
        y1 += v;
        if (x1 < x0) {
            x1 -= x0;
            if (x1 < Double.MIN_VALUE) {
                x1 = Double.MIN_VALUE;
            }

            if (x0 < Double.MIN_VALUE) {
                x0 = Double.MIN_VALUE;
            } else if (x0 > Double.MAX_VALUE) {
                x0 = Double.MAX_VALUE;
            }
        } else {
            if (x0 < Double.MIN_VALUE) {
                x0 = Double.MIN_VALUE;
            } else if (x0 > Double.MAX_VALUE) {
                x0 = Double.MAX_VALUE;
            }

            x1 -= x0;
            if (x1 < Double.MIN_VALUE) {
                x1 = Double.MIN_VALUE;
            } else if (x1 > Double.MAX_VALUE) {
                x1 = Double.MAX_VALUE;
            }
        }

        if (y1 < y0) {
            y1 -= y0;
            if (y1 < Double.MIN_VALUE) {
                y1 = Double.MIN_VALUE;
            }

            if (y0 < Double.MIN_VALUE) {
                y0 = Double.MIN_VALUE;
            } else if (y0 > Double.MAX_VALUE) {
                y0 = Double.MAX_VALUE;
            }
        } else {
            if (y0 < Double.MIN_VALUE) {
                y0 = Double.MIN_VALUE;
            } else if (y0 > Double.MAX_VALUE) {
                y0 = Double.MAX_VALUE;
            }

            y1 -= y0;
            if (y1 < Double.MIN_VALUE) {
                y1 = Double.MIN_VALUE;
            } else if (y1 > Double.MAX_VALUE) {
                y1 = Double.MAX_VALUE;
            }
        }

        this.reshape((double)((int)x0), (double)((int)y0), (double)((int)x1), (double)((int)y1));
    }

    public boolean isEmpty() {
        return this.width <= 0.0 || this.height <= 0.0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof FloatingRectangle)) {
            return super.equals(obj);
        } else {
            FloatingRectangle r = (FloatingRectangle)obj;
            return this.x == r.x && this.y == r.y && this.width == r.width && this.height == r.height;
        }
    }

    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + Double.hashCode(this.x);
        result = 31 * result + Double.hashCode(this.y);
        result = 31 * result + Double.hashCode(this.width);
        result = 31 * result + Double.hashCode(this.height);
        return result;
    }
}
