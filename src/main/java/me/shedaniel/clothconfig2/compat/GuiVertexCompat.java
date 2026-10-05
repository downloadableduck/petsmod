package me.shedaniel.clothconfig2.compat;

import net.minecraft.client.render.vertex.Tesselator;

/**
 * Vertex-format shims for Minecraft 1.7.10.
 *
 * <p>1.7.10 predates the {@code BufferBuilder}/{@code VertexFormat} vertex-format system
 * entirely; {@link Tesselator} is immediate-mode: you set colour/texture state and then call
 * {@code vertex(x, y, z)}, which commits one vertex and auto-flushes every four of them.
 *
 * <p>Cloth-config was written against the later fluent {@code BufferBuilder} API where
 * {@code vertex}/{@code texture}/{@code color} write into a slot and {@code nextVertex()}
 * advances it. {@link Builder} reproduces that call style by buffering one vertex's worth of
 * attributes and re-emitting them to {@link Tesselator} in 1.7.10's canonical
 * {@code color} → {@code texture} → {@code vertex} order. The {@link VertexFormat} constants
 * record which attributes each batch carries, so a batch only flushes what it declared.
 */
public final class GuiVertexCompat {

    private GuiVertexCompat() {
    }

    /** position(3f) only, matching later versions' {@code POSITION}. */
    public static final VertexFormat POSITION = new VertexFormat(false, false);

    /** position(3f) + colour(4ub), matching later versions' {@code POSITION_COLOR}. */
    public static final VertexFormat POSITION_COLOR = new VertexFormat(true, false);

    /** position(3f) + uv(2f) + colour(4ub). */
    public static final VertexFormat POSITION_TEX_COLOR = new VertexFormat(true, true);

    /** Stand-in for 1.8's {@code VertexFormat}: just the attributes a batch carries. */
    public static final class VertexFormat {

        private final boolean hasColor;
        private final boolean hasTexture;

        private VertexFormat(boolean hasColor, boolean hasTexture) {
            this.hasColor = hasColor;
            this.hasTexture = hasTexture;
        }
    }

    /** Buffers cloth-config's fluent vertex calls onto the 1.7.10 immediate-mode tesselator. */
    public static final class Builder {

        private final Tesselator tesselator;

        private VertexFormat format = POSITION;
        private double pendingX;
        private double pendingY;
        private double pendingZ;
        private double pendingU;
        private double pendingV;
        private float pendingRed;
        private float pendingGreen;
        private float pendingBlue;
        private float pendingAlpha = 1.0F;
        private boolean hasPendingVertex;

        private Builder(Tesselator tesselator) {
            this.tesselator = tesselator;
        }

        public static Builder of(Tesselator tesselator) {
            return new Builder(tesselator);
        }

        public static Builder getInstance() {
            return of(Tesselator.INSTANCE);
        }

        /** Starts a batch, mirroring the later {@code begin(mode, format)}. */
        public Builder begin(int mode, VertexFormat format) {
            this.format = format;
            this.hasPendingVertex = false;
            this.tesselator.begin(mode);
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
            return this.color(red / 255.0F, green / 255.0F, blue / 255.0F, alpha / 255.0F);
        }

        public Builder color(float red, float green, float blue, float alpha) {
            this.pendingRed = red;
            this.pendingGreen = green;
            this.pendingBlue = blue;
            this.pendingAlpha = alpha;
            return this;
        }

        /** Commits the buffered vertex, matching the later slot-advance behaviour. */
        public Builder nextVertex() {
            this.flushPending();
            return this;
        }

        /** Flushes any trailing vertex, then ends the batch. */
        public void end() {
            this.flushPending();
            this.tesselator.end();
        }

        private void flushPending() {
            if (!this.hasPendingVertex) {
                return;
            }
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