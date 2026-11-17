//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.compat;

import net.minecraft.client.render.Tessellator;

public final class GuiVertexCompat {
    public static final VertexFormat POSITION = new VertexFormat(false, false);
    public static final VertexFormat POSITION_COLOR = new VertexFormat(true, false);
    public static final VertexFormat POSITION_TEX_COLOR = new VertexFormat(true, true);

    private GuiVertexCompat() {
    }

    public static final class VertexFormat {
        private final boolean hasColor;
        private final boolean hasTexture;

        private VertexFormat(boolean hasColor, boolean hasTexture) {
            this.hasColor = hasColor;
            this.hasTexture = hasTexture;
        }
    }

    public static final class Builder {
        private final Tessellator tesselator;
        private VertexFormat format;
        private double pendingX;
        private double pendingY;
        private double pendingZ;
        private double pendingU;
        private double pendingV;
        private float pendingRed;
        private float pendingGreen;
        private float pendingBlue;
        private float pendingAlpha;
        private boolean hasPendingVertex;

        private Builder(Tessellator tesselator) {
            this.format = GuiVertexCompat.POSITION;
            this.pendingAlpha = 1.0F;
            this.tesselator = tesselator;
        }

        public static Builder of(Tessellator tesselator) {
            return new Builder(tesselator);
        }

        public static Builder getInstance() {
            return of(Tessellator.INSTANCE);
        }

        public Builder begin(int mode, VertexFormat format) {
            this.format = format;
            this.hasPendingVertex = false;
            this.tesselator.start(mode);
            return this;
        }

        public Builder vertex(double x, double y, double z) {
            this.flushPending();
            this.pendingX = x;
            this.pendingY = y;
            this.pendingZ = z;
            this.hasPendingVertex = true;
            return this;
        }

        public Builder texture(double u, double v) {
            this.pendingU = u;
            this.pendingV = v;
            return this;
        }

        public Builder color(int red, int green, int blue, int alpha) {
            return this.color((float)red / 255.0F, (float)green / 255.0F, (float)blue / 255.0F, (float)alpha / 255.0F);
        }

        public Builder color(float red, float green, float blue, float alpha) {
            this.pendingRed = red;
            this.pendingGreen = green;
            this.pendingBlue = blue;
            this.pendingAlpha = alpha;
            return this;
        }

        public Builder nextVertex() {
            this.flushPending();
            return this;
        }

        public void end() {
            this.flushPending();
            this.tesselator.draw();
        }

        private void flushPending() {
            if (this.hasPendingVertex) {
                if (this.format.hasColor) {
                    this.tesselator.color(this.pendingRed, this.pendingGreen, this.pendingBlue, this.pendingAlpha);
                }

                if (this.format.hasTexture) {
                    this.tesselator.texture(this.pendingU, this.pendingV);
                }

                this.tesselator.vertex(this.pendingX, this.pendingY, this.pendingZ);
                this.hasPendingVertex = false;
            }
        }
    }
}
