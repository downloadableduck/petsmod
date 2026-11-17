//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.math;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Rectangle implements Cloneable {
    public int x;
    public int y;
    public int width;
    public int height;

    public Rectangle() {
        this(0, 0, 0, 0);
    }

    public Rectangle(Rectangle r) {
        this(r.x, r.y, r.width, r.height);
    }

    public Rectangle(FloatingRectangle r) {
        this(r.x, r.y, r.width, r.height);
    }

    public Rectangle(int width, int height) {
        this(0, 0, width, height);
    }

    public Rectangle(Point p, Dimension d) {
        this(p.x, p.y, d.width, d.height);
    }

    public Rectangle(Point p, FloatingDimension d) {
        this((double)p.x, (double)p.y, d.width, d.height);
    }

    public Rectangle(FloatingPoint p, Dimension d) {
        this(p.x, p.y, (double)d.width, (double)d.height);
    }

    public Rectangle(FloatingPoint p, FloatingDimension d) {
        this(p.x, p.y, d.width, d.height);
    }

    public Rectangle(Point p) {
        this(p.x, p.y, 0, 0);
    }

    public Rectangle(FloatingPoint p) {
        this(p.x, p.y, 0.0, 0.0);
    }

    public Rectangle(Dimension d) {
        this(0, 0, d.width, d.height);
    }

    public Rectangle(FloatingDimension d) {
        this(0.0, 0.0, d.width, d.height);
    }

    public Rectangle(double x, double y, double width, double height) {
        this((int)Math.round(x), (int)Math.round(y), (int)Math.round(width), (int)Math.round(height));
    }

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return this.x;
    }

    public int getMinX() {
        return this.x;
    }

    public int getMaxX() {
        return this.x + this.width;
    }

    public int getCenterX() {
        return this.x + this.width / 2;
    }

    public int getY() {
        return this.y;
    }

    public int getMinY() {
        return this.y;
    }

    public int getMaxY() {
        return this.y + this.height;
    }

    public int getCenterY() {
        return this.y + this.height / 2;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public FloatingRectangle getFloatingBounds() {
        return new FloatingRectangle((double)this.x, (double)this.y, (double)this.width, (double)this.height);
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public void setBounds(FloatingRectangle r) {
        this.setBounds(r.x, r.y, r.width, r.height);
    }

    public void setBounds(Rectangle r) {
        this.setBounds(r.x, r.y, r.width, r.height);
    }

    public void setBounds(double x, double y, double width, double height) {
        this.setBounds((int)Math.round(x), (int)Math.round(y), (int)Math.round(width), (int)Math.round(height));
    }

    public void setBounds(int x, int y, int width, int height) {
        this.reshape(x, y, width, height);
    }

    /** @deprecated */
    @Deprecated
    public void reshape(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public FloatingPoint getFloatingLocation() {
        return new FloatingPoint((double)this.x, (double)this.y);
    }

    public Point getLocation() {
        return new Point(this.x, this.y);
    }

    public void setLocation(FloatingPoint p) {
        this.setLocation(p.x, p.y);
    }

    public void setLocation(Point p) {
        this.setLocation(p.x, p.y);
    }

    public void setLocation(double x, double y) {
        this.move((int)Math.round(x), (int)Math.round(y));
    }

    public void setLocation(int x, int y) {
        this.move(x, y);
    }

    /** @deprecated */
    @Deprecated
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public Rectangle clone() {
        return this.getBounds();
    }

    public FloatingDimension getFloatingSize() {
        return new FloatingDimension((double)this.width, (double)this.height);
    }

    public void setSize(FloatingDimension d) {
        this.setSize(d.width, d.height);
    }

    public Dimension getSize() {
        return new Dimension(this.width, this.height);
    }

    public void setSize(Dimension d) {
        this.setSize(d.width, d.height);
    }

    public void setSize(double width, double height) {
        this.resize((int)Math.round(width), (int)Math.round(height));
    }

    public void setSize(int width, int height) {
        this.resize(width, height);
    }

    /** @deprecated */
    @Deprecated
    public void resize(int width, int height) {
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
        return this.inside(x, y);
    }

    public boolean contains(double x, double y) {
        return this.inside((int)x, (int)y);
    }

    public boolean contains(FloatingRectangle r) {
        return this.contains((int)r.x, (int)r.y, (int)r.width, (int)r.height);
    }

    public boolean contains(Rectangle r) {
        return this.contains(r.x, r.y, r.width, r.height);
    }

    public boolean contains(int X, int Y, int W, int H) {
        return this.contains(X, Y) && this.contains(W, H);
    }

    /** @deprecated */
    @Deprecated
    public boolean inside(int x, int y) {
        int thisX = this.x;
        int thisY = this.y;
        return !this.isEmpty() && x >= thisX && x <= thisX + this.width && y >= thisY && y <= thisY + this.height;
    }

    public boolean intersects(Rectangle r) {
        int tw = this.width;
        int th = this.height;
        int rw = r.width;
        int rh = r.height;
        if (rw > 0 && rh > 0 && tw > 0 && th > 0) {
            int tx = this.x;
            int ty = this.y;
            int rx = r.x;
            int ry = r.y;
            rw += rx;
            rh += ry;
            tw += tx;
            th += ty;
            return (rw < rx || rw > tx) && (rh < ry || rh > ty) && (tw < tx || tw > rx) && (th < ty || th > ry);
        } else {
            return false;
        }
    }

    public Rectangle intersection(Rectangle r) {
        int tx1 = this.x;
        int ty1 = this.y;
        int rx1 = r.x;
        int ry1 = r.y;
        long tx2 = (long)tx1;
        tx2 += (long)this.width;
        long ty2 = (long)ty1;
        ty2 += (long)this.height;
        long rx2 = (long)rx1;
        rx2 += (long)r.width;
        long ry2 = (long)ry1;
        ry2 += (long)r.height;
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

        tx2 -= (long)tx1;
        ty2 -= (long)ty1;
        if (tx2 < -2147483648L) {
            tx2 = -2147483648L;
        }

        if (ty2 < -2147483648L) {
            ty2 = -2147483648L;
        }

        return new Rectangle(tx1, ty1, (int)tx2, (int)ty2);
    }

    public Rectangle union(Rectangle r) {
        long tx2 = (long)this.width;
        long ty2 = (long)this.height;
        if ((tx2 | ty2) < 0L) {
            return new Rectangle(r);
        } else {
            long rx2 = (long)r.width;
            long ry2 = (long)r.height;
            if ((rx2 | ry2) < 0L) {
                return new Rectangle(this);
            } else {
                int tx1 = this.x;
                int ty1 = this.y;
                tx2 += (long)tx1;
                ty2 += (long)ty1;
                int rx1 = r.x;
                int ry1 = r.y;
                rx2 += (long)rx1;
                ry2 += (long)ry1;
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

                tx2 -= (long)tx1;
                ty2 -= (long)ty1;
                if (tx2 > 2147483647L) {
                    tx2 = 2147483647L;
                }

                if (ty2 > 2147483647L) {
                    ty2 = 2147483647L;
                }

                return new Rectangle(tx1, ty1, (int)tx2, (int)ty2);
            }
        }
    }

    public void add(int newx, int newy) {
        if ((this.width | this.height) < 0) {
            this.x = newx;
            this.y = newy;
            this.width = this.height = 0;
        } else {
            int x1 = this.x;
            int y1 = this.y;
            long x2 = (long)this.width;
            long y2 = (long)this.height;
            x2 += (long)x1;
            y2 += (long)y1;
            if (x1 > newx) {
                x1 = newx;
            }

            if (y1 > newy) {
                y1 = newy;
            }

            if (x2 < (long)newx) {
                x2 = (long)newx;
            }

            if (y2 < (long)newy) {
                y2 = (long)newy;
            }

            x2 -= (long)x1;
            y2 -= (long)y1;
            if (x2 > 2147483647L) {
                x2 = 2147483647L;
            }

            if (y2 > 2147483647L) {
                y2 = 2147483647L;
            }

            this.reshape(x1, y1, (int)x2, (int)y2);
        }
    }

    public void add(FloatingPoint pt) {
        this.add((int)pt.x, (int)pt.y);
    }

    public void add(Point pt) {
        this.add(pt.x, pt.y);
    }

    public void add(Rectangle r) {
        long tx2 = (long)this.width;
        long ty2 = (long)this.height;
        if ((tx2 | ty2) < 0L) {
            this.reshape(r.x, r.y, r.width, r.height);
        }

        long rx2 = (long)r.width;
        long ry2 = (long)r.height;
        if ((rx2 | ry2) >= 0L) {
            int tx1 = this.x;
            int ty1 = this.y;
            tx2 += (long)tx1;
            ty2 += (long)ty1;
            int rx1 = r.x;
            int ry1 = r.y;
            rx2 += (long)rx1;
            ry2 += (long)ry1;
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

            tx2 -= (long)tx1;
            ty2 -= (long)ty1;
            if (tx2 > 2147483647L) {
                tx2 = 2147483647L;
            }

            if (ty2 > 2147483647L) {
                ty2 = 2147483647L;
            }

            this.reshape(tx1, ty1, (int)tx2, (int)ty2);
        }
    }

    public void grow(int h, int v) {
        long x0 = (long)this.x;
        long y0 = (long)this.y;
        long x1 = (long)this.width;
        long y1 = (long)this.height;
        x1 += x0;
        y1 += y0;
        x0 -= (long)h;
        y0 -= (long)v;
        x1 += (long)h;
        y1 += (long)v;
        if (x1 < x0) {
            x1 -= x0;
            if (x1 < -2147483648L) {
                x1 = -2147483648L;
            }

            if (x0 < -2147483648L) {
                x0 = -2147483648L;
            } else if (x0 > 2147483647L) {
                x0 = 2147483647L;
            }
        } else {
            if (x0 < -2147483648L) {
                x0 = -2147483648L;
            } else if (x0 > 2147483647L) {
                x0 = 2147483647L;
            }

            x1 -= x0;
            if (x1 < -2147483648L) {
                x1 = -2147483648L;
            } else if (x1 > 2147483647L) {
                x1 = 2147483647L;
            }
        }

        if (y1 < y0) {
            y1 -= y0;
            if (y1 < -2147483648L) {
                y1 = -2147483648L;
            }

            if (y0 < -2147483648L) {
                y0 = -2147483648L;
            } else if (y0 > 2147483647L) {
                y0 = 2147483647L;
            }
        } else {
            if (y0 < -2147483648L) {
                y0 = -2147483648L;
            } else if (y0 > 2147483647L) {
                y0 = 2147483647L;
            }

            y1 -= y0;
            if (y1 < -2147483648L) {
                y1 = -2147483648L;
            } else if (y1 > 2147483647L) {
                y1 = 2147483647L;
            }
        }

        this.reshape((int)x0, (int)y0, (int)x1, (int)y1);
    }

    public boolean isEmpty() {
        return this.width <= 0 || this.height <= 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Rectangle)) {
            return super.equals(obj);
        } else {
            Rectangle r = (Rectangle)obj;
            return this.x == r.x && this.y == r.y && this.width == r.width && this.height == r.height;
        }
    }

    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + this.x;
        result = 31 * result + this.y;
        result = 31 * result + this.width;
        result = 31 * result + this.height;
        return result;
    }
}
