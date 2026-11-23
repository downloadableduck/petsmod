package net.minecraft.client.render;

import net.minecraft.client.render.renderer.DrawMode;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.render.tessellator.TessellatorGeneral;

/**
 * Modern tessellator shape backed by BTA's shader tessellator.
 *
 * <p>Cloth's vertex builder emits colors and UVs before calling {@link #vertex},
 * so this wrapper forwards that state to the BTA tessellator immediately.
 */
public final class Tessellator {
    public static final Tessellator INSTANCE = new Tessellator();

    private final TessellatorGeneral delegate;
    private boolean textureUsed;

    private Tessellator() {
        this.delegate = GLRenderer.getTessellator();
    }

    public void start(int mode) {
        this.textureUsed = false;
        this.delegate.startDrawing(toDrawMode(mode));
    }

    public void color(float red, float green, float blue, float alpha) {
        this.delegate.setColor4f(red, green, blue, alpha);
    }

    public void color(int red, int green, int blue, int alpha) {
        this.color(red / 255.0F, green / 255.0F, blue / 255.0F, alpha / 255.0F);
    }

    public void texture(double u, double v) {
        this.delegate.setTextureUV(u, v);
        this.lastU = u;
        this.lastV = v;
        this.textureUsed = true;
    }

    public void vertex(double x, double y, double z) {
        if (this.textureUsed) {
            this.delegate.addVertexWithUV(x, y, z, this.lastU, this.lastV);
        } else {
            this.delegate.addVertex(x, y, z);
        }
    }

    private double lastU;
    private double lastV;

    public void draw() {
        this.delegate.draw();
        this.textureUsed = false;
    }

    private static DrawMode toDrawMode(int mode) {
        switch (mode) {
            case 0:
                return DrawMode.POINTS;
            case 1:
                return DrawMode.LINES;
            case 2:
                return DrawMode.LINE_LOOP;
            case 3:
                return DrawMode.LINE_STRIP;
            case 4:
                return DrawMode.TRIANGLES;
            case 5:
                return DrawMode.TRIANGLE_STRIP;
            case 6:
                return DrawMode.TRIANGLE_FAN;
            case 7:
            default:
                return DrawMode.QUADS;
        }
    }
}
